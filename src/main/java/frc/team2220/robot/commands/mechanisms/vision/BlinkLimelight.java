package frc.team2220.robot.commands.mechanisms.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class BlinkLimelight extends Command{

    public BlinkLimelight(int time) {
        super(time);
    }

    protected void execute() {
        Robot.limelight.setLEDMode(Limelight.LED_MODE.BLINKING);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.limelight.setLEDMode(Limelight.LED_MODE.OFF);
    }

}
