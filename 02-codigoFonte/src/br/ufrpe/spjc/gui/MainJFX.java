package br.ufrpe.spjc.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainJFX extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=  FXMLLoader.load(getClass().getResource("Principal.fxml"));
			Scene scene= new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
