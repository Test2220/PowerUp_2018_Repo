/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.drive.DriveWithXBox;
import frc.team2220.robot.commands.auto.TurnToAngle;
import frc.team2220.robot.triggers.TwilightTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	//Joysticks
	Joystick driverStick = new Joystick(0);
	Joystick climberStick = new Joystick(1);
	
	//Tank Drives
	Button tankDrive = new JoystickButton(driverStick, 5);
	Button autoTurnButton = new JoystickButton(driverStick, 11);
	Button startCurvatureDrive = new JoystickButton(driverStick, 8);
	
	// Triggers
	
	//Buttons
	Button turnRight90 = new JoystickButton(driverStick, 2);
	Button turnLeft90 = new JoystickButton(driverStick, 3);

	Button driveToDistance = new JoystickButton(driverStick, 4);

	
	
	public Joystick getDriverStick() { 
		
		return driverStick;
		
	}
	private TwilightTrigger isDriving = new TwilightTrigger(driverStick);

	public Joystick getClimberStick() {
		
		return climberStick;
		
	}
	
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
		driveToDistance.whenPressed(new DriveToDistance(10795));
	
		//driveToDistanceButton.whenPressed(new DriveStraightForDistance(finalTick));		
		//driveToDistanceButton.whenPressed(new DriveForDistanceGroup(finalTick));
		
	}

	
	
	
}
