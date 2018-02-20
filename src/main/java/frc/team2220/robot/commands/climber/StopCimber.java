package frc.team2220.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class StopCimber extends InstantCommand {

    protected void execute() {
        Robot.climber.spinFishingPole(0);
    }

}
