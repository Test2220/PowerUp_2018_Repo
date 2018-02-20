package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Converter;

public class StayInPlace extends Command {


    private double startTime;

    private double turnSensitivity;

    public StayInPlace(double turnSensitivity) {
        requires(Robot.twilightDrive);

        this.turnSensitivity = turnSensitivity;
    }

    @Override
    protected void initialize() {
        Robot.twilightDrive.resetEncoderPos();
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

        double gyro_heading = Robot.twilightDrive.navX.getAngle();    // Assuming gyro angle is given in degrees
        double desired_heading = 0;

        double angle_difference = desired_heading - gyro_heading;// Make sure to bound this from -180 to 180, otherwise you will get super large values

        double turn = turnSensitivity * angle_difference;
        Robot.twilightDrive.driveSet(Converter.ftPerSecondToNativeUnitsPer100Ms(turn), Converter.ftPerSecondToNativeUnitsPer100Ms(-turn));

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.twilightDrive.changeToPercentVBus();
    }

}

