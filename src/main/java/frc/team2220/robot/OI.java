/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.Intake.IntakePistons;
import frc.team2220.robot.commands.Shooter.*;
import frc.team2220.robot.commands.auto.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2220.robot.commands.drive.DriveWithXBox;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {


	//Joysticks
	Joystick driverStick = new Joystick(0);
	Joystick manipulatorStick = new Joystick(1);

	//BUTTONS ON DRIVERSTICK
	Button turnRight90 = new JoystickButton(driverStick, 2);
	Button turnLeft90 = new JoystickButton(driverStick, 3);
    Button driverShoot = new JoystickButton(driverStick, 5);
    Button preMatchSetup = new JoystickButton(driverStick, 8);
    Button velocityTester = new JoystickButton(driverStick, 7);
    Button pathTester = new JoystickButton(driverStick, 1);
    //Button stickInPlace = new JoystickButton(driverStick, 1);



    //BUTTONS ON MANIPULATOR STICK
	Button shootSwitch = new JoystickButton(manipulatorStick, 1);
    Button shootScale = new JoystickButton(manipulatorStick, 2);
    Button intakePistonTest = new JoystickButton(manipulatorStick, 5);



    public Joystick getDriverStick() {
		
		return driverStick;
		
	}

	public Joystick getManipulatorStick() {
		
		return manipulatorStick;
		
	}



    public OI(){

        turnRight90.whenPressed(new TurnToAngle(90));
        turnLeft90.whenPressed(new TurnToAngle(-90));
        driverShoot.whenPressed(new Shoot());
//        stickInPlace.whenPressed(new DriveToDistance(0));
//        stickInPlace.whenReleased(new DriveWithXBox());

        preMatchSetup.whileHeld(new PreMatchDefault());
        //.whenPressed(new LStartLScale());0
        velocityTester.whenPressed(new VelocityShooter());
        velocityTester.whenReleased(new StopShooter());
        pathTester.whenPressed(new PathReader("/home/lvuser/paths/LeftStart/RStartRScale_right_detailed.csv", "/home/lvuser/paths/LeftStart/RStartRScale_right_detailed.csv", 0.007));
        //driverShoot.whenPressed(new CubePiston(CubePiston.Position.UP));
        //driverShootRetract.whenPressed(new CubePiston(CubePiston.Position.DOWN));


        shootSwitch.whenPressed(new ShootSwitch());
        shootSwitch.whenReleased(new StopShooter());
        shootScale.whenPressed(new ShootScale());
        shootScale.whenReleased(new StopShooter());
        intakePistonTest.whenPressed(new IntakePistons(IntakePistons.Position.RETRACT));
        intakePistonTest.whenReleased(new IntakePistons(IntakePistons.Position.EXTEND));



	}

	
	
	
}
