package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.TurnToAngle;
import frc.team2220.robot.commands.shooter.Shoot;
import frc.team2220.robot.commands.shooter.ShootScale;
import frc.team2220.robot.utils.Converter;

public class WorstAutoGroup extends CommandGroup {

    Position position;

    public WorstAutoGroup(Position position) {
        this.position = position;

        switch (position) {
            case LEFT:
                addSequential(new DriveToDistance(Converter.inToEncTicks(228)));
                addSequential(new TurnToAngle(90));
                addSequential(new DriveToDistance(Converter.inToEncTicks(240)));
                addSequential(new TurnToAngle(-90));
                addSequential(new DriveToDistance(Converter.inToEncTicks(36)));
                addSequential(new TurnToAngle(90));
                addParallel(new ShootScale());
                addSequential(new DriveToDistance(Converter.inToEncTicks(20)));
                addSequential(new Shoot());
                addSequential(new Shoot());

            case RIGHT:
                addSequential(new DriveToDistance(Converter.inToEncTicks(226)));
                addSequential(new TurnToAngle(-90));
                addSequential(new DriveToDistance(Converter.inToEncTicks(240)));
                addSequential(new TurnToAngle(90));
                addSequential(new DriveToDistance(Converter.inToEncTicks(80)));
                addSequential(new TurnToAngle(-90));
                addSequential(new DriveToDistance(Converter.inToEncTicks(40)));
                addParallel(new ShootScale());
                addSequential(new Shoot());
                addSequential(new Shoot());
        }

    }

    public enum Position {
        LEFT, RIGHT;
    }
}
