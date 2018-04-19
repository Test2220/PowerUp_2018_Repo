/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.mechanisms.climber.ControlClimber;
import frc.team2220.robot.commands.mechanisms.climber.FishingPoleDown;
import frc.team2220.robot.commands.mechanisms.climber.FishingPoleUp;
import frc.team2220.robot.commands.mechanisms.climber.StopCimber;
import frc.team2220.robot.commands.mechanisms.intake.IntakePistons;
import frc.team2220.robot.commands.mechanisms.intake.UnjamNow;
import frc.team2220.robot.commands.mechanisms.shooter.*;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreMatchDefault;
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

        //Gagan's Side
        driverController.getLeftBumper().whenPressed(new Shoot());
        driverController.getStartButton().whileHeld(new PreMatchDefault());
        driverController.getAButton().whenPressed(new DriveToDistance(-Converter.ftToEncTicks(4)));
        driverController.getBButton().whenPressed(new MultiReversiblePathReader("TestPaths/TwoCubeGrabABox", 20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));


        //Nick's Side
//        manipulatorController.getAButton().whileHeld(new ShootSwitch());
//        manipulatorController.getAButton().whenReleased(new StopShooter());
//        manipulatorController.getBButton().whenPressed(new ShootScale(0.63));
//        manipulatorController.getBButton().whenReleased(new StopShooter());
//        manipulatorController.getYButton().whenPressed(new ShootScale());
//        manipulatorController.getYButton().whenReleased(new StopShooter());
//        manipulatorController.getLeftBumper().whenPressed(new IntakePistons(IntakePistons.Position.RETRACT));
//        manipulatorController.getLeftBumper().whenReleased(new IntakePistons(IntakePistons.Position.EXTEND));
//        manipulatorController.getXButton().whileHeld(new ShootSwitchHigh());
//        manipulatorController.getXButton().whenReleased(new StopShooter());
//        manipulatorController.getRightBumper().whileHeld(new UnjamNow());
//        manipulatorController.getRightBumper().whenReleased(new StopShooter());

        manipulatorController.getLeftBumper().whileHeld(new FishingPoleUp());
        manipulatorController.getLeftBumper().whenReleased(new StopCimber());

        manipulatorController.getRightBumper().whileHeld(new FishingPoleDown());
        manipulatorController.getRightBumper().whenReleased(new StopCimber());


    }


}

