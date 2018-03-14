/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.Intake.IntakePistons;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.*;
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

        manipulatorController.getAButton().whileHeld(new ShootSwitch());
        manipulatorController.getAButton().whenReleased(new StopShooter());
        manipulatorController.getbButton().whenPressed(new ShootScale(0.63));
        manipulatorController.getbButton().whenReleased(new StopShooter());
        manipulatorController.getyButton().whenPressed(new ShootScale());
        manipulatorController.getyButton().whenReleased(new StopShooter());
        manipulatorController.getLeftBumper().whenPressed(new IntakePistons(IntakePistons.Position.RETRACT));
        manipulatorController.getRightBumper().whenReleased(new IntakePistons(IntakePistons.Position.EXTEND));
        manipulatorController.getRightBumper().whenReleased(new IntakePistons(IntakePistons.Position.EXTEND));

    }


}
