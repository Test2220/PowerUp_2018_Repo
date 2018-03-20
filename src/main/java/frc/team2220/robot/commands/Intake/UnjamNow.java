package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class UnjamNow extends InstantCommand{

    protected void execute() {
        Robot.intake.spinEntireIntake(0.8);
    }
}
