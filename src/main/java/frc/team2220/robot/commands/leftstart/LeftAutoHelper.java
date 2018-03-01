package frc.team2220.robot.commands.leftstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.commands.miscellaneous.MatchData;

public class LeftAutoHelper extends InstantCommand {

    //SS stand for SAME SIDE
    private Command switchAutoSS = new LStartLSwitch();
    private Command scaleAutoSS = new LStartLScale();

    // private Command switchAutoSS = new PathReader("/home/lvuser/paths/LStart/LStartLSwitch_left_detailed.csv", "/home/lvuser/paths/LStart/LStartLSwitch_right_detailed.csv", 0.0015);


    //OS stand for OPPOSITE SIDE

    private Command WorstAuto = new LStartRScale();

    @Override
    protected void initialize() {
        //Robot.side = "LEFT";
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
        MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);

        if (switchSide == MatchData.OwnedSide.LEFT) {//Lxx
            System.out.println("SWITCH AUTO STARTED; LStartLSwitch");
            switchAutoSS.start();

        } else if (switchSide == MatchData.OwnedSide.RIGHT) {//Rxx
            System.out.println("SWITCH OPPOSITE; Check for Scale");

            if (scaleSide == MatchData.OwnedSide.LEFT) {//RLx
                System.out.println("SCALE IS ON THE LEFT; LStartLScale");
                scaleAutoSS.start();

            } else if (scaleSide == MatchData.OwnedSide.RIGHT) {
                System.out.println("SCALE IS ON THE RIGHT; LStartRScale");
                WorstAuto.start(); //TODO DO BASIC STUFF DONT LEAVE THIS HERE

            }


        }

    }


}
