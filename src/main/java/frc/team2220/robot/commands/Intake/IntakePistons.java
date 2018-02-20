package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class IntakePistons extends InstantCommand {


    public enum Position {
        EXTEND, RETRACT;
    }

    private Position position;

    public IntakePistons(Position position) {
        this.position = position;
    }

    protected void execute() {
        switch (position) {
            case EXTEND:
                Robot.intake.setIntakePistonsExtend();
                break;
            case RETRACT:
                Robot.intake.setIntakePistonsRetract();
                break;
        }
    }


}
