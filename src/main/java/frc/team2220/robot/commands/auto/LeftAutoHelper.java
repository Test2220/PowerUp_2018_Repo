package frc.team2220.robot.commands.auto;

import frc.team2220.robot.Robot;
import frc.team2220.robot.commands.leftstart.LStartLScale;
import frc.team2220.robot.commands.leftstart.LStartLSwitch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import openrio.powerup.MatchData;

public class LeftAutoHelper extends InstantCommand{
	
	
	@Override
	protected void initialize() {
        //Robot.side = "LEFT";
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
		MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
        System.out.println("Ran once");

		if (switchSide == MatchData.OwnedSide.LEFT) {
			new LStartLSwitch();
			
		} else if (switchSide == MatchData.OwnedSide.RIGHT) {
				
				if (scaleSide == MatchData.OwnedSide.LEFT) {
					
					new LStartLScale();
					
				}

            new LStartLScale();


        }
			
		}


}
