package frc.team2220.robot.commands.miscellaneous;

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

        Robot.intake.spinBothIntake(Converter.deadzone(value));
        Robot.intake.spinBothTransfer(Converter.deadzone(value));

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
