package frc.team2220.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.RobotMap;
import com.ctre.CANTalon.TalonControlMode;
import frc.team2220.robot.commands.Shooter.StopShooter;
import frc.team2220.robot.utils.Converter;

public class Shooter extends Subsystem{

    private CANTalon topLeft;
    private CANTalon topRight;
    private CANTalon btmLeft;
    private CANTalon btmRight;

    private DoubleSolenoid liftPistons;
    private DoubleSolenoid cubePiston;


    int maxVelTopleft;
    double topLeftF = Converter.maxVelToFGainCorrect(maxVelTopleft);
    double topLeftP = Converter.errorToPGain(0, 0);
    double topLeftI;
    double topLeftD;

    int maxVelTopRight;
    double topRightF = Converter.maxVelToFGainCorrect(maxVelTopRight);
    double topRightP = Converter.errorToPGain(0, 0);
    double topRightI;
    double topRightD;

    int maxVelBtmLeft;
    double btmLeftF = Converter.maxVelToFGainCorrect(maxVelBtmLeft);
    double btmLeftP = Converter.errorToPGain(0, 0);
    double btmLeftI;
    double btmLeftD;

    int maxVelBtmRight;
    double btmRightF = Converter.maxVelToFGainCorrect(maxVelBtmRight);
    double btmRightP = Converter.errorToPGain(0, 0);
    double btmRightI;
    double btmRightD;


    public Shooter() {

        topLeft = new CANTalon(RobotMap.SHOOTER_TOP_LEFT);
        topRight = new CANTalon(RobotMap.SHOOTER_TOP_RIGHT);
        btmLeft = new CANTalon(RobotMap.SHOOTER_BTM_LEFT);
        btmRight = new CANTalon(RobotMap.SHOOTER_BTM_RIGHT);

        topLeft.setF(topLeftF);
        topLeft.setPID(topLeftP, topLeftI, topLeftD);

        topRight.setF(topRightF);
        topRight.setPID(topRightP, topRightI, topRightD);

        btmLeft.setF(btmLeftF);
        btmLeft.setPID(btmLeftP, btmLeftI, btmLeftD);

        btmRight.setF(btmRightF);
        btmRight.setPID(btmRightP, btmRightI, btmRightD);

        topLeft.setInverted(false); //TODO FIND THE RIGHT VALS
        topRight.setInverted(true);
        btmLeft.setInverted(false);
        btmRight.setInverted(true);


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

    public void changeToVelocity(){
        topLeft.changeControlMode(TalonControlMode.Speed);
        topRight.changeControlMode(TalonControlMode.Speed);
        btmLeft.changeControlMode(TalonControlMode.Speed);
        btmRight.changeControlMode(TalonControlMode.Speed);
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
        //setDefaultCommand(new StopShooter());
    }
}
