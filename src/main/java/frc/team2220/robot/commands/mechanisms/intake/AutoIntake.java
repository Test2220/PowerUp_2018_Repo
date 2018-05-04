package frc.team2220.robot.commands.mechanisms.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2220.robot.Robot;
import frc.team2220.robot.commands.mechanisms.vision.BlinkLimelight;

public class AutoIntake extends Command {
    private double speed;

    private final int DONE_COUNT_MAX = 15;
    private int currentDoneCount = 0;

    private Command blinkLimelight = new BlinkLimelight(2);

    public AutoIntake(double speed) {
        this.speed = speed;
    }

    public AutoIntake(double speed, double timeout) {
        super(timeout);
        this.speed = speed;
        requires(Robot.intake);
    }


    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.intake.setRampUp();
        Robot.intake.setIntakePistonsExtend();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.intake.spinBothIntake(speed);
        Robot.intake.spinBothTransfer(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (Robot.intake.isBlockHalfWayLoaded()) {
            currentDoneCount++;
        } else {
            currentDoneCount = 0;
        }
        if (currentDoneCount > DONE_COUNT_MAX) {
            currentDoneCount = 0;
            System.out.println("TRUE");
            return true;
        }
        return isTimedOut();

    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        blinkLimelight.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

}
