package frc.team2220.robot.commands.paths.rightstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.commands.miscellaneous.MatchData;

public class RightAutoHelper extends InstantCommand {

    private Command switchAutoSS = new RStartRSwitch();
    private Command scaleAutoSS = new RStartRScale();

    //OS stand for OPPOSITE SIDE
    private Command WorstAuto = new RStartLScale();

    @Override
    protected void initialize() {
        //Robot.side = "LEFT";
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
        MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);

        if (switchSide == MatchData.OwnedSide.RIGHT) {//Lxx
            System.out.println("SWITCH AUTO STARTED; RStartRSwitch");
            switchAutoSS.start();

        } else if (switchSide == MatchData.OwnedSide.LEFT) {//Rxx
            System.out.println("SWITCH OPPOSITE; Check for Scale");

            if (scaleSide == MatchData.OwnedSide.RIGHT) {//RLx

                System.out.println("SCALE IS ON RIGHT; RStartRScale");
                scaleAutoSS.start();

            } else if (scaleSide == MatchData.OwnedSide.LEFT) {

                System.out.println("SCALE IS ON LEFT; RStartLScale");
                WorstAuto.start(); //TODO DO BASIC STUFF DONT LEAVE THIS HERE

            }


        }

    }


}