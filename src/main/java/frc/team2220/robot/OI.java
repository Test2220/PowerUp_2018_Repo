/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import edu.wpi.first.wpilibj.Joystick;
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
    Button preMatchSetupSTART = new JoystickButton(driverStick, 8);
    Button pathTesterBACK = new JoystickButton(driverStick, 1);

    //BUTTONS ON MANIPULATOR STICK
    Button shootSwitchA = new JoystickButton(manipulatorStick, 1);
    Button shootScaleB = new JoystickButton(manipulatorStick, 2);
    Button shootScaleHigherY = new JoystickButton(manipulatorStick, 4);
    Button intakePistonLB = new JoystickButton(manipulatorStick, 5);


    public Joystick getDriverStick() {

        return driverStick;

    }

    public Joystick getManipulatorStick() {

        return manipulatorStick;

    }


    public OI() {

        driverShootLB.whenPressed(new Shoot());
        preMatchSetupSTART.whileHeld(new PreMatchDefault());
        pathTesterBACK.whenPressed(new LStartLScale());
        pathTesterBACK.whenPressed(new PathEncoderFollower("/home/lvuser/paths/MiddleStart/MovementTest_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MovementTest_right_detailed.csv", 0));
        //pathTesterBACK.whenPressed(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MovementTestTurn_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MovementTestTurn_right_detailed.csv", 0));
        //pathTesterBACK.whenPressed(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MovementTestTurnRight123_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MovementTestTurnRight123_right_detailed.csv", 0));
        //pathTesterBACK.whenPressed(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));

        shootSwitchA.whileHeld(new ShootSwitch());
        shootSwitchA.whenReleased(new StopShooter());
        shootScaleB.whenPressed(new ShootScale(0.63));
        shootScaleB.whenReleased(new StopShooter());
        shootScaleHigherY.whenPressed(new ShootScale());
        shootScaleHigherY.whenReleased(new StopShooter());
        intakePistonLB.whenPressed(new IntakePistons(IntakePistons.Position.RETRACT));
        intakePistonLB.whenReleased(new IntakePistons(IntakePistons.Position.EXTEND));


    }


}
