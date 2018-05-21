package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;
import frc.team2220.robot.commands.mechanisms.intake.ControlIntake;

public class Intake extends Subsystem {

    private CANTalon leftIntake;
    private CANTalon rightIntake;

    private CANTalon leftTransfer;
    private CANTalon rightTransfer;

    private DoubleSolenoid rampPiston;
    private DoubleSolenoid intakePistons;

    public boolean defaultCommandRun = true;

    private DigitalInput limitSwitch;

    public Intake() {

        leftIntake = new CANTalon(RobotMap.COLLECTOR_LEFT);
        rightIntake = new CANTalon(RobotMap.COLLECTOR_RIGHT);

        leftTransfer = new CANTalon(RobotMap.TRANSFER_LEFT);
        rightTransfer = new CANTalon(RobotMap.TRANSFER_RIGHT);

        leftIntake.setInverted(true);
        rightIntake.setInverted(false);

        leftTransfer.setInverted(true);
        rightTransfer.setInverted(false);


        rampPiston = new DoubleSolenoid(RobotMap.RAMP_PISTON_EXTENDED, RobotMap.RAMP_PISTON_RETRACTED);
        intakePistons = new DoubleSolenoid(RobotMap.INTAKE_PISTON_EXTEND, RobotMap.INTAKE_PISTON_RETRACT);

        limitSwitch = new DigitalInput(RobotMap.INTAKE_LIMIT_SWITCH);

        leftIntake.enableBrakeMode(true);
        rightIntake.enableBrakeMode(true);
        leftTransfer.enableBrakeMode(true);
        rightTransfer.enableBrakeMode(true);
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
        leftTransfer.set(-value);
        rightTransfer.set(-value);
    }

    public void spinEntireIntake(double value) {
        spinBothIntake(value);
        spinBothTransfer(value);
    }

    public void setIntakePistonsExtend() {
        intakePistons.set(DoubleSolenoid.Value.kReverse);
    }

    public void setIntakePistonsRetract() {
        intakePistons.set(DoubleSolenoid.Value.kForward);
    }

    public void setRampDown() {
        rampPiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void setRampUp() {
        rampPiston.set(DoubleSolenoid.Value.kForward);
    }

    public boolean isBlockHalfWayLoaded() {
        return !limitSwitch.get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ControlIntake());
    }
}
