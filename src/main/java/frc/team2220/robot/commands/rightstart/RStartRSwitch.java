package frc.team2220.robot.commands.rightstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.utils.Constants;
import frc.team2220.robot.utils.Converter;

public class RStartRSwitch extends CommandGroup{



    public RStartRSwitch() {

        double target1 = Converter.inToEncTicks(168) - Converter.inToEncTicks(Constants.frameLengthIn/2);
        double target2 = Converter.inToEncTicks(73.25) -  Converter.inToEncTicks(Constants.frameLengthIn/2);


        addSequential(new DriveToDistance(target1));

        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(-90)));

        addSequential(new DriveToDistance(target2));

    }
}
