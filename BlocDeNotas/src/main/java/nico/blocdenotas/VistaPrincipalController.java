/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nico.blocdenotas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author nico_
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    private MenuItem abrir;
    @FXML
    private TextArea areaTexto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    /**
     * Metodo que abre un archivo usando FileChooser
     */
    private void clic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        );

        // Obtener la imagen seleccionada
        File textoSelecionado = fileChooser.showOpenDialog(null);

        // Mostar el texto
        if (textoSelecionado != null) {
            FileReader reader = null;
            try {  
                // usamos un FileReader para leer el File
                reader = new FileReader(textoSelecionado); 
                //StringBuilder que almacena el texto
                StringBuilder sb = new StringBuilder(); 
                int c; // el carácter leído
                // se leen caracteres hasta llegar al final del archivo 
                while ((c = reader.read()) != -1) { 
                    // añadir el carácter al StringBuilder
                    sb.append((char) c); 
                }   
                // cerrar el FileReader
                reader.close(); 
                // obtener el texto como una cadena
                String texto = sb.toString(); 
                // mostramos ese texto en nuestro TextArea
                areaTexto.setText(texto);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}
