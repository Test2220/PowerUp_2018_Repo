package frc.team2220.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class CubeTracker extends InstantCommand{

    private double angleError;
    private double angleDividend = 1/27.0;

    public CubeTracker(){
        requires(Robot.twilightDrive);
    }

    protected void initialize() {
        Robot.twilightDrive.changeToPercentVBus();
    }

    protected void execute() {
        angleError = Robot.limelight.getTX();
        double turn = angleError * angleDividend;

        double yVal = Robot.oi.getDriverStick().getRawAxis(1);

        Robot.twilightDrive.curvatureDrive(-yVal, turn);

    }

}
