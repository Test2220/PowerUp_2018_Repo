package frc.team2220.robot.commands.mechanisms.intake;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team2220.robot.Robot;
import frc.team2220.robot.commands.mechanisms.vision.BlinkLimelight;
import frc.team2220.robot.commands.mechanisms.vision.LimelightConfig;
import frc.team2220.robot.subsystems.Limelight;
import frc.team2220.robot.utils.Converter;

public class ControlIntake extends Command {

//    private Command blinkLimelight = new LimelightConfig(Limelight.LED_MODE.BLINKING);
//    private Command stopLimelight = new LimelightConfig(Limelight.LED_MODE.OFF);

    private Command blinklimelight = new BlinkLimelight(3);

    public ControlIntake() {
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        Robot.intake.changeToPercentVBus();
    }

    @Override
    protected void execute() {
        double leftYAxis = Robot.oi.getManipulatorController().getYAxis(GenericHID.Hand.kLeft);
        double rightYAxis = -Robot.oi.getManipulatorController().getYAxis(GenericHID.Hand.kRight);

        if (Math.abs(Converter.deadzone(leftYAxis)) > 0.2 || Math.abs(Converter.deadzone(rightYAxis)) > 0.2) {
            Robot.intake.defaultCommandRun = true;
            Robot.intake.setRampDown();
        } else {
            if (Robot.intake.defaultCommandRun) {
                Robot.intake.setRampUp();
            }
        }


        if (Converter.deadzone(leftYAxis) > 0.2) {
            Robot.intake.setIntakePistonsExtend();
            Robot.intake.spinEntireIntake(0.3);
            Robot.shooter.setShooterDown();
            System.out.println("LEFT AXIS PATH");
        } else if (Converter.deadzone(leftYAxis) < -0.2) {

//            if (Robot.intake.isBlockHalfWayLoaded()) {
//                blinklimelight.start();
//            }
            Robot.intake.setIntakePistonsExtend();
            Robot.intake.spinEntireIntake(-0.75);
            Robot.shooter.setShooterDown();
            System.out.println("LEFT AXIS PATH");
        } else if (Converter.deadzone(rightYAxis) > 0.2) {
            System.out.println("RIGHT AXIS > 0.2");
            if (Robot.intake.isBlockHalfWayLoaded()) {
                Robot.intake.spinEntireIntake(0);
                System.out.println("LIMIT SWITCH TRUE");
            } else {
                Robot.intake.setIntakePistonsExtend();
                Robot.intake.spinBothIntake(-0.3);
                Robot.intake.spinBothTransfer(-0.3);
                Robot.shooter.setShooterDown();
                System.out.println("LIMIT SWITCH FALSE");
            }
        } else if (Converter.deadzone(rightYAxis) < -0.2) {
            Robot.intake.setIntakePistonsExtend();
            Robot.intake.spinEntireIntake(0.25);
            Robot.shooter.setShooterDown();
        } else {
            Robot.intake.spinEntireIntake(0);
        }


    }


    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
