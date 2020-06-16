/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pekarna.controller;

import java.awt.Font;
import pekarna.Baza;
import java.util.Date;
import pekarna.Proizvod;
import pekarna.Narudzba;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class LogiraniProdavacController implements Initializable {

    ObservableList<Proizvod> data = FXCollections.observableArrayList();
    Baza db = new Baza();
    
    @FXML
    private TextField jIdPP;
    @FXML
    private TextField jNazivPP;
    @FXML
    private TextField jCijenaPP;
    @FXML
    private TextField jKolicinaPP;
    @FXML
    private TextField jUkupno;
    @FXML
    private Button narudzba;
    @FXML
    private Button ispis;
    
    @FXML
    private Label labela1;
    
    
    @FXML
    private ListView <Narudzba> lista;
    @FXML
    private TextField ime_operater;
    
    
    List <Narudzba> naruci= new ArrayList<Narudzba>();
    float ukupno=0;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        NaruciPrekoNaziva();
        NaruciPrekoID();
        NaruciPrekoKolicine();
        Delete();
        
        
       
    }
  
    public void NaruciPrekoNaziva(){
            
        jNazivPP.setOnKeyPressed(e -> {
                        int ID;
                        int Kolicina;
                        String Naziv;
                        float Iznos;          
            if(e.getCode() == KeyCode.ENTER){
                        
                if (!jIdPP.getText().isEmpty() && (jNazivPP.getText().isEmpty())) {
                        
                        
                        ID = Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE ID='"+jIdPP.getText()+"'");
                           
                            try {
                                while (rs.next()) {   
                                    
                                    Naziv = rs.getString(2);
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                    
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                    
                                    
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jIdPP.clear();
                    } else if(jIdPP.getText().isEmpty() && (!jNazivPP.getText().isEmpty())) {
                        //kada je naziv pun
                        //kada je sifra puna
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {   
                                    
                                   ID = Integer.parseInt(rs.getString(1));
                                   float Cijena = Float.parseFloat(rs.getString(3));
                                   Iznos = Cijena * Kolicina;
                                   
                                   Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                   lista.getItems().addAll(narudzba);
                                   naruci.add(narudzba);
                                   ukupno += Iznos;
                                   jUkupno.setText(Float.toString(ukupno));
                                   
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();

                    
                }else{
                        ID= Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"' and ID='"+jIdPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {
                                    
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                    
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                   
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();
                            jIdPP.clear();
                }
                
            } 
        });
        
    }
  
   
    public void NaruciPrekoID(){
       
        jIdPP.setOnKeyPressed(e -> {
                        int ID;
                        int Kolicina;
                        String Naziv;
                        float Iznos;
                        
            if(e.getCode() == KeyCode.ENTER){
                        
                        
                if (!jIdPP.getText().isEmpty() && (jNazivPP.getText().isEmpty())) {
                        
                        
                        ID = Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE ID='"+jIdPP.getText()+"'");
                           
                            try {
                                while (rs.next()) {   
                                    
                                    Naziv = rs.getString(2);
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                   
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                           
                                    
                                    
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jIdPP.clear();
                            
                          
                
                    } else if(jIdPP.getText().isEmpty() && (!jNazivPP.getText().isEmpty())) {
                        //kada je naziv pun
                        //kada je sifra puna
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {   
                                    
                                   ID = Integer.parseInt(rs.getString(1));
                                   float Cijena = Float.parseFloat(rs.getString(3));
                                   Iznos = Cijena * Kolicina;
                                   
                                   Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                   lista.getItems().addAll(narudzba);
                                   
                                   naruci.add(narudzba);
                                   ukupno += Iznos;
                                   jUkupno.setText(Float.toString(ukupno));
                                   
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();

                    
                }else{
                        ID= Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"' and ID='"+jIdPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {
                                    
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                    
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                   
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();
                            jIdPP.clear();
                }
                
            } 
        });
        
    }
     
    
   public void NaruciPrekoKolicine(){
        
        jKolicinaPP.setOnKeyPressed(e -> {
                        int ID;
                        int Kolicina;
                        String Naziv;
                        float Iznos;
            if(e.getCode() == KeyCode.ENTER){
                        
                if (!jIdPP.getText().isEmpty() && (jNazivPP.getText().isEmpty())) {
                        
                        
                        ID = Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE ID='"+jIdPP.getText()+"'");
                           
                            try {
                                while (rs.next()) {   
                                    
                                    Naziv = rs.getString(2);
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                   
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                    
                                    
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jIdPP.clear();
                    } else if(jIdPP.getText().isEmpty() && (!jNazivPP.getText().isEmpty())) {
                        //kada je naziv pun
                        //kada je sifra puna
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {   
                                    
                                   ID = Integer.parseInt(rs.getString(1));
                                   float Cijena = Float.parseFloat(rs.getString(3));
                                   Iznos = Cijena * Kolicina;
                                   
                                   Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                   lista.getItems().addAll(narudzba);
                                   
                                   naruci.add(narudzba);
                                   ukupno += Iznos;
                                   jUkupno.setText(Float.toString(ukupno));
                                   
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();

                    
                }else{
                        ID= Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"' and ID='"+jIdPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {
                                    
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                    
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                   
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();
                            jIdPP.clear();
                }
                
            } 
        });
        
    }
   
    
   public void NaruciEnter() {

       
            try {
                
            int ID;
            int Kolicina;
            String Naziv;
            float Iznos;
                
                
                //if osiguranje da je barem jedno razlicito od praznoga
                if (!jIdPP.getText().isEmpty() && (jNazivPP.getText().isEmpty())) {
                        //kada je sifra puna
                        
                        ID = Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                       try{
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE ID='"+jIdPP.getText()+"'"); 
                             while (rs.next())
                                {

                                    Naziv = rs.getString(2);
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                   
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                    
                                 }
                        
                }catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jIdPP.clear();
                    }
        else if(jIdPP.getText().isEmpty() && (!jNazivPP.getText().isEmpty())) {
                        //kada je naziv pun
                        //kada je sifra puna
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {      
                                    
                                    ID = Integer.parseInt(rs.getString(1));
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                    
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                    
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();

                    
                }else{
                        ID= Integer.parseInt(jIdPP.getText().toString());
                        Kolicina = Integer.parseInt(jKolicinaPP.getText().toString());
                        Naziv = jNazivPP.getText().toString();
                        
                        ResultSet rs;
                        Baza DB = new Baza();
                        
                            rs = DB.select("SELECT * FROM proizvod WHERE Naziv='"+jNazivPP.getText()+"' and ID='"+jIdPP.getText()+"'");
                            
                            try {
                                while (rs.next()) {                            
                                    
                                    float Cijena = Float.parseFloat(rs.getString(3));
                                    Iznos = Cijena * Kolicina;
                                    
                                    Narudzba narudzba = new Narudzba(ID, Naziv, Cijena, Kolicina, Iznos);
                                    lista.getItems().addAll(narudzba);
                                    
                                    naruci.add(narudzba);
                                    ukupno += Iznos;
                                    jUkupno.setText(Float.toString(ukupno));
                                    
                                    }
                            } catch (SQLException ex) {
                                System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
                            }           
                            
                            jNazivPP.clear();
                            jIdPP.clear();
                }
                    //kada je sve puno svejedno nam je preko cega trazimao
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                jNazivPP.clear();
                jIdPP.clear();
            } 
    }
    
    
    
      private void Delete()
        {
            
            lista.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE)
            {
                
                try
                {
                    
                     final int selectedIdx = lista.getSelectionModel().getSelectedIndex();
                    if (selectedIdx != -1) {
                         Narudzba itemToRemove = lista.getSelectionModel().getSelectedItem();

                      final int newSelectedIdx =
                        (selectedIdx == lista.getItems().size() - 1)
                           ? selectedIdx - 1
                           : selectedIdx;

                     lista.getItems().remove(selectedIdx);
                      lista.getSelectionModel().select(newSelectedIdx);
                      float cijenaObrisanog = itemToRemove.getIznos();
                      ukupno = Float.parseFloat(jUkupno.getText());
                      ukupno -= cijenaObrisanog;
                      jUkupno.setText(Float.toString(ukupno));
                      naruci.remove(selectedIdx);
                    }
                }
                
                   catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        
                    });
        }
    @FXML
    public void Potvrdi(ActionEvent event){
       
        
        String ispis="";
        float ukupni_racun=0.0f;
        for(int k=0; k<naruci.size() ;k++){
            ukupni_racun+=naruci.get(k).getIznos();
        }
        
       
        ResultSet rsss = db.select("SELECT * FROM osoba WHERE korisnicko_ime='"+ime_operater.getText()+"'");
        int id_prijavljenog_prodavaca=0;
        try{
        while (rsss.next()) {    
                id_prijavljenog_prodavaca=Integer.parseInt(rsss.getString(1));
        }
        }catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
        }               
                                    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date theDate = null;
        //String in = date + "/" + month + "/" + year;
        Date date = new Date();
       
      String fecha =  "Tue Sep 13 2016 00:00:00 GMT-0500 (Hora de verano central (México))";
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
      fecha = sdf.format(date);
      
      try{
        String sqll="Insert into racun (Ukupno,datum,id_konobara) values (?, '"+fecha.toString()+"', ?)";
        PreparedStatement pss=db.exec(sqll);
            pss.setFloat(1,ukupni_racun );
            
            pss.setInt(2, id_prijavljenog_prodavaca); 
            pss.execute();
            }
            
     catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
            
        int id_racuna=0;
        ResultSet rsss1 = db.select("SELECT * from racun" );
        
     try{
        while (rsss1.next()) {    
                id_racuna=Integer.parseInt(rsss1.getString(1));
        }
        }
     catch(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        }  
            
        for(int i=0; i<naruci.size() ;i++){
            
            String Naziv = naruci.get(i).getNaziv();
            String Cijena = String.valueOf(naruci.get(i).getCijena());
            String Kolicina = String.valueOf(naruci.get(i).getKolicina());
        try{
            String sql = "INSERT INTO narudzba (Naziv, Cijena, Kolicina,Iznos, id_racuna) VALUES (?, ?, ?,?,?)";
            
            PreparedStatement ps = db.exec(sql);
             
                ps.setString(1, naruci.get(i).getNaziv());
                ps.setFloat(2, naruci.get(i).getCijena());
                ps.setInt(3, naruci.get(i).getKolicina());
                ps.setFloat(4, naruci.get(i).getIznos());
                ps.setInt(5, id_racuna);
                ps.execute();
                
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        ispis += Naziv+ "\t\t\t    "+ Cijena + "\t\t "+ Kolicina+"\n";
        }
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI Light", Font.BOLD, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI Light", Font.PLAIN, 12));
        
        JOptionPane.showMessageDialog(null, "Naručeno!");
        
    }
    
    public void setIme(String username){
        this.ime_operater.setText(username);
    }
    
    
    public void Prijava(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("pekarna/view/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prijava!");
            stage.setScene(new Scene(root, 550, 3700));
            stage.show();
        } catch (Exception e) {

        }
    }

    public void Odjava(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("pekarna/view/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prijava!");
            stage.setScene(new Scene(root, 550, 370));
            stage.show();
        } catch (Exception e) {

        }
    }

}
