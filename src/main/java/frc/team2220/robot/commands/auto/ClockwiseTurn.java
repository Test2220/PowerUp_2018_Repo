package frc.team2220.robot.commands.auto;

import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class ClockwiseTurn extends Command{
	@SuppressWarnings("deprecation")


	double targetTicks;
	
	public ClockwiseTurn(double targetTicks) {
		
		requires(Robot.twilightDrive);
		this.targetTicks = targetTicks;
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
        Robot.twilightDrive.lDriveMaster.setProfile(0);
        Robot.twilightDrive.rDriveMaster.setProfile(0);

		Robot.twilightDrive.resetEncoderPos();
		
		Robot.twilightDrive.setBothAccel(2500);
		Robot.twilightDrive.setBothCruiseVel(3150);
		Robot.twilightDrive.changeToMotionMagic();

		


		System.out.println("Initialized");
		System.out.println(targetTicks);
		
		Robot.twilightDrive.driveSet(targetTicks, -targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println(Robot.twilightDrive.rDriveMaster.getClosedLoopError());

		//System.out.println("CLOCKWISE TURN");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//System.out.println(Math.abs(Robot.twilightDrive.lDriveMaster.getPosition()) - targetTicks  < 30);
	//return Robot.twilightDrive.hasHitBothSetpoints(targetTicks);
		return Robot.twilightDrive.hasZeroBothVelocity(targetTicks);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("ENDED TURN TO ANGLE CLOCKWISE");
		//Robot.twilightDrive.currentDoneCount = 0;
    	Robot.twilightDrive.changeToPercentVBus();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	
	
	
	}

}
