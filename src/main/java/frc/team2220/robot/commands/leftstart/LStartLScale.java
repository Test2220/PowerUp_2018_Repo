package frc.team2220.robot.commands.leftstart;

import frc.team2220.robot.commands.auto.AntiClockwiseTurn;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;

public class LStartLScale extends CommandGroup{
	
	public LStartLScale() {
		//Division by 2 as Robot mid should be on target point
		double target1 = Converter.getInstance().inToEncTicks(324) - Converter.getInstance().inToEncTicks(Constants.frameLengthIn/2);
		double target2 = Converter.getInstance().inToEncTicks(12) - Converter.getInstance().inToEncTicks(Constants.frameLengthIn/2);
		
		addSequential(new DriveToDistance(target1));
		addSequential(new AntiClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
		addSequential(new DriveToDistance(target2));
		
	}

}
