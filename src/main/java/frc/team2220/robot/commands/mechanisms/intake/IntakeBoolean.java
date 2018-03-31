package frc.team2220.robot.commands.mechanisms.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class IntakeBoolean extends InstantCommand {

    boolean defaultCommandRun;

    public IntakeBoolean(boolean defaultCommandRun) {
        this.defaultCommandRun = defaultCommandRun;
    }

    protected void execute() {
        Robot.intake.defaultCommandRun = defaultCommandRun;
        Robot.intake.setRampDown();
    }

}
