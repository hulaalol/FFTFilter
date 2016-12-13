package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Sample Skeleton for 'layoutTest.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class GUIController implements Initializable {

    @FXML // fx:id="sourceImg"
    private ImageView sourceImg; // Value injected by FXMLLoader

    @FXML // fx:id="fftImage"
    private ImageView fftImage; // Value injected by FXMLLoader

    @FXML // fx:id="loadImage"
    private Button loadImage; // Value injected by FXMLLoader

    @FXML // fx:id="amplitude"
    private Button amplitude; // Value injected by FXMLLoader

    @FXML // fx:id="phase"
    private Button phase; // Value injected by FXMLLoader

    @FXML // fx:id="filterFactor"
    private TextField filterFactor; // Value injected by FXMLLoader

    @FXML // fx:id="filter"
    private Button filter; // Value injected by FXMLLoader
    
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        
    	loadImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
    			FileChooser fileChooser = new FileChooser();
    			fileChooser.setTitle("Open Source Image");
    			File f = fileChooser.showOpenDialog(loadImage.getScene().getWindow());
    			
    			
    			 Image image = new Image(f.toURI().toString());
    			sourceImg.setImage(image);
    			
            }
        });
    	
    	
    	amplitude.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
            	try {
					fftImage.setImage(logic.IMGio.img2AmpImg(sourceImg.getImage()));
				} catch (IOException e) {
					System.err.println("Oops! Couldn't read the file :(");
				}

            }
        });
    	
    	phase.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
            	try {
					fftImage.setImage(logic.IMGio.img2PhaseImg(sourceImg.getImage()));
				} catch (IOException e) {
					System.err.println("Oops! Couldn't read the file :(");
				}

            }
        });
    	
    	filter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
            	try {
					fftImage.setImage(logic.IMGio.img2FFTCompress(sourceImg.getImage(),Double.parseDouble(filterFactor.getText())));
				} catch (IOException e) {
					System.out.println("Oops! Couldn't read the file :(");
				}

            }
        });
    	
    	
    	
    	
    	
    	
    	
    	
    	

    }
    
    

}
