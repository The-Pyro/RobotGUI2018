package robogui.gui;

import java.io.InputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import edu.wpi.first.networktables.NetworkTableEntry;

public class LEDController extends VBox {
	NetworkTableEntry entry;

	@FXML
	Slider brightness;

	@FXML
	Button enabled;

	public LEDController() throws IOException {
		super();

		InputStream fxml =
			getClass().getResourceAsStream("/res/LEDController.fxml");

		FXMLLoader loader = new FXMLLoader();

		loader.setRoot(this);
		loader.setController(this);
		loader.load(fxml);
	}

	@FXML
	public void initialize() {
		brightness.valueProperty().addListener((observable, old, newValue) -> {
			if (entry != null) {
				entry.setNumber(newValue.intValue());
			}
		});
	}

	public void setTableEntry(NetworkTableEntry e) {
		entry = e;
	}

	@FXML
	protected void handleClick(ActionEvent e) {
		if (!brightness.isDisable()) {
			enabled.setText("Enable");
			brightness.setDisable(true);
		} else {
			enabled.setText("Disable");
			brightness.setDisable(false);
		}

		if (entry != null) {
			entry.setNumber(brightness.isDisable() ? 0.0:brightness.getValue());
		}
	}
}
