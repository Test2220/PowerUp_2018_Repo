package frc.team2220.robot.commands.paths.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.intake.AutoIntake;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ReversiblePathReader;

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
        addSequential(new MultiReversiblePathReader("TestPaths/TwoCubeGrabABoxRSwitch", 50, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new AutoIntake(0.8, 1));
        addSequential(new AutoIntake(-0.25, 0.4));
        addSequential(new AutoIntake(0.8, 1));


    }

}
