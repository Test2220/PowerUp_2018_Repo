package frc.team2220.robot.commands.auto;

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


    public double turnSensitivity;

    public PathEncoderFollower(String leftFile, String rightFile, double turnSensitivity) {
        requires(Robot.twilightDrive);
        leftTraj = Pathfinder.readFromCSV(new File(leftFile));
        rightTraj = Pathfinder.readFromCSV(new File(rightFile));

        leftFollow = new EncoderFollower(leftTraj);
        rightFollow = new EncoderFollower(rightTraj);

        this.turnSensitivity = turnSensitivity;
    }

    public PathEncoderFollower(String baseFilePath, double turnSensitivity) {
        this("/home/lvuser/paths/" + baseFilePath + "_left_detailed.csv", "/home/lvuser/paths/" + baseFilePath + "_right_detailed.csv", turnSensitivity);
        String leftFile = "/home/lvuser/paths/" + baseFilePath + "_left_detailed.csv";
        String rightFile = "/home/lvuser/paths/" + baseFilePath + "_right_detailed.csv";

        requires(Robot.twilightDrive);

    }


    @Override
    protected void initialize() {
        Robot.twilightDrive.changeToPercentVBus();

        leftFollow.configureEncoder(Robot.twilightDrive.getLPosition(), Constants.encTickPerRev, Constants.wheelCircumferenceIn/12);
        leftFollow.configurePIDVA(0.1, 0, 0, 1 / 10, 0);
        rightFollow.configureEncoder(Robot.twilightDrive.getRPosition(), Constants.encTickPerRev, Constants.wheelCircumferenceIn/12);
        rightFollow.configurePIDVA(0.1, 0, 0, 1 / 10, 0);


        startTime = Timer.getFPGATimestamp() * 1000.0;
//        Robot.twilightDrive.lDriveMaster.setProfile(1);
//        Robot.twilightDrive.rDriveMaster.setProfile(1);

        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();

    }

    @Override
    protected void execute() {
        index = ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 20));

        if (isFinished()) return;


        double leftSet = leftFollow.calculate(Robot.twilightDrive.getLPosition());
        double rightSet = rightFollow.calculate(Robot.twilightDrive.getRPosition());
        SmartDashboard.putNumber("RIGHT VELOCITY ENCODER FOLLOWER", Robot.twilightDrive.getRPosition());


        SmartDashboard.putNumber("Left Calculate", leftSet);
        SmartDashboard.putNumber("Right Calculate", rightSet);


        double gyro_heading = Robot.twilightDrive.navX.getAngle();// Assuming gyro angle is given in degrees
        double desired_heading = Pathfinder.r2d(leftTraj.segments[index].heading);
        double angle_difference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);// Make sure to bound this from -180 to 180, otherwise you will get super large values
        System.out.printf("Desired Heading = %03.2f ; Gyro Heading = %03.2f ; Angle Difference = %03.2f ; Turn Sensitivity = %.4f \n", desired_heading, gyro_heading, angle_difference, turnSensitivity);
        double turn = turnSensitivity * angle_difference;
        Robot.twilightDrive.driveSet(leftSet, rightSet);

    }

    @Override
    protected boolean isFinished() {
        return index + 1 >= leftTraj.segments.length || index + 1 >= rightTraj.segments.length;
    }

    protected void end() {
        Robot.twilightDrive.changeToPercentVBus();
    }

}
