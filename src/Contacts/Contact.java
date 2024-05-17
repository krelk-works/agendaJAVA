package Contacts;

public class Contact {
    public int id = 0;
    public String nom;
    public String cognom;
    public long telefon;
    public String direccio;

    // Primer constructor sense ID per a poder afegir contactes
    public Contact(String nom,String cognom,long telefon,String direccio) {
        this.nom = nom;
        this.cognom = cognom;
        this.telefon = telefon;
        this.direccio = direccio;
    }

    // Segon contructor per a poder agafar també la ID de la base de dades
    public Contact(int id,String nom,String cognom,long telefon,String direccio) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.telefon = telefon;
        this.direccio = direccio;
    }
}
