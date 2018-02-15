package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;

public class Climber extends Subsystem{

    private CANTalon climber;

    public Climber() {

        climber = new CANTalon(RobotMap.CLIMBER);

        climber.setInverted(false);

    }

    @Override
    protected void initDefaultCommand() {
    }

    public void spinClimber() {
        climber.set(0.3);
    }

}
