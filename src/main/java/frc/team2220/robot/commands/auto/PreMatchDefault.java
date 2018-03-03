package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Intake.IntakeBoolean;
import frc.team2220.robot.commands.Intake.IntakePistons;
import frc.team2220.robot.commands.Intake.RampControl;
import frc.team2220.robot.commands.Shooter.CubePiston;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.StopShooter;

public class PreMatchDefault extends CommandGroup {

    public PreMatchDefault() {
        addSequential(new IntakeBoolean(false));
        addSequential(new ShootScale());
        addSequential(new StopShooter());
        addSequential(new IntakePistons(IntakePistons.Position.RETRACT));
        addSequential(new RampControl(RampControl.Position.RETRACTED));
        addSequential(new CubePiston(CubePiston.Position.UP));
    }

}
