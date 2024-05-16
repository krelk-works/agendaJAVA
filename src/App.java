import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Contact.*;
import MySQL.*;

public class App {
    

    public static void main(String[] args) throws Exception {
        SQLController controlSQL = new SQLController("localhost", "root", "", "agendaJAVA");

        List<Contact> llistaContactes = controlSQL.getContacts();

        for (Contact contact : llistaContactes) {
            System.out.println(contact.nom+" "+contact.cognom+" "+contact.telefon+" "+contact.direccio);
        }

        Contact nouContacte = new Contact("Agusti", "Perez", 685564412, "C/ Marina 254");

        //controlSQL.addContact(nouContacte);
    }
}
