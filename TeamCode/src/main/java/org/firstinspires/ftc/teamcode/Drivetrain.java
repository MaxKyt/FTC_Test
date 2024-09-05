package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Drivetrain extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

    }


    public void initMotors(){
        DcMotor LeftForwardMotor = hardwareMap.get(DcMotor.class, "LeftFrontMotor");
        DcMotor RightForwardMotor = hardwareMap.get(DcMotor.class, "RightFrontMotor");
        DcMotor LeftBackMotor = hardwareMap.get(DcMotor.class, "LeftBackMotor");
        DcMotor RightBackMotor = hardwareMap.get(DcMotor.class, "RightBackMotor");

        RightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightForwardMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftForwardMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightForwardMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


}
