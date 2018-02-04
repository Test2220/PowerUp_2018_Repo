package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;

public class VelocityTestSubsystem extends Subsystem{

    public CANTalon testMotor;

    double kp = 0;
    double ki = 0;
    double kd = 0;
    double kf = 0;

    @Override
    protected void initDefaultCommand() {

    }

    public VelocityTestSubsystem() {

        testMotor = new CANTalon(RobotMap.TESTMOTOR);
        testMotor.enableBrakeMode(false);

        testMotor.setInverted(false);
        testMotor.reverseOutput(false);

        testMotor.setPID(kp, ki, kd);
        testMotor.setF(kf);

        testMotor.configMaxOutputVoltage(9);

    }

    public void motorSet(double vel) {
        testMotor.set(vel);
    }

    public void changeToVelocity() {
        testMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    }

    public void getEncVel() {
        testMotor.getEncVelocity();
    }


}
