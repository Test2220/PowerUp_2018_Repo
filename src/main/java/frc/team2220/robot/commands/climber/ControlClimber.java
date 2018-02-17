package frc.team2220.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ControlClimber extends InstantCommand{

    public ControlClimber() {
        requires(Robot.climber);
    }

    protected void execute() {
        double triggerValLeft = Robot.oi.getManipulatorStick().getRawAxis(2);
        double triggerValRight = Robot.oi.getManipulatorStick().getRawAxis(3);

        Robot.climber.spinFishingPole(triggerValLeft);
        Robot.climber.spinWinch(triggerValRight);

    }

}
