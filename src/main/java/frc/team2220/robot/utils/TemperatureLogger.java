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
            builder.append(Math.round(temperature * 100.0) / 100.0);
            builder.append(',');
        }
        Logger.writeLog(builder.toString());
    }

    public void addHeader() {
        StringBuilder builder = new StringBuilder();
        for(CANTalon talon : talons) {
            String talonName = talon.getDescription();
            builder.append(talonName);
            builder.append(',');
        }
        Logger.writeLog(builder.toString());
    }


}
