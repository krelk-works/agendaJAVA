package MySQL;

import Contact.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLController {
    // Variables
    private String host;
    private String user;
    private String password;
    private String bd;

    // El señor contructor
    public SQLController(String host, String user, String password, String bd) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.bd = bd;
    }
    
    public List<Contact> getContacts() {
        // Definim una nova llista de objectes del tipus "Contact" -> Contactes
        List<Contact> llistaContactes = new ArrayList<Contact>();

        // Mitjançant un try provem la connexió i retornarem una llista
        try(Connection conn = DriverManager.getConnection(getSQL(), user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda");
            // -------------------------------------
            while(rs.next()) {
                Contact contactToADD = new Contact(rs.getString("nom"), rs.getString("cognom"), rs.getInt("telefon"), rs.getString("direccio"));
                llistaContactes.add(contactToADD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llistaContactes;
    }

    public void addContact(Contact contact) {
        System.out.println("Agregando el contacto con nombre :"+contact.nom);
        // Mitjançant un try provem la connexió i retornarem una llista
        try(Connection conn = DriverManager.getConnection(getSQL(), user, password)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO agenda (nom, cognom, telefon, direccio) VALUE ('"+contact.nom+"', '"+contact.cognom+"', "+contact.telefon+", '"+contact.direccio+"')");
            if (stmt.getUpdateCount() > 1) {
                System.out.println("S'ha afegit correctament el contacte amb nom "+contact.nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getSQL() {
        return "jdbc:mysql://"+host+":3306/"+bd;
    }

    private checkSpecialChars


    
}