package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class RampControl extends InstantCommand{
    public enum Position {
        RETRACTED, EXTENDED;
    }

    private Position position;
    public RampControl(Position position) {
        this.position = position;
    }


    protected void execute() {
        switch (position){
            case EXTENDED:
                Robot.intake.setRampUp();
                break;
            case RETRACTED:
                Robot.intake.setRampDown();
                break;

        }
    }


}
