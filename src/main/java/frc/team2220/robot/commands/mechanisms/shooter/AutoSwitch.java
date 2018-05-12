package frc.team2220.robot.commands.mechanisms.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class AutoSwitch extends Command{

    public AutoSwitch(int time) {
        super(time);
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
        if (timeSinceInitialized() > 0.3) {
            Robot.shooter.setCubePistonUp();
        }
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.shooter.setShooterDown();
    }

}
