package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class RunToPos extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "RightFrontMotor");

        waitForStart();


        while (opModeIsActive()) {
            if (gamepad1.a) {
                motor.setTargetPosition(360);

                motor.setPower(0.5);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.b) {
                motor.setTargetPosition(0);

                motor.setPower(0.3);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            telemetry.addData("Motor Encoder: ", motor.getCurrentPosition());
            telemetry.update();
        }
}
}
