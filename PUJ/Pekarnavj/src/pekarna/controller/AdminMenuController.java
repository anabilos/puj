/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pekarna.controller;

import java.io.IOException;
import pekarna.Baza;
import pekarna.Proizvod;
import pekarna.Osoba;
import static pekarna.Osoba.listaProdavaca;
import static pekarna.Proizvod.listaPita;
import static pekarna.Proizvod.listaPeciva;
import static pekarna.Proizvod.listaPizza;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * 
 */
public class AdminMenuController implements Initializable {
    ObservableList<Proizvod> data = FXCollections.observableArrayList();
    ObservableList<Osoba> data1 = FXCollections.observableArrayList();
    Baza db = new Baza();
    
    @FXML
    private TextField jIdP;
    @FXML
    private TextField jNazivP;
    @FXML
    private TextField jCijenaP;
    @FXML
    private TextField jKolicinaP;
    @FXML
    private TableView<Proizvod> jTableP;
    @FXML
    private TableColumn<Proizvod, String> ColumnIdP;
    @FXML
    private TableColumn<Proizvod, String> ColumnNazivP;
    @FXML
    private TableColumn<Proizvod, String> ColumnCijenaP;
    @FXML
    private TableColumn<Proizvod, String> ColumnKolicinaP;
    
    
    @FXML
    private TextField jIdPr;
    @FXML
    private TextField jImePr;
    @FXML
    private TextField jPrezimePr;
    @FXML
    private TextField jJMBGPr;
    @FXML
    private TextField jAdresaPr;
    @FXML
    private TextField jTelefonPr;
    @FXML
    private TextField jKImePr;
    @FXML
    private TextField jLozinkaPr;
    @FXML
    private TableView<Osoba> jTablePr;
    @FXML
    private TableColumn<Osoba, String> ColumnIdPr;
    @FXML
    private TableColumn<Osoba, String> ColumnImePr;
    @FXML
    private TableColumn<Osoba, String> ColumnPrezimePr;
    @FXML
    private TableColumn<Osoba, String> ColumnJMBGPr;
    @FXML
    private TableColumn<Osoba, String> ColumnAdresaPr;
    @FXML
    private TableColumn<Osoba, String> ColumnTelefonPr;
    @FXML
    private TableColumn<Osoba, String> ColumnKImePr;
    @FXML
    private TableColumn<Osoba, String> ColumnLozinkaPr;
    
    @FXML
    private TextField jIdPe;
    @FXML
    private TextField jNazivPe;
    @FXML
    private TextField jCijenaPe;
    @FXML
    private TextField jKolicinaPe;
    @FXML
    private TableView<Proizvod> jTablePe;
    @FXML
    private TableColumn<Proizvod, String> ColumnIdPe;
    @FXML
    private TableColumn<Proizvod, String> ColumnNazivPe;
    @FXML
    private TableColumn<Proizvod, String> ColumnCijenaPe;
    @FXML
    private TableColumn<Proizvod, String> ColumnKolicinaPe;
    

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     listaPita();
        postaviPodatkeUCelijeP();
  
