package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class VelocityShooter extends Command{

    public void VelocityShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
        Robot.shooter.changeToVelocity();
    }

    protected void execute() {
        Robot.shooter.topLeft.set(300);
        System.out.println("TOP LEFT VELOCITY = " + Robot.shooter.topLeft.getEncVelocity());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
