package frc.team2220.robot.commands.mechanisms.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.intake.SpinBothTransfer;
import frc.team2220.robot.commands.mechanisms.intake.StopTransfer;

public class Shoot extends CommandGroup {

    public Shoot() {

        addSequential(new CubePiston(CubePiston.Position.UP));
        addSequential(new SpinBothTransfer());
        addSequential(new StopTransfer());
        addSequential(new CubePiston(CubePiston.Position.DOWN));
    }

}
