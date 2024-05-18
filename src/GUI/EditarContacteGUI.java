package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

@SuppressWarnings("unused")
public class EditarContacteGUI {
    private JFrame frame;
    private JTextField nomField;
    private JTextField cognomField;
    private JTextField telefonField;
    private JTextField direccioField;
    private Contact contacte;
    private SQLController sqlController;

    public EditarContacteGUI(Contact contacte, SQLController sqlController, AgendaGUI agendaGUI) {
        this.contacte = contacte;
        this.sqlController = sqlController;

        frame = new JFrame("Editar Contacte");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Dimension textFieldSize = new Dimension(200, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nom:"), gbc);
        
        gbc.gridx = 1;
        nomField = new JTextField(contacte.nom);
        nomField.setPreferredSize(textFieldSize);
        formPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Cognom:"), gbc);

        gbc.gridx = 1;
        cognomField = new JTextField(contacte.cognom);
        cognomField.setPreferredSize(textFieldSize);
        formPanel.add(cognomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Telefon:"), gbc);
        
        gbc.gridx = 1;
        telefonField = new JTextField(String.valueOf(contacte.telefon));
        telefonField.setPreferredSize(textFieldSize);
        formPanel.add(telefonField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Direcció:"), gbc);

        gbc.gridx = 1;
        direccioField = new JTextField(contacte.direccio);
        direccioField.setPreferredSize(textFieldSize);
        formPanel.add(direccioField, gbc);

        JButton guardarButton = new JButton("Desar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText().trim();
                String cognom = cognomField.getText().trim();
                String telefonStr = telefonField.getText().trim();
                String direccio = direccioField.getText().trim();

                if (!nom.matches("[a-zA-ZÀ-ÿñÑ-]+")) {
                    JOptionPane.showMessageDialog(frame, "El nom no pot tenir caracters espacials i els noms compostos van junts amb un '-'.");
                    return;
                }
                if (nom.length() > 50) {
                    JOptionPane.showMessageDialog(frame, "El nom no pot tenir més de 50 caràcters.");
                    return;
                }

                if (!cognom.matches("^[a-zA-ZÀ-ÿñÑ]+( [a-zA-ZÀ-ÿñÑ]+)?$")) {
                    JOptionPane.showMessageDialog(frame, "El cognom no ha de contenir caràcters especials ni tampoc pot tenir més de 2 cognoms.");
                    return;
                }
                if (cognom.length() > 100) {
                    JOptionPane.showMessageDialog(frame, "El cognom no ha de superar els 100 caràcters.");
                    return;
                }

                if (direccio.length() > 150) {
                    JOptionPane.showMessageDialog(frame, "La direcció no ha de superar els 150 caràcters.");
                    return;
                }

                if (!direccio.matches("[0-9a-zA-ZÀ-ÿñÑ/ºª, -]+")) {
                    JOptionPane.showMessageDialog(frame, "La direcció no pot contenir caracters que no siguin '/', ',' 'º', 'ª' o '-'.");
                    return;
                }               

                long telefon;
                try {
                    telefon = Long.parseLong(telefonStr);
                    if (telefonStr.length() < 9 || telefonStr.length() > 15) {
                        throw new NumberFormatException("El telèfon ha de tenir entre 9 i 15 dígits.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "El telèfon ha de ser un número vàlid entre 9 i 15 dígits.");
                    return;
                }

                Contact contactoActualizado = new Contact(contacte.id, nom, cognom, telefon, direccio);
                sqlController.updateContact(contactoActualizado);

                JOptionPane.showMessageDialog(frame, "Contacte actualitzat correctament!");

                agendaGUI.actualitzarLlistaContactes();

                frame.dispose();

                new DetallContacteGUI(contactoActualizado, sqlController, agendaGUI);
            }
        });

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(guardarButton, BorderLayout.SOUTH);

        // Icona d'APP
        String fileSeparator = System.getProperty("file.separator");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(".."+fileSeparator+"media"+fileSeparator+"editcon.png"));
        frame.setIconImage(icon);
        // ------------------------------

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
