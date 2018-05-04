package frc.team2220.robot.commands.mechanisms.vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class CubeFollower extends Command{

    private Command LimelightConfig;
    private double turnSensitivty = 20;
    private int cruiseVel = 400;
    private double angleError;

    public CubeFollower(double timeout, Limelight.LED_MODE led_mode, Limelight.CAM_MODE cam_mode) {
        super(timeout);
        requires(Robot.twilightDrive);
        LimelightConfig = new LimelightConfig(led_mode, cam_mode);
    }

    protected void initialize() {
        Robot.twilightDrive.changeToVelocity();
        LimelightConfig.start();
    }

    protected void execute() {
        angleError = Robot.limelight.getTX() - 8;
        double turn = angleError * turnSensitivty;
        SmartDashboard.putNumber("TURN", turn);
        Robot.twilightDrive.driveSet(cruiseVel + turn, cruiseVel - turn);
    }

    protected boolean isFinished() {
        return Robot.intake.isBlockHalfWayLoaded() || isTimedOut();
    }

}
