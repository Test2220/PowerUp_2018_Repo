package frc.team2220.robot.commands.mechanisms.climber;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ControlClimber extends InstantCommand {

    public ControlClimber() {
        requires(Robot.climber);
    }

    protected void initialize() {
    }

    protected void execute() {
        double triggerValLeft = Robot.oi.getManipulatorController().getTrigger(GenericHID.Hand.kLeft);
        double triggerValRight = Robot.oi.getManipulatorController().getTrigger(GenericHID.Hand.kRight);

        boolean isLeftBumper = Robot.oi.getManipulatorController().getLeftBumperBoolean();
        boolean isRightBumper = Robot.oi.getManipulatorController().getRightBumperBoolean();

//        double triggerValLeft = Robot.oi.getDriverController().getTrigger(GenericHID.Hand.kLeft);
//        double triggerValRight = Robot.oi.getDriverController().getTrigger(GenericHID.Hand.kRight);
//
//        boolean isLeftBumper = Robot.oi.getDriverController().getLeftBumperBoolean();
//        boolean isRightBumper = Robot.oi.getDriverController().getRightBumperBoolean();



        if (!isLeftBumper) {
            Robot.climber.spinFishingPole(triggerValLeft);
        } else {
            Robot.climber.spinFishingPole(-1);
        }

        if (!isRightBumper) {
            Robot.climber.spinWinch(-triggerValRight);
        } else {
            Robot.climber.spinWinch(1);
        }

    }

}
