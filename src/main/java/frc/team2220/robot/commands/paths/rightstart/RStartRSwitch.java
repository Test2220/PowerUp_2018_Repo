package frc.team2220.robot.commands.paths.rightstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.shooter.LiftPistons;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.ScaledPathReader;

public class RStartRSwitch extends CommandGroup {


    public RStartRSwitch() {
//
//        double target1 = Converter.inToEncTicks(168) - Converter.inToEncTicks(Constants.frameLengthIn/2);
//        double target2 = Converter.inToEncTicks(35.25) -  Converter.inToEncTicks(Constants.frameLengthIn/2);
//
//
//        addSequential(new DriveToDistance(target1));
//        addSequential(new TurnToAngle(90));
//        addParallel(new ShootSwitch());
//        addSequential(new DriveToDistance(-target2));
//        addSequential(new Shoot());
//        addSequential(new Shoot());
//        addSequential(new StopShooter());
        //addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addParallel(new LiftPistons(LiftPistons.Position.RETRACTED));
        addSequential(new ScaledPathReader("/home/lvuser/paths/RightStart/RStartRSwitch_left_detailed.csv", "/home/lvuser/paths/RightStart/RStartRSwitch_right_detailed.csv", 0));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());

    }
}
