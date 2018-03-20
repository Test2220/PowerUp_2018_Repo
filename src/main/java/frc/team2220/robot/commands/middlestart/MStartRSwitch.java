package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Intake.AutoIntake;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.*;

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
