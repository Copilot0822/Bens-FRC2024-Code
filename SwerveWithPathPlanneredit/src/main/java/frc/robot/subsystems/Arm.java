// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //private final TalonSRX intakeSrx = new TalonSRX(30);
  private final CANSparkMax leftSparkMax = new CANSparkMax(Constants.leftArmId, MotorType.kBrushless);
  private final CANSparkMax rightSparkMax = new CANSparkMax(Constants.rightArmId, MotorType.kBrushless);

  private final RelativeEncoder leftEncoder = leftSparkMax.getEncoder();
  

  private final DigitalInput upStop = new DigitalInput(Constants.armUpstopPort);
  private final DigitalInput downStop = new DigitalInput(Constants.armDownstopPort);
  private double position = 0;
  private double p;
  

  


  





  public Arm() {
    leftSparkMax.setInverted(Constants.armInvert);
    rightSparkMax.follow(leftSparkMax, Constants.oppositeInvert);
    leftEncoder.setInverted(Constants.armInvert);
    leftEncoder.setPositionConversionFactor(Constants.armConversionFactor);
    leftSparkMax.setIdleMode(IdleMode.kBrake);
    
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Arm Down Limit", downStop.get());
    SmartDashboard.putBoolean("Arm Up Limit", upStop.get());
    SmartDashboard.putNumber("Arm Encoder", leftEncoder.getPosition());
    SmartDashboard.putNumber("Arm Command", leftSparkMax.getAppliedOutput()); 
    
    


    if(downStop.get() == true){
      leftEncoder.setPosition(0);
    }
    else if(upStop.get() == true){
      leftEncoder.setPosition(Constants.armEndStopPos);
    }
    if(leftEncoder.getPosition() < position){
      p = (position-leftEncoder.getPosition())*Constants.armSlopeValue;

      //p = Math.sin(Math.toDegrees(p));




      /*if(p > 0.3){
        leftSparkMax.set(0.3);
      }
      else{
        leftSparkMax.set(p);
      }*/

      //leftSparkMax.set(position-leftEncoder.getPosition()*Constants.armSlopeValue);
    }

    //else{
    //  leftSparkMax.set(0);
    //}
    


    
    // This method will be called once per scheduler run
    
}
  





  public void runArm(double speed){
    leftSparkMax.set(speed);

  }
  public double getArmDegrees(){
    return leftEncoder.getPosition();
  }
  public boolean getUpStopPos(){
    return upStop.get();
  }
  public boolean getDownStopPos(){
    return downStop.get();
  }
  public void zeroArm(){
    leftEncoder.setPosition(0);
  }
  public void topStopZero(){
    leftEncoder.setPosition(Constants.armEndStopPos);
  }

  public void setArmPosi(double posi){
    position = posi;

  }



  

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}