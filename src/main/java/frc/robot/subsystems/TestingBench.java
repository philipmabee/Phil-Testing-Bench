package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class TestingBench extends SubsystemBase{
	private static TestingBench instance;

	private SparkMax brushlessMoter;

	public void setState(States state, double speed) {
		switch (state) {
			case STOPPED:
				handleStoppedState();
				break;
			case RUNNING:
				handleRunningState(speed);
				break;

			default:
				handleStoppedState();
				break;
		}
	}

	private void handleStoppedState() {
		brushlessMoter.stopMotor();
	}

	private void handleRunningState(double speed) {
		brushlessMoter.set(speed);
	}

	private TestingBench() {
		brushlessMoter = new SparkMax(3, MotorType.kBrushless);

		setState(States.STOPPED, 0);
	}


	// sets the state to STOPPED
	public static TestingBench getInstance() {
		if (instance == null) {
			instance = new TestingBench();
		}

		return instance;
	}

	public enum States {
		STOPPED,
		RUNNING
	}

}
