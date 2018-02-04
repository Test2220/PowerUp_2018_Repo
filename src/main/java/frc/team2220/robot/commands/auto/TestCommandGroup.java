package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.utils.Converter;

public class TestCommandGroup extends CommandGroup{
	
	public TestCommandGroup() {
		
		System.out.println("RAN ONCE");
		
		double target1 = Converter.getInstance().ftToEncTicks(5);


		//addSequential(new DriveToDistance(target1));

		addSequential(new ResetEncoderPos());

		addSequential(new DriveToDistance(target1));
		//addSequential(new Delay(1));
		//TODO Turn right 90 degrees
		addSequential(new ResetEncoderPos());
		//addSequential(new TurnToAngle(90));
		addSequential(new ClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());
		//addSequential(new TurnToAngle(90));
		//addSequential(new Delay(1));
		addSequential(new DriveToDistance(target1));

		addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());

		addSequential(new DriveToDistance(target1));

		addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());

		addSequential(new DriveToDistance(target1));


		addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.getInstance().degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());





	}


}
