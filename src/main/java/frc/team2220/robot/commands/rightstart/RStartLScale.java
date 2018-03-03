package frc.team2220.robot.commands.rightstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.LiftPistons;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.PathReaderMultiDirectional;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.utils.Converter;

public class RStartLScale extends CommandGroup {

    public RStartLScale() {
//        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new PathReaderMultiDirectional("/home/lvuser/paths/RightStart/RStartLScale_left_detailed.csv", "/home/lvuser/paths/RightStart/RStartLScale_right_detailed.csv", 0.015, 0.0067));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(160)));
        addSequential(new ShootScale());
        addSequential(new Shoot());
        addSequential(new StopShooter());
    }

}
