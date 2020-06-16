/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pekarna.controller;

import pekarna.Baza;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 */
public class LoginController implements Initializable {
    @FXML
    TextField jUsername;
    
    @FXML
    PasswordField jPassword;
    
    @FXML
    Label jPoruka;

    
        public void Prijavise (ActionEvent event){
        Baza db = new Baza();
        String username = this.jUsername.getText();
        String password = this.jPassword.getText();
        String sql = "select * from osoba where Korisnicko_ime=? and Lozinka=?";
        try{
            PreparedStatement ps = db.exec(sql);
            ps.setString(1, jUsername.getText());
            ps.setString(2, jPassword.getText());
            ResultSet rs = ps.executeQuery();
            
            if(username.contains("admin") && password.contains("admin")){
                ((Node)event.getSource()).getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("pekarna/view/adminMenu.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Admin menu");
                stage.setScene(new Scene(root,720,720));
                stage.show();
             
        }
            else if(rs.next()){
             ((Node)event.getSource()).getScene().getWindow().hide();
                
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/pekarna/view/logiraniProdavac.fxml"));
                try{
                    Loader.load();
                } catch(IOException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }          
                LogiraniProdavacController ime = Loader.getController();
                
                ime.setIme(username);
                
                Stage stage = new Stage();
                stage.setTitle("Izbornik");
                Parent root = Loader.getRoot();
                stage.setScene(new Scene(root));
                stage.show();  
                
                          
        }
            
            else{
                
                jPoruka.setText("Pogrešno ime ili lozinka");
                
            }
        }
        catch(Exception e){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null,e);
            
        }
}          

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
