// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final AutoCommand autoCommand = new AutoCommand();
  final XboxController driverController = new XboxController(USBConstants.DRIVER_CONTROLLER_PORT);


  public RobotContainer() {
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(new RunCommand(() -> {
      driveSubsystem.arcadeDrive(
          -driverController.getY(GenericHID.Hand.kLeft),
          driverController.getX(GenericHID.Hand.kRight));
    }, driveSubsystem));
  }

  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
  
    // An  will run in autonomous
    return autoCommand;
  }
}
