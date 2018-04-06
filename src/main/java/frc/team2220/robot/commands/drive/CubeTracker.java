package frc.team2220.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class CubeTracker extends InstantCommand {

    private double angleError;
    private double angleDividend = 1 / 50.0;

    public CubeTracker() {
        requires(Robot.twilightDrive);
    }

    protected void initialize() {
        Robot.twilightDrive.changeToPercentVBus();
        Robot.limelight.setLEDMode(Limelight.LED_MODE.ON);
        Robot.limelight.setCamMode(Limelight.CAM_MODE.VISION_PROCESSING);
        Robot.limelight.setPipeline(0);
    }

    protected void execute() {
        angleError = Robot.limelight.getTX();
        double turn = angleError * angleDividend;

        double yVal = Robot.oi.getDriverStick().getRawAxis(1);

        Robot.twilightDrive.curvatureDrive(-yVal, turn);

    }

}
