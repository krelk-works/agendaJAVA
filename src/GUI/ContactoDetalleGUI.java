package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

public class ContactoDetalleGUI {
    private JFrame frame;
    private Contact contacto;
    private SQLController sqlController;

    public ContactoDetalleGUI(Contact contacto, SQLController sqlController) {
        this.contacto = contacto;
        this.sqlController = sqlController;

        frame = new JFrame("Detalles del Contacto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JButton editarButton = new JButton("Editar");
        editarButton.setPreferredSize(new Dimension(frame.getWidth(), 40));
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarContactoGUI(contacto, sqlController);
                frame.dispose();
            }
        });
        frame.add(editarButton, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Añadir etiquetas y valores al panel de detalles
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(contacto.nom), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(contacto.cognom), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(String.valueOf(contacto.telefon)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsPanel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        detailsPanel.add(new JLabel(contacto.direccio), gbc);

        frame.add(detailsPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
    /* 
    public static void main(String[] args) {
        SQLController sqlController = new SQLController("localhost", "root", "password", "mi_base_de_datos");
        Contact contacto = new Contact(1, "Juan", "Pérez", 1234567890L, "Calle Falsa 123");
        new ContactoDetalleGUI(contacto, sqlController);
    }
    */
}
