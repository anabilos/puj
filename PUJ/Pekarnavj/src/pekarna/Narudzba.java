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
public class Narudzba {
     private int ID;
     private String Naziv;
     private int Kolicina;
     private float Cijena;
     private float Iznos;

    public Narudzba(int ID, String Naziv, float Cijena, int Kolicina, float Iznos) {
        this.ID = ID;
        this.Naziv = Naziv;
        this.Cijena = Cijena;
        this.Kolicina = Kolicina;
        this.Iznos = Iznos;
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

    public float getIznos() {
        return Iznos;
    }

    
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIme(String Naziv) {
        this.Naziv = Naziv;
    }

    public void setCijena(float Cijena) {
        this.Cijena = Cijena;
    }

    public void setKolicina(int Kolicina) {
        this.Kolicina = Kolicina;
    }

    public void setIznos(float Iznos) {
        this.Iznos = Iznos;
    }

    public String toString() { 
        return  this.ID  +"\t\t\t" +this.Naziv +"\t\t\t"+  this.Cijena +"\t\t\t "+ this.Kolicina +"\t\t "+ this.Iznos ;
} 
    
}

