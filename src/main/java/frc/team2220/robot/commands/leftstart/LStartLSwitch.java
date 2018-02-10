package frc.team2220.robot.commands.leftstart;

import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.ResetEncoderPos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;


public class LStartLSwitch extends CommandGroup{


	
	
	public LStartLSwitch() {
		
		double target1 = Converter.inToEncTicks(168) - Converter.inToEncTicks(Constants.frameLengthIn/2);
		double target2 = Converter.inToEncTicks(73.25) -  Converter.inToEncTicks(Constants.frameLengthIn/2);


		//System.out.println("LStartLSwitch");
		addSequential(new DriveToDistance(target1));

		addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));

		addSequential(new DriveToDistance(target2));
	
	}
	
}
