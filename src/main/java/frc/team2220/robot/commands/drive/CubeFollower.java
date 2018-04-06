package frc.team2220.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class CubeFollower extends Command{

    private Command LimelightConfig;
    private double turnSensitivty = 0;
    private int cruiseVel = 200;
    private double angleError;

    public CubeFollower(double timeout, double turnSensitivty) {
        super(timeout);
        requires(Robot.twilightDrive);
        this.turnSensitivty = turnSensitivty;
    }

    protected void initialize() {
        Robot.twilightDrive.changeToVelocity();
        Robot.limelight.setLEDMode(Limelight.LED_MODE.ON);
        Robot.limelight.setPipeline(0);
        Robot.limelight.setCamMode(Limelight.CAM_MODE.VISION_PROCESSING);
//        LimelightConfig.start();
    }

    protected void execute() {
        angleError = Robot.limelight.getTX();
        double turn = angleError * turnSensitivty;
        SmartDashboard.putNumber("turn", turn);
        SmartDashboard.putNumber("Target Area", Robot.limelight.getTA());
        Robot.twilightDrive.driveSet(cruiseVel + turn, cruiseVel - turn);
    }

    protected void end() {
        LimelightConfig = new LimelightConfig(Limelight.LED_MODE.OFF);
        LimelightConfig.start();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

}
