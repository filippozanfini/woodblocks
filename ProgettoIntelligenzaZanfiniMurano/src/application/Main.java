package application;

import controllers.StartController;
import controllers.WoodBlockController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
	
		FXMLLoader loaderStart = new FXMLLoader(getClass().getResource("/view/Start.fxml"));
		BorderPane root = (BorderPane) loaderStart.load();
		StartController rc = loaderStart.getController();
		rc.init(primaryStage);

		Scene scene = new Scene(root,875,762);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
