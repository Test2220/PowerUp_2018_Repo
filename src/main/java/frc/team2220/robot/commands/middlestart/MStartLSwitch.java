package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ScaledPathReader;
import frc.team2220.robot.commands.shooter.Shoot;
import frc.team2220.robot.commands.shooter.ShootSwitch;
import frc.team2220.robot.commands.shooter.StopShooter;

public class MStartLSwitch extends CommandGroup {


    public MStartLSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MStartLSwitch_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MStartLSwitch_right_detailed.csv", 0.000));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
    }


}
