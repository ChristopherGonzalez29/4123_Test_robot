// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ElevatorDownCommand;
import frc.robot.commands.ElevatorUpCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();

  private final ElevatorDownCommand elevatorDownCommand = new ElevatorDownCommand(elevatorSubsystem);
  private final ElevatorUpCommand elevatorUpCommand = new ElevatorUpCommand(elevatorSubsystem);
  
  final XboxController driverController = new XboxController(USBConstants.DRIVER_CONTROLLER_PORT);

  private void calibrate(){
    System.out.println("Gyro is calibrating...");
    driveSubsystem.calibrateGyro();
  }
  public RobotContainer() {
    calibrate();
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(new RunCommand(() -> {
      driveSubsystem.arcadeDrive(
          -driverController.getY(GenericHID.Hand.kLeft),
          driverController.getX(GenericHID.Hand.kRight));
    }, driveSubsystem));
  }

  private void configureButtonBindings() {
    new JoystickButton(driverController, XboxConstants.LB_BUTTON).whileHeld(elevatorDownCommand);
    new JoystickButton(driverController, XboxConstants.RB_BUTTON).whileHeld(elevatorUpCommand);
  }


  public Command getAutonomousCommand() {
  
    return new ElevatorDownCommand(elevatorSubsystem);
  }
}
