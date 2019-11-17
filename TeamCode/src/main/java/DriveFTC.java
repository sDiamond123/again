package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveFTC extends OpMode {
    double speed=1;
    double wPos=0;
    double [] wPositions={};
    int wIndex;
    double la=1;
    double lspeed=0;
    Servo wrist;
    DcMotor leftFront, leftRear, rightFront, rightRear, linearSlide;
    @Override
    public void init() {
        linearSlide=hardwareMap.dcMotor.get("linSlide");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        wrist=hardwareMap.servo.get("wrist");
        //leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        //rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {

        //driving code start
        if (gamepad1.dpad_up) {
            speed = 1;
        }
        if (gamepad1.dpad_left || gamepad1.dpad_right) {
            speed = .5;
        }
        if (gamepad1.dpad_down) {
            speed = .2;
        }
        double x = gamepad1.left_stick_x * speed; // * (reverse ? -1 : 1);
        double y = -gamepad1.left_stick_y * speed; // * (reverse ? -1 : 1);
        double turn =-1* gamepad1.right_stick_y * speed;
        leftFront.setPower(x + y + turn);
        rightFront.setPower(-x + y - turn);
        leftRear.setPower(-x + y + turn);
        rightRear.setPower(x + y - turn);
        //driving code end

        //arm code start
        if (gamepad2.dpad_up) {
            la = 1;
        }
        if (gamepad2.dpad_left || gamepad2.dpad_right) {
            la = .5;
        }
        if (gamepad2.dpad_down) {
            la = .2;
        }
        if (gamepad2.a)
        {
            wIndex++;
            wIndex%=wPositions.length;
            wPos=(wPositions[wIndex]);
        }
        if (gamepad2.b)
        {
            wIndex--;
            if (wIndex<0)
            {
                wIndex=-wIndex;
            }
            wIndex%=wPositions.length;
            wPos=(wPositions[wIndex]);
        }
        if (gamepad2.x)
        {
            wPos+=.01;
            wPos%=1;
        }
        if (gamepad2.y)
        {
            wPos-=.01;
            if (wPos<0)
            {
                wPos=-wPos;
            }
            wPos%=1;
        }
        wrist.setPosition(wPos);

        if (gamepad2.left_stick_y>0)
        {
            lspeed+=.01*la;
            linearSlide.setPower(lspeed);
        }
        if (gamepad2.left_stick_y<0)
        {
            lspeed-=.01*la;
            if (lspeed<0)
            {
                lspeed=-lspeed;
            }
            linearSlide.setPower(lspeed);
        }
        //arm code end
    }
}
