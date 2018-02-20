package frc.team2220.robot.commands.auto;

import frc.team2220.robot.Robot;
import frc.team2220.robot.subsystems.TwilightDrive;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoderPos extends Command {

    public ResetEncoderPos() {

        requires(Robot.twilightDrive);

    }

    protected void initialize() {

        Robot.twilightDrive.changeToPercentVBus();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
