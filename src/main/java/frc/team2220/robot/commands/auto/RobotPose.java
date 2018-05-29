package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Converter;

public class RobotPose {

    private Notifier notifier;

    private double prev_x;
    private double prev_y;

    private double prev_dist;

    public RobotPose() {
        notifier = new Notifier(() -> {
            double currentAngle = Robot.twilightDrive.navX.getAngle();
            double curentDistance = Robot.twilightDrive.getAvgPosition() - prev_dist;

            prev_dist = Robot.twilightDrive.getAvgPosition();

            double x = curentDistance * Math.sin(Math.toRadians(currentAngle));
            double y = curentDistance * Math.cos(Math.toRadians(currentAngle));

            prev_x += x;
            prev_y += y;
            SmartDashboard.putNumber("Current X", Converter.encTicksToIn(prev_x));
            SmartDashboard.putNumber("Current Y", Converter.encTicksToIn(prev_y));

        });
    }

    public void startNotifier(double period) {
        Robot.twilightDrive.resetEncoderPos();
        Robot.twilightDrive.navX.reset();
        notifier.startPeriodic(period);
    }


}
