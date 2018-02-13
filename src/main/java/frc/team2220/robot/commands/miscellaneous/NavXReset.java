package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class NavXReset extends InstantCommand{

    protected void execute() {
        Robot.twilightDrive.navX.zeroYaw();
    }

}
