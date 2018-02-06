package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;

public class PathFollow extends Command{

    private Trajectory leftTraj;
    private Trajectory rightTraj;

    private double startTime;
    private int index;

    public PathFollow() {
        requires(Robot.twilightDrive);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        TwilightDrive.getInstance().lDriveMaster.setProfile(1);
        TwilightDrive.getInstance().rDriveMaster.setProfile(1);

        TwilightDrive.getInstance().resetEncoderPos();
        TwilightDrive.getInstance().changeToVelocity();

        TankModifier modifier = Robot.pathGen.getCenterToLeftMod();

        leftTraj = modifier.getLeftTrajectory();
        //leftTraj = Robot.pathGen.getTestLeftTraj();
        rightTraj = modifier.getRightTrajectory();

        startTime = Timer.getFPGATimestamp() * 1000.0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        index = ((int) Math.floor(((Timer.getFPGATimestamp() * 1000.0) - startTime) / 50));

        double leftVelo = leftTraj.segments[index].velocity;
        double rightVelo = rightTraj.segments[index].velocity;
        //double rightVelo = 0;
        TwilightDrive.getInstance().driveSet(leftVelo*200, rightVelo*200);
        System.out.println(leftVelo);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return index + 1 >= leftTraj.segments.length;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        TwilightDrive.getInstance().stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
