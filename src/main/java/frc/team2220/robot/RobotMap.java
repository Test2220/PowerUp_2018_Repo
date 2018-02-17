/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2220.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {


    public static final int LEFTMASTER = 1,
            LEFTSLAVE = 3,
            RIGHTMASTER = 2,
            RIGHTSLAVE = 4;

    public static final int SHOOTER_TOP_LEFT = 10,
            SHOOTER_TOP_RIGHT = 11,
            SHOOTER_BTM_LEFT = 12,
            SHOOTER_BTM_RIGHT = 9;

    public static final int TRANSFER_LEFT = 13,
            TRANSFER_RIGHT = 6;

    public static final int COLLECTOR_LEFT = 8,
            COLLECTOR_RIGHT = 7;

    public static final int CUBE_PISTON_UP = 0,
            CUBE_PISTON_DOWN = 1;

    public static final int LIFT_PISTON_EXTEND = 2,
            LIFT_PISTON_RETRACT = 3;

    public static final int INTAKE_PISTON_EXTEND = 5,
            INTAKE_PISTON_RETRACT = 4;

    public static final int RAMP_PISTON_EXTENDED = 7,
            RAMP_PISTON_RETRACTED = 6;


    public static final int FISHING_POLE = 14,
            CLIMBER = 5;


}
