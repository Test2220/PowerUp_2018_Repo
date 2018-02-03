/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;



public class ExampleSubsystem extends PIDSubsystem {

double p;
double i;
double d;
double period;
double f;

	public ExampleSubsystem(double p, double i, double d, double period, double f) {
		super(p, i, d, period, f);
		// TODO Auto-generated constructor stub
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}
