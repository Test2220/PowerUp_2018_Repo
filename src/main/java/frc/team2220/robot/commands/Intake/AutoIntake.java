package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class AutoIntake extends Command {
    double value;

    public AutoIntake(double value) {
        this.value = value;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.intake.setRampUp();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.intake.spinBothIntake(value);
        Robot.intake.spinBothTransfer(value);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
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

}
