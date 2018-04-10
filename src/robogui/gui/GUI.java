package robogui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.networktables.TableEntryListener;
import edu.wpi.first.shuffleboard.plugin.cameraserver.widget.CameraServerWidget;

public class GUI {
	@FXML
	BorderPane rootNode;

	/*
	@FXML
	CameraController camera;
	*/

	@FXML
	LEDController leds;

	@FXML
	Rectangle cubeStatus;

	@FXML
	Label uLiftHeight;
	@FXML
	Label lLiftHeight; 
	@FXML
	Label cubeHeight;
	@FXML
	Label liftMode;
	@FXML
	Label liftSetpoint;
	@FXML
	Label robotDisplacement;
	@FXML
	Label robotHeading;
	@FXML
	Label robotTilt;
	@FXML
	Label driveMode;

	@FXML
	Slider trim;

	private final String UTICKS_KEY = "UpperLiftTicks";
	private final String LTICKS_KEY = "LowerLiftTicks";
	private final String UHEIGHT_KEY = "UpperLiftHeight";
	private final String LHEIGHT_KEY = "LowerLiftHeight";
	private final String XDISP_KEY = "displacementX";
	private final String YDISP_KEY = "displacementY";
	private final String BRIGHTNESS_KEY = "ledBrightness";
	private final String WHITE_BALANCE_KEY = "cameraWhiteBal";
	private final String EXPOSURE_KEY = "cameraExposure";
	private final String CUBE_KEY = "cubeDetect";
	private final String LIFT_MODE_KEY = "liftMode";
	private final String LIFT_SETPOINT_KEY = "liftSetpoint";
	private final String ANGLE_KEY = "gyroAngle";
	private final String TILT_KEY = "gyroTilt";
	private final String DRIVE_MODE_KEY = "driveMode";

