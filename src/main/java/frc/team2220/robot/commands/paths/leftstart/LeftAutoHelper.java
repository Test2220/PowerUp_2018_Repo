package frc.team2220.robot.commands.paths.leftstart;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.commands.miscellaneous.MatchData;

public class LeftAutoHelper extends InstantCommand {

    //SS stand for SAME SIDE
    private Command lStart_LSwitchLScale = new LStart_LSwitchLScale();
    private Command lStart_LScaleRSwitch = new LStart_LScaleRSwitch();

    private Command lStart_LSwitchRScale = new LStart_LSwitchRScale();

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
                lStart_LSwitchLScale.start();
            } else {
                lStart_LSwitchRScale.start();
            }
        } else if (switchSide == MatchData.OwnedSide.RIGHT){
            if (scaleSide == MatchData.OwnedSide.LEFT) {
                lStart_LScaleRSwitch.start();
            }
        }


//        if (scaleSide == MatchData.OwnedSide.LEFT) {
//            LStart_LScaleRSwitch.start();
//        } else {
//            WorstAuto.start();
//        }

//        if (switchSide == MatchData.OwnedSide.LEFT) {//Lxx
//            System.out.println("SWITCH AUTO STARTED; lStart_LSwitchLScale");
//            lStart_LSwitchLScale.start();
//
//        } else if (switchSide == MatchData.OwnedSide.RIGHT) {//Rxx
//            System.out.println("SWITCH OPPOSITE; Check for Scale");
//
//            if (scaleSide == MatchData.OwnedSide.LEFT) {//RLx
//                System.out.println("SCALE IS ON THE LEFT; LStart_LScaleRSwitch");
//                LStart_LScaleRSwitch.start();
//
//            } else if (scaleSide == MatchData.OwnedSide.RIGHT) {
//                System.out.println("SCALE IS ON THE RIGHT; lStart_LSwitchRScale");
//                WorstAuto.start(); //TODO DO BASIC STUFF DONT LEAVE THIS HERE
//
//            }
//
//
//        }




    }


}
