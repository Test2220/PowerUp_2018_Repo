/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2220.robot.commands.Intake.IntakePistons;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.leftstart.LStartLScale;

public class OI {


    //Joysticks
    Joystick driverStick = new Joystick(0);
    Joystick manipulatorStick = new Joystick(1);

    //BUTTONS ON DRIVERSTICK
    Button driverShootLB = new JoystickButton(driverStick, 5);
    Button preMatchSetupStart = new JoystickButton(driverStick, 8);
    Button pathTester = new JoystickButton(driverStick, 1);

    //BUTTONS ON MANIPULATOR STICK
    Button shootSwitch = new JoystickButton(manipulatorStick, 1);
    Button shootScale = new JoystickButton(manipulatorStick, 2);
    Button shootScaleHigher = new JoystickButton(manipulatorStick, 4);
    Button intakePistonTest = new JoystickButton(manipulatorStick, 5);


    public Joystick getDriverStick() {

        return driverStick;

    }

    public Joystick getManipulatorStick() {

        return manipulatorStick;

    }


    public OI() {

        driverShootLB.whenPressed(new Shoot());
        preMatchSetupStart.whileHeld(new PreMatchDefault());
        pathTester.whenPressed(new LStartLScale());
        //pathTester.whenPressed(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MovementTest_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MovementTest_right_detailed.csv", 0));
        //pathTester.whenPressed(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MovementTestTurn_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MovementTestTurn_right_detailed.csv", 0));
        //pathTester.whenPressed(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MovementTestTurnRight123_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MovementTestTurnRight123_right_detailed.csv", 0));
        //pathTester.whenPressed(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));

        shootSwitch.whileHeld(new ShootSwitch());
        shootSwitch.whenReleased(new StopShooter());
        shootScale.whenPressed(new ShootScale(0.63));
        shootScale.whenReleased(new StopShooter());
        shootScaleHigher.whenPressed(new ShootScale());
        shootScaleHigher.whenReleased(new StopShooter());
        intakePistonTest.whenPressed(new IntakePistons(IntakePistons.Position.RETRACT));
        intakePistonTest.whenReleased(new IntakePistons(IntakePistons.Position.EXTEND));


    }


}
