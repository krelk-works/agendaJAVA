import java.util.List;

import Contacts.*;
import MySQL.*;
import Finestres.*;

public class App {
    public static void main(String[] args) throws Exception {
        SQLController controlSQL = new SQLController("localhost", "root", "", "agendaJAVA");

        /*List<Contact> llistaContactes = controlSQL.getContacts();

        for (Contact contact : llistaContactes) {
            System.out.println(contact.nom+" "+contact.cognom+" "+contact.telefon+" "+contact.direccio);
        }

        Contact nouContacte = new Contact("Reinaldo", "Gutiérrez", 645231654L, "C/ Marina 254 1º 1º");

        controlSQL.addContact(nouContacte);*/

        Finestra finestra = new Finestra();
        finestra.mainMenu();


        
    }
}
