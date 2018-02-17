package frc.team2220.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ControlClimber extends InstantCommand{

    public ControlClimber() {
        requires(Robot.climber);
    }

    protected void execute() {
        double triggerVal = Robot.oi.getManipulatorStick().getRawAxis(2);
        Robot.climber.spinClimber(triggerVal);
    }

}
