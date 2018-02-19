package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2220.robot.Robot;

public class SpinBothTransfer extends Command{

    public SpinBothTransfer() {
        super(2);
    }

    public SpinBothTransfer(double timeout) {
        super(timeout);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.intake.spinBothTransfer(-0.5);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

}
