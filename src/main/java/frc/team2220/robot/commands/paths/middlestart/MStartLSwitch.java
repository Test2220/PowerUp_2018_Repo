package frc.team2220.robot.commands.paths.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.intake.AutoIntake;
import frc.team2220.robot.commands.mechanisms.intake.JitterIntake;
import frc.team2220.robot.commands.mechanisms.shooter.AutoSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ReversiblePathReader;
import frc.team2220.robot.commands.mechanisms.vision.CubeFollower;
import frc.team2220.robot.subsystems.Limelight;
import frc.team2220.robot.utils.Converter;

public class MStartLSwitch extends CommandGroup {


    public MStartLSwitch() {
        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ReversiblePathReader("MiddleStart/MStartLSwitch", 50, ReversiblePathReader.Direction.FORWARD));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
        addSequential(new MultiReversiblePathReader("MiddleStart/LSwitch_MCube", 20, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addParallel(new AutoIntake(-0.75, 5.5));
        addSequential(new CubeFollower(2.5, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
        addParallel(new JitterIntake(1));
        addSequential(new MultiReversiblePathReader("MiddleStart/MCube_RSwitch", 22, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addParallel(new JitterIntake(1));
        addParallel(new Shoot());
        addSequential(new AutoSwitch(2));
        addSequential(new StopShooter());
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
