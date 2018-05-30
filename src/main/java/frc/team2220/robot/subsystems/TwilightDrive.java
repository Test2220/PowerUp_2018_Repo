package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team2220.robot.RobotMap;
import frc.team2220.robot.commands.mechanisms.drive.DriveWithXBox;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;

@SuppressWarnings("deprecation")

public class TwilightDrive extends Subsystem {

    public CANTalon lDriveMaster;
    public CANTalon lDriveSlave;
    public CANTalon rDriveMaster;
    public CANTalon rDriveSlave;

    public AHRS navX;

    double pLeft = Converter.errorToPGain(19556.9594071321 - 18442.0, 1.45); //LEFT SIDE
    double iLeft = 0.000;
    double dLeft = 10 * pLeft;
    double fLeft = Converter.maxVelToFGainCorrect(Constants.maxDrivetrainVelocity);
    int iZoneLeft = 50;


    double pRight = Converter.errorToPGain(19556.9594071321 - 18900.0, 0.8); //RIGHT SIDE
    double iRight = 0.0000;
    double dRight = 0;
    double fRight = Converter.maxVelToFGainCorrect(Constants.maxDrivetrainVelocity);
    int iZoneRight = 50;

    //------------VELOCITY STUFF-------------//

    double pLeft2 = Converter.errorToPGain(40, 0.0175); //LEFT SIDE
    double iLeft2 = 0.000;
    double dLeft2 = pLeft2 * 15;
    double fLeft2 = Converter.maxVelToFGainCorrect(Constants.maxDrivetrainVelocity);
    int iZoneLeft2 = 50;


    double pRight2 = Converter.errorToPGain(55, 0.018); //RIGHT SIDE
    double iRight2 = 0.0000;
    double dRight2 = 0;
    double fRight2 = Converter.maxVelToFGainCorrect(Constants.maxDrivetrainVelocity);
    int iZoneRight2 = 50;

    double accel = Constants.maxDrivetrainVelocity * 0.75;
    double cruise = Constants.maxDrivetrainVelocity * 0.75;

    public final static int CLOSEDLOOPERROR = 50;

    public static double rDriveMotorSetpoint = 0;
    public static double lDriveMotorSetpoint = 0;

    public DifferentialDrive TwilightDrive;

