package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Intake.AutoIntake;
import frc.team2220.robot.commands.Intake.IntakePistons;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootScale;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.utils.Converter;

public class MStartLSwitch extends CommandGroup {


	public MStartLSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.FORWARD));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
//        addParallel(new IntakePistons(IntakePistons.Position.RETRACT));
//        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.BACKWARD));

//        addSequential(new DriveToDistance(-Converter.ftToEncTicks(3)));
//        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(213), 3));
////        addSequential(new TurnToAngle(180));
//        addParallel(new AutoIntake(0.6, 8));
//        addSequential(new DriveToDistance(Converter.ftToEncTicks(5), 10, 1000));

//        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 25, ReversiblePathReader.Direction.BACKWARD));

    }



}
