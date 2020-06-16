/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pekarna.controller;

import pekarna.Baza;
import pekarna.Narudzba;
import pekarna.Proizvod;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 
 */
public class RacunController implements Initializable {
    ObservableList<Proizvod> data = FXCollections.observableArrayList();
    Baza db = new Baza();
    
    @FXML
    private ListView  lista1;
    @FXML
    private TextField konacno;
    @FXML
    private Button izlaz;
    
    
    /**
     * Initializes the controller class.
     */
    
   
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void izlaz (ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
}
    

   public void setNarudzba(String ispis){
        this.lista1.getItems().addAll(ispis);
   }
   
   public void setUkupno(Float ukupno){
       this.konacno.setText(Float.toString(ukupno));
   }
}