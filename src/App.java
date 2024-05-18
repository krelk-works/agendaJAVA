import GUI.*;
import MySQL.*;

public class App {
    public static void main(String[] args) throws Exception {
        SQLController controlSQL = new SQLController("localhost", "root", "", "agendaJAVA");

        /*List<Contact> llistaContactes = controlSQL.getContacts();

        for (Contact contact : llistaContactes) {
            System.out.println(contact.nom+" "+contact.cognom+" "+contact.telefon+" "+contact.direccio);
        }

        Contact nouContacte = new Contact("Reinaldo", "Gutiérrez", 645231654L, "C/ Marina 254 1º 1º");

        controlSQL.addContact(nouContacte);*/

        //Finestra finestra = new Finestra();
        //finestra.mainMenu();

        /*for (int i = 1; i < 20; i++) {
            Contact nouContacte = new Contact("Test"+i, "Test"+i, 645231654L, "C/ Marina 254 1º 1º");

            controlSQL.addContact(nouContacte);
        }*/
        
        new AgendaGUI(controlSQL);
    }
}
