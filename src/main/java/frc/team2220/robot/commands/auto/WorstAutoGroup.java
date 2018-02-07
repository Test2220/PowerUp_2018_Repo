package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.utils.Converter;

public class WorstAutoGroup extends CommandGroup{

    public WorstAutoGroup(){
        addSequential(new DriveToDistance(Converter.ftToEncTicks(5)));
    }
}
