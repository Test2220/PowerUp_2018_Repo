package frc.team2220.robot.commands.miscellaneous;

import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class AntiClockwiseTurn extends Command{
	


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

		
		
		System.out.println("Initialized");
		System.out.println(targetTicks);
		
		Robot.twilightDrive.driveSet(-targetTicks, targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		//System.out.println("RUNNING");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
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
