package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command{

	double targetTicks;
	
	public DriveToDistance(double targetTicks) {
		
		requires(TwilightDrive.getInstance());
		this.targetTicks = targetTicks;
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		TwilightDrive.getInstance().resetEncoderPos();
		TwilightDrive.getInstance().resetEncoderPos();

		TwilightDrive.getInstance().setBothAccel(700);
		TwilightDrive.getInstance().setBothCruiseVel(800);
		TwilightDrive.getInstance().changeToMotionMagic();

		
		
		System.out.println("Initialized");
		System.out.println(targetTicks);
		
		TwilightDrive.getInstance().driveSet(targetTicks, targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("EXPECTED POSITION", targetTicks);
		//System.out.println("RUNNING");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
	@SuppressWarnings("deprecation")
	@Override
	protected boolean isFinished() {
	System.out.println(TwilightDrive.getInstance().rDriveMaster.getClosedLoopError());
	return TwilightDrive.getInstance().hasHitBothSetpoints(targetTicks);
	
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("ENDED DRIVE TO DISTANCE ");
		TwilightDrive.getInstance().currentDoneCount = 0;

    	TwilightDrive.getInstance().changeToPercentVBus();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	
	
	
	}
}
