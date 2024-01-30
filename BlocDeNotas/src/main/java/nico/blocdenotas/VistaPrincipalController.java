/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nico.blocdenotas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import modelo.ControlFicheros;

/**
 * FXML Controller class
 *
 * @author nico_
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    private TextArea areaTexto;
    @FXML
    private MenuItem abrirMenuItem;
    @FXML
    private MenuItem nuevoMenuItem;
    @FXML
    private MenuItem ventanaNuevaMenuItem;
    @FXML
    private MenuItem guardarMenuItem;
    @FXML
    private MenuItem guardarComoMenuItem;
    @FXML
    private MenuItem salirMenuItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirArchivo(ActionEvent event) {
        areaTexto.setText(ControlFicheros.obtenerArchivo());
    }

    @FXML
    private void guardar(ActionEvent event) {
        ControlFicheros.guardarArchivoComo(areaTexto.getText());
    }

    @FXML
    private void resetVentana(ActionEvent event) {
        areaTexto.setText("");
    }

    @FXML
    private void abrirVentanaNueva(ActionEvent event) throws IOException {
                
        FXMLLoader loader = new FXMLLoader (getClass().getResource("vistaPrincipal.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Bloc de notas");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void guardarComo(ActionEvent event) {
        ControlFicheros.guardarArchivoComo(areaTexto.getText());
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        //Obtenemos el stage con ayuda de algun elemento
        Stage stage = (Stage) this.areaTexto.getScene().getWindow();
        //cerramos el stage
        stage.close();
    }
    
}
