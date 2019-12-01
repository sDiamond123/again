package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
@Autonomous
public class RepositionandParkAutoRed extends LinearOpMode {
    DcMotor leftFront, leftRear, rightFront, rightRear, linearSlide;
    int angleconversion;
    int fullcircle;
    int countsper10cm;
    CRServo wrist;

    @Override
    public void runOpMode() throws InterruptedException {
        //int count =
        //double power = 1;
        //number of counts required for the robot to turn a full circle
        fullcircle = 5500;

        //number of counts needed to turn 1 degree
        angleconversion = fullcircle / 360;

        //number of counts the motor has to run to go 10cm
        countsper10cm = 1050;

        linearSlide = hardwareMap.dcMotor.get("linSlide");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        wrist = hardwareMap.crservo.get("wrist");
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();


        //Step5(1, 6 * countsper10cm);
        //turns 90 degrees to the left
        //Step8(90);
        //moves forward and moves the linear slide up
        Step9(1, (int)2.6 * countsper10cm, -300);
        Step10(.5, (int)250);
        //Step11(1, (int)-2.8 * countsper10cm);
    }
    // Repositioning steps
    //public void rStep1 {
    // }
    public void Step5(double power, int distance) {
        DriveFor(power, distance);
    }

    public void Step8(int angle) {
        TurnLeftToAngle(angle);
    }

    public void Step9(double power, int distance, int raise) {
        //DriveFor(power, distance);
        MoveLinearSlide(power, raise);
    }

    public void Step10(double power, int raise){
        MoveLinearSlide(power, raise);
    }
    public void Step11(double power, int distance){
        DriveFor(power, distance);
    }

    public void StopDriving(){
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
    }
    public void DriveFor(double power, int distance){
        //reset encoders
        ResetEncoders();
        //set target position
        SetTargetPosition(distance);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        DriveForward(power);
        //while loop that keeps the method going until the motors finish
        while(leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()){
        }
        //stops driving
        StopDriving();
    }
    public void DriveForward(double power){
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(-power);
        rightRear.setPower(-power);
    }
    public void ResetEncoders(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void SetToRunToPosition(){
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    //distance is the number of counts that we want each motor to do
    public void SetTargetPosition(int distance){
        leftFront.setTargetPosition(distance);
        leftRear.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);
        rightRear.setTargetPosition(distance);
    }
    //
    public void TurnRightToAngle(int angle){
        int turncounts = angleconversion * angle;
        //reset encoders
        ResetEncoders();
        //set target position
        leftFront.setTargetPosition(turncounts);
        leftRear.setTargetPosition(turncounts);
        rightFront.setTargetPosition(-turncounts);
        rightRear.setTargetPosition(-turncounts);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(-1);
        rightRear.setPower(-1);
        while(leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()){

        }
        //stops driving
        StopDriving();
    }
    public void TurnLeftToAngle(int angle){
        int turncounts = angleconversion * angle;
        //reset encoders
        ResetEncoders();
        //set target position
        leftFront.setTargetPosition(-turncounts);
        leftRear.setTargetPosition(-turncounts);
        rightFront.setTargetPosition(turncounts);
        rightRear.setTargetPosition(turncounts);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);
        while(leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()){

        }
        //stops driving
        StopDriving();
    }
    public void MoveLinearSlide(double power, int raise){
        //reset encoders
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //set target position
        linearSlide.setTargetPosition(raise);
        //set to Run to position mode
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //drive at power
        linearSlide.setPower(power);
        //while loop that keeps the method going until the motors finish
        while(linearSlide.isBusy()){

        }
        //if((((raise - 10)) <= linearSlide.getCurrentPosition()) && (linearSlide.getCurrentPosition() <= (raise + 10))){
            StopDriving();
        //}

        //stops driving

    }
}