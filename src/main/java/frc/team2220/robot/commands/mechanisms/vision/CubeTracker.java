package frc.team2220.robot.commands.mechanisms.vision;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class CubeTracker extends InstantCommand {

    private double angleError;
    private double angleDividend = 0.1;

    public CubeTracker() {
        requires(Robot.twilightDrive);
    }

    protected void initialize() {
        Robot.twilightDrive.changeToPercentVBus();
        Robot.limelight.setLEDMode(Limelight.LED_MODE.OFF);
        Robot.limelight.setCamMode(Limelight.CAM_MODE.VISION_PROCESSING);
        Robot.limelight.setPipeline(0);
    }

    protected void execute() {

        angleError = Robot.limelight.getTX() - 8;
        SmartDashboard.putNumber("ANGLE ERROR", angleError);
        double turn = angleError * angleDividend;
        double yVal = Robot.oi.getDriverController().getYAxis(GenericHID.Hand.kLeft);

        SmartDashboard.putNumber("TURN", turn);

        Robot.twilightDrive.curvatureDrive(-yVal, turn);

    }

}
