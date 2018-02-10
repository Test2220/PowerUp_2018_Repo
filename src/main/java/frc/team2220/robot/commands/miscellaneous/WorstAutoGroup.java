package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.utils.Converter;

public class WorstAutoGroup extends CommandGroup{

    public WorstAutoGroup(){
        addSequential(new DriveToDistance(Converter.ftToEncTicks(5)));
    }
}
