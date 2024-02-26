/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nico.blocdenotas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nico_
 */
public class VistaBuscarController implements Initializable {

    // Referencia al controlador de la ventana padre
    private VistaPrincipalController ventanaPadreController;
    
    @FXML
    private TextField textoBuscar;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarCoincidencias(ActionEvent event) {
        //Captamos el texto del stage principal
        String txt = ventanaPadreController.areaTexto.getText();
        
        //Captamos el texto a buscar
        String buscar = textoBuscar.getText();

        //Si se encuentran coincidencias se selecciona el texto
        if(txt.contains(buscar)){
            //obtenemos el indice donde se encuentra el texto que ha coincidido
            int indice = txt.lastIndexOf(buscar);
            //Seleccionamos de la ventana padre el texto
            ventanaPadreController.areaTexto.selectRange(indice + buscar.length(), indice);
        }else{ //En caso contrario se lanza alerta
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("No se han encontrado coincidencias");

            alert.showAndWait();

        }
    }
    
    //Método para establecer la referencia al controlador de la ventana padre
    public void setVentanaPadreController(VistaPrincipalController controller) {
        this.ventanaPadreController = controller;
    }
    
}
