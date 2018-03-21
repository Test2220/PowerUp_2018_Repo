package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ShootScale extends InstantCommand {

    double speed = 0.7;

    public ShootScale() {
        requires(Robot.shooter);
    }

    public ShootScale(double speed) {
        requires(Robot.shooter);
        this.speed = speed;
    }


    public ShootScale(double speed, double timeout) {
        requires(Robot.shooter);
        this.speed = speed;
    }

    protected void initialize() {
        Robot.shooter.changeToPercentVBus();
        Robot.shooter.setShooterUp();
    }

    protected void execute() {
        Robot.oi.getDriverController().setRumble(0.1);
        Robot.shooter.spinAllMotors(speed);
    }

}
