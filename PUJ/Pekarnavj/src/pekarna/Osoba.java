/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pekarna;

/**
 *
 * @author A&A
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Osoba {
    private int ID;
    private String Ime;
    private String Prezime;
    private int JMBG;
    private String Adresa;
    private int Telefon;
    private String Korisnicko_ime;
    private String Lozinka;

    public Osoba(int ID, String Ime, String Prezime, int JMBG, String Adresa, int Telefon, String Korisnicko_ime, String Lozinka) {
        this.ID = ID;
        this.Ime = Ime;
        this.Prezime = Prezime;
        this.JMBG = JMBG;
        this.Adresa = Adresa;
        this.Telefon = Telefon;
        this.Korisnicko_ime = Korisnicko_ime;
        this.Lozinka = Lozinka;
    }

    public int getID() {
        return ID;
    }

    public String getIme() {
        return Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public int getJMBG() {
        return JMBG;
    }

    public String getAdresa() {
        return Adresa;
    }

    public int getTelefon() {
        return Telefon;
    }

    public String getKorisnicko_ime() {
        return Korisnicko_ime;
    }

    public String getLozinka() {
        return Lozinka;
    }
    

    
 public static ObservableList<Osoba> listaProdavaca () {
        ObservableList<Osoba> lista1 = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM osoba");
        
        try {
            while (rs.next()) {
                lista1.add(new Osoba(rs.getInt("ID"), rs.getString("Ime"), rs.getString("Prezime"), rs.getInt("JMBG"),  rs.getString("Adresa"),  rs.getInt("Telefon"),  rs.getString("Korisnicko_ime"),  rs.getString("Lozinka")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista1;
    }
}

