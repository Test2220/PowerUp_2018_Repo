package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class VelocityTester extends Command {

    public VelocityTester() {
        requires(Robot.twilightDrive);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.twilightDrive.lDriveMaster.setProfile(1);
        Robot.twilightDrive.rDriveMaster.setProfile(1);

        Robot.twilightDrive.changeToVelocity();

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.twilightDrive.driveSet(300, 300);


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
