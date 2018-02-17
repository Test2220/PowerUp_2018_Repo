package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;
import frc.team2220.robot.commands.climber.ControlClimber;
import frc.team2220.robot.utils.Converter;

public class Climber extends Subsystem{

    private CANTalon climber;

    public Climber() {

        climber = new CANTalon(RobotMap.CLIMBER);
        climber.setInverted(true);

    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ControlClimber());
    }

    public void spinClimber(double val) {
        climber.set(Converter.deadzone(val));
    }

}
