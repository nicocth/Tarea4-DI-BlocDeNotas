/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nico.blocdenotas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.ControlFicheros;

/**
 * FXML Controller class
 * Clase controladora de la vista principal de nuestro bloc de notas, aqui estaran la mayoria de los metodos que usa nuestro programa, tanto de gestion de ficheros como de 
 * funcionalidades para la edicion del texto.
 * @author nico_
 */
public class VistaPrincipalController implements Initializable {
    
    @FXML
    public TextArea areaTexto;
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
    @FXML
    private MenuItem buscarMenuItem;
    @FXML
    private MenuItem reemplazarMenuItem;
    @FXML
    private MenuItem mayusMenuItem;
    @FXML
    private MenuItem minusMenuItem;
    @FXML
    private CheckMenuItem barraEstadoMenuItem;
    @FXML
    private HBox statusBar;
    @FXML
    private Label posicionStatusBar;
    @FXML
    private Label CaracteresStatusBar;
    @FXML
    private Label codificacionStatusBar;
    @FXML
    private MenuItem acercaDe;
        
    private ControlFicheros cf;
    
    private boolean modificado;

    /**
     * Inicializa la clase controladora.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializamos el control de fichero para poder mantener el guardado y modificacion de ficheros
        cf = new ControlFicheros();
        modificado = false;
    }    

    @FXML
    /**
     * Método que usa la clase ControlFicheros para buscar un archivo, añade el texto de este al textArea de nuestra ventana y actualiza el titulo.
     */
    private void abrirArchivo(ActionEvent event) {
        areaTexto.setText(cf.obtenerArchivo());
        actualizarTitulo();
        
        // usamos un FileReader para obtener la codificacion del fichero actual
        FileReader reader; 
        try {
            reader = new FileReader(cf.getFichero());
            //Actualizamos la barra de estado segun la codificacion del archivo
            codificacionStatusBar.setText(reader.getEncoding());        
        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    /**
     * Método que usa la clase ControlFicheros para guardar nuestro archivo, si ya se habia guardado antes se guardara directamente sin tener que elegir fichero de destino
     * Aparte actualiza el titulo para eliminar el asterisco que marca que el texto ha sido modificado.
     */
    private void guardar(ActionEvent event) {
        cf.guardarArchivo(areaTexto.getText());
        actualizarTitulo();
    }

    @FXML
    /**
     * Método que reinicia la ventana inicializando los elementos de la vista y la clase controlficheros
     */
    private void resetVentana(ActionEvent event) {
        //borramos lo escrito en el area de escritura 
        areaTexto.setText("");
        
        //obtenemos stage
        Stage stage = (Stage) this.areaTexto.getScene().getWindow();
        //cambiamos el titulo para marcar que el texto ha sido modificado
        stage.setTitle("Sin título: Bloc de notas");
        
        //inicializamos a null el atributo fichero y modificado
        cf.setFichero(null);
        modificado = false;
        
    }

    @FXML
    /**
     * Método que inicia una ventana nueva totalmente independiente de la actual.
     */
    private void abrirVentanaNueva(ActionEvent event) throws IOException {
                
        FXMLLoader loader = new FXMLLoader (getClass().getResource("vistaPrincipal.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Sin título: Bloc de notas");
        stage.getIcons().add(new Image("images/icontitle.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    /**
     * Metodo que guarda nuestro texto en un archivo preguntando siempre el fichero destino
     */
    private void guardarComo(ActionEvent event) {
        cf.guardarArchivoComo(areaTexto.getText());
        actualizarTitulo();
    }

    @FXML
    /**
     * Método que cierra el programa
     */
    private void cerrarVentana(ActionEvent event) {
        //obtenemos el stage para modificar 
        Stage stage = (Stage) this.areaTexto.getScene().getWindow();
        //cerramos el stage
        stage.close();
    }

    @FXML
    /**
     * Método que añade el asterisco al titulo cuando este es modificado
     *Aparte actualiza el numero de caracteres en la barra de estado
     */
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
        

        
        //obtenemos el numero de caracteres del textArea y lo actualizamos en la barra de estado.
        int numeroCaracteres = areaTexto.getText().length();
        CaracteresStatusBar.setText("Caracteres: " + numeroCaracteres);


        
    }
    

    @FXML
    /**
     * Método que abre otra ventana que te permite buscar un texto dentro del textArea de la ventana principal
     */
    private void buscar(ActionEvent event) throws IOException {
        //obtenemos el stage para modificar 
        Stage primaryStage = (Stage) this.areaTexto.getScene().getWindow();
        
        //cargamos la nueva ventana
        FXMLLoader loader = new FXMLLoader (getClass().getResource("vistaBuscar.fxml"));
        Parent root = loader.load();
        
        //Obtenemos el controlador de la ventana hija
        VistaBuscarController ventanaHijaController = loader.getController();        
        // Pasar la referencia al controlador de la ventana padre
        ventanaHijaController.setVentanaPadreController(this); 
        
        //creamos ventana modal con la nueva ventana haciendo referencia al stage principal
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Buscar");
        stage.getIcons().add(new Image("images/iconbuscar.jpg"));
        stage.initModality(Modality.NONE);
        stage.initOwner(primaryStage);
        stage.show();
    }

    @FXML
    /**
     * Método que abre otra ventana que te permite buscar un texto dentro del textArea de la ventana principal y reemplazarlo.
     */
    private void reemplazar(ActionEvent event) throws IOException {
        //obtenemos el stage para modificar 
        Stage primaryStage = (Stage) this.areaTexto.getScene().getWindow();
        
        //cargamos la nueva ventana
        FXMLLoader loader = new FXMLLoader (getClass().getResource("vistaReemplazar.fxml"));
        Parent root = loader.load();
        
        //Obtenemos el controlador de la ventana hija
        VistaReemplazarController ventanaHijaController = loader.getController();
        // Pasar la referencia al controlador de la ventana padre
        ventanaHijaController.setVentanaPadreController(this); 
        
        //creamos ventana modal con la nueva ventana haciendo referencia al stage principal
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Buscar");
        stage.getIcons().add(new Image("images/find_replace.png"));
        stage.initModality(Modality.NONE);
        stage.initOwner(primaryStage);
        stage.show();
    }

    @FXML
    /**
     * Método que convierte a mayusculas el texto seleccionado en el textArea
     */
    private void convertiramayusculas(ActionEvent event) {
        //obtenemos el texto seleccionado
        String textoSeleccionado = areaTexto.getSelectedText();
        //Lo pasamos a mayusculas
        String textoMayus = textoSeleccionado.toUpperCase();
        //obtenemos todo el texto del textArea
        String texto = areaTexto.getText();
        //reemplazamos el cacho seleccionado
        String textoMod = texto.replaceAll(textoSeleccionado, textoMayus);
        //insertamos el texto modificado
        areaTexto.setText(textoMod);
    }

    @FXML
    /**
     * Método que convierte a minusculas el texto seleccionado en el textArea
     */
    private void convertiraminuscula(ActionEvent event) {
        //obtenemos el texto seleccionado
        String textoSeleccionado = areaTexto.getSelectedText();
        //Lo pasamos a mayusculas
        String textoMinus = textoSeleccionado.toLowerCase();
        //obtenemos todo el texto del textArea
        String texto = areaTexto.getText();
        //reemplazamos el cacho seleccionado
        String textoMod = texto.replaceAll(textoSeleccionado, textoMinus);
        //insertamos el texto modificado
        areaTexto.setText(textoMod);
    }

    @FXML
    /**
     * Método que oculta/muestra la barra de estado
     */
    private void mostrarOcultarBarraEstado(ActionEvent event) {
        if(barraEstadoMenuItem.isSelected()){
            statusBar.setVisible(false);
        }else{
            statusBar.setVisible(true);
        }
    }

    @FXML
    /**
     * Metodo que actualiza la linea y columna de la barra de estado al hacer clic con el raton
     */
    private void actualizarPosicion(MouseEvent event) {

        int lineNumber = getLineNumber(areaTexto);
        int columnIndex = getColumnIndex(areaTexto);
        //el metodo de contar lineas falla si no hay ningun caracter en la ultima linea añadida, con esto compensamos el fallo
        if(columnIndex == 1){
            lineNumber++;
        }
        posicionStatusBar.setText("Linea: " + lineNumber + ", Columna: " + columnIndex);
    }

    @FXML
    /**
     * Método que abre una ventana de informacion sobre el programa
     */
    private void abrirAcercaDe(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca del Bloc de notas");

            //Establece el titulo de la alerta
            alert.setHeaderText("Informacion del programa:");
            //Establece el cuerpo de la alerta
            alert.setContentText("Programa hecho por Nicolás Calderón Torres.\n"
                    + "Perteneciente a la Tarea de la Unidad de Trabajo 2.\n"
                    + "- Elaboración de interfaces mediante documentos xml.\n"
                    + "Modulo: Desarrollo de Interfaces.");
            alert.showAndWait();
    }

    @FXML
    /**
     * Metodo que actualiza la linea y columna de la barra de estado al soltar una tecla del teclado
     */
    private void actualizarBarraEstado(KeyEvent event) {
        //actualiza la barra de estado
        int lineNumber = getLineNumber(areaTexto);
        int columnIndex = getColumnIndex(areaTexto);
        //el metodo de contar lineas falla si no hay ningun caracter en la ultima linea añadida, con esto compensamos el fallo
        if(columnIndex == 1){
            if(lineNumber!=1){
               lineNumber++; 
            } 
        }
        posicionStatusBar.setText("Linea: " + lineNumber + ", Columna: " + columnIndex);
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

    /**
     * metodo que devuelve la linea en la que se encuentra el cursor actualmente dentro de un textArea
     * @param textArea
     * @return 
     */
    private static int getLineNumber(TextArea textArea) {
        String text = textArea.getText();
        int position = textArea.getCaretPosition();
        String[] lineas = text.split("\n");
        int totalLineas = lineas.length;
        int contadorCaracteres = 0;
        int lineaActual = 0;
        
        for (int i = 0; i < totalLineas; i++) {
            contadorCaracteres += lineas[i].length();
            lineaActual++;
            if (position <= contadorCaracteres) {
                break;
            }
        }
        return lineaActual;
    }

    /**
     * metodo que devuelve la columna en la que se encuentra el cursor actualmente dentro de un textArea
     * @param textArea
     * @return 
     */
    private static int getColumnIndex(TextArea textArea) {
        int caretPosition = textArea.getCaretPosition();
        String text = textArea.getText();
        int lastNewlineIndex = text.lastIndexOf('\n', caretPosition);
        return caretPosition - lastNewlineIndex;
    }
}

