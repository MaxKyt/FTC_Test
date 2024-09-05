package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
@TeleOp
public class Button extends LinearOpMode {
    TouchSensor button;


    @Override
    public void runOpMode() {
        button = hardwareMap.get(TouchSensor.class, "Button");

        waitForStart();

        while (opModeIsActive()){
            if (!button.isPressed()){
                telemetry.addData("Button: ", "Pressed");
            }
            else {
                telemetry.addData("Button", "Not pressed");
            }
            telemetry.update();
            sleep(500);
        }
    }
}
