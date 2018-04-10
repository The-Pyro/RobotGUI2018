package robogui.gui;

import java.io.InputStream;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Soundboard {
	@FXML
	VBox rootNode;

	@FXML
	TilePane musicPane, sfxPane, memePane;

	@FXML
	Slider volSlider;

	NetworkTableEntry fileName, volume;

	@FXML
	public void initialize() {
		NetworkTable ourTable =
			NetworkTableInstance.getDefault().getTable("Dashboard");

		fileName = ourTable.getEntry("audioFile");
		volume = ourTable.getEntry("audioVolume");

		InputStream file = getClass().getResourceAsStream("/res/audioFiles");
		if (file != null) {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.isEmpty() || line.charAt(0) == '#')  {
					continue;
				}

				Button b = new Button();
				String tokens[] = line.split(":");
				b.setText(tokens[0]);
				b.setUserData(tokens[1]);
				b.setOnAction((e) -> {
					String path = (String)((Button)e.getTarget()).getUserData();
					fileName.setString(path);
				});

				switch (tokens[1].split("/")[0]) {
					case "music":
						musicPane.getChildren().add(b);
						break;
					case "sfx":
						sfxPane.getChildren().add(b);
						break;
					case "memes":
						memePane.getChildren().add(b);
						break;
				}
			}
			s.close();
		}

		volSlider.valueProperty().addListener((observable, old, newValue) -> {
			volume.setNumber(newValue.doubleValue());
		});
	}
}
