package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootScale;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.utils.Converter;

public class LStartRScale extends CommandGroup {

    public LStartRScale() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LStartRScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));

//        addSequential(new ClockwiseTurn(-Converter.degreesTurnToEncTicks(120)));
//        addParallel(new AutoIntake(0.6, 4));
//        addSequential(new DriveToDistance(Converter.ftToEncTicks(6)), 4);
//        addSequential(new DriveToDistance(Converter.ftToEncTicks(6)), 4);
//        addSequential(new DriveToDistance(-Converter.ftToEncTicks(6)));
//        addSequential(new ClockwiseTurn(-Converter.degreesTurnToEncTicks(90)));
//        addSequential(new ShootScale());
//        addSequential(new StopShooter());

    }

}

