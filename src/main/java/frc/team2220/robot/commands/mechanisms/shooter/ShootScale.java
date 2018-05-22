package frc.team2220.robot.commands.mechanisms.shooter;

import com.mach.LightDrive.Color;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ShootScale extends InstantCommand {

    double speed = 0.7;

    public ShootScale() {
        requires(Robot.shooter);
    }

    public ShootScale(double speed) {
        requires(Robot.shooter);
        this.speed = speed;
    }


    protected void initialize() {
        Robot.shooter.changeToPercentVBus();
    }

    protected void execute() {
        Robot.lightDriveCAN.SetColor(1, Color.YELLOW);
        Robot.lightDriveCAN.SetColor(2, Color.YELLOW);
        Robot.lightDriveCAN.Update();
        if(Robot.shooter.isShooterUp()) {
            Robot.oi.getDriverController().setRumble(0.1);
            Robot.shooter.spinAllMotors(speed);
        }
        if(!Robot.shooter.isShooterUp()){
            Robot.shooter.setShooterUp();

        }

    }

}
