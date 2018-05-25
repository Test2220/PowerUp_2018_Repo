/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.mechanisms.intake.AutoIntake;
import frc.team2220.robot.commands.mechanisms.intake.IntakePistons;
import frc.team2220.robot.commands.mechanisms.intake.JitterIntake;
import frc.team2220.robot.commands.mechanisms.intake.UnjamNow;
import frc.team2220.robot.commands.mechanisms.shooter.*;
import frc.team2220.robot.commands.mechanisms.vision.BlinkLimelight;
import frc.team2220.robot.commands.mechanisms.vision.CubeFollower;
import frc.team2220.robot.commands.mechanisms.vision.CubeTracker;
import frc.team2220.robot.commands.paths.leftstart.LStartLScale;
import frc.team2220.robot.commands.paths.leftstart.LStart_LScaleRSwitch;
import frc.team2220.robot.commands.paths.leftstart.LStart_LSwitchLScale;
import frc.team2220.robot.commands.paths.leftstart.LStart_LSwitchRScale;
import frc.team2220.robot.commands.paths.rightstart.RStartRScale;
import frc.team2220.robot.utils.Converter;
import frc.team2220.robot.utils.TwilightXBoxController;

public class OI {

    private TwilightXBoxController driverController;
    private TwilightXBoxController manipulatorController;

    public TwilightXBoxController getDriverController() {
        return driverController;
    }

    public TwilightXBoxController getManipulatorController() {
        return manipulatorController;
    }

    public OI() {

        driverController = new TwilightXBoxController(0);
        manipulatorController = new TwilightXBoxController(1);

        driverController.getLeftBumper().whenPressed(new Shoot());
        driverController.getStartButton().whileHeld(new PreMatchDefault());
        driverController.getXButton().whenPressed(new JitterIntake(2));
        driverController.getBackButton().whenPressed(new MultiReversiblePathReader("LeftStart_v2/LStartRScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
//        driverController.getXButton().whenPressed(new LStart_LSwitchLScale());
        driverController.getYButton().whenPressed(new AutoIntake(-0.75, 5));
        driverController.getRightBumper().whileHeld(new CubeTracker());

        driverController.getAButton().whileHeld(new ShootSwitch());
        driverController.getAButton().whenReleased(new StopShooter());
        driverController.getBButton().whenPressed(new ShootScale(0.63));
        driverController.getBButton().whenReleased(new StopShooter());
//        driverController.getStartButton().whileHeld(new PreMatchDefault());
//        driverController.getBackButton().whenPressed(new LStart_LSwitchRScale());
//        driverController.getAButton().whenPressed(new LStart_LScaleRSwitch());
//        driverController.getXButton().whenPressed(new LStart_LSwitchLScale());
//        driverController.getYButton().whenPressed(new AutoIntake(-0.6, 5));
//        driverController.getBButton().whileHeld(new CubeTracker());



        //Nick's Side

        manipulatorController.getAButton().whileHeld(new ShootSwitch());
        manipulatorController.getAButton().whenReleased(new StopShooter());
        manipulatorController.getBButton().whenPressed(new ShootScale(0.63));
        manipulatorController.getBButton().whenReleased(new StopShooter());

        manipulatorController.getYButton().whenPressed(new ShootScale());
        manipulatorController.getYButton().whenReleased(new StopShooter());
        manipulatorController.getXButton().whileHeld(new ShootSwitchHigh());
        manipulatorController.getXButton().whenReleased(new StopShooter());
        manipulatorController.getRightBumper().whileHeld(new UnjamNow());
        manipulatorController.getRightBumper().whenReleased(new StopShooter());

    }


}

