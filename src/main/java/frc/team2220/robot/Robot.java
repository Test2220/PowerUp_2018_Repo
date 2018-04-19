/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.commands.auto.PreMatchDefault;
import frc.team2220.robot.commands.paths.leftstart.LeftAutoHelper;
import frc.team2220.robot.commands.paths.middlestart.MiddleAutoHelper;
import frc.team2220.robot.commands.miscellaneous.ExampleSubsystem;
import frc.team2220.robot.commands.paths.rightstart.RightAutoHelper;
import frc.team2220.robot.subsystems.*;


public class Robot extends TimedRobot {


    public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem(1.5, 2, 3, 4, 5);
    public static final TwilightDrive twilightDrive = new TwilightDrive();
    public static final Shooter shooter = new Shooter();
    public static final Intake intake = new Intake();
    public static final Climber climber = new Climber();
    public static final Limelight limelight = new Limelight();

    public static OI oi;


    private Command autonomousCommand;
    private SendableChooser<Command> sideChooser = new SendableChooser<>();

    @Override
    public void robotInit() {
        oi = new OI();
        Compressor airCompressor = new Compressor(0);
        airCompressor.start();
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(128, 173);
        camera.setFPS(30);
        sideChooser.addDefault("LEFT", new LeftAutoHelper());
        sideChooser.addObject("MIDDLE", new MiddleAutoHelper());
        sideChooser.addObject("RIGHT", new RightAutoHelper());

        SmartDashboard.putData("Auto mode", sideChooser);
        //Logger.writeLog("YES ITS WORKING HAHAHAHHAHHA");
    }


    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putBoolean("Boi Boolean", true);
        SmartDashboard.putString("CURRENT AUTO", sideChooser.getSelected().toString());
        SmartDashboard.putData(new PreMatchDefault());
    }

    @Override
    public void autonomousInit() {
        Robot.twilightDrive.navX.zeroYaw();

        autonomousCommand = sideChooser.getSelected();

        System.out.println(DriverStation.getInstance().getGameSpecificMessage());

        if (autonomousCommand == null) {
            System.out.println("NULL AUTO");
        }
        if (autonomousCommand != null) {
            System.out.println("NOT NU  LL AUTO" + autonomousCommand.toString());

            autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        Robot.twilightDrive.navX.zeroYaw();

        Robot.shooter.setCubePistonDown();
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

    }


    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

//        Robot.twilightDrive.rDriveMaster.set(0.5);
//        Robot.twilightDrive.rDriveSlave.set(0.5);
    }

    @Override
    public void testPeriodic() {

    }
}
