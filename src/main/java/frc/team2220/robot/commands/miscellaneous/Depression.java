package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class Depression extends Command {

    public Depression() {
        super(5);
        requires(Robot.twilightDrive);
    }

    protected void execute() {
        Robot.twilightDrive.driveSet(0.5, 0.5);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

}
