/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nico.blocdenotas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
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

    @FXML
    private void guardar(ActionEvent event) {
        //Obtenemos el texto que queremos guardar
        String texto = areaTexto.getText();

        // Crea un FileChooser
        FileChooser fileChooser = new FileChooser();

        // Configura el título del diálogo
        fileChooser.setTitle("Guardar como");

        // Configura el filtro de extensión para archivos de texto
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));

        // Muestra el diálogo de guardar y obtiene el archivo seleccionado
        File file = fileChooser.showSaveDialog(null);

        // Si el archivo no es nulo, guarda el texto en el archivo
        if (file != null) {
            BufferedWriter bw = null;
            try {
                // Crea un BufferedWriter
                bw = new BufferedWriter(new FileWriter(file));
                // Escribe el texto del TextArea en el archivo
                bw.write(texto);
                // Cierra el BufferedWriter
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void resetVentana(ActionEvent event) {
    }

    @FXML
    private void abrirVentanaNueva(ActionEvent event) {
 
    }

    @FXML
    private void guardarComo(ActionEvent event) {
        ControlFicheros.guardarArchivoComo(areaTexto.getText());
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
    }
    
}
