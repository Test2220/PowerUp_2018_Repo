package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.commands.miscellaneous.MatchData;

public class LeftAutoHelper extends InstantCommand {

    //SS stand for SAME SIDE
    private Command LStart_LSwitchLScale = new LStart_LSwitchLScale();
    private Command scaleAutoSS = new LStartLScale();

    // private Command LStart_LSwitchLScale = new PathReader("/home/lvuser/paths/LStart/LStartLSwitch_left_detailed.csv", "/home/lvuser/paths/LStart/LStartLSwitch_right_detailed.csv", 0.0015);


    //OS stand for OPPOSITE SIDE

    private Command LStart_LSwitchRScale = new LStart_LSwitchRScale();

    @Override
    protected void initialize() {
        //Robot.side = "LEFT";
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
        MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);



        if (switchSide == MatchData.OwnedSide.LEFT) {
            if (scaleSide == MatchData.OwnedSide.LEFT) {
                LStart_LSwitchLScale.start();
            } else {
                LStart_LSwitchRScale.start();
            }
        }


//        if (scaleSide == MatchData.OwnedSide.LEFT) {
//            scaleAutoSS.start();
//        } else {
//            WorstAuto.start();
//        }

//        if (switchSide == MatchData.OwnedSide.LEFT) {//Lxx
//            System.out.println("SWITCH AUTO STARTED; LStart_LSwitchLScale");
//            LStart_LSwitchLScale.start();
//
//        } else if (switchSide == MatchData.OwnedSide.RIGHT) {//Rxx
//            System.out.println("SWITCH OPPOSITE; Check for Scale");
//
//            if (scaleSide == MatchData.OwnedSide.LEFT) {//RLx
//                System.out.println("SCALE IS ON THE LEFT; LStartLScale");
//                scaleAutoSS.start();
//
//            } else if (scaleSide == MatchData.OwnedSide.RIGHT) {
//                System.out.println("SCALE IS ON THE RIGHT; LStart_LSwitchRScale");
//                WorstAuto.start(); //TODO DO BASIC STUFF DONT LEAVE THIS HERE
//
//            }
//
//
//        }




    }


}
