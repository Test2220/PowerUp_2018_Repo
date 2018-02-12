package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;

public class Intake extends Subsystem{

    private CANTalon leftIntake;
    private CANTalon rightIntake;

    private CANTalon leftTransfer;
    private CANTalon rightTransfer;

    public Intake() {

        leftIntake = new CANTalon(RobotMap.COLLECTOR_LEFT);
        rightIntake = new CANTalon(RobotMap.COLLECTOR_RIGHT);

        leftTransfer = new CANTalon(RobotMap.TRANSFER_LEFT);
        rightTransfer = new CANTalon(RobotMap.TRANSFER_RIGHT);

        leftIntake.setInverted(true);
        rightIntake.setInverted(false);

        leftTransfer.setInverted(true);

    }

    public void changeToPercentVBus() {
        leftIntake.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rightIntake.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void spinBothIntake(double value) {
        leftIntake.set(value);
        rightIntake.set(value);
    }

    public void spinBothTransfer(double value) {
        leftTransfer.set(value);
        rightTransfer.set(value);
    }


    @Override
    protected void initDefaultCommand() {

    }
}
