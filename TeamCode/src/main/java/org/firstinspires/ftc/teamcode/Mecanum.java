package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


// Эта программа предназначена для того, чтобы управлять роботом с помощью джойстиков на геймпаде.
// Вот руководство по управлению:
// Наклон левого джойстика по оси у - движение робота вперед или назад.
// Наклон левого джойстика оп оси х - движение робота влево или вправо
// Наклон правого джойстика по оси х - поворот робота вокруг своей оси соответственно вправо или влево.
@TeleOp
public class Mecanum extends LinearOpMode {

    @Override
    public void runOpMode(){
        // Инициализация 4 моторов:
        DcMotor LeftForwardMotor = hardwareMap.get(DcMotor.class, "LeftFrontMotor");
        DcMotor RightForwardMotor = hardwareMap.get(DcMotor.class, "RightFrontMotor");
        DcMotor LeftBackMotor = hardwareMap.get(DcMotor.class, "LeftBackMotor");
        DcMotor RightBackMotor = hardwareMap.get(DcMotor.class, "RightBackMotor");
        Drivetrain drivetrain = new Drivetrain();
        drivetrain.initMotors();



        waitForStart();

        //
        while (opModeIsActive()){
            // Инициализация переменных наклона джойстиков

            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rightX = gamepad1.right_stick_x;

            // Может случиться, что мощность на мотор получается больше 1. Тогда она автоматически сбрасывается до 1,
            // но отношение мощностей тогда нарушится, и робот поедет не совсем так, как ожидал оператор.
            // Чтобы этого избежать, я вычисляю максимально возможную мощность и делю каждую из мощностей на это число.
            // Это переменная Biggest.
            // Тогда отношение скоростей останется таким же, как отношение наклонов джойстиков, а наибольшая скорость будет равна 1.
            double Biggest = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rightX), 1);

            // Здесь я задаю мощности на каждый мотор.
            // Эти мощности вычисляются по формулам. Разберу каждую составляющую:
            // у - наклон левого джойстика по оси у - отвечает за движение робота вперед-назад.
            // х - наклон леовго джойстика по оси х - отвечает за движение робота влево-вправо.
            // Я прочитал, что обычно на роботах стоят колеса с валиками, установленными под углом 45 градусов
            // к направлению движения "вперед", причем на задних колесиках валики направлены наружу,
            // а на передних - внутрь, и исходя из этого я прибавлял или вычитал значение х из мощностей моторов,
            // а также значение rightX (наклон правого джойстика по оси х).
            // Значение rightX отвчает за поворот робота вокруг своей оси по или против часовой стрелки.
            double LeftBackPower = (y - x + rightX) / Biggest;
            double LeftForwardPower = (y + x + rightX) / Biggest;
            double RightBackPower = (y + x - rightX) / Biggest;
            double RightForwardPower = (y - x - rightX) / Biggest;


            // Следующие 4 условия - стабильное движение вперед (кнопка а), назад (кнопка b), вправо (кнопка у) и влево (кнопка х).
            // Это может быть полезно, т. к. джойстик можно случайно отклонить от оси у, и робот поедет не прямо,
            // а кнопка - стабильно нажата или не нажата.
            if (gamepad1.a){
                double LeftBackPower2 = 1;
                double LeftForwardPower2 = 1;
                double RightBackPower2 = 1;
                double RightForwardPower2 = 1;

                LeftBackMotor.setPower(LeftBackPower2);
                LeftForwardMotor.setPower(LeftForwardPower2);
                RightForwardMotor.setPower(RightForwardPower2);
                RightBackMotor.setPower(RightBackPower2);
            }

            else if (gamepad1.b){
                double LeftBackPower2 = -1;
                double LeftForwardPower2 = -1;
                double RightBackPower2 = -1;
                double RightForwardPower2 = -1;

                LeftBackMotor.setPower(LeftBackPower2);
                LeftForwardMotor.setPower(LeftForwardPower2);
                RightForwardMotor.setPower(RightForwardPower2);
                RightBackMotor.setPower(RightBackPower2);
            }

            else if (gamepad1.x){
                double LeftBackPower2 = 1;
                double LeftForwardPower2 = -1;
                double RightBackPower2 = -1;
                double RightForwardPower2 = 1;

                LeftBackMotor.setPower(LeftBackPower2);
                LeftForwardMotor.setPower(LeftForwardPower2);
                RightForwardMotor.setPower(RightForwardPower2);
                RightBackMotor.setPower(RightBackPower2);
            }

            else if (gamepad1.y){
                double LeftBackPower2 = -1;
                double LeftForwardPower2 = 1;
                double RightBackPower2 = 1;
                double RightForwardPower2 = -1;

                LeftBackMotor.setPower(LeftBackPower2);
                LeftForwardMotor.setPower(LeftForwardPower2);
                RightForwardMotor.setPower(RightForwardPower2);
                RightBackMotor.setPower(RightBackPower2);
            }


            // А здесь я поворачиваюсь роботом вокруг свой оси по куркам.
            else if (gamepad1.left_trigger > 0.1){
                double LeftBackPower2 = -1;
                double LeftForwardPower2 = -1;
                double RightBackPower2 = 1;
                double RightForwardPower2 = 1;

                LeftBackMotor.setPower(LeftBackPower2);
                LeftForwardMotor.setPower(LeftForwardPower2);
                RightForwardMotor.setPower(RightForwardPower2);
                RightBackMotor.setPower(RightBackPower2);
            }

            else if (gamepad1.right_trigger > 0.1){
                double LeftBackPower2 = 1;
                double LeftForwardPower2 = 1;
                double RightBackPower2 = -1;
                double RightForwardPower2 = -1;

                LeftBackMotor.setPower(LeftBackPower2);
                LeftForwardMotor.setPower(LeftForwardPower2);
                RightForwardMotor.setPower(RightForwardPower2);
                RightBackMotor.setPower(RightBackPower2);
            }

            // Здесь я задаю моторам соответствующие мощности.
            else {
                LeftBackMotor.setPower(LeftBackPower);
                LeftForwardMotor.setPower(LeftForwardPower);
                RightForwardMotor.setPower(RightForwardPower);
                RightBackMotor.setPower(RightBackPower);
            }

        }
    }
}

