package frc.team2220.robot.commands.drive;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveOff extends InstantCommand
{

	public DriveOff()
	{
		super();
		requires(Robot.twilightDrive);
	}

	// Called once when the command executes
	protected void initialize()
	{
		Robot.twilightDrive.changeToPercentVBus();
		Robot.twilightDrive.stopMotors();
		SmartDashboard.putBoolean("isRunningTeleMotion", false);
	}

}
