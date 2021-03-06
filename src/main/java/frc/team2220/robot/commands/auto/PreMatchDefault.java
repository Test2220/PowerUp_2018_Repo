package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.intake.IntakeBoolean;
import frc.team2220.robot.commands.mechanisms.intake.IntakePistons;
import frc.team2220.robot.commands.mechanisms.intake.RampControl;
import frc.team2220.robot.commands.mechanisms.shooter.CubePiston;
import frc.team2220.robot.commands.mechanisms.shooter.ShootScale;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;

public class PreMatchDefault extends CommandGroup {

    public PreMatchDefault() {
        addSequential(new IntakeBoolean(false));
        addSequential(new ShootScale());
        addSequential(new StopShooter());
        addSequential(new IntakePistons(IntakePistons.Position.RETRACT));
        addSequential(new RampControl(RampControl.Position.RETRACTED));
        addSequential(new CubePiston(CubePiston.Position.DOWN));
    }

}
