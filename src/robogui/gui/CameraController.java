package robogui.gui;

import java.io.InputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import edu.wpi.first.networktables.NetworkTableEntry;

public class CameraController extends VBox {
	NetworkTableEntry whiteBalEntry, exposureEntry;

	@FXML
	Label whiteBalLabel, exposureLabel;

	@FXML
	Slider whiteBal, exposure;

	public CameraController() throws IOException {
		super();

		InputStream fxml =
			getClass().getResourceAsStream("/res/CameraController.fxml");

		FXMLLoader loader = new FXMLLoader();

		loader.setRoot(this);
		loader.setController(this);
		loader.load(fxml);
	}

	@FXML
	public void initialize() {
		whiteBal.valueProperty().addListener((observable, oldValue, newValue)->{
			if (whiteBalEntry != null) {
				whiteBalEntry.setNumber(newValue.intValue());
			}

			whiteBalLabel.setText(
					String.format("Camera White Balance: %dK",
						newValue.intValue()));
		});

		exposure.valueProperty().addListener((observable, oldValue, newValue)->{
			if (exposureEntry != null) {
				exposureEntry.setNumber(newValue.intValue());
			}

			exposureLabel.setText(
					String.format("Camera Exposure: %d%%",
						newValue.intValue()));
		});

	}

	public void setTableEntries(NetworkTableEntry whiteBalEntry,
			NetworkTableEntry exposureEntry) {
		this.whiteBalEntry = whiteBalEntry;
		this.exposureEntry = exposureEntry;
	}
}
