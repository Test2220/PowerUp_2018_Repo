package frc.team2220.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class TwilightTrigger extends Trigger {
    Joystick driverStick;

    public TwilightTrigger(Joystick driverStick) {

        this.driverStick = driverStick;

    }

    public boolean get() {
        if (Math.abs(driverStick.getRawAxis(1)) > 0.12 ||
                Math.abs(driverStick.getRawAxis(5)) > 0.12 || Math.abs(driverStick.getRawAxis(4)) > 0.12)
            return true;
        return false;
    }

}
