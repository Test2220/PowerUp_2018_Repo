package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

@SuppressWarnings("deprecation")

public class DriveToDistance extends Command {

    private double targetTicks;
    private double maxSpeed = 3155;


    public DriveToDistance(double targetTicks) {
        requires(Robot.twilightDrive);
        this.targetTicks = targetTicks;
    }

    public DriveToDistance(double targetTicks, double timeout) {
        super(timeout);
        requires(Robot.twilightDrive);
        this.targetTicks = targetTicks;
    }


    public DriveToDistance(double targetTicks, double timeout, double maxSpeed) {
        super(timeout);
        requires(Robot.twilightDrive);
        this.targetTicks = targetTicks;
        this.maxSpeed = maxSpeed;
    }

    @Override
    protected void initialize() {
        Robot.twilightDrive.lDriveMaster.setProfile(0);
        Robot.twilightDrive.rDriveMaster.setProfile(0);

        Robot.twilightDrive.resetEncoderPos();
        Robot.twilightDrive.resetEncoderPos();

        Robot.twilightDrive.changeToMotionMagic();
        Robot.twilightDrive.setBothAccel(maxSpeed / (3155 / 2000));
        Robot.twilightDrive.setBothCruiseVel(maxSpeed);

        Robot.twilightDrive.driveSet(targetTicks, targetTicks);

    }

    @Override
    protected boolean isFinished() {
        return Robot.twilightDrive.hasZeroBothVelocity(targetTicks) || isTimedOut();
    }

    @Override
    protected void end() {
        System.out.println("FINISHED");
        Robot.twilightDrive.currentDoneCount = 0;
        Robot.twilightDrive.changeToPercentVBus();

    }

}