	@FXML
	public void initialize() {
		NetworkTable t = NetworkTableInstance.getDefault()
			.getTable("Dashboard");

		/*
		camera.setTableEntries(t.getEntry("cameraWhiteBal"),
				t.getEntry("cameraExposure"));
		*/

		leds.setTableEntry(t.getEntry("ledBrightness"));

		/* Register listener to change Cube Status indicator */
		t.addEntryListener(CUBE_KEY, (table, key, entry, value, flags) -> {
			boolean state = value.getBoolean();
			Platform.runLater(() -> {
				cubeStatus.setFill(state ? Color.GREEN : Color.RED);
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);

		/* Register listeners to update lifter info */
		t.addEntryListener(UHEIGHT_KEY, (table, key, entry, value, flags) -> {
			double uHeight = value.getDouble();
			double lHeight = t.getEntry(LHEIGHT_KEY).getDouble(0.0);
			int uTicks = t.getEntry(UTICKS_KEY).getNumber(0).intValue();
			int lTicks = t.getEntry(LTICKS_KEY).getNumber(0).intValue();

			Platform.runLater(() -> {
				uLiftHeight.setText(String.format("Upper Lifter is at %.1f "
							+ "inches (%d ticks)", uHeight, uTicks));
				lLiftHeight.setText(String.format("Lower Lifter is at %.1f "
							+ "inches (%d ticks)", lHeight, lTicks));
				cubeHeight.setText(String.format("Total cube height:  %.1f",
							uHeight + lHeight));
			});

		}, TableEntryListener.kNew | TableEntryListener.kUpdate);
		t.addEntryListener(LHEIGHT_KEY, (table, key, entry, value, flags) -> {
			double uHeight = t.getEntry(UHEIGHT_KEY).getDouble(0.0);
			double lHeight = value.getDouble();
			int uTicks = t.getEntry(UTICKS_KEY).getNumber(0).intValue();
			int lTicks = t.getEntry(LTICKS_KEY).getNumber(0).intValue();

			Platform.runLater(() -> {
				uLiftHeight.setText(String.format("Upper Lifter is at %.1f "
							+ "inches (%d ticks)", uHeight, uTicks));
				lLiftHeight.setText(String.format("Lower Lifter is at %.1f "
							+ "inches (%d ticks)", lHeight, lTicks));
				cubeHeight.setText(String.format("Total cube height:  %.1f",
							uHeight + lHeight));
			});

		}, TableEntryListener.kNew | TableEntryListener.kUpdate);
		t.addEntryListener(UTICKS_KEY, (table, key, entry, value, flags)->{
			double uHeight = t.getEntry(UHEIGHT_KEY).getDouble(0.0);
			double lHeight = t.getEntry(LHEIGHT_KEY).getDouble(0.0);
			int uTicks = (int)value.getDouble();
			int lTicks = t.getEntry(LTICKS_KEY).getNumber(0).intValue();

			Platform.runLater(() -> {
				uLiftHeight.setText(String.format("Upper Lifter is at %.1f "
							+ "inches (%d ticks)", uHeight, uTicks));
				lLiftHeight.setText(String.format("Lower Lifter is at %.1f "
							+ "inches (%d ticks)", lHeight, lTicks));
				cubeHeight.setText(String.format("Total cube height:  %.1f",
							uHeight + lHeight));
			});

		}, TableEntryListener.kNew | TableEntryListener.kUpdate);
		t.addEntryListener(LTICKS_KEY, (table, key, entry, value, flags)->{
			double uHeight = t.getEntry(UHEIGHT_KEY).getDouble(0.0);
			double lHeight = t.getEntry(LHEIGHT_KEY).getDouble(0.0);
			int uTicks = t.getEntry(UTICKS_KEY).getNumber(0).intValue();
			int lTicks = (int)value.getDouble();

			Platform.runLater(() -> {
				uLiftHeight.setText(String.format("Upper Lifter is at %.1f "
							+ "inches (%d ticks)", uHeight, uTicks));
				lLiftHeight.setText(String.format("Lower Lifter is at %.1f "
							+ "inches (%d ticks)", lHeight, lTicks));
				cubeHeight.setText(String.format("Total cube height:  %.1f",
							uHeight + lHeight));
			});

		}, TableEntryListener.kNew | TableEntryListener.kUpdate);

		/* Register lifters to update lifter mode */
		t.addEntryListener(LIFT_MODE_KEY, (table, key, entry, value, flags) -> {
			String mode = value.getString();
			Platform.runLater(() -> {
				liftMode.setText(String.format("Lifter is in %s mode", mode));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);
		t.addEntryListener(LIFT_SETPOINT_KEY, (table, key, entry, value, flags)-> {
			double setpoint = value.getDouble();
			Platform.runLater(() -> {
				liftSetpoint.setText(String.format("Lifter setpoint if %f",
							setpoint));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);

		/* Register listeners to udpate robot displacement info */
		t.addEntryListener(XDISP_KEY, (table, key, entry, value, flags)->{
			double x = value.getDouble();
			double y = t.getEntry(YDISP_KEY).getDouble(0.0);
			Platform.runLater(() -> {
				robotDisplacement.setText(String.format("Robot position is X = "
							+ "%.1f inches, Y = %.1f inches", x, y));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);
		t.addEntryListener(YDISP_KEY, (table, key, entry, value, flags)->{
			double x = value.getDouble();
			double y = t.getEntry(XDISP_KEY).getDouble(0.0);
			Platform.runLater(() -> {
				robotDisplacement.setText(String.format("Robot position is X = "
							+ "%.1f inches, Y = %.1f inches", x, y));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);

		/* Register listeners to update robot rotation information */
		t.addEntryListener(ANGLE_KEY, (table, key, entry, value, flags) -> {
			double angle = value.getDouble();
			Platform.runLater(() -> {
				robotHeading.setText(String.format("Gyro heading is %.1f\u00b0",
							angle));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);
		t.addEntryListener(TILT_KEY, (table, key, entry, value, flags) -> {
			double angle = value.getDouble();
			Platform.runLater(() -> {
				robotTilt.setText(String.format("Robot tilt is %.1f\u00b0",
							angle));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);

		t.addEntryListener(DRIVE_MODE_KEY, (table, key, entry, value, flags) ->{
			String mode = value.getString();
			Platform.runLater(() -> {
				driveMode.setText(String.format("Drive mode is %s", mode));
			});
		}, TableEntryListener.kNew | TableEntryListener.kUpdate);


		trim.valueProperty().addListener((observable, old, newValue) -> {
				t.getEntry("robotTrim").setNumber(newValue.intValue());
		});

	}
}
