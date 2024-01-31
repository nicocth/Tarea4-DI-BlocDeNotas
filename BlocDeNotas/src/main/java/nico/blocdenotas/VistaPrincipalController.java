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
import javafx.scene.input.KeyEvent;
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
    
    private ControlFicheros cf;
    private boolean modificado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializamos el control de fichero para poder mantener el guardado y modificacion de ficheros
        cf = new ControlFicheros();
        modificado = false;
    }    

    @FXML
    private void abrirArchivo(ActionEvent event) {
        areaTexto.setText(ControlFicheros.obtenerArchivo());
    }

    @FXML
    private void guardar(ActionEvent event) {
        cf.guardarArchivo(areaTexto.getText());
        actualizarTitulo();
    }

    @FXML
    private void resetVentana(ActionEvent event) {
        //borramos lo escrito en el area de escritura 
        areaTexto.setText("");
        
        //reseteamos el titulo
        Stage stage = (Stage) this.areaTexto.getScene().getWindow();
        //Obtenemos el titulo para modificarlo 
        String titulo = stage.getTitle();
        //cambiamos el titulo para marcar que el texto a sido modificado
        stage.setTitle("Sin título: Bloc de notas");
        
        //inicializamos a null el atributo fichero y modificado
        cf.setFichero(null);
        modificado = false;
        
    }

    @FXML
    private void abrirVentanaNueva(ActionEvent event) throws IOException {
                
        FXMLLoader loader = new FXMLLoader (getClass().getResource("vistaPrincipal.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Sin título: Bloc de notas");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void guardarComo(ActionEvent event) {
        cf.guardarArchivoComo(areaTexto.getText());
        actualizarTitulo();
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        //obtenemos el stage para modificar 
        Stage stage = (Stage) this.areaTexto.getScene().getWindow();
        //cerramos el stage
        stage.close();
    }

    @FXML
    private void textoModificado(KeyEvent event) {
        //Si no ha sido modificado ya cambiamos el titulo
        if (modificado == false){
            //obtenemos el stage para modificar 
            Stage stage = (Stage) this.areaTexto.getScene().getWindow();
            //Obtenemos el titulo para modificarlo 
            String titulo = stage.getTitle();
            //cambiamos el titulo para marcar que el texto a sido modificado
            stage.setTitle("*" + titulo);
            modificado = true;
        }     
    }
    
    /**
     * Metodo que actualiza el titulo de la ventana
     */
    private void actualizarTitulo(){
                //modificamos titulo
        if(cf.getFichero() != null){
            modificado = false;
            //obtenemos el stage para modificar 
            Stage stage = (Stage) this.areaTexto.getScene().getWindow();

            //cambiamos el titulo para marcar que el texto a sido modificado
            stage.setTitle(cf.getFichero().getName() + ": Bloc de notas");
        }
    }
}
