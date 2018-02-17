package frc.team2220.robot.commands.rightstart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.TurnToAngle;
import frc.team2220.robot.commands.miscellaneous.AntiClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;

public class RStartRScale extends CommandGroup{

    public RStartRScale() {
        //Division by 2 as Robot mid should be on target point
        //Division by 2 as Robot mid should be on target point
        double target1 = Converter.inToEncTicks(324) - Converter.inToEncTicks(Constants.frameLengthIn/2);
        //double target1 = Converter.ftToEncTicks(27);
        double target2 = Converter.inToEncTicks(55) - Converter.inToEncTicks(Constants.frameLengthIn/2);

        System.out.println(target1);

        addSequential(new DriveToDistance(target1));
        addSequential(new TurnToAngle(90));
        addSequential(new DriveToDistance(target2));
        addParallel(new ShootScale());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new StopShooter());

    }


}
