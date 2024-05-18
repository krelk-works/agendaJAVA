package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contacts.*;
import MySQL.*;

@SuppressWarnings("unused")
public class AfegirContacteGUI {
    private JFrame frame;
    private JTextField nomField;
    private JTextField cognomField;
    private JTextField telefonField;
    private JTextField direccioField;
    private SQLController sqlController;

    public AfegirContacteGUI(SQLController sqlController, AgendaGUI agendaGUI) {
        this.sqlController = sqlController;

        frame = new JFrame("Afegir contacte");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        formPanel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        formPanel.add(nomField);

        formPanel.add(new JLabel("Cognom:"));
        cognomField = new JTextField();
        formPanel.add(cognomField);

        formPanel.add(new JLabel("Telefon:"));
        telefonField = new JTextField();
        formPanel.add(telefonField);

        formPanel.add(new JLabel("Direcció:"));
        direccioField = new JTextField();
        formPanel.add(direccioField);

        JButton afegirButton = new JButton("Afegir contacte");
        afegirButton.addActionListener(new ActionListener() {
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

                Contact nouContacte = new Contact(nom, cognom, telefon, direccio);
                sqlController.addContact(nouContacte);

                JOptionPane.showMessageDialog(frame, "Contacte afegit correctament!");
                agendaGUI.actualitzarLlistaContactes();

                frame.dispose();
            }
        });

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(afegirButton, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);

        // Icona d'APP
        String fileSeparator = System.getProperty("file.separator");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(".."+fileSeparator+"media"+fileSeparator+"newcon.png"));
        frame.setIconImage(icon);
        // ------------------------------

        frame.setVisible(true);
    }
}
