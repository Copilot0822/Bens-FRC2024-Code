// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  //CAN Ids
  public static final int intakeId = 47;
  public static final int indexerId = 40;
  public static final int bottomShooterId = 41;
  public static final int topShooterId = 42;
  public static final int leftArmId = 43;
  public static final int rightArmId = 44;

   //Motor Inverts
  public static final boolean intakeInvert = false;
  public static final boolean indexerInvert = false;
  public static final boolean shooterTopInvert = false;
  public static final boolean shooterBottomInvert = false;
  
  //Intake Command vars
  public static final double intakeSpeedIn = 0.4;
  public static final double indexSpeedIn = 0.4;
  public static final double indexCurrentThreshould = 2;
  public static final double indexBackOutSpeed = 0.25;
  public static final int indexBackOutTime = 100;

  //Shoot Command vars
  public static final double shooterSpeed = 0.8;
  public static final double shooterVariance = 50; //rpm variance
  public static final double indexerPush = 0.8;
  public static final int afterIndexPushDelay = 750;

  public static class OperatorConstants {
    //public static final int kDriverControllerPort = 0;
  }
}
