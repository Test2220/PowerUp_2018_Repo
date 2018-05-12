package frc.team2220.robot.commands.paths.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.mechanisms.intake.AutoIntake;
import frc.team2220.robot.commands.mechanisms.shooter.AutoSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.Shoot;
import frc.team2220.robot.commands.mechanisms.shooter.ShootSwitch;
import frc.team2220.robot.commands.mechanisms.shooter.StopShooter;
import frc.team2220.robot.commands.auto.MultiReversiblePathReader;
import frc.team2220.robot.commands.auto.PreAutoDefault;
import frc.team2220.robot.commands.auto.ReversiblePathReader;
import frc.team2220.robot.commands.mechanisms.vision.CubeFollower;
import frc.team2220.robot.subsystems.Limelight;

public class MStartRSwitch extends CommandGroup {
    //
//    String leftPath = "/home/lvuser/paths/MiddleStart/MStartRSwitch_left_detailed.csv";
//    String rightPath = "/home/lvuser/paths/MiddleStart/MStartRSwitch_right_detailed.csv";
//
    public MStartRSwitch() {

        addParallel(new PreAutoDefault(PreAutoDefault.FinalShooterPosition.SWITCH));
        addSequential(new ReversiblePathReader("MiddleStart/MStartRSwitch", 50, ReversiblePathReader.Direction.FORWARD));
        addSequential(new ShootSwitch());
        addSequential(new Shoot());
        addSequential(new StopShooter());
        addSequential(new MultiReversiblePathReader("MiddleStart/RSwitch_MCube", 50, MultiReversiblePathReader.Direction.COLLECTOR_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addParallel(new AutoIntake(-0.75, 5));
        addSequential(new CubeFollower(2.05, Limelight.LED_MODE.OFF, Limelight.CAM_MODE.VISION_PROCESSING));
//        addParallel(new AutoIntake(-0.6, 3));
        addSequential(new MultiReversiblePathReader("MiddleStart/MCube_LSwitch", 22, MultiReversiblePathReader.Direction.SHOOTER_FIRST, MultiReversiblePathReader.CSVReadDirection.TOP_TO_BOTTOM));
        addSequential(new AutoSwitch(2));
        addParallel(new Shoot());
        addSequential(new StopShooter());
        // addSequential(new StopShooter());
    }

}
