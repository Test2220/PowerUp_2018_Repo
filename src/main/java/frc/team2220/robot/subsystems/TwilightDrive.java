package frc.team2220.robot.subsystems;

import frc.team2220.robot.RobotMap;
//import frc.team2220.robot.commands.drive.DriveWithXBox;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import jaci.pathfinder.Pathfinder;
//import jaci.pathfinder.Trajectory;
//import jaci.pathfinder.Pathfinder;
//import jaci.pathfinder.Trajectory;
import frc.team2220.robot.utils.*;

@SuppressWarnings("deprecation")

public class TwilightDrive extends Subsystem{
	
	public CANTalon lDriveMaster;
	public CANTalon lDriveSlave;
	public CANTalon rDriveMaster;
	public CANTalon rDriveSlave;

	private static TwilightDrive instance_ = new TwilightDrive();
	
	
	public AHRS navX;
	
	public static FeedbackDevice QuadEncoder;
	
	
	double ticksPerRev = 1440;
	//double cyclesPerRev = 360;

	int  maxRPMl = 1460;
	int  maxRPMr = 1053;
	
	double pLeft= Converter.getInstance().errorToPGain(8159, 23); //LEFT SIDE
	double iLeft = 0.000;
	double dLeft = 10 * pLeft;
	double fLeft = Converter.getInstance().maxRPMToFGain(maxRPMl); 
	int  iZoneLeft = 50;

	
	double pRight = Converter.getInstance().errorToPGain(10573, 35); //RIGHT SIDE
	double iRight = 0.0000;
	double dRight = 0;
	double fRight = Converter.getInstance().maxRPMToFGain(maxRPMr);
	int  iZoneRight = 50;
	 
	double accel = maxRPMl * 0.75;
	double cruise = maxRPMl * 0.75;
	
	public final static int CLOSEDLOOPERROR = 40; 
	
	public static double rDriveMotorSetpoint = 0;
	public static double lDriveMotorSetpoint = 0;
	
	public DifferentialDrive TwilightDrive;
	
	
	
