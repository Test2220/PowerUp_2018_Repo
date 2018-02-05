/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.drive.DriveWithXBox;
import frc.team2220.robot.commands.miscellaneous.VelocityMotorCommand;
import frc.team2220.robot.commands.miscellaneous.VelocityTester;
import frc.team2220.robot.commands.miscellaneous.stopVelocityMotorCommand;
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
	Joystick climberStick = new Joystick(1);
	
	//Tank Drives
	Button startCurvatureDrive = new JoystickButton(driverStick, 8);
	
	// Triggers
	
	//Buttons
	Button turnRight90 = new JoystickButton(driverStick, 2);
	Button turnLeft90 = new JoystickButton(driverStick, 3);

	Button testCommandButton = new JoystickButton(driverStick, 4);
	Button velocityMotorButton = new JoystickButton(driverStick, 5);

    Button velocityMotorButtonStop = new JoystickButton(driverStick, 6);



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

        velocityMotorButton.whileHeld(new VelocityMotorCommand(6000));
        velocityMotorButtonStop.whenPressed(new stopVelocityMotorCommand());

		testCommandButton.whenPressed(new TestCommandGroup());
		//testCommandButton.whenPressed(new PathFollow());
       // testCommandButton.whenPressed(new ClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
       // testCommandButton.whenPressed(new VelocityTester());

	}

	
	
	
}
