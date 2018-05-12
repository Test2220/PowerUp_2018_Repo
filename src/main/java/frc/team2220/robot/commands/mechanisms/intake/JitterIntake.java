package frc.team2220.robot.commands.mechanisms.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;

public class JitterIntake extends Command{


    public JitterIntake(int time){
        super(time);
        requires(Robot.intake);
    }

    protected void initialize() {

    }

    protected void execute() {
        if (timeSinceInitialized() < 0.4) {
            Robot.intake.spinEntireIntake(-0.75);
        } else {
            if (timeSinceInitialized() > 0.4 && timeSinceInitialized() < 0.5){
                Robot.intake.spinEntireIntake(0.4);
            } else {
                if (timeSinceInitialized() > 0.6 && timeSinceInitialized() < 1) {
                    Robot.intake.spinBothTransfer(-0.75);
                } else {
                    if (timeSinceInitialized() > 1 && timeSinceInitialized() < 1.2) {
                        Robot.intake.spinEntireIntake(0.4);
                    } else {
                        Robot.intake.spinEntireIntake(-0.75);
                    }
                }
            }
        }


    }


    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
