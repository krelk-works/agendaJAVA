import java.sql.SQLException;
import GUI.*;
import MySQL.*;

public class App {
    public static void main(String[] args) {
        try {
            // Afegir aqui les dades de la teva base de dades

            SQLController controlSQL = new SQLController("localhost", "root", "", "agendaJAVA");

            // --------------------------------------------------------------------------------------------------------
            
            // Si tot va bé, s'obre la finestra de l'agenda
            new AgendaGUI(controlSQL);
        } catch (SQLException e) {
            System.err.println("Error amb la conexió a la base de dades: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("S'ha produït un error inesperat': " + e.getMessage());
        }
    }
}
