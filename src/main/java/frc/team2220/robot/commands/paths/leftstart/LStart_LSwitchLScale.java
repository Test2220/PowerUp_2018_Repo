package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.mechanisms.intake.AutoIntake;
import frc.team2220.robot.commands.mechanisms.shooter.LiftPistons;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.mechanisms.vision.CubeFollower;
import frc.team2220.robot.subsystems.Limelight;
import frc.team2220.robot.utils.Converter;


public class LStart_LSwitchLScale extends CommandGroup {


    public LStart_LSwitchLScale() {

//        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
//        addParallel(new LiftPistons(LiftPistons.Position.RETRACTED));
//        addSequential(new PathReader("/home/lvuser/paths/LeftStart/LStartLSwitch_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartLSwitch_right_detailed.csv", 0.015));
//        addSequential(new ShootSwitch());
//        addSequential(new Shoot());
//        addSequential(new StopShooter());

        addSequential(new MultiReversiblePathReader("LeftStart/LStartLSwitch", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new MultiReversiblePathReader("LeftStart/LSwitch_LCube", 20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(58)));
        addParallel(new AutoIntake(-0.8, 3));
        addSequential(new CubeFollower(2.3, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
        addSequential(new RelativeTurnToAngle(-90));
        addSequential(new MultiReversiblePathReader("LeftStart/LCube_LScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(17)));

//
//        double target1 = Converter.inToEncTicks(168) - Converter.inToEncTicks(Constants.frameLengthIn/2);
//        double target2 = Converter.inToEncTicks(35.25) -  Converter.inToEncTicks(Constants.frameLengthIn/2);
//
//        addSequential(new DriveToDistance(target1));
//        addSequential(new TurnToAngle(-90));
//        addParallel(new ShootSwitch());
//        addSequential(new DriveToDistance(-target2));
//        addSequential(new Shoot());
//        addSequential(new Shoot());
//        addSequential(new StopShooter());

    }

}
