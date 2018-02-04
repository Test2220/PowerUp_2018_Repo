package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;
import frc.team2220.robot.utils.Converter;

public class VelocityTestSubsystem extends Subsystem{

    private static VelocityTestSubsystem instance_ = new VelocityTestSubsystem();

    public static VelocityTestSubsystem getInstance()
    {
        return instance_;
    }


    public CANTalon testMotor;

    double kp = Converter.getInstance().errorToPGain(5154, 0.3);
    double ki = 0.000001;
    double kd = kp * 45;
    double kf = Converter.getInstance().maxVelToFGain( 28691, 1024);

    @Override
    protected void initDefaultCommand() {

    }

    public VelocityTestSubsystem() {


        testMotor = new CANTalon(RobotMap.TESTMOTOR);
        testMotor.enableBrakeMode(false);

        testMotor.setInverted(false);
        testMotor.reverseOutput(true);

        testMotor.setPID(kp, ki, kd);
        testMotor.setF(kf);

        //testMotor.configMaxOutputVoltage(9);

    }

    public void motorSet(double vel) {
        testMotor.set(vel);
    }

    public void changeToVelocity() {
        testMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        System.out.println(kf);
    }

    public void changeToPercentVBus() {
        testMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public double getEncVel() {
        return testMotor.getEncVelocity();
    }


}
