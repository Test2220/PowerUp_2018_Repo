package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.intake.IntakePistons;
import frc.team2220.robot.commands.mechanisms.intake.SpinBothTransfer;
import frc.team2220.robot.commands.mechanisms.shooter.ControlShooter;
import frc.team2220.robot.commands.mechanisms.shooter.LiftPistons;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;

public class PreAutoDefault extends CommandGroup {

    public enum FinalShooterPosition {
        SWITCH, SCALE;
    }


    public PreAutoDefault(FinalShooterPosition finalShooterPosition) {

        addSequential(new IntakePistons(IntakePistons.Position.EXTEND));
        switch (finalShooterPosition) {
            case SWITCH:
                addSequential(new LiftPistons(LiftPistons.Position.RETRACTED));
                break;
            case SCALE:
                addSequential(new LiftPistons(LiftPistons.Position.EXTENDED));
                break;
        }
        addSequential(new ControlShooter(-0.2));
        addParallel(new SpinBothTransfer(1.5));
        addSequential(new StopShooter());


    }

}
