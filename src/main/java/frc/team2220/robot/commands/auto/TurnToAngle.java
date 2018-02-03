package frc.team2220.robot.commands.auto;

import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command{
	

    
    double targetAngle;
    PIDController turnPIDController;
    
    double kP;
    double kI;
    double kD;
    
    public class Output implements PIDOutput {
        @Override
        public void pidWrite(double output) {
            
        	// Check if the output is in the deadzone
        	if (Math.abs(output) <= .175) {
        		
        		// If we need to turn right...
        		if (output > 0) {
        			TwilightDrive.getInstance().leftSet(Math.abs(output));
        		}
        		
        		// If we need to turn left...
        		if (output < 0) {
        			TwilightDrive.getInstance().rightSet(Math.abs(output));
        		}
        		
        	}
        	
        	// If the output isn't in the deadzone
        	else {
        		
        		// Scale down the output
        		output = output * .5;
            
            	// Set the speeds
	        	TwilightDrive.getInstance().rightSet(output);
	            TwilightDrive.getInstance().leftSet(output);
	            
            }
        }
    }
    
    public TurnToAngle(double angle) {
    	
        requires(Robot.twilightDrive);

        this.targetAngle = angle;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	TwilightDrive.getInstance().changeToPercentVBus();
    	TwilightDrive.getInstance().navX.reset();
        
        // Dev
        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn started!");
        System.out.println("Target angle: " + targetAngle);
        
        // Reset
        //twilightDrive.getInstance().navX.reset();
        TwilightDrive.getInstance().resetEncoderPos();
        // Start the PID Controller
        turnPIDController = new PIDController(0.05, 0.0, 0.001, TwilightDrive.getInstance().navX, new Output());
        turnPIDController.setSetpoint(targetAngle);
        turnPIDController.setAbsoluteTolerance(1.3);
        turnPIDController.enable();
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
        // Dev
        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn running!");
        System.out.println("Angle: " + TwilightDrive.getInstance().navX.getAngle());
        System.out.println("Error: " + turnPIDController.getError());
        System.out.println("Output: " + turnPIDController.get());
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
        return turnPIDController.onTarget();
        
    }

    // Called once after isFinished returns true
    protected void end() {
        
        // Dev
        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn ended!");
        System.out.println("Error: " + turnPIDController.getError());
        
        // Stop the motors
        TwilightDrive.getInstance().rightSet(0);
        TwilightDrive.getInstance().leftSet(0);
        
        // Reset the PID Controller
        turnPIDController.disable();
        turnPIDController.reset();
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
        // Dev
        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn interrupted!");
        System.out.println("Error: " + turnPIDController.getError());
        
        // Reset the PID Controller
        turnPIDController.disable();
        turnPIDController.reset();
        
}

}