        listaPeciva();
        postaviPodatkeUCelijePe();
    
 
   
        
        listaProdavaca();
        postaviPodatkeUCelijePr();
        
     
    } 
    public void listaProdavaca () { 
        
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM osoba");
        ColumnIdPr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("ID"));
        ColumnImePr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Ime"));
        ColumnPrezimePr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Prezime"));
        ColumnJMBGPr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("JMBG"));
        ColumnAdresaPr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Adresa"));
        ColumnTelefonPr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Telefon"));
        ColumnKImePr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Korisnicko_ime"));
        ColumnLozinkaPr.setCellValueFactory(new PropertyValueFactory<Osoba, String>("Lozinka"));
        
        try {
            
            data1.clear();
            while (rs.next()) {
                data1.add(new Osoba(
                        rs.getInt("ID"), 
                        rs.getString("Ime"), 
                        rs.getString("Prezime"), 
                        rs.getInt("JMBG"),
                        rs.getString("Adresa"), 
                        rs.getInt("Telefon"), 
                        rs.getString("Korisnicko_ime"), 
                        rs.getString("Lozinka")));
                jTablePr.setItems(data1);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }
    
    public void listaPita () { 
        
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM proizvod WHERE ID_Vrsta_proizvoda=1");
        ColumnIdP.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("ID"));
        ColumnNazivP.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("Naziv"));
        ColumnCijenaP.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("Cijena"));
        ColumnKolicinaP.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("Kolicina"));
        
        try {
            data.clear();
            while (rs.next()) {
                data.add(new Proizvod(
                        rs.getInt("ID"), 
                        rs.getString("Naziv"), 
                        rs.getFloat("Cijena"), 
                        rs.getInt("Kolicina")));
                jTableP.setItems(data);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }
   public void postaviPodatkeUCelijePr(){ 
        jTablePr.setOnMouseClicked(e -> {
            Osoba os = (Osoba) jTablePr.getItems().get(jTablePr.getSelectionModel().getSelectedIndex());
            jIdPr.setText(String.valueOf(os.getID()));
            jImePr.setText(os.getIme());
            jPrezimePr.setText(os.getPrezime());
            jJMBGPr.setText(String.valueOf(os.getJMBG()));
            jAdresaPr.setText(os.getAdresa());
            jTelefonPr.setText( String.valueOf(os.getTelefon()));
            jKImePr.setText(os.getKorisnicko_ime());
            jLozinkaPr.setText(os.getLozinka());
        });
    }
    
    public void postaviPodatkeUCelijeP(){ 
        jTableP.setOnMouseClicked(e -> {
            Proizvod pr = (Proizvod) jTableP.getItems().get(jTableP.getSelectionModel().getSelectedIndex());
            jIdP.setText(String.valueOf(pr.getID()));
            jNazivP.setText(pr.getNaziv());
            jCijenaP.setText(String.valueOf(pr.getCijena()));
            jKolicinaP.setText(String.valueOf(pr.getKolicina()));
        });
    }
    public void postaviPodatkeUCelijePe(){
       jTablePe.setOnMouseClicked(e -> {
            Proizvod pr = (Proizvod) jTablePe.getItems().get(jTablePe.getSelectionModel().getSelectedIndex());
            jIdPe.setText(String.valueOf(pr.getID()));
            jNazivPe.setText(pr.getNaziv());
            jCijenaPe.setText(String.valueOf(pr.getCijena()));
            jKolicinaPe.setText(String.valueOf(pr.getKolicina()));
        });
    }
  
 
    
    public void DodajP(ActionEvent event){
    try{
            String sql = "INSERT INTO proizvod (Naziv, Cijena, Kolicina,ID_Vrsta_Proizvoda) VALUES (?, ?, ?,1)";
            
            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jNazivP.getText());
            ps.setString(2, jCijenaP.getText());
            ps.setString(3, jKolicinaP.getText());
            ps.execute();
            
         
          JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            jIdP.clear();
            jNazivP.clear();
            jCijenaP.clear();
            jKolicinaP.clear();
            data.clear();
            listaPita();
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void UrediP(ActionEvent event){
        try{
            
            String sql =  "UPDATE `proizvod` SET `Naziv`='"+jNazivP.getText()+"',`Cijena`='"+jCijenaP.getText()+"',`Kolicina`="+jKolicinaP.getText()+" WHERE `ID` = "+jIdP.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno ažurirano");
            data.clear();
            listaPita();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void ObrisiP(ActionEvent event){
        try{
            String sql = "DELETE FROM proizvod WHERE ID="+jIdP.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            jIdP.clear();
            jNazivP.clear();
            jCijenaP.clear();
            jKolicinaP.clear();
            data.clear();
            listaPita();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void IsprazniPoljaP(ActionEvent event){
        try{
            jIdP.clear();
            jNazivP.clear();
            jCijenaP.clear();
            jKolicinaP.clear();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    public void DodajPr(ActionEvent event){
    try{
            String sql = "INSERT INTO osoba (Ime, Prezime, JMBG, Adresa, Telefon, Korisnicko_ime, Lozinka) VALUES (?, ?, ?,?,?,?,?)";
            
            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jImePr.getText());
            ps.setString(2, jPrezimePr.getText());
            ps.setString(3, jJMBGPr.getText());
            ps.setString(4, jAdresaPr.getText());
            ps.setString(5, jTelefonPr.getText());
            ps.setString(6, jKImePr.getText());
            ps.setString(7, jLozinkaPr.getText());
            
            ps.execute();
            
         
          JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            jIdPr.clear();
            jImePr.clear();
            jPrezimePr.clear();
            jJMBGPr.clear();
            jAdresaPr.clear();
            jTelefonPr.clear();
            jKImePr.clear();
            jLozinkaPr.clear();
            data1.clear();
            listaProdavaca();
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void UrediPr(ActionEvent event){
        try{
            
            String sql =  "UPDATE `osoba` SET `Ime`='"+jImePr.getText()+"',`Prezime`='"+jPrezimePr.getText()+"',`JMBG`='"+jJMBGPr.getText()+"',`Adresa`='"+jAdresaPr.getText()+"',`Telefon`='"+jTelefonPr.getText()+"',`Korisnicko_ime`='"+jKImePr.getText()+"',`Lozinka`='"+jLozinkaPr.getText()+"' WHERE `ID`= "+jIdPr.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno ažurirano");
            data1.clear();
            listaProdavaca();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void ObrisiPr(ActionEvent event){
        try{
            String sql = "DELETE FROM osoba WHERE ID="+jIdPr.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            jIdPr.clear();
            jImePr.clear();
            jPrezimePr.clear();
            jJMBGPr.clear();
            jAdresaPr.clear();
            jTelefonPr.clear();
            jKImePr.clear();
            jLozinkaPr.clear();
            data1.clear();
            listaProdavaca();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void IsprazniPoljaPr(ActionEvent event){
        try{
            jIdPr.clear();
            jImePr.clear();
            jPrezimePr.clear();
            jJMBGPr.clear();
            jAdresaPr.clear();
            jTelefonPr.clear();
            jKImePr.clear();
            jLozinkaPr.clear();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void IsprazniPoljaPe(ActionEvent event){
        try{
            jIdPe.clear();
            jNazivPe.clear();
            jCijenaPe.clear();
            jKolicinaPe.clear();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
}

    


    
    public void listaPeciva () {
        
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM proizvod WHERE ID_Vrsta_proizvoda=2");
        ColumnIdPe.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("ID"));
        ColumnNazivPe.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("Naziv"));
        ColumnCijenaPe.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("Cijena"));
        ColumnKolicinaPe.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("Kolicina"));
        
        try {
            data.clear();
            while (rs.next()) {
                data.add(new Proizvod(
                        rs.getInt("ID"), 
                        rs.getString("Naziv"), 
                        rs.getFloat("Cijena"), 
                        rs.getInt("Kolicina")));
                jTablePe.setItems(data);
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
    }
    
    public void DodajPe(ActionEvent event){
    try{
            String sql = "INSERT INTO proizvod (Naziv, Cijena, Kolicina,ID_Vrsta_Proizvoda) VALUES (?, ?, ?,2)";
            
            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jNazivPe.getText());
            ps.setString(2, jCijenaPe.getText());
            ps.setString(3, jKolicinaPe.getText());
            ps.execute();
            
         
          JOptionPane.showMessageDialog(null, "Uspjesno dodano");
            jIdPe.clear();
            jNazivPe.clear();
            jCijenaPe.clear();
            jKolicinaPe.clear();
            data.clear();
            listaPeciva();
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void UrediPe(ActionEvent event){
        try{
            
            String sql =  "UPDATE `proizvod` SET `Naziv`='"+jNazivPe.getText()+"',`Cijena`='"+jCijenaPe.getText()+"',`Kolicina`="+jKolicinaPe.getText()+" WHERE `ID` = "+jIdPe.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno ažurirano");
            data.clear();
            listaPeciva();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void ObrisiPe(ActionEvent event){
        try{
            String sql = "DELETE FROM proizvod WHERE ID="+jIdPe.getText();
            PreparedStatement ps = db.exec(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Uspjesno obrisano");
            jIdPe.clear();
            jNazivPe.clear();
            jCijenaPe.clear();
            jKolicinaPe.clear();
            data.clear();
            listaPeciva();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
   
    
    public void Odjava (ActionEvent event){
        try{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("pekarna/view/login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Prijava!");
        stage.setScene(new Scene(root, 550, 370));
        stage.show();
        }
        catch(Exception e){
            
        }
    }
      
  
 
}
   

   
    

