package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class StopTransfer extends InstantCommand {

    protected void execute() {
        Robot.intake.spinBothTransfer(0);
    }

}
