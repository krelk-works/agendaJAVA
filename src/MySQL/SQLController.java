package MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Contacts.*;

public class SQLController {
    // -------------------------------------
    Boolean debug = true;

    // Variables
    private String host;
    private String user;
    private String password;
    private String bd;

    // El constructor
    public SQLController(String host, String user, String password, String bd) throws SQLException {
        this.host = host;
        this.user = user;
        this.password = password;
        this.bd = bd;

        // Verificar la conexió y la existencia de la base de dades
        verifyDatabaseConnection();
    }

    public List<Contact> getContacts() {
        List<Contact> llistaContactes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agenda ORDER BY nom, cognom ASC");

            while (rs.next()) {
                Contact contactToADD = new Contact(rs.getInt("id"), rs.getString("nom"), rs.getString("cognom"), rs.getLong("telefon"), rs.getString("direccio"));
                llistaContactes.add(contactToADD);
            }
        } catch (SQLException e) {
            System.err.println("Error amb la conexió a la base de dades: " + e.getMessage());
        }
        return llistaContactes;
    }

    public void addContact(Contact contact) {
        try (Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            if (!validateContact(contact)) {
                System.out.println("El contacte no és valid");
                return;
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO agenda (nom, cognom, telefon, direccio) VALUES ('" + contact.nom + "', '" + contact.cognom + "', " + contact.telefon + ", '" + contact.direccio + "')");
        } catch (SQLException e) {
            System.err.println("Error amb la conexió a la base de dades: " + e.getMessage());
        }
    }

    public void deleteContact(int id) {
        try (Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM agenda WHERE id = " + id);
        } catch (SQLException e) {
            System.err.println("Error amb la conexió a la base de dades: " + e.getMessage());
        }
    }

    public void updateContact(Contact contact) {
        try (Connection conn = DriverManager.getConnection(getSQLURL(), user, password)) {
            if (!validateContact(contact)) {
                System.out.println("El contacte no és valid");
                return;
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE agenda SET nom = '" + contact.nom + "', cognom = '" + contact.cognom + "', telefon = " + contact.telefon + ", direccio = '" + contact.direccio + "' WHERE id = " + contact.id);
        } catch (SQLException e) {
            System.err.println("Error amb la conexió a la base de dades: " + e.getMessage());
        }
    }

    private String getSQLURL() {
        return "jdbc:mysql://" + host + ":3306/" + bd;
    }

    private boolean validateContact(Contact contact) {
        if (!contact.nom.matches("[a-zA-ZÀ-ÿñÑ-]+")) {
            return false;
        }

        if (contact.nom.length() > 50) {
            return false;
        }

        if (!contact.cognom.matches("^[a-zA-ZÀ-ÿñÑ]+( [a-zA-ZÀ-ÿñÑ]+)?$")) {
            return false;
        }
        if (contact.cognom.length() > 100) {
            return false;
        }

        if (contact.direccio.length() > 150) {
            return false;
        }

        if (!contact.direccio.matches("[0-9a-zA-ZÀ-ÿñÑ/ºª, -]+")) {
            return false;
        }

        if (String.valueOf(contact.telefon).length() < 9 || String.valueOf(contact.telefon).length() > 15) {
            return false;
        }

        return true;
    }

    private void verifyDatabaseConnection() throws SQLException {
        try (Connection conn = DriverManager.getConnection(getSQLURL(), user, password);
            Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT 1 FROM agenda LIMIT 1");
        } catch (SQLException e) {
            throw e;
        }
    }
}
