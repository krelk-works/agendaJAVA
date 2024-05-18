package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

public class EditarContactoGUI {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField telefonoField;
    private JTextField direccionField;
    private Contact contacto;
    private SQLController sqlController;

    public EditarContactoGUI(Contact contacto, SQLController sqlController) {
        this.contacto = contacto;
        this.sqlController = sqlController;

        frame = new JFrame("Editar Contacto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Dimension textFieldSize = new Dimension(200, 30);  // Aumenta el tamaño preferido de los campos de texto

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nombreField = new JTextField(contacto.nom);
        nombreField.setPreferredSize(textFieldSize);
        formPanel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        apellidoField = new JTextField(contacto.cognom);
        apellidoField.setPreferredSize(textFieldSize);
        formPanel.add(apellidoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        telefonoField = new JTextField(String.valueOf(contacto.telefon));
        telefonoField.setPreferredSize(textFieldSize);
        formPanel.add(telefonoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        direccionField = new JTextField(contacto.direccio);
        direccionField.setPreferredSize(textFieldSize);
        formPanel.add(direccionField, gbc);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                long telefono;
                try {
                    telefono = Long.parseLong(telefonoField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "El teléfono debe ser un número válido.");
                    return;
                }
                String direccion = direccionField.getText();

                Contact contactoActualizado = new Contact(contacto.id, nombre, apellido, telefono, direccion);
                sqlController.updateContact(contactoActualizado);

                JOptionPane.showMessageDialog(frame, "Contacto actualizado con éxito!");

                // Cierra la ventana después de actualizar el contacto
                frame.dispose();

                // Muestra nuevamente los detalles del contacto actualizado
                new ContactoDetalleGUI(contactoActualizado, sqlController);
            }
        });

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(guardarButton, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /* 
    public static void main(String[] args) {
        SQLController sqlController = new SQLController("localhost", "root", "password", "mi_base_de_datos");
        Contact contacto = new Contact(1, "Juan", "Pérez", 1234567890L, "Calle Falsa 123");
        new EditarContactoGUI(contacto, sqlController);
    }
    */
}