	public static TwilightDrive getInstance()
	{
		return instance_;
	}

	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new DriveWithXBox());
	
	}
	

	public TwilightDrive() {
		
		//Differential Drive Initialize
		
		
		//Basic NavX and Drivetrain setup
		navX = new AHRS(SPI.Port.kMXP);
		
		lDriveMaster = new CANTalon(RobotMap.LEFTMASTER);
		lDriveSlave  = new CANTalon(RobotMap.LEFTSLAVE);
		
		rDriveMaster = new CANTalon(RobotMap.RIGHTMASTER);
		rDriveSlave  = new CANTalon(RobotMap.RIGHTSLAVE);
		
		lDriveSlave.changeControlMode(TalonControlMode.Follower);
		lDriveSlave.set(lDriveMaster.getDeviceID());
		
		rDriveSlave.changeControlMode(TalonControlMode.Follower);
		rDriveSlave.set(rDriveMaster.getDeviceID());
		
		lDriveMaster.setInverted(false); //TODO Find out Correct Vals
		rDriveMaster.setInverted(false); //TODO Find out Correct Vals

		lDriveMaster.reverseOutput(false);
		rDriveMaster.reverseOutput(false);

		/* FOR 2018 PRACTICE BOT
		rDriveMaster.setInverted(true);
		lDriveMaster.reverseOutput(false);
		rDriveMaster.reverseOutput(true);
		rDriveSlave.reverseOutput(true);
		*/
		// Encoder Stuff
		
		lDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		lDriveMaster.reverseSensor(true);// TODO Check real boolean in Web Client
		lDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);
		
		rDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rDriveMaster.reverseSensor(false); // TODO Check real boolean in Web Client
		rDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR); 
		
		
		//Set PID and Motion Magic Vals
		
		lDriveMaster.setPID(pLeft, iLeft, dLeft);
		lDriveMaster.setIZone(iZoneLeft);
		lDriveMaster.setF(fLeft);
		lDriveMaster.setMotionMagicAcceleration(accel);
		lDriveMaster.setMotionMagicCruiseVelocity(cruise);
		
		rDriveMaster.setPID(pRight, iRight, dRight);
		rDriveMaster.setIZone(iZoneRight);
		rDriveMaster.setF(fRight);
		rDriveMaster.setMotionMagicAcceleration(accel);
		rDriveMaster.setMotionMagicCruiseVelocity(cruise);
		
		TwilightDrive = new DifferentialDrive(lDriveMaster, rDriveMaster);
		TwilightDrive.setSafetyEnabled(false);
		
	}
	
	

	//------------DRIVE SETS------------//

	public void driveSet(double leftVal, double rightVal){
			
		lDriveMaster.set(leftVal);
		rDriveMaster.set(rightVal);
		
	}
	
	public void curvatureDrive(double xVal, double zVal) {
		System.out.println(xVal);
		TwilightDrive.curvatureDrive(deadzone(xVal) * 0.25, deadzone(zVal) * 0.25, true);
		
	}
	
	public void rightSet(double position){
		rDriveMaster.set(position);
	}
	
	public void leftSet(double position){
		lDriveMaster.set(position);
	}

	public void stopMotors(){
		rDriveMaster.set(0);
		lDriveMaster.set(0);
	}
	
	
	//-----------------ENCODER STUFF------------------//

	public void resetEncoderPos(){
		lDriveMaster.setEncPosition(0);
		rDriveMaster.setEncPosition(0);
		lDriveMaster.setPosition(0);//TODO Figure out the difference between these two
		rDriveMaster.setPosition(0);
		System.out.printf("ZERO ENCODERS %d %d", lDriveMaster.getEncPosition(), rDriveMaster.getEncPosition());
	}
	
	public double getLPosition(){
		return lDriveMaster.getPosition();
	}
	
	public double getRPosition(){
		return rDriveMaster.getPosition();
	}
	
	public double getAvgPosition() {
		return (getLPosition() + getRPosition()) / 2;
	}
	
	//-------------------DRIVE TYPE MODIFIERS-------------------//
	
	public void changeToMotionMagic()
	{
		
		rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
		lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
	}
	
	public void changeToPercentVBus()
	{
		rDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
		lDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	
	public void changeToSpeed()
	{
		rDriveMaster.changeControlMode(TalonControlMode.Speed);
		lDriveMaster.changeControlMode(TalonControlMode.Speed);
	}

	
	//--------------------MOTION PROFILE SETTERS---------------//
	
	public void setBothCruiseVel(double x){
		setRCruiseVel(x);
		setLCruiseVel(x);
	}

	public void setRCruiseVel(double x){
		rDriveMaster.setMotionMagicCruiseVelocity(x);
	}

	public void setLCruiseVel(double x){
		lDriveMaster.setMotionMagicCruiseVelocity(x);
	}

	public void setBothAccel(double x){
		setRAccel(x);
		setLAccel(x);
	}

	public void setRAccel(double x){
		rDriveMaster.setMotionMagicAcceleration(x);
	}

	public void setLAccel(double x){
		lDriveMaster.setMotionMagicAcceleration(x);
	}

	//--------------SETPOINT CHECKERS------------------//

	public boolean hasHitLSetpoint(){
		return Math.abs(lDriveMaster.getClosedLoopError()) <= CLOSEDLOOPERROR;
	}
	
	public boolean hasHitRSetpoint(){
		return Math.abs(rDriveMaster.getClosedLoopError()) <= CLOSEDLOOPERROR;
	}

	public final int DONE_COUNT_MAX = 10;
	public  int currentDoneCount = 0;
	
	
	public boolean hasHitBothSetpoints(double checker) {
		
	/*	if (Math.abs(lDriveMaster.getClosedLoopError()) < CLOSEDLOOPERROR  && Math.abs(rDriveMaster.getClosedLoopError()) < CLOSEDLOOPERROR ) {
		return true;
		} else {
			return false;
		}*/
		
		if (hasHitLSetpoint() && hasHitRSetpoint())
			currentDoneCount++;
		else
			currentDoneCount = 0;
		if(currentDoneCount > DONE_COUNT_MAX)
		{
			currentDoneCount = 0;
			return true;
		}
		return false;
		
	}
	
	/*private final int DONE_COUNT_MAX = 10;
	private int currentDoneCount = 0;
	public boolean setpointDoneCounterReached()
	{
		if (hasHitRSetpoint() && hasHitLSetpoint())
			currentDoneCount++;
		else
			currentDoneCount = 0;
		if(currentDoneCount > DONE_COUNT_MAX)
		{
			currentDoneCount = 0;
			return true;
		}
		return false;
	}*/
	
	//Super Basic Deadzone function
	public double deadzone(double val) {
		double signum = Math.signum(val);
		return signum * Math.pow(Math.abs(val), 1.6);
  }
	
	//----------------MOTION PROFILE STUFF-------------------//
//	
//	public void  loadFromCSV(String leftPath, String rightPath) {
//		
//		File leftTraj = new File(leftPath);
//		File rightTraj = new File(rightPath);
//		
//		Trajectory leftTrajectory  = Pathfinder.readFromCSV(leftTraj);
//		Trajectory rightTrajectory = Pathfinder.readFromCSV(rightTraj);
//		
//	}
	
}
