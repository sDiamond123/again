package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Autonomous
public class drivetest extends LinearOpMode {
    DcMotor leftFront, leftRear, rightFront, rightRear;
    int angleconversion;
    int fullcircle;
    int countsper10cm;
    @Override
    public void runOpMode() throws InterruptedException {
        //int count =
        //double power = 1;
        //number of counts required for the robot to turn a full circle
        fullcircle = 5000;


        //number of counts needed to turn 1 degree
        angleconversion = fullcircle/360;

        //number of counts the motor has to run to go 10cm
        countsper10cm = 1050;


        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();

        DriveFor(1, 800);
        //reset encoders
        ResetEncoders();
        //set target position
        SetTargetPosition(fullcircle);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(-1);
        rightRear.setPower(-1);
        //while loop that keeps the method going until the motors finish
        while(leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()){
        }
        //stops driving
        StopDriving();
        //int count;
        //count = rightFront.getCurrentPosition();
        //telemetry.addData("ticks", count);
          //      telemetry.update();

    }
    //moves the robot forward 10 cm
    public void Step1(double power, int distance){
        DriveFor(power, distance);
    }
    //turns the robot 90 degrees to the left
    public void Step2(int angle){
        TurnRightToAngle(angle);
    }
    public void Step3(double power, int distance){
        DriveFor(power, distance);
    }
    public void Step4(int angle){
        TurnLeftToAngle(angle);
    }
    public void Step5(double power, int distance){
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
    public void TurnRightToAngle(int angle){
        int turncounts = angleconversion * angle;
        //reset encoders
        ResetEncoders();
        //set target position
        SetTargetPosition(turncounts);
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
        SetTargetPosition(turncounts);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        leftFront.setPower(-1);
        leftRear.setPower(-1);
        rightFront.setPower(1);
        rightRear.setPower(1);
        while(leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()){

        }
        //stops driving
        StopDriving();
    }
}
