package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

@SuppressWarnings("unused")
public class DetallContacteGUI {
    private JFrame frame;
    private Contact contacte;
    private SQLController sqlController;
    private AgendaGUI agendaGUI;

    public DetallContacteGUI(Contact contacte, SQLController sqlController, AgendaGUI agendaGUI) {
        this.contacte = contacte;
        this.sqlController = sqlController;
        this.agendaGUI = agendaGUI;

        frame = new JFrame("Detalls del contacte");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));

        JButton editarButton = new JButton("Editar");
        editarButton.setPreferredSize(new Dimension(frame.getWidth() / 2, 40));
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarContacteGUI(contacte, sqlController, agendaGUI);
                frame.dispose();
            }
        });

        JButton esborrarButton = new JButton("Esborrar");
        esborrarButton.setPreferredSize(new Dimension(frame.getWidth() / 2, 40));
        esborrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame, "Estàs segur que vols eliminar aquest contacte?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    sqlController.deleteContact(contacte.id);
                    JOptionPane.showMessageDialog(frame, "Contacte eliminat amb èxit!");
                    frame.dispose();
                    agendaGUI.actualitzarLlistaContactes();
                }
            }
        });

        topPanel.add(editarButton);
        topPanel.add(esborrarButton);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(contacte.nom), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Cognom:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(contacte.cognom), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Telefon:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(String.valueOf(contacte.telefon)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsPanel.add(new JLabel("Direcció:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(contacte.direccio), gbc);

        frame.add(detailsPanel, BorderLayout.CENTER);

        // Icona d'APP
        String fileSeparator = System.getProperty("file.separator");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(".."+fileSeparator+"media"+fileSeparator+"conn.png"));
        frame.setIconImage(icon);
        // ------------------------------

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
