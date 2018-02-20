package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class StopShooter extends InstantCommand {

    public void StopShooter() {
        requires(Robot.shooter);
    }

    protected void execute() {
        Robot.shooter.spinAllMotors(0);
    }

}
