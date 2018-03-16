package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class AutoIntake extends Command {
    private double speed;

    public AutoIntake(double speed) {
        this.speed = speed;
    }

    public AutoIntake(double speed, double timeout) {
        super(timeout);
        this.speed = speed;
    }


    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.intake.setRampUp();
        Robot.intake.setIntakePistonsExtend();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.intake.spinBothIntake(speed);
        Robot.intake.spinBothTransfer(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
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
