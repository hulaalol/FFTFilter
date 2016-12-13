package gui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			GridPane layout = (GridPane) FXMLLoader.load(Main.class.getResource("layout.fxml"));
			
			primaryStage.setTitle("FFT-Filter");
			
//			Button button2 = new Button("Accept");
//			Button button3 = new Button("Accept2");
//			
//			StackPane layout = new StackPane();
//			
//			layout.getChildren().add(button2);
//			layout.getChildren().add(button3);

			Scene scene = new Scene(layout,1400,900);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
//			FileChooser fileChooser = new FileChooser();
//			fileChooser.setTitle("Open Resource File");
//			fileChooser.showOpenDialog(primaryStage);
			
			
			


			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
