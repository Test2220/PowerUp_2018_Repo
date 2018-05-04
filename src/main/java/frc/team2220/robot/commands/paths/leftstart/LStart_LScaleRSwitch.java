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

public class LStart_LScaleRSwitch extends CommandGroup {

    public LStart_LScaleRSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LStartLScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new ShootScale(0.6));
        addSequential(new Shoot());
        addSequential(new StopShooter());
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(-40)));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LScale_RCube", 20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.BOTTOM_TO_TOP));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
        addParallel(new AutoIntake(-0.8, 2.5));
        addSequential(new CubeFollower(2.5, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
        addSequential(new DriveToDistance(Converter.ftToEncTicks(-2)));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(180)));
        addSequential(new DriveToDistance(Converter.ftToEncTicks(-3)));

    }

}
