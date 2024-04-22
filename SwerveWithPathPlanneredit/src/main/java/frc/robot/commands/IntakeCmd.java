// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class IntakeCmd extends Command {
  private final Intake intake;
  private final Indexer indexer;
  
  
  private boolean x = false; //x is the trigger for ending the command (on true)
  private boolean y = false; //y is the backout trigger (on true)
  
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final Intake Intake;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem
   * the subsystem used by this command.
   */
  public IntakeCmd(Intake intake, Indexer indexer) {
    this.indexer = indexer;
    this.intake = intake;

    //m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    addRequirements(indexer);
    //addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {//sets the booleans to false
    y = false;
    x = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //indexer.setIndexer(Constants.indexSpeedIn);
    //intake.setIntake(Constants.intakeSpeedIn);

    if(indexer.getIndexerCurrent() > Constants.indexCurrentThreshould){ // if the current is above the threshould then output change y to true
      y = true;
      indexer.startIndexTimer();

    }
    if(!y){ //if y false (current not yet over limit) set indexer and intake to in speed
      indexer.setIndexer(Constants.indexSpeedIn);
      intake.setIntake(Constants.intakeSpeedIn);
    }
    else if(y){ //if y is true (current trigger has been triggered) set intake speed 0 then decide off of Index timer value whether or not to turn backwards or end command
      //indexer.setIndexer(0);
      intake.setIntake(0);


      indexer.setIndexer(-Constants.indexBackOutSpeed);
      //indexer.startIndexTimer();

      if(indexer.getIndexTimer() > Constants.indexBackOutTime){
        indexer.setIndexer(0);
        x = true;

      }
      //else{
        //indexer.setIndexer(Constants.indexBackOutSpeed);
      //}


    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    indexer.setIndexer(0);
    intake.setIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*if(indexer.getIndexerCurrent()> Constants.indexCurrentThreshould){
      return true;
    }
    else{
      return false;
    }*/


    return x;
  }
}
