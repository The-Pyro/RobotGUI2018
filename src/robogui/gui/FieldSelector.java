package robogui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class FieldSelector {
	@FXML
	Pane rootNode;

	@FXML
	ToggleGroup rInboardGroup, lInboardGroup, startPosGroup;

	@FXML
	ToggleButton lScaleButton, rScaleButton, lSwitchButton, rSwitchButton;

	NetworkTableEntry lSwitchEntry;
	NetworkTableEntry rSwitchEntry;
	NetworkTableEntry lScaleEntry;
	NetworkTableEntry rScaleEntry;
	NetworkTableEntry rInboardEntry;
	NetworkTableEntry lInboardEntry;
	NetworkTableEntry startPosEntry;

	@FXML
	protected void initialize() {
		NetworkTable table = NetworkTableInstance.getDefault()
			.getTable("Dashboard");

		lSwitchEntry = table.getEntry("lSwitchOffLimits");
		rSwitchEntry = table.getEntry("rSwitchOffLimits");
		lScaleEntry = table.getEntry("lScaleOffLimits");
		rScaleEntry = table.getEntry("rScaleOffLimits");
		rInboardEntry = table.getEntry("isInboardSwitchR");
		lInboardEntry = table.getEntry("isInboardSwitchL");
		startPosEntry = table.getEntry("startPos");

		/* Make sure initial values are set */
		lScaleHandle(null);
		rScaleHandle(null);
		lSwitchHandle(null);
		rSwitchHandle(null);
	}

	@FXML
	protected void lScaleHandle(ActionEvent e) {
		boolean state = lScaleButton.isSelected();
		lScaleEntry.setBoolean(state);
		lScaleButton.setStyle(state ? "-fx-base: #ff0000;":"-fx-base: #00ff00");
	}

	@FXML
	protected void rScaleHandle(ActionEvent e) {
		boolean state = rScaleButton.isSelected();
		rScaleEntry.setBoolean(state);
		rScaleButton.setStyle(state ? "-fx-base: #ff0000;":"-fx-base: #00ff00");
	}

	@FXML
	protected void lSwitchHandle(ActionEvent e) {
		boolean state = lSwitchButton.isSelected();
		lSwitchEntry.setBoolean(state);
		lSwitchButton.setStyle(state? "-fx-base: #ff0000;":"-fx-base: #00ff00");
	}

	@FXML
	protected void rSwitchHandle(ActionEvent e) {
		boolean state = rSwitchButton.isSelected();
		rSwitchEntry.setBoolean(state);
		rSwitchButton.setStyle(state? "-fx-base: #ff0000;":"-fx-base: #00ff00");
	}

	@FXML
	protected void lInboardHandle(ActionEvent e) {
		lInboardEntry.setBoolean(new Boolean(lInboardGroup
			.getSelectedToggle().getUserData().toString()).booleanValue());
	}

	@FXML
	protected void rInboardHandle(ActionEvent e) {
		rInboardEntry.setBoolean(new Boolean(rInboardGroup
			.getSelectedToggle().getUserData().toString()).booleanValue());
	}

	@FXML
	protected void startPosHandle(ActionEvent e) {
		startPosEntry.setNumber(new Integer(startPosGroup.getSelectedToggle()
			.getUserData().toString()));
	}

}
