// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorDownCommand extends CommandBase {

  ElevatorSubsystem elevatorSubsystem;

public ElevatorDownCommand(ElevatorSubsystem elevatorSubsystem) {
  
    addRequirements(elevatorSubsystem);
    this.elevatorSubsystem = elevatorSubsystem;
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute(){
    elevatorSubsystem.setElevatorSpeed(-.25);
  }

  
  @Override
  public void end(boolean interrupted) {
    elevatorSubsystem.setElevatorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
