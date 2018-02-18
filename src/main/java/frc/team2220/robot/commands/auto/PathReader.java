package frc.team2220.robot.commands.auto;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.RobotMap;
import frc.team2220.robot.subsystems.TwilightDrive;
import frc.team2220.robot.utils.Converter;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import java.io.File;

public class PathReader extends Command{

    public CANTalon lDriveMaster;
    public CANTalon lDriveSlave;
    public CANTalon rDriveMaster;
    public CANTalon rDriveSlave;

        private Trajectory left;
        private Trajectory right;

        private double startTime;
        private int index;


        public double turnSensitivity;

        public PathReader(String leftFile, String rightFile, double turnSensitivity) {
            requires(Robot.twilightDrive);
            left = Pathfinder.readFromCSV(new File(leftFile));
            right = Pathfinder.readFromCSV(new File(rightFile));
            this.turnSensitivity = turnSensitivity;
        }

        @Override
        protected void initialize() {
            System.out.println("PATH READER INIT ");
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

            double gyro_heading = Robot.twilightDrive.navX.getAngle();    // Assuming gyro angle is given in degrees
            double desired_heading = Pathfinder.r2d(left.segments[index].heading);

            double angle_difference = desired_heading - gyro_heading;// Make sure to bound this from -180 to 180, otherwise you will get super large values
            SmartDashboard.putNumber("Angle Difference", angle_difference);

            double turn =  turnSensitivity * angle_difference;
            Robot.twilightDrive.driveSet(Converter.ftPerSecondToNativeUnitsPer100Ms((leftVelo + turn)), Converter.ftPerSecondToNativeUnitsPer100Ms((rightVelo - turn)));

        }

        @Override
        protected boolean isFinished() {

            return index + 1 >= left.segments.length || index + 1 >= right.segments.length;
            //return false;
        }

        protected void end() {
            Robot.twilightDrive.changeToPercentVBus();
        }

    }

