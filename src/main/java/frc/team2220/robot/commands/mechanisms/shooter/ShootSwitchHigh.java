package frc.team2220.robot.commands.mechanisms.shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ShootSwitchHigh extends InstantCommand {

    double speed = 0.3;

    public ShootSwitchHigh() {
        requires(Robot.shooter);
    }

    public ShootSwitchHigh(double speed) {
        requires(Robot.shooter);
        this.speed = speed;
    }


    public ShootSwitchHigh(double speed, double timeout) {
        requires(Robot.shooter);
        this.speed = speed;
    }

    protected void initialize() {
        Robot.shooter.changeToPercentVBus();
    }

    protected void execute() {
        Robot.oi.getDriverController().setRumble(0.1);
        if (Robot.shooter.isShooterUp()) {
            Robot.shooter.spinAllMotors(speed);
        }

        if (!Robot.shooter.isShooterUp()) {
            Robot.shooter.setShooterUp();
        }
    }

}
