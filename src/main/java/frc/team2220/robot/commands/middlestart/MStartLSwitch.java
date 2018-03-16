package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ReversiblePathReader;
import frc.team2220.robot.commands.auto.ScaledPathReader;

public class MStartLSwitch extends CommandGroup {


	public MStartLSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.FORWARD));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
//        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.BACKWARD));

//        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 25, ReversiblePathReader.Direction.BACKWARD));

    }



}
