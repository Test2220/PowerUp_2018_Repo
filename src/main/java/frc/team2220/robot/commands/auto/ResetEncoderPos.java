package frc.team2220.robot.commands.auto;

import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoderPos extends Command{

	public ResetEncoderPos() {
		
		requires(TwilightDrive.getInstance());
		
	}
	
protected void initialize() {
		
		TwilightDrive.getInstance().changeToPercentVBus();
		System.out.println("RAN ONCE");
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}
