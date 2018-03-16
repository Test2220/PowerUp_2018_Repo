package frc.team2220.robot.commands.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Intake.AutoIntake;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.utils.Converter;

public class LStartRScale extends CommandGroup {

    public LStartRScale() {
//        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new PathReader("/home/lvuser/paths/LeftStart/LStartRScale_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartRScale_right_detailed.csv", 0.1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(220)));
        addSequential(new ShootScale());
        addSequential(new Shoot());
        addSequential(new StopShooter());
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

