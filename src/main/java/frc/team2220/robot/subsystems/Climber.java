package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;
import frc.team2220.robot.commands.mechanisms.climber.ControlClimber;
import frc.team2220.robot.utils.Converter;

public class Climber extends Subsystem {

    public CANTalon fishingPole;
    private CANTalon winch;

    public Climber() {

        fishingPole = new CANTalon(RobotMap.FISHING_POLE);
        winch = new CANTalon(RobotMap.CLIMBER);

        fishingPole.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        winch.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        winch.setInverted(true);
        fishingPole.setInverted(true);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ControlClimber());
    }

    public void spinFishingPole(double val) {
        fishingPole.set(val);
    }

    public void spinWinch(double val) {
        winch.set(val);
    }

}
