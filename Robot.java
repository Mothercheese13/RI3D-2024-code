// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot; // FRC Package Time Robot
import edu.wpi.first.wpilibj.Timer; 
import edu.wpi.first.wpilibj.XboxController; // Xbox controller package 
import edu.wpi.first.wpilibj.drive.DifferentialDrive; // Driving Differential
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX; // Library to interface with the motor controller 
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup; // links 2 motors together 
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory. 
 */
public class Robot extends TimedRobot {

  private final WPI_TalonFX frontLeft = new WPI_TalonFX(2); 
  private final WPI_TalonFX frontRight = new WPI_TalonFX(3);
  private final WPI_TalonFX backRight = new WPI_TalonFX(4);
  private final WPI_TalonFX backLeft = new WPI_TalonFX(1);

// motor code 
  private final WPI_TalonFX flywheelMotor1 = new WPI_TalonFX(6);
  private final WPI_TalonFX flywheelMotor2 = new WPI_TalonFX(5);
  // flywheelMotor1.set(0.6); // the % output of the motor, between -1 and 1
  // flywheelMotor2.set(0.6);


  private final MotorControllerGroup LeftGroup = new MotorControllerGroup(frontLeft,backLeft); 
  private final MotorControllerGroup RightGroup = new MotorControllerGroup(frontRight,backRight); 

  private final DifferentialDrive kai_mobile = new DifferentialDrive(LeftGroup, RightGroup);
  
  // create x box controller object ;
  private final XboxController m_controller = new XboxController(0); 


  // private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive); 
  // private final XboxController m_controller = new XboxController(0); // interprets xbox controllers 
  private final Timer m_timer = new Timer();
   // pneumatics 
  // final Compressor comp = new Compressor();
  // final DoubleSolenoid solenoid = new DoubleSolenoid(6,7);

  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,7,6);
  // m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
  // m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);


  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */


   /** hehehehehehehehehheheheehehehehehehhehehehehehehehehhehe haw  */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    LeftGroup.setInverted(true);
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);

  }

  // /** This function is run once each time the robot enters autonomous mode. */
  // @Override
  // public void autonomousInit() {
  //   m_timer.restart();
  // }

  // /** This function is called periodically during autonomous. */
  // @Override
  // public void autonomousPeriodic() {
  //   // Drive for 2 seconds
  //   if (m_timer.get() < 2.0) {
  //     // Drive forwards half speed, make sure to turn input squaring off
  //     kai_mobile.arcadeDrive(0.5, 0.0, false);
  //   } else {
  //     kai_mobile.stopMotor(); // stop robot
  //   }
  // }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    // LeftGroup.setInverted(true);
    // RightGroup.setInverted(true);
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    // think of it like an y and x axis
    // kai_mobile.tankDrive(m_controller.getLeftY()* 0.7, m_controller.getRightY() *0.7);
     // kai_mobile.arcadeDrive(-m_controller.getLeftY(), -m_controller.getLeftX());
    kai_mobile.arcadeDrive(m_controller.getLeftY()* 0.6, m_controller.getLeftX()*0.6 );

    //  Motor spark1 = new Spark1(0); // 0 is the RIO PWM port this is connected to

  
    
    // if(m_controller.getAButton()){
    //   flywheelMotor1.set(1);
    //   flywheelMotor2.set(1);
    // }
    // else if(m_controller.getBButton()){
    //   flywheelMotor1.set(-0.5);
    //   flywheelMotor2.set(-0.4);
    // }
    // else{
    //   flywheelMotor1.set(0);// bottom motor
    //   // flywheelMotor2.set(0);// top motor
    // }

    // if (m_controller.getRightBumper()){
    //   flywheelMotor2.set(1);
    // }
    // if (m_controller.getRightBumperReleased()){
    //   flywheelMotor2.set(0);
    // }

    if(m_controller.getAButton()){
      flywheelMotor1.set(1);
      flywheelMotor2.set(1);
    }else if(m_controller.getBButton()){
      flywheelMotor1.set(0.3);
      flywheelMotor2.set(0.3);
    }else if(m_controller.getYButton()){
      flywheelMotor2.set(-0.2);
    }else{
      flywheelMotor1.set(0);
      flywheelMotor2.set(0);
    }

    if(m_controller.getXButtonPressed()) {
      // exampleSingle.toggle();
       m_doubleSolenoid.toggle();

       

    } 
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}

