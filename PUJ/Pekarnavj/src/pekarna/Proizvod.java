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

/**
 *
 * 
 */
public class Proizvod {
    private int ID;
    private String Naziv;
    private float Cijena;
    private int Kolicina;

    public Proizvod(int ID, String Naziv, float Cijena, int Kolicina) {
        this.ID = ID;
        this.Naziv = Naziv;
        this.Cijena = Cijena;
        this.Kolicina = Kolicina;
        
    
    }

    public Proizvod(int ID, String Naziv, float Cijena) {
        this.ID = ID;
        this.Naziv = Naziv;
        this.Cijena = Cijena;
    }
    

    public int getID() {
        return ID;
    }

    public String getNaziv() {
        return Naziv;
    }

    public float getCijena() {
        return Cijena;
    }

    public int getKolicina() {
        return Kolicina;
    } 
     public static ObservableList<Proizvod> listaPita () {
        ObservableList<Proizvod> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM proizvod where ID_Vrsta_Proizvoda=1");
        
        try {
            while (rs.next()) {
                lista.add(new Proizvod(rs.getInt("ID"), rs.getString("Naziv"), rs.getFloat("Cijena"), rs.getInt("Kolicina")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }

   public static ObservableList<Proizvod> listaPeciva () {
        ObservableList<Proizvod> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM proizvod where ID_Vrsta_Proizvoda=2");
        
        try {
            while (rs.next()) {
                lista.add(new Proizvod(rs.getInt("ID"), rs.getString("Naziv"), rs.getFloat("Cijena"), rs.getInt("Kolicina")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
    
    public static ObservableList<Proizvod> listaPizza () {
        ObservableList<Proizvod> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM proizvod where ID_Vrsta_Proizvoda=3");
        
        try {
            while (rs.next()) {
                lista.add(new Proizvod(rs.getInt("ID"), rs.getString("Naziv"), rs.getFloat("Cijena"), rs.getInt("Kolicina")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
 
}


    


