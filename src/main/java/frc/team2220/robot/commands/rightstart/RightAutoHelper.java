package frc.team2220.robot.commands.rightstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.commands.miscellaneous.MatchData;
import frc.team2220.robot.commands.miscellaneous.WorstAutoGroup;

public class RightAutoHelper extends InstantCommand {

    private Command switchAutoSS = new RStartRSwitch();
    private Command scaleAutoSS = new RStartRScale();

    //OS stand for OPPOSITE SIDE

    private Command WorstAuto = new WorstAutoGroup();

    @Override
    protected void initialize() {
        //Robot.side = "LEFT";
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        switchAutoSS.start();

        MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
        MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);

        System.out.println("Ran once");

        if (switchSide == MatchData.OwnedSide.RIGHT) {//Lxx

            switchAutoSS.start();

        } else if (switchSide == MatchData.OwnedSide.LEFT) {//Rxx

            if (scaleSide == MatchData.OwnedSide.RIGHT) {//RLx

                scaleAutoSS.start();

            } else if (scaleSide == MatchData.OwnedSide.LEFT) {

                WorstAuto.start(); //TODO DO BASIC STUFF DONT LEAVE THIS HERE

            }


        }

    }


}
