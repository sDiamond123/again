package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class motortest extends OpMode {
    DcMotor leftFront, leftRear, rightFront, rightRear, linearSlide;
    double speed=1;    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");
    }

    @Override
    public void loop(){
        //driving code start
      //if (gamepad1.a) leftFront.setPower(1);else leftFront.setPower(0);if(gamepad1.b)leftRear.setPower(1);else leftRear.setPower(0);if(gamepad1.x)rightFront.setPower(1);else rightFront.setPower(0);if(gamepad1.y)rightRear.setPower(1);else rightRear.setPower(0);

    }}