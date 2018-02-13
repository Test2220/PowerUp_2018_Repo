package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command{

	double targetTicks;
	
	public DriveToDistance(double targetTicks) {
		
		requires(Robot.twilightDrive);
		this.targetTicks = targetTicks;
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	    Robot.twilightDrive.lDriveMaster.setProfile(0);
	    Robot.twilightDrive.rDriveMaster.setProfile(0);

		Robot.twilightDrive.resetEncoderPos();
		Robot.twilightDrive.resetEncoderPos();

		Robot.twilightDrive.setBothAccel(700);
		Robot.twilightDrive.setBothCruiseVel(800);
		Robot.twilightDrive.changeToMotionMagic();

		
		
		System.out.println("Initialized");
		System.out.println(targetTicks);
		
		Robot.twilightDrive.driveSet(targetTicks, targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("EXPECTED POSITION", targetTicks);
		SmartDashboard.putNumber("CURRENT POSITION", Robot.twilightDrive.getAvgPosition());
		//System.out.println("RUNNING");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
	@SuppressWarnings("deprecation")
	@Override
	protected boolean isFinished() {
	System.out.println(Robot.twilightDrive.rDriveMaster.getClosedLoopError());
	return Robot.twilightDrive.hasHitBothSetpoints(targetTicks);

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("ENDED DRIVE TO DISTANCE ");
		Robot.twilightDrive.currentDoneCount = 0;

    	Robot.twilightDrive.changeToPercentVBus();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	
	
	
	}
}
