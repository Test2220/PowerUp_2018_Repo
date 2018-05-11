package frc.team2220.robot.commands.mechanisms.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class JitterIntake extends Command{

    public JitterIntake(int time){
        super(time);
        requires(Robot.intake);
    }

    protected void execute() {

    }


    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
