package frc.team2220.robot.commands.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.LiftPistons;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;


public class LStartLSwitch extends CommandGroup {


    public LStartLSwitch() {

//        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addParallel(new LiftPistons(LiftPistons.Position.RETRACTED));
        addSequential(new PathReader("/home/lvuser/paths/LeftStart/LStartLSwitch_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartLSwitch_right_detailed.csv", 0.015));
        addParallel(new ShootSwitch());
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
