package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class AntiClockwiseTurn extends Command {


    double targetTicks;

    public AntiClockwiseTurn(double targetTicks) {

        requires(Robot.twilightDrive);
        this.targetTicks = targetTicks;

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.twilightDrive.lDriveMaster.setProfile(0);
        Robot.twilightDrive.rDriveMaster.setProfile(0);

        Robot.twilightDrive.resetEncoderPos();

        Robot.twilightDrive.setBothAccel(200);
        Robot.twilightDrive.setBothCruiseVel(400);
        Robot.twilightDrive.changeToMotionMagic();

        Robot.twilightDrive.driveSet(-targetTicks, targetTicks);

    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {

        return Robot.twilightDrive.hasZeroBothVelocity(targetTicks);

    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        System.out.println("ENDED?");
        Robot.twilightDrive.changeToPercentVBus();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {


    }

}
