package frc.team2220.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.Limelight;

public class LimelightConfig extends InstantCommand{

    private Limelight.LED_MODE led_mode;

    public LimelightConfig(Limelight.LED_MODE led_mode) {
        this.led_mode = led_mode;
    }

    protected void execute() {
        Robot.limelight.setLEDMode(led_mode);
    }

}
