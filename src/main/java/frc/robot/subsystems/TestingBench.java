package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class TestingBench extends SubsystemBase{
	private static TestingBench instance;

	private SparkMax brushMoter, brushlessMoter; // what

	public void setState(States state, float speed) {
		switch (state) {
			case STOPPED:
				handle_stopped_state();
				break;
			case RUNNING:
				handle_running_state(speed);
				break;
		}
	}

	private void handle_stopped_state() {
		brushMoter.stopMotor();
		brushlessMoter.stopMotor();
	}

	private void handle_running_state(float speed) {
		brushMoter.set(speed);;
		brushlessMoter.set(speed);
	}

	private TestingBench() {
		brushMoter = new SparkMax(1, MotorType.kBrushed);
		brushlessMoter = new SparkMax(2, MotorType.kBrushless);

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
