package frc.team2220.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.utils.Converter;

public class ControlIntake extends Command{

    public ControlIntake() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.intake.changeToPercentVBus();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double value = Robot.oi.getManipulatorStick().getRawAxis(5);

        if (Math.abs(Converter.deadzone(value)) > 0.2) {
            Robot.intake.setRampDown();
        } else {
            Robot.intake.setRampUp();
        }

        if (Converter.deadzone(value) > 0.2) {
            Robot.intake.spinBothIntake(0.5);
            Robot.intake.spinBothTransfer(0.5);
        } else if (Converter.deadzone(value) < -0.2){
            Robot.intake.spinBothIntake(-0.5);
            Robot.intake.spinBothTransfer(-0.5);
        } else {
            Robot.intake.spinBothIntake(0);
            Robot.intake.spinBothTransfer(0);
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
