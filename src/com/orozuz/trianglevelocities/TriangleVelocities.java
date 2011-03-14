package com.orozuz.trianglevelocities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TriangleVelocities extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView tasTV = (TextView) findViewById(R.id.tas);
                TextView trackTV = (TextView) findViewById(R.id.track);
                TextView windDirTV = (TextView) findViewById(R.id.windDirection);
                TextView windVelTV = (TextView) findViewById(R.id.windVelocity);
                TextView distTV = (TextView) findViewById(R.id.dist);
                double tas = Double.parseDouble(tasTV.getText().toString());
                double track = Double.parseDouble(trackTV.getText().toString());
                double windDirection = Double.parseDouble(windDirTV.getText().toString());
                double windVelocity = Double.parseDouble(windVelTV.getText().toString());
                double dist = Double.parseDouble(distTV.getText().toString());

                double trueHeading = getTrueHeading(track, tas, windDirection, windVelocity);
                double groundSpeed = getGroundSpeed(track, tas, windDirection, windVelocity, trueHeading);
                double time = dist * 60. / groundSpeed;

                TextView headingTV = (TextView) findViewById(R.id.heading);
                headingTV.setText(Math.round(trueHeading) + "°");
                TextView groundSpeedTV = (TextView) findViewById(R.id.groundSpeed);
                groundSpeedTV.setText("" + Math.round(groundSpeed));
                TextView timeTV = (TextView) findViewById(R.id.time);
                timeTV.setText(Math.round(time) + "'");
            }
        });

    }

    private double getTrueHeading(double track, double trueAirSpeed, double windDirection, double windSpeed) {
        double trackInRadians = Math.toRadians(track);
        double windDirectionInRadians = Math.toRadians(windDirection) - Math.PI;
        double trueHeading = trackInRadians + Math.asin((windSpeed * Math.sin(trackInRadians - windDirectionInRadians)) / trueAirSpeed);
        return Math.toDegrees(trueHeading);
    }

    private double getGroundSpeed(double track, double trueAirSpeed, double windDirection, double windSpeed, double trueHeading) {
        double trackInRadians = Math.toRadians(track);
        double windDirectionInRadians = Math.toRadians(windDirection) - Math.PI;
        return trueAirSpeed * Math.cos(Math.toRadians(trueHeading) - trackInRadians) + windSpeed * Math.cos(trackInRadians - windDirectionInRadians);
    }
}
