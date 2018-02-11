/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import frc.team2220.robot.commands.auto.GameInfo;
import frc.team2220.robot.commands.miscellaneous.PathGen;
import frc.team2220.robot.commands.leftstart.LeftAutoHelper;
import frc.team2220.robot.commands.middlestart.MiddleAutoHelper;
import frc.team2220.robot.commands.miscellaneous.ExampleSubsystem;
import frc.team2220.robot.commands.rightstart.RightAutoHelper;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.subsystems.VelocityTestSubsystem;
import openrio.powerup.MatchData;

import java.io.IOException;

import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.getEntry;
import static javax.swing.UIManager.getString;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    public static String side;

	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem(1.5, 2, 3, 4, 5);
	public static final TwilightDrive twilightDrive = new TwilightDrive();
	public static final VelocityTestSubsystem velocityTestSubsystem = new VelocityTestSubsystem();

	MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
	MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);

	public static PathGen pathGen;

	public static OI oi;

	public Command autonomousCommand;
	SendableChooser<Command> sideChooser = new SendableChooser<>();

    NetworkTableInstance offSeasonNetworkTable;

    /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	@Override
	public void robotInit() {
		oi = new OI();
		pathGen = new PathGen();
        offSeasonNetworkTable = NetworkTableInstance.create();
        offSeasonNetworkTable.startClient("10.0.100.5", 1735);
		//sideChooser.addObject("RIGHT", new RightAutoHelper());
		//sideChooser.addDefault("RIGHT", new LStartLSwitch());
		//DriverStation.getInstance().getGameSpecificMessage()

		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", sideChooser);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.o
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

/*
        String gameData = offSeasonNetworkTable.getEntry("GameData").getString("defaultValue");
        System.out.println(gameData);
*/
	}

	@Override
	public void disabledPeriodic() {


        sideChooser.setName("SIDE");
        sideChooser.addObject("LEFT", new LeftAutoHelper());
        sideChooser.addObject("MIDDLE", new MiddleAutoHelper());
        sideChooser.addObject("RIGHT", new RightAutoHelper());

		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit(){
	  try{
          String gameData = offSeasonNetworkTable.getTable("OffseasonFMSInfo").getEntry("GameData").getString("defaultValue");
            System.out.println("OFFSEASON INFO " + gameData);
          //System.out.println(GameInfo.getGameSpecificMessage_WeekZero());
      }catch (Exception error) {
          //System.out.println(error);
      }



        autonomousCommand = sideChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		if (autonomousCommand == null) {
			System.out.println("NULL AUTO");
		}
		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			System.out.println("NOT NULL AUTO" + autonomousCommand.toString());

			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("CURRENT VELOCITY", VelocityTestSubsystem.getInstance().getEncVel());
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("TEST MOTOR VOLTAGE", VelocityTestSubsystem.getInstance().testMotor.getOutputVoltage());
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
