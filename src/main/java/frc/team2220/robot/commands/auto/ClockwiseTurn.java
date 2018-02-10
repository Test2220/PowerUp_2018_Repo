package frc.team2220.robot.commands.auto;

import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class ClockwiseTurn extends Command{
	@SuppressWarnings("deprecation")


	double targetTicks;
	
	public ClockwiseTurn(double targetTicks) {
		
		requires(TwilightDrive.getInstance());
		this.targetTicks = targetTicks;
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
        TwilightDrive.getInstance().lDriveMaster.setProfile(0);
        TwilightDrive.getInstance().rDriveMaster.setProfile(0);

		TwilightDrive.getInstance().resetEncoderPos();
		
		TwilightDrive.getInstance().setBothAccel(600);
		TwilightDrive.getInstance().setBothCruiseVel(1000);
		TwilightDrive.getInstance().changeToMotionMagic();

		


		System.out.println("Initialized");
		System.out.println(targetTicks);
		
		TwilightDrive.getInstance().driveSet(targetTicks, -targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println(TwilightDrive.getInstance().rDriveMaster.getClosedLoopError());

		//System.out.println("CLOCKWISE TURN");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//System.out.println(Math.abs(twilightDrive.getInstance().lDriveMaster.getPosition()) - targetTicks  < 30);
	//return TwilightDrive.getInstance().hasHitBothSetpoints(targetTicks);
		return TwilightDrive.getInstance().hasZeroBothVelocity();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("ENDED TURN TO ANGLE CLOCKWISE");
		//TwilightDrive.getInstance().currentDoneCount = 0;
    	TwilightDrive.getInstance().changeToPercentVBus();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	
	
	
	}

}
