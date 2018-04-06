package frc.team2220.robot.commands.shooter;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class StopShooter extends InstantCommand {

    public void StopShooter() {
        requires(Robot.shooter);
    }

    protected void execute() {
        Robot.shooter.spinAllMotors(0);
        Robot.oi.getDriverStick().setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        Robot.oi.getDriverStick().setRumble(GenericHID.RumbleType.kRightRumble, 0);
    }

}
