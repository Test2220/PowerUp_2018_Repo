package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ShootScale extends InstantCommand {

    public void ShootScale() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        Robot.shooter.changeToPercentVBus();
        Robot.shooter.setShooterUp();
    }

    protected void execute() {
        Robot.oi.getDriverStick().setRumble(GenericHID.RumbleType.kLeftRumble, 0.1);
        Robot.oi.getDriverStick().setRumble(GenericHID.RumbleType.kRightRumble, 0.1);
        Robot.shooter.spinAllMotors(0.7);
    }

}
