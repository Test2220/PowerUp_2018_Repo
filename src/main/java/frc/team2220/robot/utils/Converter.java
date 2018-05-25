package frc.team2220.robot.utils;

public class Converter {


    public static double ftToEncTicks(double inputFt) {

        return ((inputFt * 12) / (Constants.wheelCircumferenceIn)) * Constants.encTickPerRev;

    }

    public static double encTicksPer100MSToFtPerSecond(double encTicks) {
        //Input velocity multiplied by 10 --> To make it per second
        return (((encTicks * 10) / Constants.encTickPerRev) * Constants.wheelCircumferenceIn) * 12;
    }

    public static double inToEncTicks(double inputIn) {

        return ((inputIn) / (Constants.wheelCircumferenceIn)) * Constants.encTickPerRev;

    }

    public static double encTicksToIn(double encoder_distance) {

        return (encoder_distance / Constants.encTickPerRev) * Constants.wheelCircumferenceIn;

    }

    public static double degreesTurnToEncTicks(double degreesToTurn) {

        double circumFt = Constants.frameWidthFt * Math.PI;
        double turnRatio = (degreesToTurn + 15) / 360;

        double arcLengthFt = turnRatio * circumFt;
        double arcLengthTicks = ftToEncTicks(arcLengthFt);


        return arcLengthTicks;
    }

    public static double ftPerSecondToNativeUnitsPer100Ms(double ftPerSecond) {
        return ftToEncTicks(ftPerSecond) * (1.0 / 10);
    }

    public static double NativeUnitsToMetresPerSecond(double nativeUnits) {
        return ((nativeUnits * 10) / Constants.encTickPerRev) * Constants.wheelCircumferenceMetres;
    }

    public static double maxVelToFGainWrong(int maxVel, double encTicksPerRev) {

        if (maxVel == 0) {

            return 0;

        } else {

            double fGainTemp = maxVel * (1.0 / 60) * (1.0 / 10) * (encTicksPerRev / 1);
            double fGain = 1023.0 / fGainTemp;
            return fGain;

        }
    }

    public static double maxVelToFGainCorrect(int maxVel) {
        if (maxVel == 0) {

            return 0;

        } else {
            return 1023.0 / maxVel;
        }
    }


    public static double errorToPGain(double error, double pMultiplier) {
        if (error == 0) {

            return 0;

        } else {

            double pGain = (pMultiplier * 1023) / error;
            return pGain;

        }
    }

    //Super Basic Deadzone function
    public static double deadzone(double val) {
        double signum = Math.signum(val);
        return signum * Math.pow(Math.abs(val), 1.3);
    }

}
