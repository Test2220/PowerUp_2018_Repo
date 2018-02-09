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

public class PathReader extends Command{


        private Trajectory left;
        private Trajectory right;

        private double startTime;
        private int index;

        public PathReader(String leftFile, String rightFile) {
            requires(Robot.twilightDrive);
            left = Pathfinder.readFromCSV(new File(leftFile));
            right = Pathfinder.readFromCSV(new File(rightFile));
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


            double leftVelo = left.segments[index].velocity;
            double rightVelo = right.segments[index].velocity;

            double gyro_heading = TwilightDrive.getInstance().navX.getAngle();    // Assuming gyro angle is given in degrees
            double desired_heading = Pathfinder.r2d(left.segments[index].heading);

            double angle_difference = desired_heading - gyro_heading;// Make sure to bound this from -180 to 180, otherwise you will get super large values
            SmartDashboard.putNumber("Angle Difference", angle_difference);
            SmartDashboard.putNumber("Left Heading", left.segments[index].heading);

            double turn =  0.0015 * angle_difference;
            System.out.println("TURN" + turn);
            TwilightDrive.getInstance().driveSet(Converter.ftPerSecondToNativeUnitsPer100Ms(leftVelo + turn), Converter.ftPerSecondToNativeUnitsPer100Ms(rightVelo - turn));

            System.out.println("Left Velo" + leftVelo);



        }

        @Override
        protected boolean isFinished() {

            return index + 2 >= left.segments.length;

        }

        protected void end() {
            TwilightDrive.getInstance().changeToPercentVBus();
        }

    }

