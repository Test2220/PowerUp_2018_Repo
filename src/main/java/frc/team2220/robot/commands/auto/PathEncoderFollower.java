package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

import java.io.File;

public class PathEncoderFollower extends Command {

    EncoderFollower leftFollow;
    EncoderFollower rightFollow;

    private Trajectory leftTraj;
    private Trajectory rightTraj;

    private double startTime;
    private int index;

    private int encTicksPerRev = 1440;

    Notifier notifier;

    public double turnSensitivity;

    public PathEncoderFollower(String leftFile, String rightFile, double turnSensitivity) {
        requires(Robot.twilightDrive);
        leftTraj = Pathfinder.readFromCSV(new File(leftFile));
        rightTraj = Pathfinder.readFromCSV(new File(rightFile));

        this.turnSensitivity = turnSensitivity;
    }



    public PathEncoderFollower(String baseFilePath, double turnSensitivity) {

        this("/home/lvuser/paths/" + baseFilePath + "_left_detailed.csv", "/home/lvuser/paths/" + baseFilePath + "_right_detailed.csv", turnSensitivity);
        requires(Robot.twilightDrive);

    }

    @Override
    protected void initialize() {
        leftFollow = new EncoderFollower(leftTraj);
        rightFollow = new EncoderFollower(rightTraj);

        Robot.twilightDrive.changeToPercentVBus();

        Robot.twilightDrive.resetEncoderPos();

        leftFollow.configureEncoder(Robot.twilightDrive.getLPosition(), encTicksPerRev, Constants.wheelDiameterIn/12);
        leftFollow.configurePIDVA(0, 0, 0, 1 / 15, 0);
        rightFollow.configureEncoder(Robot.twilightDrive.getRPosition(), encTicksPerRev, Constants.wheelDiameterIn/12);
        rightFollow.configurePIDVA(0, 0, 0, 1 / 15 , 0);

        startTime = Timer.getFPGATimestamp() * 1000.0;

        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
    }

    @Override
    protected void execute() {

        index = ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 10));

        SmartDashboard.putNumber("INDEX", index);

        if (isFinished()) return;

        double leftSet = leftFollow.calculate(Robot.twilightDrive.getLPosition());
        double rightSet = rightFollow.calculate(Robot.twilightDrive.getRPosition());

        SmartDashboard.putNumber("LEFT ENC POS", Robot.twilightDrive.getLEncPosition());
        SmartDashboard.putNumber("RIGHT ENC POS", Robot.twilightDrive.getREncPosition());


        SmartDashboard.putNumber("LEFT POS", Robot.twilightDrive.getLPosition());
        SmartDashboard.putNumber("RIGHT POS", Robot.twilightDrive.getRPosition());

        SmartDashboard.putNumber("LEFT CALCULATE", leftSet);
        SmartDashboard.putNumber("RIGHT CALCULATE", rightSet);

        SmartDashboard.putNumber("Segment Stuff", leftTraj.segments[index].x);

        SmartDashboard.putNumber("EXPECTED TICK COUNT", Converter.ftToEncTicks(leftTraj.segments[index].x));
        double gyro_heading = Robot.twilightDrive.navX.getAngle();// Assuming gyro angle is given in degrees
        double desired_heading = Pathfinder.r2d(leftTraj.segments[index].heading);
        double angle_difference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);// Make sure to bound this from -180 to 180, otherwise you will get super large values
        double turn = turnSensitivity * angle_difference;


        Robot.twilightDrive.driveSet(leftSet, -rightSet);
    }

    @Override
    protected boolean isFinished() {
        return index + 1 >= leftTraj.segments.length || index + 1 >= rightTraj.segments.length;
    }

    protected void end() {
        Robot.twilightDrive.changeToPercentVBus();
    }

}
