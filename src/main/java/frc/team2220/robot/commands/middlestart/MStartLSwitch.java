package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.Intake.AutoIntake;
import frc.team2220.robot.commands.Shooter.Shoot;
import frc.team2220.robot.commands.Shooter.ShootSwitch;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ReversiblePathReader;
import frc.team2220.robot.utils.Converter;

public class MStartLSwitch extends CommandGroup {


    public MStartLSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.FORWARD));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
        addSequential(new MultiReversiblePathReader("TestPaths/TwoCubeGrabABox", 20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addParallel(new AutoIntake(0.8, 8));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(-20)));

        //        addSequential(new MultiReversiblePathReader("TestPaths/TwoCubeGrabABox", 20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.BOTTOM_TO_TOP));

//        addParallel(new IntakePistons(IntakePistons.Position.RETRACT));
//        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.BACKWARD));
//
//        addSequential(new DriveToDistance(-Converter.ftToEncTicks(3)));
////        addSequential(new RelativeTurnToAngle(170));
//        addSequential(new TurnToAngle(180));
////        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(210), 3));
////        addSequential(new TurnToAngle(180));
//        addParallel(new AutoIntake(0.6, 8));
//        addSequential(new DriveToDistance(Converter.ftToEncTicks(5), 10, 1000));

    }


}
