package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ShootScale extends InstantCommand {

    public void ShootScale() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        Robot.shooter.setShooterUp();
    }

    protected void execute() {
        Robot.shooter.spinAllMotors(0.7);
    }

}
