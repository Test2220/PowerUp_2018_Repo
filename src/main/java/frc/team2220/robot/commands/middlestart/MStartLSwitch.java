package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ScaledPathReader;

public class MStartLSwitch extends CommandGroup {


	public MStartLSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ScaledPathReader("/home/lvuser/paths/MiddleStart/MStartLSwitch_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MStartLSwitch_right_detailed.csv", 0.000));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
    }



}
