package frc.team2220.robot.commands.mechanisms.vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class CubeFollower extends Command{

    private Command LimelightConfig;
    private double turnSensitivty = 20;
    private int cruiseVel = 400;
    private double angleError;

    double startTime;

    public CubeFollower(double timeout, Limelight.LED_MODE led_mode, Limelight.CAM_MODE cam_mode) {
        super(timeout);
        requires(Robot.twilightDrive);
        LimelightConfig = new LimelightConfig(led_mode, cam_mode);
    }

    protected void initialize() {
        Robot.twilightDrive.changeToVelocity();
        LimelightConfig.start();
        Robot.limelight.setPipeline(0);
        startTime = Timer.getFPGATimestamp();

    }

    protected void execute() {
        angleError = Robot.limelight.getTX();
        SmartDashboard.putNumber("angleError", Robot.limelight.getTX());
        double turn = angleError * 50;

        if(Robot.limelight.getTA() < 34) {
            Robot.twilightDrive.driveSet(cruiseVel + turn, cruiseVel - turn);
        } else {
            Robot.twilightDrive.driveSet(cruiseVel, cruiseVel);
        }
    }

    protected boolean isFinished() {
        return isTimedOut() || Robot.intake.isBlockHalfWayLoaded();
    }

}
