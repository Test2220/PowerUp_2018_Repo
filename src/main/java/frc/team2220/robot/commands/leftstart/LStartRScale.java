package frc.team2220.robot.commands.leftstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.auto.PathReader;

public class LStartRScale extends CommandGroup{

    public LStartRScale() {
        addSequential(new PathReader("/home/lvuser/paths/LeftStart/LStartRScale_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartRScale_right_detailed.csv", 0.1));
        addSequential(new ShootScale());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
    }

}
