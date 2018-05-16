
package frc.team2220.robot;


import frc.team2220.robot.commands.Intake.IntakePistons;
import frc.team2220.robot.commands.auto.*;
import frc.team2220.robot.commands.shooter.Shoot;
import frc.team2220.robot.commands.shooter.ShootScale;
import frc.team2220.robot.commands.shooter.ShootSwitch;
import frc.team2220.robot.commands.shooter.StopShooter;
import frc.team2220.robot.utils.Converter;
import frc.team2220.robot.utils.TwilightXBoxController;

public class OI {

    private TwilightXBoxController driverController;
    private TwilightXBoxController manipulatorController;

    public TwilightXBoxController getDriverController() {
        return driverController;
    }

    public TwilightXBoxController getManipulatorController() {
        return manipulatorController;
    }

    public OI() {

        driverController = new TwilightXBoxController(0);
        manipulatorController = new TwilightXBoxController(1);

        //Gagan's Side
        driverController.getLeftBumper().whenPressed(new Shoot());
        driverController.getStartButton().whileHeld(new PreMatchDefault());
        driverController.getAButton().whenPressed(new ReversiblePathReader("MiddleStart/MStartRSwitch", 20, ReversiblePathReader.Direction.BACKWARD));
        driverController.getBButton().whenPressed(new PathEncoderFollower("TestPaths/ForwardFiveFeet", 1));
        driverController.getYButton().whenPressed(new VelocityTester());


        //Nick's Side

    }


}
