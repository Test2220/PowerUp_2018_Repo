package frc.team2220.robot.commands.mechanisms.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class FishingPoleUp extends InstantCommand{

    public FishingPoleUp() {
        requires(Robot.climber);
    }

    protected void execute() {
        Robot.climber.spinFishingPole(1);
    }

}
