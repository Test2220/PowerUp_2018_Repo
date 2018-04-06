package frc.team2220.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;


public class ControlShooter extends Command {

    private double value;
    private double startTime;

    //TODO THIS IS GARBAGE. DONT USE FPGA USE SUPER()

    public ControlShooter(double value) {
        requires(Robot.shooter);
        this.value = value;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startTime = Timer.getFPGATimestamp() * 1000;
        Robot.shooter.changeToPercentVBus();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.shooter.spinAllMotors(value);

    }

    protected boolean isFinished() {
        double difference = Timer.getFPGATimestamp() * 1000.0 - startTime;
        if (difference > 500) {
            return true;
        }
        return false;
    }

    // Make this return true when this Command no longer needs to run execute()

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.shooter.spinAllMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
