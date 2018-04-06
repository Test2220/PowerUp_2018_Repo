package frc.team2220.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class VelocityShooter extends Command {

    public void VelocityShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        Robot.shooter.changeToVelocity();
    }

    protected void execute() {
        Robot.shooter.topLeft.set(300);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
