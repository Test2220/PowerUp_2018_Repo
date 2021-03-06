package frc.team2220.robot.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2220.robot.Robot;

public class RelativeTurnToAngle extends Command {


    private int DONE_COUNT_MAX = 10;
    private int currentDoneCount;

    private double targetAngle;
    private PIDController turnPIDController;

    private double startTime;

    public class Output implements PIDOutput {
        @Override
        public void pidWrite(double output) {

            // Check if the output is in the deadzone
            if (Math.abs(output) <= .10) {

                // If we need to turn right...
                if (output > 0) {
                    Robot.twilightDrive.leftSet(Math.abs(output));
                }

                // If we need to turn left...
                if (output < 0) {
                    Robot.twilightDrive.rightSet(Math.abs(output));
                }

            }

            // If the output isn't in the deadzone
            else {

                // Scale down the output
                output = output * .5;

                // Set the speeds
                Robot.twilightDrive.rightSet(output);
                Robot.twilightDrive.leftSet(output);

            }
        }
    }

    public RelativeTurnToAngle(double angle) {
        requires(Robot.twilightDrive);
        this.targetAngle = angle;
    }

    public RelativeTurnToAngle(double angle, double time) {
        super(time);
        requires(Robot.twilightDrive);
        this.targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        currentDoneCount = 0;
        startTime = Timer.getFPGATimestamp() * 1000.0;
        Robot.twilightDrive.changeToPercentVBus();
        // Dev
        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn started!");
        System.out.println("Target angle: " + targetAngle);

        //Robot.twilightDrive.navX.reset();
        Robot.twilightDrive.resetEncoderPos();
        // Start the PID Controller
        turnPIDController = new PIDController(0.024, 0.0002, 0.02, Robot.twilightDrive.navX, new RelativeTurnToAngle.Output());
        turnPIDController.setSetpoint(targetAngle);
        turnPIDController.setAbsoluteTolerance(1);
        turnPIDController.enable();

    }

    protected void execute() {
        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn running!");
        System.out.println("Angle: " + Robot.twilightDrive.navX.getAngle());
        System.out.println("Error: " + turnPIDController.getError());
        System.out.println("Output: " + turnPIDController.get());

    }

    protected boolean isFinished() {
        SmartDashboard.putData(turnPIDController);
        double difference = Timer.getFPGATimestamp() * 1000.0 - startTime;
        if (difference > 4000) {
            System.out.println("TIME UP");
            return true;
        }

        if (Math.abs(turnPIDController.getError()) < 4)
            currentDoneCount++;
        else
            currentDoneCount = 0;
        if (currentDoneCount > DONE_COUNT_MAX) {
            currentDoneCount = 0;
            return true;
        }
        return isTimedOut();

    }

    protected void end() {

        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn ended!");
        System.out.println("Error: " + turnPIDController.getError());

        // Stop the motors
        Robot.twilightDrive.rightSet(0);
        Robot.twilightDrive.leftSet(0);

        // Reset the PID Controller
        turnPIDController.disable();
        turnPIDController.reset();

    }

    protected void interrupted() {

        System.out.println("--------------------------------------------------");
        System.out.println("DriveTurn interrupted!");
        System.out.println("Error: " + turnPIDController.getError());

        turnPIDController.disable();
        turnPIDController.reset();

    }

}
