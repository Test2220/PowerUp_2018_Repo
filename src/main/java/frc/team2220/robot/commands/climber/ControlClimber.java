package frc.team2220.robot.commands.climber;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ControlClimber extends InstantCommand {

    public ControlClimber() {
        requires(Robot.climber);
    }

    protected void execute() {
        double triggerValLeft = Robot.oi.getManipulatorController().getTrigger(GenericHID.Hand.kLeft);
        double triggerValRight = Robot.oi.getManipulatorController().getTrigger(GenericHID.Hand.kLeft);

        Robot.climber.spinFishingPole(triggerValLeft);
        Robot.climber.spinWinch(triggerValRight);

    }

}
