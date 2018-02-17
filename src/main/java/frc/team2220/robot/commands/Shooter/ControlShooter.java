package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Shooter;
import frc.team2220.robot.utils.Converter;

import static frc.team2220.robot.Robot.oi;
import static frc.team2220.robot.Robot.shooter;

public class ControlShooter extends Command{

    private double value;

    public ControlShooter(double value) {
        requires(Robot.shooter);
        this.value = value;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

        Robot.shooter.changeToPercentVBus();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.shooter.spinAllMotors(value);

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
