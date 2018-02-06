package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

public class VelocityTester extends Command{

    public VelocityTester() {
        requires(Robot.twilightDrive);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        TwilightDrive.getInstance().lDriveMaster.setProfile(1);
        TwilightDrive.getInstance().rDriveMaster.setProfile(1);

        TwilightDrive.getInstance().changeToVelocity();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        TwilightDrive.getInstance().driveSet(300, 300);
        SmartDashboard.putNumber("LEFT MOTOR ERROR", TwilightDrive.getInstance().lDriveMaster.getClosedLoopError());
        SmartDashboard.putNumber("RIGHT MOTOR ERROR", TwilightDrive.getInstance().rDriveMaster.getClosedLoopError());

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
