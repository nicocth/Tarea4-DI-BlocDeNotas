/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author nico_
 */
public class ControlFicheros {
    
        /**
         * Metodo que permite guardar un archivo usando FileChooser
         * 
         * @param event 
         */
        public static void guardarArchivoComo(String texto) {

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
}
