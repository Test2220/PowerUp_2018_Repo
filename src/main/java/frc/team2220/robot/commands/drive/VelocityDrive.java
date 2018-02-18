package frc.team2220.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Constants;

//-----------------------------DO NOT USE-------------------------------//
//Using speed control isn't worth it as Talons are in brake mode and it stops just as well using PID.

public class VelocityDrive extends Command{

    public VelocityDrive() {
        requires(Robot.twilightDrive);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.twilightDrive.changeToPercentVBus();
        Robot.twilightDrive.setPIDProfile(1);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//        double xVal = Robot.oi.getDriverStick().getRawAxis(1) * -Constants.maxDrivetrainVelocity;
//        double zVal = Robot.oi.getDriverStick().getRawAxis(5) * -Constants.maxDrivetrainVelocity;
//        System.out.println("Control Mode" + Robot.twilightDrive.lDriveMaster.getControlMode());
//        Robot.twilightDrive.driveSet(xVal, zVal);

        double xVal = Robot.oi.getDriverStick().getRawAxis(1);
        double zVal = Robot.oi.getDriverStick().getRawAxis(5);
        System.out.println("Control Mode" + Robot.twilightDrive.lDriveMaster.getControlMode());
        Robot.twilightDrive.driveSet(-xVal, zVal);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

}
