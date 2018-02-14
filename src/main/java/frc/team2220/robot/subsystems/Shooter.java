package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2220.robot.RobotMap;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter extends Subsystem{

    private CANTalon topLeft;
    private CANTalon topRight;
    private CANTalon btmLeft;
    private CANTalon btmRight;

    private DoubleSolenoid liftPistons;
    private DoubleSolenoid cubePiston;


    /*
    private static Shooter instance_ = new Shooter();

    public static Shooter getInstance()
    {
        return instance_;
    }

*/
    public Shooter() {

        topLeft = new CANTalon(RobotMap.SHOOTER_TOP_LEFT);
        topRight = new CANTalon(RobotMap.SHOOTER_TOP_RIGHT);
        btmLeft = new CANTalon(RobotMap.SHOOTER_BTM_LEFT);
        btmRight = new CANTalon(RobotMap.SHOOTER_BTM_RIGHT);

        topLeft.setInverted(true); //TODO FIND THE RIGHT VALS
        topRight.setInverted(false);
        btmLeft.setInverted(true);
        btmRight.setInverted(false);

        liftPistons = new DoubleSolenoid(RobotMap.LIFT_PISTON_EXTEND, RobotMap.LIFT_PISTON_RETRACT);
        cubePiston = new DoubleSolenoid(RobotMap.CUBE_PISTON_UP, RobotMap.CUBE_PISTON_DOWN);

    }

    public void spinAllMotors(double value) {
        topLeft.set(value);
        topRight.set(value);
        btmLeft.set(value);
        btmRight.set(value);
    }

    public void changeToPercentVBus() {
        topLeft.changeControlMode(TalonControlMode.PercentVbus);
        topRight.changeControlMode(TalonControlMode.PercentVbus);
        btmLeft.changeControlMode(TalonControlMode.PercentVbus);
        btmRight.changeControlMode(TalonControlMode.PercentVbus);
    }

    //Lift Stuff

    public void setShooterUp() {
        liftPistons.set(DoubleSolenoid.Value.kForward);
    }

    public void setShooterDown() {
        liftPistons.set(DoubleSolenoid.Value.kReverse);
    }

    //Cube Piston

    public void setCubePistonDown() {
        cubePiston.set(DoubleSolenoid.Value.kReverse);
    }


    public void setCubePistonUp() {
        cubePiston.set(DoubleSolenoid.Value.kForward);
    }

    @Override
    protected void initDefaultCommand() {
       // setDefaultCommand(Con);
    }
}
