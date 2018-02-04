package frc.team2220.robot.commands.auto;

import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class AntiClockwiseTurn extends Command{
	


	double targetTicks;
	
	public AntiClockwiseTurn(double targetTicks) {
		
		requires(TwilightDrive.getInstance());
		this.targetTicks = targetTicks;
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		TwilightDrive.getInstance().resetEncoderPos();
		
		TwilightDrive.getInstance().setBothAccel(200);
		TwilightDrive.getInstance().setBothCruiseVel(400);
		TwilightDrive.getInstance().changeToMotionMagic();

		
		
		System.out.println("Initialized");
		System.out.println(targetTicks);
		
		TwilightDrive.getInstance().driveSet(-targetTicks, targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		//System.out.println("RUNNING");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
	@Override
	protected boolean isFinished() {
	
	return TwilightDrive.getInstance().hasZeroBothVelocity();
	
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("ENDED?");
    	TwilightDrive.getInstance().changeToPercentVBus();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	
	
	
	}

}
