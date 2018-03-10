package frc.team2220.robot.utils;

public class Constants {

    //All Measurements in Inches unless specified otherwise

    public static int encTickPerRev = 4096;

    public static double wheelDiameterIn = 6.0;
    public static double wheelDiameterMetres = 0.1524;
    public static double wheelCircumferenceIn = Math.PI * wheelDiameterIn;
    public static double wheelCircumferenceMetres = Math.PI * wheelDiameterMetres;

    public static double frameWidthIn = 23.25;
    public static double frameWidthFt = frameWidthIn / 12.0;
    public static double frameLengthIn = 32.2;

    public static final double PATH_MAX_SPEED = 1.75;

    public static final int maxDrivetrainVelocity = 3155;

    //public static final double drivetrainMultiplier = 1.93; //GOES PERFECT WHILE DRIVING 5 FT
    public static final double drivetrainMultiplier = 1.3;
}
