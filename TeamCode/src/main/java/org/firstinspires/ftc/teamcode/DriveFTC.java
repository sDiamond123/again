package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DriveFTC extends OpMode {
    double speed=1;
    //double wPos=0;
    //double [] wPositions={1,0};
    //int wIndex;
    double la=1;
    double lspeed=0;
    CRServo wrist;
    DcMotor leftFront, leftRear, rightFront, rightRear, linearSlide;
    ServoController S = new ServoController(1, .5, 1);

    @Override
    public void init() {
        linearSlide = hardwareMap.dcMotor.get("linSlide");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        wrist = hardwareMap.crservo.get("wrist");
        //leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
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
        double x = -gamepad1.left_stick_x * speed;
        double y = -gamepad1.left_stick_y * speed;
        double turn = gamepad1.right_stick_x * speed;
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

        if (gamepad2.right_stick_x > 0 && S.getPosition() < 1){
            S.moveRight();
        }
        //
        if (gamepad2.right_stick_x < 0 && S.getPosition() > 0){
            S.moveLeft();
        }


        /*
        while (gamepad2.right_stick_x > 0 && wPos > 0)
        {
            //wPos--;
            //wIndex++;
            //wIndex%=wPositions.length;
            //wPos=(wPositions[wIndex]);
        }
        while (gamepad2.right_stick_x < 0 && wPos < 1)
        {
            //wPos++;

            wIndex--;
            if (wIndex<0)
            {
                wIndex=-wIndex;
            }
            wIndex%=wPositions.length;

            //wPos=(wPositions[wIndex]);
        }


        if (gamepad2.x){
            wPos=wPositions[wIndex];
            if (wIndex<0)
            {
                wIndex=-wIndex;
            }
            wIndex%=wPositions.length;

            wPos=(wPositions[wIndex]);
        }
        if (gamepad2.b){
            wIndex++;
            wIndex%=wPositions.length;
            wPos=(wPositions[wIndex]);
        }
        */
        /*
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
        */
        //wrist.setPosition(wPos);



        wrist.setPower(S.getData());
        //double wristx = -gamepad2.right_stick_x;
        //wrist.setPower(wristx);
        if (gamepad2.left_stick_y>.1)
        {
            //lspeed+=.01;
            //lspeed%=1;
            linearSlide.setPower(la);
        }
        else if (gamepad2.left_stick_y<-.1)
        {
            //lspeed-=.01;
            //lspeed%=1;
            linearSlide.setPower(-la);
        }
        else {
            linearSlide.setPower(0);
        }

        //arm code end
    }
}
