package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2220.robot.Robot;

public class Shoot extends CommandGroup{

    public Shoot() {

        addSequential(new CubePiston(CubePiston.Position.UP));
        addSequential(new WaitCommand(1));
        addSequential(new CubePiston(CubePiston.Position.DOWN));

    }

}
