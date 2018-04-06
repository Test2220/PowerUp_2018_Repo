package frc.team2220.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Intake.SpinBothTransfer;
import frc.team2220.robot.commands.Intake.StopTransfer;

public class Shoot extends CommandGroup {

    public Shoot() {

        addSequential(new CubePiston(CubePiston.Position.UP));
        addSequential(new SpinBothTransfer());
        addSequential(new StopTransfer());
        addSequential(new CubePiston(CubePiston.Position.DOWN));
    }

}
