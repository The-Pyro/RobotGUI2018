package robogui.main;

import java.io.InputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Main extends Application {
	public static void main(String args[]) {
		/* Configure Network Table protocol */
		NetworkTableInstance defaultNT = NetworkTableInstance.getDefault();
		defaultNT.setNetworkIdentity("GUI");
		defaultNT.startClientTeam(871);

		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException{
		/* Load Comic Sans */
		InputStream fontFile = getClass().getResourceAsStream("/res/comic.ttf");
		Font f = Font.loadFont(fontFile, 18);
		System.out.printf("%s loaded\n", f.toString());
		fontFile.close();

		/* Load main window */
		InputStream guifxml = getClass().getResourceAsStream("/res/GUI.fxml");

		FXMLLoader guiLoader = new FXMLLoader();

		Pane guiRoot = (Pane)guiLoader.load(guifxml);

		Scene guiScene = new Scene(guiRoot);
		stage.setScene(guiScene);
		stage.setTitle("Robot Dashboard 2018");

		stage.show();

		/* Load soundboard */
		InputStream soundfxml =
			getClass().getResourceAsStream("/res/Soundboard.fxml");

		FXMLLoader soundLoader = new FXMLLoader();

		Pane soundRoot = (Pane)soundLoader.load(soundfxml);

		Stage soundStage = new Stage();
		Scene soundScene = new Scene(soundRoot);

		soundStage.setScene(soundScene);
		soundStage.setTitle("Soundboard");

		soundStage.show();

		/* Load Auton Path Selector */
		InputStream pathfxml =
			getClass().getResourceAsStream("/res/FieldSelector.fxml");

		FXMLLoader pathLoader = new FXMLLoader();

		Pane pathRoot = (Pane)pathLoader.load(pathfxml);

		Stage pathStage = new Stage();
		Scene pathScene = new Scene(pathRoot);

		pathStage.setScene(pathScene);
		pathStage.setTitle("Auton Path Selector");
		pathStage.setResizable(false);

		pathStage.show();

		/* Load Camera Viewer */
		/*
		InputStream camerafxml = getClass().getResourceAsStream(
"/edu/wpi/first/shuffleboard/plugin/cameraserver/widget/CameraServerWidget.fxml"
			);

		FXMLLoader cameraLoader = new FXMLLoader();

		Pane cameraRoot = (Pane)cameraLoader.load(camerafxml);

		Stage cameraStage = new Stage();
		Scene cameraScene = new Scene(cameraRoot);

		cameraStage.setScene(cameraScene);
		cameraStage.setTitle("Camera Viewer");

		cameraStage.show();
		*/
	}
}
