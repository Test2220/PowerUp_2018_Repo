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

        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LStartLSwitch", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LSwitch_RScale",     20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
        addParallel(new AutoIntake(-0.8, 4.5));
        addSequential(new CubeFollower(4, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
        addSequential(new RelativeTurnToAngle(-90));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/RCube_RScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(-60)));
        addSequential(new ShootScale(0.6));
        addSequential(new Shoot());
        addSequential(new StopShooter());
    }

}

