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
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación cuando se cierra esta ventana
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        JButton agregarButton = new JButton("+ Agregar contacto");
        agregarButton.setPreferredSize(new Dimension(frame.getWidth(), 40));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarContactoGUI(sqlController);
            }
        });
        frame.add(agregarButton, BorderLayout.NORTH);

        contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(contactPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarListaContactos();
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
    }

    private void actualizarListaContactos() {
        contactPanel.removeAll();
        for (Contact contacto : sqlController.getContacts()) {
            JButton contactoButton = new JButton(contacto.nom + " " + contacto.cognom);
            contactoButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            contactoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ContactoDetalleGUI(contacto, sqlController);
                }
            });
            contactPanel.add(contactoButton);
        }
        contactPanel.revalidate();
        contactPanel.repaint();
    }
    
    public static void main(String[] args) {
        SQLController sqlController = new SQLController("localhost", "root", "password", "mi_base_de_datos");
        new AgendaGUI(sqlController);
    }
    
}
