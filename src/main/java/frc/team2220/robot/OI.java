/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

import frc.team2220.robot.commands.Shooter.CubePiston;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.drive.DriveWithXBox;

import frc.team2220.robot.commands.Intake.ControlIntake;

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
	Joystick manipulatorStick = new Joystick(1);

	//BUTTONS ON DRIVERSTICK
    Button startCurvatureDrive = new JoystickButton(driverStick, 8);
	Button turnRight90 = new JoystickButton(driverStick, 2);
	Button turnLeft90 = new JoystickButton(driverStick, 3);
    Button driverShoot = new JoystickButton(driverStick, 5);
    Button driverShootRetract = new JoystickButton(driverStick, 6);


    //BUTTONS ON MANIPULATOR STICK
	Button shootSwitch = new JoystickButton(manipulatorStick, 1);
    Button shootScale = new JoystickButton(manipulatorStick, 2);
    Button controlIntake = new JoystickButton(manipulatorStick, 6);




    public Joystick getDriverStick() {
		
		return driverStick;
		
	}

	public Joystick getManipulatorStick() {
		
		return manipulatorStick;
		
	}



    public OI(){

		startCurvatureDrive.whenPressed(new DriveWithXBox());
        turnRight90.whenPressed(new TurnToAngle(90));
        turnLeft90.whenPressed(new TurnToAngle(-90));
        driverShoot.whenPressed(new Shoot());
        //driverShoot.whenPressed(new CubePiston(CubePiston.Position.UP));
        //driverShootRetract.whenPressed(new CubePiston(CubePiston.Position.DOWN));


        shootSwitch.whenActive(new ShootSwitch());
        shootScale.whenActive(new ShootScale());
        controlIntake.whenPressed(new ControlIntake());




	}

	
	
	
}
