import java.sql.SQLException;
import GUI.*;
import MySQL.*;

public class App {
    public static void main(String[] args) {
        try {
            SQLController controlSQL = new SQLController("localhost", "root", "", "agendaJAVA");
            new AgendaGUI(controlSQL);
        } catch (SQLException e) {
            System.err.println("Error amb la conexió a la base de dades: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("S'ha produït un error inesperat': " + e.getMessage());
        }
    }
}
