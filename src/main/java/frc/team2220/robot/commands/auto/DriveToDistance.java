package frc.team2220.robot.commands.auto;

import frc.team2220.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("deprecation")

public class DriveToDistance extends Command {

    private double targetTicks;

    public DriveToDistance(double targetTicks) {
        requires(Robot.twilightDrive);
        this.targetTicks = targetTicks;
    }

    @Override
    protected void initialize() {
        Robot.twilightDrive.lDriveMaster.setProfile(0);
        Robot.twilightDrive.rDriveMaster.setProfile(0);

        Robot.twilightDrive.resetEncoderPos();
        Robot.twilightDrive.resetEncoderPos();

        Robot.twilightDrive.changeToMotionMagic();
        Robot.twilightDrive.setBothAccel(2000);
        Robot.twilightDrive.setBothCruiseVel(3155);

        Robot.twilightDrive.driveSet(targetTicks, targetTicks);

    }

    @Override
    protected boolean isFinished() {
        return Robot.twilightDrive.hasZeroBothVelocity(targetTicks);
    }

    @Override
    protected void end() {
        Robot.twilightDrive.currentDoneCount = 0;
        Robot.twilightDrive.changeToPercentVBus();

    }

}
