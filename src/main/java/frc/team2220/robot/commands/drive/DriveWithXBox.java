package frc.team2220.robot.commands.drive;

import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithXBox extends Command{

	// Called just before this Command runs the first time
	
		public DriveWithXBox() {
			
			requires(Robot.twilightDrive);
			
		}
	
		@Override
		protected void initialize() {
			
			TwilightDrive.getInstance().changeToPercentVBus();
			
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			
			//Negated for appropriation
			
//			double leftSet = -twilightDrive.getInstance().deadzone(Robot.oi.getDriverStick().getRawAxis(1));
//			double rightSet = -twilightDrive.getInstance().deadzone(Robot.oi.getDriverStick().getRawAxis(5));
//			
//			Robot.DriveTrain.driveSet(leftSet, -rightSet);
			//System.out.println(twilightDrive.getInstance().getLPosition());
			//System.out.println(twilightDrive.getInstance().getRPosition());

			
				double xVal = Robot.oi.getDriverStick().getRawAxis(1);
				double zVal = Robot.oi.getDriverStick().getRawAxis(4);
				
				TwilightDrive.getInstance().curvatureDrive(-xVal, zVal);
	
			}
			
		

		// Called once after isFinished returns true
		@Override
		protected void end() {
			if (true) {
				
				
			}
		}
		

		@Override
		protected boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override
		protected void interrupted() {
			
			new DriveOff();
			
		}

}
