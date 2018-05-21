package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.mechanisms.shooter.LiftPistons;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.PathReader;


public class LStartLSwitch extends CommandGroup {


    public LStartLSwitch() {

        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
//        addParallel(new LiftPistons(LiftPistons.Position.RETRACTED));
        addSequential(new MultiReversiblePathReader("LeftStart_v2/LStartLSwitch", 20, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
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
