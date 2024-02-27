/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author nico_
 */
public class ControlFicheros {
    
    //Declaracion de variables
    File fichero;
    /**
     * Constructor para la clase ControlFicheros
     */
    public ControlFicheros() {
        //inicializamos variables
        fichero = null;
    }
    /**
     * Mutador para atributo
     * @param fichero 
     */
    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    /**
     * Accesor para atributo fichero
     * @return 
     */
    public File getFichero() {
        return fichero;
    }
        
    /**
     * Metodo que permite abrir un fichero usando FileChooser y devuelve el texto que contiene
     * @param event 
     */
    public String obtenerArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");
        String texto = "";
        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        );

        // Obtener la imagen seleccionada
        fichero = fileChooser.showOpenDialog(null);

        // Mostrar el texto
        if (fichero != null) {
            FileReader reader = null;
            try {  
                // usamos un FileReader para leer el File
                reader = new FileReader(fichero); 
                System.out.println(reader.getEncoding());
                
                // StringBuilder que almacena el texto
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
                texto = sb.toString(); 
                // mostramos ese texto en nuestro TextArea
                return texto;
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
        return texto;
    }

    /**
     * Metodo que permite guardar un archivo usando FileChooser
     * 
     * @param texto
     */
    public void guardarArchivoComo(String texto) {

        // Crea un FileChooser
        FileChooser fileChooser = new FileChooser();

        // Configura el título del diálogo
        fileChooser.setTitle("Guardar como");

        // Configura el filtro de extensión para archivos de texto
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));

        // Muestra el diálogo de guardar y obtiene el archivo seleccionado
        fichero = fileChooser.showSaveDialog(null);

        //escribimos en el fichero seleccionado
        escribirEnFichero(texto);
    }
        
    /**
     * Metodo que cuando se guarda por primera vez funciona exactamente igual que guardar como, 
     * pero cuando guardamos mas de una vez no pregunta directorio y directamente guarda el archivo 
     * @param texto 
     */
    public void guardarArchivo(String texto) {
        //si no hemos guardado ninguna vez iremos a guardar como 
        if (fichero == null){
           guardarArchivoComo(texto); 
        }else{
            escribirEnFichero(texto);
        } 
    }

    private void escribirEnFichero(String texto) {
                // Si el archivo no es nulo, guarda el texto en el archivo
        if (fichero != null) {
            BufferedWriter bw = null;
            try {
                // Crea un BufferedWriter
                bw = new BufferedWriter(new FileWriter(fichero));
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

