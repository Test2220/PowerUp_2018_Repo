/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.drive.DriveWithXBox;
import frc.team2220.robot.commands.middlestart.MStartRSwitch;

import frc.team2220.robot.commands.miscellaneous.ControlIntake;
import frc.team2220.robot.commands.miscellaneous.ControlShooter;
import frc.team2220.robot.triggers.TwilightTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2220.robot.utils.Converter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {


	//Joysticks
	Joystick driverStick = new Joystick(0);
	Joystick manipulatorStick = new Joystick(1);
	
	//Tank Drives
	Button startCurvatureDrive = new JoystickButton(driverStick, 8);
	
	// Triggers
	
	//Buttons on DRIVERSTICK
	Button turnRight90 = new JoystickButton(driverStick, 2);
	Button turnLeft90 = new JoystickButton(driverStick, 3);
	Button testCommandButton = new JoystickButton(driverStick, 4);
    Button stickInPlace = new JoystickButton(driverStick, 5);

    //Buttons on MANIPULATORSTICK
    Button controlShooter = new JoystickButton(manipulatorStick, 4);
    Button controlIntake = new JoystickButton(manipulatorStick, 1);


    public Joystick getDriverStick() {
		
		return driverStick;
		
	}

	public Joystick getManipulatorStick() {
		
		return manipulatorStick;
		
	}

//    private TwilightTrigger isDriving = new TwilightTrigger(driverStick);


    public OI(){
		
		/*if(Math.abs(getDriverStick().getRawAxis(1)) > 0.05 || Math.abs(getDriverStick().getRawAxis(5)) > 0.05) {
			new DriveWithXBox();
		}
		*/
		//System.out.println(driverStick);
		//isDriving.whileActive(new DriveWithXBox());

		startCurvatureDrive.whenPressed(new DriveWithXBox());
		
		turnRight90.whenPressed(new TurnToAngle(90));
		turnLeft90.whenPressed(new TurnToAngle(-90));

        //velocityMotorButton.whileHeld(new VelocityMotorCommand(6000));
        //velocityMotorButtonStop.whenPressed(new stopVelocityMotorCommand());

        //stickInPlace.whenPressed(new StayInPlace(0.25));
        stickInPlace.whenPressed(new DriveToDistance(0));
		//testCommandButton.whenPressed(new TestCommandGroup());
        //testCommandButton.whenPressed(new DriveToDistanceTestInput(Converter.degreesTurnToEncTicks(800), 0.0));
        testCommandButton.whenPressed(new MStartRSwitch());

        controlShooter.whenPressed(new ControlShooter());
        controlIntake.whenPressed(new ControlIntake());

		//testCommandButton.whenPressed(new PathReader("/home/lvuser/paths/MStart/MStartRSwitch_left_detailed.csv", "/home/lvuser/paths/MStart/MStartRSwitch_right_detailed.csv", 0.00092));
        //testCommandButton.whenPressed(new PathReader("/home/lvuser/paths/LStart/TestTurn_left_detailed.csv", "/home/lvuser/paths/LStart/TestTurn_right_detailed.csv"));
       // testCommandButton.whenPressed(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
       //testCommandButton.whenPressed(new VelocityTester());

	}

	
	
	
}
