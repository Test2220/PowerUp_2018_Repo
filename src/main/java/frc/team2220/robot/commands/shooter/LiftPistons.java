package frc.team2220.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class LiftPistons extends InstantCommand {

    public enum Position {
        RETRACTED, EXTENDED;
    }

    Position position;

    public LiftPistons(Position position) {
        this.position = position;
    }

    protected void execute() {
        switch (position) {
            case RETRACTED:
                Robot.shooter.setShooterDown();
                break;
            case EXTENDED:
                Robot.shooter.setShooterUp();
                break;
        }
    }

}
