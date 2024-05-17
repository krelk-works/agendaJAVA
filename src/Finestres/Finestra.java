package Finestres;

import java.util.List;

import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import Contacts.*;
import MySQL.*;

public class Finestra extends javax.swing.JFrame {
    public JFrame object;

    public void setWindow(int x,int y, String title) {
        this.object = new JFrame(title);
        object.setSize(y, x);
        object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Icona de l'aplicació
        try {
            String sep = System.getProperty("file.separator");
            object.setIconImage(ImageIO.read(new File("src"+sep+"media"+sep+"appicon.png")));
        } catch (Exception e) {
            System.out.println("No s'ha pogut carregar la icona de l'aplicació");
        }
        
        /* Alineament del titol al centre */
        object.setFont(new Font("System", Font.PLAIN, 14));
        Font f = object.getFont();
        FontMetrics fm = object.getFontMetrics(f);
        int x_temp = fm.stringWidth(title);
        int y_temp = fm.stringWidth(" ");
        int z = object.getWidth()/2 - (x_temp/2);
        int w = z/y_temp;
        String pad ="";
        pad = String.format("%"+w+"s", pad);
        object.setTitle(pad+title);
        // -------------------------------------
    }

    public void mainMenu() {
        setWindow(400, 400, "Agenda");

        object.setLayout(new GridLayout(10, 10, 0, 0));

        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setBackground(Color.lightGray);
        addButtonPanel.setLayout(new GridLayout(1, 10, 5, 5));

        JButton addButton = new JButton("+ Afegir contacte");
        addButton.addActionListener(e -> {
            System.out.println("Afegir contacte");
        });
        addButtonPanel.add(addButton);
        object.add(addButtonPanel);
        object.setLocationRelativeTo(null); // Centrar el marco en la pantalla
        object.setResizable(false);

        SQLController controlSQL = new SQLController("localhost", "root", "", "agendaJAVA");

        List<Contact> llistaContactes = controlSQL.getContacts();



        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(Color.lightGray);
        contactPanel.setLayout(new GridLayout(9, 1, 0, 0));
        
        for (Contact contact : llistaContactes) {
            //System.out.println(contact.nom+" "+contact.cognom+" "+contact.telefon+" "+contact.direccio);
            JButton contactButton = new JButton(contact.nom);
            contactPanel.add(contactButton);

            contactButton.addActionListener(e -> {
                System.out.println("Contacte: "+contact.nom+" "+contact.cognom+" "+contact.telefon+" "+contact.direccio);
            });
        }

        JScrollPane scrollPane = new JScrollPane(contactPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        
        object.add(contactPanel);





        showWindow();
    }

    private void showWindow() {
        object.setVisible(true);
    }

    public void closeWindow() {
        object.dispose();
    }
}
