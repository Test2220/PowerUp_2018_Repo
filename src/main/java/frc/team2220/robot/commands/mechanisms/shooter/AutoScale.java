package frc.team2220.robot.commands.mechanisms.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class AutoScale extends Command{

    private double speed;

    public AutoScale(double speed,double timeout) {
        super(timeout);
        requires(Robot.shooter);
        this.speed = speed;
    }

    protected void initialize() {
        Robot.shooter.changeToPercentVBus();
        Robot.shooter.setShooterUp();
    }

    protected void execute() {
        Robot.shooter.spinAllMotors(speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.shooter.setShooterDown();
    }
}
