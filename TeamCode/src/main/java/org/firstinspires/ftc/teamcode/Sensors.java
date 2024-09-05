package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Sensors extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "RightFrontMotor");

        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Motor Encoder: ", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
