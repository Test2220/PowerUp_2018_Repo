package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;
import frc.team2220.robot.utils.Converter;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import java.io.File;

public class StayInPlace extends Command{


    private Trajectory left;
    private Trajectory right;


    private double startTime;
    private int index;

    public double turnSensitivity;

    public StayInPlace(double turnSensitivity) {
        requires(Robot.twilightDrive);

        this.turnSensitivity = turnSensitivity;
    }

    @Override
    protected void initialize() {
        TwilightDrive.getInstance().resetEncoderPos();
        startTime = Timer.getFPGATimestamp() * 1000.0;
        TwilightDrive.getInstance().changeToVelocity();

        TwilightDrive.getInstance().lDriveMaster.setProfile(1);
        TwilightDrive.getInstance().rDriveMaster.setProfile(1);


        TwilightDrive.getInstance().navX.zeroYaw();
        TwilightDrive.getInstance().navX.zeroYaw();
        TwilightDrive.getInstance().navX.zeroYaw();
        TwilightDrive.getInstance().navX.zeroYaw();

    }

    @Override
    protected void execute() {
        index = ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 10));

        double gyro_heading = TwilightDrive.getInstance().navX.getAngle();    // Assuming gyro angle is given in degrees
        double desired_heading = 0;

        double angle_difference = desired_heading - gyro_heading;// Make sure to bound this from -180 to 180, otherwise you will get super large values


        double turn =  turnSensitivity * angle_difference;
        System.out.println("TURN" + turn);
        TwilightDrive.getInstance().driveSet(Converter.ftPerSecondToNativeUnitsPer100Ms(turn), Converter.ftPerSecondToNativeUnitsPer100Ms(-turn));




    }

    @Override
    protected boolean isFinished() {

        //eturn index + 2 >= left.segments.length;
        return false;

    }

    protected void end() {
        TwilightDrive.getInstance().changeToPercentVBus();
    }

}

