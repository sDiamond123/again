package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class PickUpandParkAutoRed extends LinearOpMode {
    DcMotor leftFront, leftRear, rightFront, rightRear, linearSlide;
    int angleconversion;
    int fullcircle;
    int countsper10cm;
    CRServo wrist;
    private ElapsedTime
            runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        //double power = 1;
        //number of counts required for the robot to turn a full circle
        fullcircle = 4800;
        //number of counts needed to turn 1 degree
        angleconversion = fullcircle/360;
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
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();

        //drives forward to the yellow bricks and opens the wrist
        //Step1(1, (int)2.5 *countsper10cm);
        //closes the wrist
        //Step2();
        //moves back nearer to the wall
    /*    Step3(1, 2 * countsper10cm);
        //turns right now that it has moved back near the wall
        Step4(90);
        //drives to the tape
        Step5(1, (int)2.8 * countsper10cm);
        //lets go of the brick
        Step6();
        //continues to drive forward
        Step7(1, (int) 3.5 * countsper10cm);
        //turns 90 degrees to the left
        Step8(90); */
        //moves forward and moves the linear slide up
        Step9(1, 200);
        Step10(1, (int)3 * countsper10cm, 3);
        Step11(1, -175);
        //figure out a way for the robot to move back slower using power
        Step12(1, (int)-3 * countsper10cm, 3);
        Step13(1, 275);
        Step14(1, (int)3 * countsper10cm, 3);
    }
    // Repositioning steps

    //moves the robot forward 10 cm
    public void Step1(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
        //wrist.setPower(1);
    }
    public void Step2(){
        wrist.setPower(-1);
    }
    public void Step3(double power, int distance, int timeoutS){
        DriveFor(-power, distance, timeoutS);
    }
    public void Step4(int angle, int timeoutS){
        TurnRightToAngle(angle, timeoutS);
    }
    public void Step5(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
    }
    public void Step6(){
        wrist.setPower(0);
    }
    public void Step7(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
    }
    public void Step8(int angle, int timeoutS){
        TurnLeftToAngle(angle, timeoutS);
    }
    //moves the linear slide up
    public void Step9(double power, int raise){
        //the timeout here is how long you are giving the linearslide to raise
        MoveLinearSlide(power, raise, (int)2.5);
    }
    //move forward to the construction site
    public void Step10(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
        //the timeout here is how long you are giving the linearslide to raise
    }
    //move the linearslide down
    public void Step11(double power, int raise){
        MoveLinearSlide(power, raise, 1);
    }
    //move back to the wall
    public void Step12(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
    }
    //lift up the linear slide
    public void Step13(double power, int raise){
        MoveLinearSlide(power, raise, 1);
    }
    //strafe left to the tape
    public void Step14(double power, int distance, int timeoutS){
        runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(-distance);
        leftRear.setTargetPosition(-distance);
        rightRear.setPower(distance);
        SetToRunToPosition();
        DriveForward(power);
        while((timeoutS < runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
        }
        //stops driving
        StopDriving();
    }
    public void StopDriving(){
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
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
        while((leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) && (timeoutS > runtime.seconds())){
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
    public void TurnRightToAngle(int angle, int timeoutS){
        int turncounts = angleconversion * angle;
        runtime.reset();
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
        while((timeoutS < runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
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
        SetTargetPosition(turncounts);
        //set to Run to position mode
        SetToRunToPosition();
        //drive at power
        leftFront.setPower(-1);
        leftRear.setPower(-1);
        rightFront.setPower(1);
        rightRear.setPower(1);
        while((leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) && (runtime.seconds() > timeoutS)) {

        }
        //stops driving
        StopDriving();
    }
    public void MoveLinearSlide(double power, int raise, int timeoutS){
        runtime.reset();
        //reset encoders
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //set target position
        linearSlide.setTargetPosition(raise);
        //set to Run to position mode
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //drive at power
        linearSlide.setPower(power);
        //while loop that keeps the method going until the motors finish
        while(linearSlide.isBusy() && (runtime.seconds() < timeoutS)){
        }
        //stops driving
        StopDriving();
    }
}
