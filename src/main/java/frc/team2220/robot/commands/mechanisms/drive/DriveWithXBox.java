package frc.team2220.robot.commands.mechanisms.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class DriveWithXBox extends Command {

    public DriveWithXBox() {

        requires(Robot.twilightDrive);

    }

    @Override
    protected void initialize() {

        Robot.twilightDrive.changeToPercentVBus();

    }

    @Override
    protected void execute() {

        double xVal = Robot.oi.getDriverController().getYAxis(GenericHID.Hand.kLeft);
        double zVal = Robot.oi.getDriverController().getXAxis(GenericHID.Hand.kRight);


        Robot.twilightDrive.curvatureDrive(-xVal, zVal);

    }


    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {

        new DriveOff();

    }

}
