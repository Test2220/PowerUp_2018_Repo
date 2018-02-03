package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.InstantCommand;
import openrio.powerup.MatchData;

public class RightAutoHelper extends InstantCommand{
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
		MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
		
		if (switchSide == MatchData.OwnedSide.LEFT) {
			
			
		}else if (switchSide == MatchData.OwnedSide.RIGHT) {
			
			
			
		}
		
	}



}
