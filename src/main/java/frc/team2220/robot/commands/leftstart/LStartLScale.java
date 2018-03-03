package frc.team2220.robot.commands.leftstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.utils.Constants;
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


        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SCALE));
        addSequential(new ScaledPathReader("/home/lvuser/paths/LeftStart/LStartLScale_left_detailed.csv", "/home/lvuser/paths/LeftStart/LStartLScale_right_detailed.csv", 0));
        addSequential(new ShootScale());
        addSequential(new Shoot());
        addSequential(new StopShooter());

    }

}
