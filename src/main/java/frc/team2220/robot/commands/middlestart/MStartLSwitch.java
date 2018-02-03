package frc.team2220.robot.commands.middlestart;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.utils.Converter;

public class MStartLSwitch extends CommandGroup{
	
	double target1 = Converter.getInstance().ftToEncTicks(27);
	double target2 = Converter.getInstance().ftToEncTicks(3.49);
	
	
}
