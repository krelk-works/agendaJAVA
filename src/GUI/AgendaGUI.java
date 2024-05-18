package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

public class AgendaGUI {
    private JFrame frame;
    private JPanel contactPanel;
    private JScrollPane scrollPane;
    private SQLController sqlController;

    public AgendaGUI(SQLController sqlController) {
        this.sqlController = sqlController;

        frame = new JFrame("Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        JButton agregarButton = new JButton("+ Afegir contacte");
        agregarButton.setPreferredSize(new Dimension(frame.getWidth(), 40));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AfegirContacteGUI(sqlController, AgendaGUI.this);
            }
        });
        frame.add(agregarButton, BorderLayout.NORTH);

        contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(contactPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualitzarLlistaContactes();

        // Icona d'APP
        String fileSeparator = System.getProperty("file.separator");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(".."+fileSeparator+"media"+fileSeparator+"appicon.png"));
        frame.setIconImage(icon);
        // ------------------------------

        frame.setVisible(true);
    }

    public void actualitzarLlistaContactes() {
        contactPanel.removeAll();
        for (Contact contacte : sqlController.getContacts()) {
            JButton contacteButton = new JButton(contacte.nom + " " + contacte.cognom);
            contacteButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            contacteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DetallContacteGUI(contacte, sqlController, AgendaGUI.this);
                }
            });
            contactPanel.add(contacteButton);
        }
        contactPanel.revalidate();
        contactPanel.repaint();
    }
}
