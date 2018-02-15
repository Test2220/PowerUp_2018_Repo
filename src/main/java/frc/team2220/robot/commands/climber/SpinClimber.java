package frc.team2220.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class SpinClimber extends InstantCommand{

    public SpinClimber() {
        requires(Robot.climber);
    }

    protected void execute() {
        Robot.climber.spinClimber();
    }

}
