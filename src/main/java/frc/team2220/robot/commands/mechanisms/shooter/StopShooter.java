package frc.team2220.robot.commands.mechanisms.shooter;

import com.mach.LightDrive.Color;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class StopShooter extends InstantCommand {

    public void StopShooter() {
        requires(Robot.shooter);
    }

    protected void execute() {
        Robot.shooter.spinAllMotors(0);
        Robot.oi.getDriverController().setRumble(0);
        Robot.shooter.setCubePistonDown();
        Robot.lightDriveCAN.SetColor(1, Color.TEAL);
        Robot.lightDriveCAN.SetColor(2, Color.TEAL);
        Robot.lightDriveCAN.Update();
    }

}
