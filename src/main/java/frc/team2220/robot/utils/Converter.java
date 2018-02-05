package frc.team2220.robot.utils;

public class Converter {


	
	private static Converter _instance = new Converter();

	public static Converter getInstance() {

		return _instance;

	}

	public double ftToEncTicks(double inputFt) {

		return (inputFt * 12) / (Constants.wheelCircumference) * Constants.encTickPerRev ;

	}

	public double inToEncTicks(double inputIn) {

		return (inputIn) / (Constants.wheelCircumference) * Constants.encTickPerRev ;

	}

	public double degreesTurnToEncTicks(double degreesToTurn) {

		double circumFt = Constants.frameWidthFt * Math.PI;
		double turnRatio = (degreesToTurn + 15)/360;

		double arcLengthFt = turnRatio * circumFt;
		double arcLengthTicks = ftToEncTicks(arcLengthFt);


		return arcLengthTicks;

	}

	public double maxVelToFGainWrong(int maxVel, double encTickPerRev) {

		if (maxVel ==  0) {

			return 0;

		} else {

			double fGainTemp = maxVel * (1.0/60) * (1.0/10) * (encTickPerRev/1);
			double fGain = 1023.0/fGainTemp;
			System.out.println("FGain = " + fGain);
			return fGain;

		}
	}

	public double maxVelToFGainCorrect(int maxVel, double encTickPerRev) {
        if (maxVel == 0) {

            return 0;

        } else {
            return 1023 / maxVel;
        }
    }

	public double errorToPGain(double error, double pMultiplier) {
		if (error == 0) {

			return 0;

		} else {

			double pGain = (pMultiplier*1023) / error;
			return pGain;

		}
	}

}
