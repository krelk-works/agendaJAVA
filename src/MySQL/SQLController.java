package MySQL;

import Contact.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLController {
    // -------------------------------------
    Boolean debug = true;

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
        try(Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda");
            // -------------------------------------
            while(rs.next()) {
                Contact contactToADD = new Contact(rs.getInt("id"),rs.getString("nom"), rs.getString("cognom"), rs.getLong("telefon"), rs.getString("direccio"));
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
        try(Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            if (!validateContact(contact)) {
                System.out.println("El contacte no és valid");
                return;
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO agenda (nom, cognom, telefon, direccio) VALUE ('"+contact.nom+"', '"+contact.cognom+"', "+contact.telefon+", '"+contact.direccio+"')");
            if (debug) {
                if (stmt.getUpdateCount() > 1) {
                    System.out.println("[ROW AFFECTED: "+stmt.getUpdateCount()+"] S'ha afegit correctament el contacte amb nom: "+contact.nom);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        // Mitjançant un try provem la connexió i esborrarem el contacte amb la ID
        try(Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM agenda WHERE id = "+id);
            if (debug) {
                if (stmt.getUpdateCount() > 1) {
                    System.out.println("[ROW AFFECTED: "+stmt.getUpdateCount()+"] S'ha eliminat correctament el contacte amb ID: "+id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact) {
        // Mitjançant un try provem la connexió i actualitzarem el contacte amb la ID
        try(Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            if (!validateContact(contact)) {
                System.out.println("El contacte no és valid");
                return;
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE agenda SET nom = '"+contact.nom+"', cognom = '"+contact.cognom+"', telefon = "+contact.telefon+", direccio = '"+contact.direccio+"' WHERE id = "+contact.id);
            if (debug) {
                if (stmt.getUpdateCount() > 1) {
                    System.out.println("[ROW AFFECTED: "+stmt.getUpdateCount()+"] S'ha actualitzat correctament el contacte amb ID: "+contact.id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getSQLURL() {
        return "jdbc:mysql://"+host+":3306/"+bd;
    }

    // Métode privat per a validar els contactes
    private boolean validateContact(Contact contact) {
        // Revisem que no tinguem caràcters especials als textos (Strings). Ens assegurem que ni tingui espais en blanc ni al principi ni al final del text i que no superi la longitud màxima
        if (contact.nom.trim().matches("[a-zA-Z0-9]+") && contact.nom.length() <= 50
        || contact.cognom.trim().matches("[a-zA-Z0-9]+") && contact.cognom.length() <= 100
        || contact.direccio.trim().matches("[a-zA-Z0-9/,ºª]+") && contact.direccio.length() <= 150) {
            // La L final de la numeració es per a fer referencia al tipus d'enter LONG (Revisió de la longitud del número de telèfon)
            if (contact.telefon > 99999L && contact.telefon < 100000000000000L) {
                return true;
            } else {
                if (debug) {
                    System.out.println("El número de telèfon no és vàlid");
                }
                return false;
            }
        } else {
            if (debug) {
                System.out.println("El nom, cognom o direcció no és vàlid");
            }
            return false;
        }
    }
    
}