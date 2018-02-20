package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class ClockwiseTurn extends Command {
    @SuppressWarnings("deprecation")


    private double targetTicks;

    public ClockwiseTurn(double targetTicks) {
        requires(Robot.twilightDrive);
        this.targetTicks = targetTicks;
    }

    @Override
    protected void initialize() {
        Robot.twilightDrive.lDriveMaster.setProfile(0);
        Robot.twilightDrive.rDriveMaster.setProfile(0);

        Robot.twilightDrive.resetEncoderPos();

        Robot.twilightDrive.setBothAccel(2500);
        Robot.twilightDrive.setBothCruiseVel(3150);
        Robot.twilightDrive.changeToMotionMagic();

        Robot.twilightDrive.driveSet(targetTicks, -targetTicks);

    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return Robot.twilightDrive.hasZeroBothVelocity(targetTicks);
    }

    @Override
    protected void end() {
        Robot.twilightDrive.changeToPercentVBus();
    }

    @Override
    protected void interrupted() {
    }
}
