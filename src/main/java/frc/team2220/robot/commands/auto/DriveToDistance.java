package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command{

	public double targetTicks;
	
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

        Robot.twilightDrive.changeToMotionMagic();
		Robot.twilightDrive.setBothAccel(2000);
		Robot.twilightDrive.setBothCruiseVel(3155);

		
		
		System.out.println("Initialized");
		System.out.println("targetTicks = " + targetTicks);
		
		Robot.twilightDrive.driveSet(targetTicks, targetTicks);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {


        //System.out.println("RUNNING");
		
	}

	// Make this return true when this sCommand no longer needs to run execute()
	@SuppressWarnings("deprecation")
	@Override
	protected boolean isFinished() {
	System.out.println("CLOSED LOOP ERROR" + Robot.twilightDrive.rDriveMaster.getClosedLoopError());
	return Robot.twilightDrive.hasZeroBothVelocity(targetTicks);
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
