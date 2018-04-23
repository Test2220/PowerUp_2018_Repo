package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Converter;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

import java.io.File;

public class MultiReversiblePathReader extends Command {


    private Trajectory left;
    private Trajectory right;

    private double startTime;
    private int index;


    private double turnSensitivity;

    public enum Direction {
        COLLECTOR_FIRST, SHOOTER_FIRST;
    }


    public enum CSVReadDirection {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP;
    }

    private Direction direction;
    private CSVReadDirection csvReadDirection;

    boolean resetNavX = true;


    public MultiReversiblePathReader(String leftFile, String rightFile, double turnSensitivity, Direction direction, CSVReadDirection csvReadDirection) {
        requires(Robot.twilightDrive);
        left = Pathfinder.readFromCSV(new File(leftFile));
        right = Pathfinder.readFromCSV(new File(rightFile));
        this.turnSensitivity = turnSensitivity;
        this.direction = direction;
        this.csvReadDirection = csvReadDirection;
    }

    public MultiReversiblePathReader(String baseFilePath, double turnSensitivity, Direction direction, CSVReadDirection csvReadDirection) {

        this("/home/lvuser/paths/" + baseFilePath + "_left_detailed.csv", "/home/lvuser/paths/" + baseFilePath + "_right_detailed.csv", turnSensitivity, direction, csvReadDirection);
        requires(Robot.twilightDrive);

    }

    public MultiReversiblePathReader(String baseFilePath, double turnSensitivity, Direction direction, CSVReadDirection csvReadDirection, boolean resetNavX) {

        this("/home/lvuser/paths/" + baseFilePath + "_left_detailed.csv", "/home/lvuser/paths/" + baseFilePath + "_right_detailed.csv", turnSensitivity, direction, csvReadDirection);
        requires(Robot.twilightDrive);
        this.resetNavX = resetNavX;
    }


    @Override
    protected void initialize() {

        startTime = Timer.getFPGATimestamp() * 1000.0;
        Robot.twilightDrive.changeToVelocity();

        Robot.twilightDrive.lDriveMaster.setProfile(1);
        Robot.twilightDrive.rDriveMaster.setProfile(1);

        if (resetNavX) {
            Robot.twilightDrive.navX.zeroYaw();
            Robot.twilightDrive.navX.zeroYaw();
            Robot.twilightDrive.navX.zeroYaw();
            Robot.twilightDrive.navX.zeroYaw();
        }


    }

    @Override
    protected void execute() {


        switch (csvReadDirection) {
            case TOP_TO_BOTTOM:
                index = ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 10));
                break;

            case BOTTOM_TO_TOP:
                index = left.segments.length - ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 10)) - 1;
                break;
        }

        if (isFinished()) return;

        double leftVelo = left.segments[index].velocity;
        double rightVelo = right.segments[index].velocity;

        SmartDashboard.putNumber("EXPECTED VELOCITY", left.segments[index].velocity);


        double gyro_heading = Robot.twilightDrive.navX.getAngle();// Assuming gyro angle is given in degrees
        double desired_heading = -Pathfinder.r2d(left.segments[index].heading);
        double angle_difference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);// Make sure to bound this from -180 to 180, otherwise you will get super large values
//        System.out.printf("Desired Heading = %03.2f ; Gyro Heading = %03.2f ; Angle Difference = %03.2f ; Turn Sensitivity = %.4f \n", desired_heading, gyro_heading, angle_difference, turnSensitivity);

        double turn = turnSensitivity * angle_difference;
        System.out.printf("Turn = %03.2f ; Left Velocity = %03.2f ; Right Velocity = %03.2f ; \n", turn, leftVelo, rightVelo);

        switch (direction) {
            case COLLECTOR_FIRST:
                Robot.twilightDrive.scaledDriveSet(Converter.ftPerSecondToNativeUnitsPer100Ms((leftVelo)) + turn, Converter.ftPerSecondToNativeUnitsPer100Ms((rightVelo)) - turn);
                break;

            case SHOOTER_FIRST:
                Robot.twilightDrive.scaledDriveSet(-Converter.ftPerSecondToNativeUnitsPer100Ms((rightVelo)) + turn, -Converter.ftPerSecondToNativeUnitsPer100Ms((leftVelo)) - turn);
                break;
        }

    }

    @Override
    protected boolean isFinished() {
        switch (csvReadDirection) {
            case TOP_TO_BOTTOM:
                return index + 1 >= left.segments.length || index + 1 >= right.segments.length;

            case BOTTOM_TO_TOP:
                return index - 1 <= 0;
        }
        return false;
    }

    protected void end() {
        Robot.twilightDrive.changeToPercentVBus();
    }

}

