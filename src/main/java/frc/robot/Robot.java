// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final PWMVictorSPX Motor_ti = new PWMVictorSPX(3); //trasero izquiero
  private final PWMVictorSPX Motor_td = new PWMVictorSPX(0);// trasero derecho
  private final PWMVictorSPX Motor_di = new PWMVictorSPX(6);//dalentero izquierdo
  private final PWMVictorSPX Motor_dd = new PWMVictorSPX(1);//delantero derecho
  private final PWMTalonSRX intk =new PWMTalonSRX(8);//motor intake
  private final PWMVictorSPX shoo =new PWMVictorSPX(5);//motor shooter
  private final PWMVictorSPX enm =new PWMVictorSPX(2);//motor de enmedio>>
  private final Joystick Control = new Joystick(0);//control

  public static double rightTrigger;
  







  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }




  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
     //chasis

     
     double speed = Control.getRawAxis(1);
     double turn = Control.getRawAxis(4);
   


  
     

     
     double left = speed - turn;
     double right = speed + turn;
 
     Motor_ti.set(-left); 
     Motor_td.set(right);
     Motor_di.set(-left);
     Motor_dd.set(right);
 
 
 //intake 
 
     if(Control.getRawButton(6)){
       intk.set(-0.9);
     }else if (Control.getRawButton(5)){
       intk.set(0.8);
     }else {
       intk.set(0);
     }
 
 
 // shooter
 if(Control.getRawButton(1)){
       shoo.set(1);
       enm.set(1);
     }else if (Control.getRawButton(2)){
       shoo.set(0.6);
       enm.set(1);
     } else if (Control.getRawButton(3){
       shoo.set(-0.6);
       enm.set(-1);
     } else {
       shoo.set(0);
       enm.set(0);
         }
  }
  

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
