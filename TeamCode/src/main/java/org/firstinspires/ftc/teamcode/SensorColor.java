package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class SensorColor extends LinearOpMode {
    NormalizedColorSensor colorSensor;
    View relativeLayout;



    @Override
    public void runOpMode() throws InterruptedException {
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        try {
            Color();
        }
        finally {
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });
        }

        }

    protected void Color(){
        float gain = 2f;
        final float[] hsvValues = new float[3];
        boolean xButtonPreviouslyPressed = false;
        boolean xButtonCurrentlyPressed = false;

        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "Color");

        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }

        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.a){
                gain += 0.005f;
            }
            else if(gamepad1.b && gain > 1){
                gain -= 0.005f;

            }
            telemetry.addData("Gain", gain);
            colorSensor.setGain(gain);

            xButtonCurrentlyPressed = gamepad1.x;

            if (xButtonCurrentlyPressed != xButtonPreviouslyPressed){
                if (xButtonCurrentlyPressed){
                    if (colorSensor instanceof SwitchableLight) {
                        SwitchableLight light = (SwitchableLight)colorSensor;
                        light.enableLight(!light.isLightOn());}
                }
            }
            xButtonPreviouslyPressed = xButtonCurrentlyPressed;
            NormalizedRGBA colors = colorSensor.getNormalizedColors();
            Color.colorToHSV(colors.toColor(), hsvValues);


            telemetry.addLine()
                    .addData("Red", "%.3f", colors.red)
                    .addData("Green", "%.3f", colors.green)
                    .addData("Blue", "%.3f", colors.blue);

            telemetry.addData("Alpha", "%.3f", colors.alpha);

            if (colorSensor instanceof DistanceSensor) {
                telemetry.addData("Distance (cm)", "%.3f", ((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM));
            }

            telemetry.update();

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
                }
            });
        }


    }
}