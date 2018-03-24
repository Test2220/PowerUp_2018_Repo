/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.Shooter.*;
import frc.team2220.robot.commands.auto.DriveToDistance;
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

        driverController.getLeftBumper().whenPressed(new Shoot());
        driverController.getStartButton().whileHeld(new PreMatchDefault());

        driverController.getAButton().whenPressed(new DriveToDistance(-Converter.ftToEncTicks(4)));


    }


}

