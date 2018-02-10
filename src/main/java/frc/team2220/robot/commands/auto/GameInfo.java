package frc.team2220.robot.commands.auto;

import frc.team2220.robot.commands.auto.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import edu.wpi.first.wpilibj.DriverStation;

public class GameInfo
{
    private static String GAMEDATA_IP = "10.0.100.55";
    private static int GAMEDATA_PORT = 5555;

    public GameInfo()
    {
    }

    public static String getGameSpecificMessage_WeekZero() throws IOException
    {
        String msg = "";
        Socket s = new Socket(GAMEDATA_IP, GAMEDATA_PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String gamedata_msg = input.readLine();
        s.close();
        int i = gamedata_msg.indexOf(':');
        if (i >= 0) {
            DriverStation ds = DriverStation.getInstance();
            if (ds.getAlliance() == DriverStation.Alliance.Red) {
                msg = gamedata_msg.substring(0, i-1);
            } else {
                msg = gamedata_msg.substring(i+1);
            }
        }
        return msg;
    }
}
