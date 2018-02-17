package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.PathReader;
import frc.team2220.robot.utils.Converter;

public class MStartLSwitch extends CommandGroup{
	
	public MStartLSwitch() {
	    addSequential(new PathReader("/home/lvuser/paths/MiddleStart/MStartLSwitch_left_detailed.csv", "/home/lvuser/paths/MiddleStart/MStartLSwitch_right_detailed.csv", 0.000));
    }
	
	
}
