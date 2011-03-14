package com.orozuz.trianglevelocities;

/**
 * User: oroz
 * Date: 14/03/11
 */
public class Reference {
    public static void main(String[] args) {
        computeHeading(167, 80, 20, 280);
    }

    private static void computeHeading(double track, double trueAirSpeed, double windSpeed, double windDirection) {
        double trackInRadians = Math.toRadians(track);
        double windDirectionInRadians = Math.toRadians(windDirection) - Math.PI;


        double trueHeading = trackInRadians + Math.asin((windSpeed * Math.sin(trackInRadians - windDirectionInRadians)) / trueAirSpeed);

        double groundSpeed = trueAirSpeed * Math.cos(trueHeading - trackInRadians) + windSpeed * Math.cos(trackInRadians - windDirectionInRadians);
        trueHeading = Math.toDegrees(trueHeading);

        System.out.println("TAS\tTR(T)\tW/V\tHDG(T)\tG/S");
        System.out.println((int) trueAirSpeed + "\t" + (int) track + '\t' + (int) windDirection + "/" + (int) windSpeed +
                '\t' + Math.round(trueHeading) + '\t' + Math.round(groundSpeed));
    }
}
