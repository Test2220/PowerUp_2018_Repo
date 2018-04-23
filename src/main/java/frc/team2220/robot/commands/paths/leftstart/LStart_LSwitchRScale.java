package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.mechanisms.intake.AutoIntake;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootScale;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.mechanisms.vision.CubeFollower;
import frc.team2220.robot.subsystems.Limelight;
import frc.team2220.robot.utils.Converter;

public class LStart_LSwitchRScale extends CommandGroup {

    public LStart_LSwitchRScale() {
//        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));

        addSequential(new MultiReversiblePathReader("LeftStart/LStartLSwitch", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
//        addSequential(new PathReader("/home/lvuser/paths/LeftStart/LStartRScale_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartRScale_right_detailed.csv", 0.1));
//        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(220)));
//        addSequential(new ShootScale());
//        addSequential(new Shoot());
//        addSequential(new StopShooter());

        addSequential(new MultiReversiblePathReader("LeftStart/LSwitch_RScale",     20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
//        addSequential(new DriveToDistance(Converter.ftToEncTicks(-2.5)));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
        addParallel(new AutoIntake(-0.8, 4.5));
        addSequential(new CubeFollower(4, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
        addSequential(new RelativeTurnToAngle(-90));
        addSequential(new MultiReversiblePathReader("LeftStart/RCube_RScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(-60)));
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