    private CANTalon.FeedbackDeviceStatus isLeftSensorPresent;
    private CANTalon.FeedbackDeviceStatus isRightSensorPresent;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithXBox());
    }


    public TwilightDrive() {

        //Differential Drive Initialize


        //Basic NavX and Drivetrain setup
        navX = new AHRS(SPI.Port.kMXP);

        lDriveMaster = new CANTalon(RobotMap.LEFTMASTER);
        lDriveSlave = new CANTalon(RobotMap.LEFTSLAVE);

        rDriveMaster = new CANTalon(RobotMap.RIGHTMASTER);
        rDriveSlave = new CANTalon(RobotMap.RIGHTSLAVE);

        lDriveSlave.changeControlMode(TalonControlMode.Follower);
        lDriveSlave.set(lDriveMaster.getDeviceID());

        rDriveSlave.changeControlMode(TalonControlMode.Follower);
        rDriveSlave.set(rDriveMaster.getDeviceID());

        lDriveMaster.enableBrakeMode(true);
        rDriveMaster.enableBrakeMode(true);
        lDriveSlave.enableBrakeMode(true);
        rDriveSlave.enableBrakeMode(true);

        rDriveMaster.setInverted(false);


        lDriveMaster.reverseOutput(false);
        rDriveMaster.reverseOutput(true);
        rDriveSlave.reverseOutput(true);

        // Encoder Stuff

        lDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        lDriveMaster.reverseSensor(true);// TODO Check real boolean in Web Client
        lDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);

        rDriveMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        rDriveMaster.reverseSensor(false); // TODO Check real boolean in Web Client
        rDriveMaster.setAllowableClosedLoopErr(CLOSEDLOOPERROR);

        isLeftSensorPresent = lDriveMaster.isSensorPresent(FeedbackDevice.QuadEncoder);
        isRightSensorPresent = rDriveMaster.isSensorPresent(FeedbackDevice.QuadEncoder);


        if (isLeftSensorPresent == CANTalon.FeedbackDeviceStatus.FeedbackStatusNotPresent) {
            DriverStation.reportError("LEFT ENCODER NOT PRESENT" + isLeftSensorPresent, false);
        }

        if (isRightSensorPresent == CANTalon.FeedbackDeviceStatus.FeedbackStatusNotPresent) {
            DriverStation.reportError("RIGHT ENCODER NOT PRESENT" + isRightSensorPresent, false);
        }

        //Set PID and Motion Magic Vals
        lDriveMaster.setPID(pLeft, iLeft, dLeft, fLeft, iZoneLeft, 0, 0);
        lDriveMaster.setMotionMagicAcceleration(accel);
        lDriveMaster.setMotionMagicCruiseVelocity(cruise);

        rDriveMaster.setPID(pRight, iRight, dRight, fRight, iZoneRight, 0, 0);
        rDriveMaster.setMotionMagicAcceleration(accel);
        rDriveMaster.setMotionMagicCruiseVelocity(cruise);

        lDriveMaster.setPID(pLeft2, iLeft2, dLeft2, fLeft2, iZoneLeft2, 0, 1);
        rDriveMaster.setPID(pRight2, iRight2, dRight2, fRight2, iZoneRight2, 0, 1);


        TwilightDrive = new DifferentialDrive(lDriveMaster, rDriveMaster);
        TwilightDrive.setSafetyEnabled(false);

    }

    public String getEncoderStatus() {

        if (isLeftSensorPresent == CANTalon.FeedbackDeviceStatus.FeedbackStatusNotPresent) {
            String error = "ERROR : LEFT ENCODER NOT PRESENT" + isLeftSensorPresent;
            return error;
        }

        if (isRightSensorPresent == CANTalon.FeedbackDeviceStatus.FeedbackStatusNotPresent) {
            String error = "ERROR : RIGHT ENCODER NOT PRESENT" + isRightSensorPresent;
            return error;
        }

        return "ALL ENCODERS FUNCTIONA";

    }



    //------------DRIVE SETS------------//

    public void driveSet(double leftVal, double rightVal) {

        lDriveMaster.set(leftVal);
        rDriveMaster.set(rightVal);

    }

    public void scaledDriveSet(double leftVal, double rightVal) {
        lDriveMaster.set(leftVal * Constants.drivetrainMultiplier);
        rDriveMaster.set(rightVal * Constants.drivetrainMultiplier);
//        lDriveMaster.set(leftVal * SmartDashboard.getNumber("multiplier", 0));
//        rDriveMaster.set(rightVal * SmartDashboard.getNumber("multiplier", 0));


    }

    public void curvatureDrive(double xVal, double zVal) {
        TwilightDrive.curvatureDrive(Converter.deadzone(xVal) * 0.5, Converter.deadzone(zVal) * 1, true);

    }

    public void rightSet(double position) {
        rDriveMaster.set(position);
    }

    public void leftSet(double position) {
        lDriveMaster.set(position);
    }

    public void stopMotors() {
        rDriveMaster.set(0);
        lDriveMaster.set(0);
    }


    //-----------------ENCODER STUFF------------------//

    public void resetEncoderPos() {
        lDriveMaster.setEncPosition(0);
        rDriveMaster.setEncPosition(0);
//		lDriveMaster.setPosition(0);//TODO Figure out the difference between these two
//		rDriveMaster.setPosition(0);
        System.out.printf("ZERO ENCODERS %d %d", lDriveMaster.getEncPosition(), rDriveMaster.getEncPosition());
    }

    public int getLPosition() {
        return ((int) lDriveMaster.getPosition());
    }

    public int getRPosition() {
        return ((int) rDriveMaster.getPosition());
    }

    public double getAvgPosition() {
        return (Math.abs(getLPosition()) + Math.abs(getRPosition())) / 2;
    }

    public int getLeftVelocity() {
        return lDriveMaster.getEncVelocity();
    }

    public int getRightVelocity() {
        return rDriveMaster.getEncVelocity();
    }


    //-------------------DRIVE TYPE MODIFIERS-------------------//

    public void changeToMotionMagic() {
        lDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
        rDriveMaster.changeControlMode(TalonControlMode.MotionMagic);
    }

    public void changeToVelocity() {
        lDriveMaster.changeControlMode(TalonControlMode.Speed);
        rDriveMaster.changeControlMode(TalonControlMode.Speed);
    }

    public void changeToPercentVBus() {
        lDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
        rDriveMaster.changeControlMode(TalonControlMode.PercentVbus);
    }

    public void setPIDProfile(int val) {
        lDriveMaster.setProfile(val);
        rDriveMaster.setProfile(val);
    }

    //--------------------MOTION PROFILE SETTERS---------------//

    public void setBothCruiseVel(double x) {
        setRCruiseVel(x);
        setLCruiseVel(x);
    }

    public void setRCruiseVel(double x) {
        rDriveMaster.setMotionMagicCruiseVelocity(x);
    }

    public void setLCruiseVel(double x) {
        lDriveMaster.setMotionMagicCruiseVelocity(x);
    }

    public void setBothAccel(double x) {
        setRAccel(x);
        setLAccel(x);
    }

    public void setRAccel(double x) {
        rDriveMaster.setMotionMagicAcceleration(x);
    }

    public void setLAccel(double x) {
        lDriveMaster.setMotionMagicAcceleration(x);
    }

    //--------------SETPOINT CHECKERS------------------//

    public boolean hasHitLSetpoint() {
        return Math.abs(lDriveMaster.getClosedLoopError()) <= CLOSEDLOOPERROR;
    }

    public boolean hasHitRSetpoint() {
        return Math.abs(rDriveMaster.getClosedLoopError()) <= CLOSEDLOOPERROR;
    }

    public boolean hasZeroLVelocity() {
        return Math.abs(lDriveMaster.getEncVelocity()) == 0;
    }

    public boolean hasZeroRVelocity() {
        return Math.abs(rDriveMaster.getEncVelocity()) == 0;
    }

    public final int DONE_COUNT_MAX = 1;
    public int currentDoneCount = 0;

    public boolean hasZeroBothVelocity(double targetTicks) {
        // System.out.println("TARGET TICKS - AVERAGE = " + (targetTicks - getAvgPosition()));
        System.out.println("TARGET CALC" + (Math.abs(targetTicks) - Math.abs(getAvgPosition())));
        if (Math.abs(targetTicks) - Math.abs(getAvgPosition()) < 400) {
            if (hasZeroLVelocity() && hasZeroRVelocity()) {
                currentDoneCount++;
            } else {
                currentDoneCount = 0;
            }
            if (currentDoneCount > DONE_COUNT_MAX) {
                currentDoneCount = 0;
                System.out.println("TRUE");
                return true;
            }
        }
        return false;
    }

    public boolean hasReachedTargetTicks(double targetTicks) {
        return targetTicks - getAvgPosition() < 100;
    }


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
        if (currentDoneCount > DONE_COUNT_MAX) {
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
