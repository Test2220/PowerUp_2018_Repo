package frc.team2220.robot.commands.leftstart;

import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.ResetEncoderPos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.utils.Converter;


public class LStartLSwitch extends CommandGroup{

	
	
	public LStartLSwitch() {
		
		double tickCheck1 = Converter.getInstance().ftToEncTicks(14);
		double tickCheck2 = Converter.getInstance().ftToEncTicks(4.6);
		
		System.out.println(tickCheck1);
		System.out.println("LStartLSwitch");
		addSequential(new DriveToDistance(tickCheck1));
		//addSequential(new Delay(2));
		//addSequential(new TurnToAngle(90));
		//addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
        //addSequential(new TurnToAngle(90));
		//addSequential(new Delay(2));
		//Turn Right 90 degrees
		addSequential(new ResetEncoderPos());
		addSequential(new DriveToDistance(tickCheck1));
	
	}
	
}
