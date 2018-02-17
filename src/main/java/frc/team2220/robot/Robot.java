/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.team2220.robot.commands.auto.GameInfo;
import frc.team2220.robot.commands.miscellaneous.MatchData;
import frc.team2220.robot.commands.miscellaneous.PathGen;
import frc.team2220.robot.commands.leftstart.LeftAutoHelper;
import frc.team2220.robot.commands.middlestart.MiddleAutoHelper;
import frc.team2220.robot.commands.miscellaneous.ExampleSubsystem;
import frc.team2220.robot.commands.rightstart.RightAutoHelper;
import frc.team2220.robot.subsystems.Climber;
import frc.team2220.robot.subsystems.Intake;
import frc.team2220.robot.subsystems.Shooter;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public static final Shooter shooter = new Shooter();
    public static final Intake intake = new Intake();
    public static final Climber climber = new Climber();

	public static PathGen pathGen;

	public static OI oi;

	public Command autonomousCommand;
	SendableChooser<Command> sideChooser = new SendableChooser<>();

    /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	@Override
	public void robotInit() {
		oi = new OI();
		pathGen = new PathGen();

        Compressor airCompressor = new Compressor();
        airCompressor.start();
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(128, 173);
        camera.setFPS(30);
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/MStartRSwitch_left.csv");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        try {
//            System.out.println("bufferedReader.readLine() = " + bufferedReader.sreadLine());
//        } catch (Exception error) {
//            System.out.println("ERROR IN READING CSV FILE = " + error);
//        }
        sideChooser.addDefault("LEFT", new LeftAutoHelper());
		sideChooser.addObject("RIGHT", new RightAutoHelper());
		sideChooser.addObject("MIDDLE", new MiddleAutoHelper());
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
	public void disabledPeriodic(){
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
//
//        try {
//            System.out.println("GameInfo.getGameSpecificMessage_WeekZero()" + GameInfo.getGameSpecificMessage_WeekZero());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        autonomousCommand = sideChooser.getSelected();

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
        Robot.shooter.setCubePistonDown();
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("LEFT POSITION", Robot.twilightDrive.getLPosition());
        SmartDashboard.putNumber("RIGHT POSITION", Robot.twilightDrive.getRPosition());
        SmartDashboard.putNumber("LEFT MOTOR VEL", Robot.twilightDrive.lDriveMaster.getEncVelocity());
        SmartDashboard.putNumber("RIGHT MOTOR VEL", Robot.twilightDrive.rDriveMaster.getEncVelocity());

	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
