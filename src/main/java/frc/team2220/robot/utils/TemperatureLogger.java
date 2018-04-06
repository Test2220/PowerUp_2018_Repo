package frc.team2220.robot.utils;

import com.ctre.CANTalon;

public class TemperatureLogger{

    private CANTalon[] talons;


    public TemperatureLogger(CANTalon[] talons) {
        this.talons = talons;
    }

    public void logTalons() {
        StringBuilder builder = new StringBuilder();
        for(CANTalon talon : talons) {
            double temperature = talon.getTemperature();
            builder.append(temperature);
            builder.append(',');
        }
        Logger.writeLog(builder.toString());
    }


}
    