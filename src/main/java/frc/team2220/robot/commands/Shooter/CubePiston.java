package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;

public class CubePiston extends InstantCommand {

    public enum Position {
        UP, DOWN;
    }

    private Position position;

    public CubePiston(Position position) {
        requires(Robot.shooter);
        this.position = position;
    }

    protected void execute() {

        switch (position) {

            case UP:
                Robot.shooter.setCubePistonUp();
                break;

            case DOWN:
                Robot.shooter.setCubePistonDown();
                break;
        }

    }

}
