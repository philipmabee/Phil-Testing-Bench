// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.TestingBench;
import frc.robot.subsystems.TestingBench.States;

import edu.wpi.first.wpilibj.XboxController; // oh yeah
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
	private final TestingBench testingBench = TestingBench.getInstance();
	private final XboxController controller = new XboxController(0);

  public RobotContainer() {
		configureBindings();
  }

  private void configureBindings() {
		Trigger rightBumper = new Trigger(controller::getRightBumperButton);
		Trigger leftBumper = new Trigger(controller::getLeftBumperButton);

		Trigger aButton = new Trigger(controller::getAButtonPressed);
		Trigger bButton = new Trigger(controller::getBButtonPressed);

		rightBumper.onTrue(new InstantCommand(() -> testingBench.setState(States.RUNNING, 1)));
		rightBumper.onFalse(new InstantCommand(() -> testingBench.setState(States.STOPPED, 0)));

		leftBumper.onTrue(new InstantCommand(() -> testingBench.setState(States.RUNNING, -1)));
		leftBumper.onFalse(new InstantCommand(() -> testingBench.setState(States.STOPPED, 0)));

		aButton.onTrue(new InstantCommand(() -> testingBench.setState(States.RUNNING, 0.5)));
		aButton.onFalse(new InstantCommand(() -> testingBench.setState(States.STOPPED, 0)));

		bButton.onTrue(new InstantCommand(() -> testingBench.setState(States.RUNNING, -0.5)));
		bButton.onFalse(new InstantCommand(() -> testingBench.setState(States.STOPPED, 0)));
  }

  public Command getAutonomousCommand() {
	  return Commands.print("There is no command configured man");
  }
}
