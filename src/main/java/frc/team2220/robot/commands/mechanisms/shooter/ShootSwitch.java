package frc.team2220.robot.commands.mechanisms.shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class ShootSwitch extends InstantCommand {

    public ShootSwitch() {
        requires(Robot.shooter);
    }


    protected void initialize() {
        Robot.shooter.changeToPercentVBus();
        Robot.shooter.setShooterDown();
    }

    protected void execute() {
        Robot.oi.getDriverController().setRumble(0.1);
        Robot.shooter.spinAllMotors(0.24);
        Robot.intake.spinBothTransfer(-0.5);
    }


}
