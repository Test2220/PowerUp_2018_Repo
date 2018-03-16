package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.PathReaderMultiDirectional;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ReversiblePathReader;
import frc.team2220.robot.commands.auto.ScaledPathReader;

public class MStartRSwitch extends CommandGroup {
//
//    String leftPath = "/home/lvuser/paths/MiddleStart/MStartRSwitch_left_detailed.csv";
//    String rightPath = "/home/lvuser/paths/MiddleStart/MStartRSwitch_right_detailed.csv";
//
    public MStartRSwitch() {

        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ReversiblePathReader("MiddleStart/MStartRSwitch", 50, ReversiblePathReader.Direction.FORWARD));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());

    }

}
