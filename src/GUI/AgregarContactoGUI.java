package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

public class AgregarContactoGUI {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField telefonoField;
    private JTextField direccionField;
    private SQLController sqlController;

    public AgregarContactoGUI(SQLController sqlController) {
        this.sqlController = sqlController;

        frame = new JFrame("Agregar Contacto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        formPanel.add(telefonoField);

        formPanel.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        formPanel.add(direccionField);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
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

                Contact nuevoContacto = new Contact(nombre, apellido, telefono, direccion);
                sqlController.addContact(nuevoContacto);

                JOptionPane.showMessageDialog(frame, "Contacto agregado con éxito!");

                // Cierra la ventana después de agregar el contacto
                frame.dispose();
            }
        });

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(agregarButton, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
    /* 
    public static void main(String[] args) {
        SQLController sqlController = new SQLController("localhost", "root", "password", "mi_base_de_datos");
        new AgregarContactoGUI(sqlController);
    }
    */
}
