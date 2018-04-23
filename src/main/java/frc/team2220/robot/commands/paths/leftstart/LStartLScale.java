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

public class LStartLScale extends CommandGroup {

    public LStartLScale() {
        //Division by 2 as Robot mid should be on target point
//
//
//		double target1 = Converter.inToEncTicks(324) - Converter.inToEncTicks(Constants.frameLengthIn/2);
//        //double target1 = Converter.ftToEncTicks(27);
//		double target2 = Converter.inToEncTicks(21) - Converter.inToEncTicks(Constants.frameLengthIn/2);
//
//		System.out.println(target1);
//		addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
//		addSequential(new DriveToDistance(-target1));
//		addSequential(new TurnToAngle(90));
//		addParallel(new ShootScale());
//		addSequential(new DriveToDistance(target2));
//		addSequential(new Shoot());
//        addSequential(new Shoot());
//        addSequential(new StopShooter());


//        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
//        addSequential(new MultiReversiblePathReader("LeftStart/LStartLScale", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
//        addSequential(new ScaledPathReader("/home/lvuser/paths/LeftStart/LStartLScale_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartLScale_right_detailed.csv", 0));
//        addSequential(new DriveToDistance(Converter.inToEncTicks(5)));
//        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(20)));
        addSequential(new ShootScale(0.6));
        addSequential(new Shoot());
        addSequential(new StopShooter());
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(-45), 1));
//        addSequential(new TurnToAngle(-40));
        addParallel(new AutoIntake(-0.8, 4.5));
        addSequential(new CubeFollower(4.6, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
        addSequential(new DriveToDistance(Converter.ftToEncTicks(-5.5)));
        addSequential(new RelativeTurnToAngle(40));
//        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(100)));
        addSequential(new DriveToDistance(Converter.ftToEncTicks(3)));


//        addSequential(new ClockwiseTurn(-Converter.degreesTurnToEncTicks(70)));
//        addParallel(new AutoIntake(0.6, 4));
//        addSequential(new DriveToDistance(Converter.ftToEncTicks(6)), 4);
//        addSequential(new DriveToDistance(-Converter.ftToEncTicks(6)));
//        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(70)));
//        addSequential(new Shoot());
//        addSequential(new StopShooter());M
    }

}
