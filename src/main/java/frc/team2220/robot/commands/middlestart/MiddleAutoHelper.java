package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import openrio.powerup.MatchData;

public class MiddleAutoHelper extends InstantCommand{

    private Command leftSwitch = new MStartLSwitch();
    private Command rightSwitch = new MStartRSwitch();

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {



		MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);

		if (switchSide == MatchData.OwnedSide.LEFT) {

		    leftSwitch.start();
			
		}else if (switchSide == MatchData.OwnedSide.RIGHT) {
			
			rightSwitch.start();
			
		}
		
	}


}
