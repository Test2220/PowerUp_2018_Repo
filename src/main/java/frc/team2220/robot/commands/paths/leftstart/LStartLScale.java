package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.mechanisms.shooter.AutoScale;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootScale;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;

public class LStartLScale extends CommandGroup {

    public LStartLScale() {

        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LScale_Init", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new RelativeTurnToAngle(74, 1.8));
        addSequential(new AutoScale(0.72, 2));
        addSequential(new StopShooter());
    }

}
