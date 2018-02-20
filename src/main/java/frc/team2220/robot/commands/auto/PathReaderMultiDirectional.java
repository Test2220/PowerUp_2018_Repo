package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Converter;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import java.io.File;

public class PathReaderMultiDirectional extends Command{

    private Trajectory left;
    private Trajectory right;

    private double startTime;
    private int index;

    private double absTurnSensitivityLeft;
    private double absTurnSensitivityRight;

    public PathReaderMultiDirectional(String leftFile, String rightFile, double absTurnSensitivityLeft, double absTurnSensitivityRight) {
        requires(Robot.twilightDrive);
        left = Pathfinder.readFromCSV(new File(leftFile));
        right = Pathfinder.readFromCSV(new File(rightFile));
        this.absTurnSensitivityLeft = absTurnSensitivityLeft;
        this.absTurnSensitivityRight = absTurnSensitivityRight;
    }

    @Override
    protected void initialize() {

        startTime = Timer.getFPGATimestamp() * 1000.0;
        Robot.twilightDrive.changeToVelocity();

        Robot.twilightDrive.lDriveMaster.setProfile(1);
        Robot.twilightDrive.rDriveMaster.setProfile(1);


        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();
        Robot.twilightDrive.navX.zeroYaw();

    }

    @Override
    protected void execute() {



        index = ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 10));

        if (isFinished()) return;
        double leftVelo = left.segments[index].velocity;
        double rightVelo = right.segments[index].velocity;

        double gyro_heading = Robot.twilightDrive.navX.getAngle();// Assuming gyro angle is given in degrees
        double desired_heading = Pathfinder.r2d(left.segments[index].heading);
        double angle_difference = (desired_heading - gyro_heading) % 180;// Make sure to bound this from -180 to 180, otherwise you will get super large values

        double turn = 0;
        if (Robot.twilightDrive.navX.getRate() < -0.05) {
             turn =  -absTurnSensitivityLeft * angle_difference;
        } else if (Robot.twilightDrive.navX.getRate() > 0.05 && left.segments.length/2 > index) {
             turn =  absTurnSensitivityRight * angle_difference;
        } else if (Robot.twilightDrive.navX.getRate() > 0.05 && left.segments.length/2 < index){
            turn = (0.02) * angle_difference;
        }

        Robot.twilightDrive.driveSet(-Converter.ftPerSecondToNativeUnitsPer100Ms((rightVelo - turn)), -Converter.ftPerSecondToNativeUnitsPer100Ms((leftVelo + turn)));

    }

    @Override
    protected boolean isFinished() {
        return index + 1 >= left.segments.length || index + 1 >= right.segments.length;
    }

    protected void end() {
        Robot.twilightDrive.changeToPercentVBus();
    }

}

