package frc.team2220.robot.commands.mechanisms.climber;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;

public class ControlClimber extends InstantCommand {


    public ControlClimber() {
        requires(Robot.climber);
    }

    protected void initialize() {
        Robot.climber.fishingPole.setCurrentLimit(10);
    }

    protected void execute() {
        double triggerValLeft = Robot.oi.getManipulatorController().getTrigger(GenericHID.Hand.kLeft);
        double triggerValRight = Robot.oi.getManipulatorController().getTrigger(GenericHID.Hand.kRight);



        SmartDashboard.putNumber("TRIGGER VAL", triggerValLeft);

        if(triggerValLeft > triggerValRight) {
            Robot.climber.spinWinch(triggerValLeft * 10);
        } else {
            Robot.climber.spinWinch(-triggerValRight * 10);

        }
////        Robot.climber.spinFishingPole(-triggerValRight);
        System.out.println(Robot.climber.fishingPole.getOutputCurrent());



    }

}
