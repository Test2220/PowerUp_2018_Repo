package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootScale;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.ScaledPathReader;

public class LStartLScale extends CommandGroup {

    public LStartLScale() {

        addSequential(new ScaledPathReader("/home/lvuser/paths/LeftStart/LStartLScale_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartLScale_right_detailed.csv", 0));

        addSequential(new ShootScale(0.6));
        addSequential(new Shoot());
        addSequential(new StopShooter());

    }

}
