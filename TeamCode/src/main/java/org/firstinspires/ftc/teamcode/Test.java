package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class Test extends LinearOpMode {
    DcMotor leftFront, leftRear, rightFront, rightRear;
    int countsper10cm;
    int angleconversion;
    int fullcircle;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //number of counts the motor has to run to go 10cm
        countsper10cm = 1050;
        fullcircle = 22000;
        //number of counts needed to turn 1 degree
        angleconversion = fullcircle/360;
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
        StrafeRight(1, 3 * countsper10cm, 3);
        StrafeLeft(1, 3 * countsper10cm, 3);
    }

    public void StrafeRight(double power, int distance, int timeoutS){
        runtime.reset();
        //reset encoders
        ResetEncoders();
        //set target position
        leftFront.setTargetPosition(-distance);
        leftRear.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);
        rightRear.setTargetPosition(-distance);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        DriveForward(power);
        while((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {

        }
        //stops driving
        StopDriving();
    }

    public void StrafeLeft (double power, int distance, int timeoutS) {
        runtime.reset();
        //reset encoders
        ResetEncoders();
        //set target position
        leftFront.setTargetPosition(distance);
        leftRear.setTargetPosition(-distance);
        rightFront.setTargetPosition(-distance);
        rightRear.setTargetPosition(distance);
        // set to Run to position mode
        SetToRunToPosition();
        //drive at power
        DriveForward(power);
        while ((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {
        }
    }
    public void DriveFor(double power, int distance, int timeoutS){       //reset encoders
        runtime.reset();
        ResetEncoders();
        //set target position
        SetTargetPosition(distance);//set to Run to position mode
        SetToRunToPosition();
        //drive at power
        DriveForward(power);
        //while loop that keeps the method going until the motors finish
        while((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (timeoutS > runtime.seconds())){
            telemetry.addData("hello",runtime.seconds());
        }
        //stops driving
        StopDriving();
    }
    public void SetTargetPosition(int distance){
        leftFront.setTargetPosition(distance);
        leftRear.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);
        rightRear.setTargetPosition(distance);
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

    public void StopDriving(){
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
    }
    public void SetToRunToPosition(){
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void TurnRightToAngle(int angle, int timeoutS){
        int turncounts = angleconversion * angle;
        runtime.reset();
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
        rightFront.setPower(1);
        rightRear.setPower(1);
        while((timeoutS > runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
        }
        //stops driving
        StopDriving();
    }
    public void TurnLeftToAngle(int angle, int timeoutS){
        int turncounts = angleconversion * angle;
        runtime.reset();
        //reset encoders
        ResetEncoders();
        //set target position
        leftFront.setTargetPosition(-turncounts);
        leftRear.setTargetPosition(-turncounts);
        rightFront.setTargetPosition(turncounts);
        rightRear.setTargetPosition(turncounts);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);
        while((leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {

        }
        //stops driving
        StopDriving();
    }
}
