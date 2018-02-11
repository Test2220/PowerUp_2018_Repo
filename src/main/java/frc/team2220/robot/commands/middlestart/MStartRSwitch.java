package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.utils.Converter;

public class MStartRSwitch extends CommandGroup{

    String leftPath = "/home/lvuser/paths/MStart/MStartRSwitch_left_detailed.csv";
    String rightPath = "/home/lvuser/paths/MStart/MStartRSwitch_right_detailed.csv";


    public MStartRSwitch() {

        addSequential(new PathReader(leftPath, rightPath, 0.002));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(180)));
        addSequential(new DriveToDistance(Converter.ftToEncTicks(-3)));

    }

}
