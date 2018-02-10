package frc.team2220.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2220.robot.commands.auto.ClockwiseTurn;
import frc.team2220.robot.commands.auto.DriveToDistance;
import frc.team2220.robot.utils.Converter;

public class TestCommandGroup extends CommandGroup{

    double target1 = Converter.ftToEncTicks(1.5);

    public TestCommandGroup() {

        //addSequential(new DriveToDistance(target1));
        //addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(120)));
        //addSequential(new DriveToDistance(target1));
        //addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(120)));
        //addSequential(new DriveToDistance(target1));
        //addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(120)));
        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

        addSequential(new DriveToDistance(target1));
        addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(30)));

    }

	/*
	public TestCommandGroup() {
		
		System.out.println("RAN ONCE");
		
		double target1 = Converter.ftToEncTicks(5);



		//addSequential(new DriveToDistance(target1));

		addSequential(new ResetEncoderPos());

		addSequential(new DriveToDistance(target1));
		//addSequential(new Delay(1));
		//TODO Turn right 90 degrees
		addSequential(new ResetEncoderPos());
		//addSequential(new TurnToAngle(90));
		addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());
		//addSequential(new TurnToAngle(90));
		//addSequential(new Delay(1));
		addSequential(new DriveToDistance(target1));

		addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());

		addSequential(new DriveToDistance(target1));

		addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());

		addSequential(new DriveToDistance(target1));


		addSequential(new ResetEncoderPos());
		addSequential(new ClockwiseTurn(Converter.degreesTurnToEncTicks(90)));
		addSequential(new ResetEncoderPos());


        */
	}



