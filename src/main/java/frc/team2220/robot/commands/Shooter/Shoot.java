package frc.team2220.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team2220.robot.Robot;
import frc.team2220.robot.commands.Intake.SpinBothTransfer;
import frc.team2220.robot.commands.Intake.StopTransfer;

public class Shoot extends CommandGroup{

    public Shoot() {

        addSequential(new CubePiston(CubePiston.Position.UP));
        addSequential(new SpinBothTransfer());
        addSequential(new StopTransfer());
        addSequential(new CubePiston(CubePiston.Position.DOWN));
    }

}
