package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class RepositionandParkAutoRed extends LinearOpMode {
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
        //was 4800
        fullcircle = 5400;
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
        telemetry.addData("encodervalue", leftFront.getTargetPosition());
        telemetry.update();


        //moves forward and moves the linear slide up
        Step9(1, 260, 1);
        DriveFor(1, (countsper10cm), 1);
        //(int)(2.6 * countsper10cm)
        //strafe
        Step10(1, (int)(1.7*countsper10cm), 1);
        //drive to thing
        Step12(1, (int)(1.93 *countsper10cm), 3);
        Step13(1, -250,2);
        //figure out a way for the robot to move back slower using power
        Step14(.4,(-65*countsper10cm), 3);
        //turns the robot 90 degrees to the left
        Step11(210, 2);
        DriveFor(.5, countsper10cm, 1);
        Step15(1, 260, (int)1);
        Step16(1,(int)(2.2 * countsper10cm), (int)1);
        //total should equal 3.8
        Step17(1, -250, 1);
        Step19(1, countsper10cm, 1);
        Step18(1, (int)(1.5 * countsper10cm), (int)1);
        Step20(1, (int)(.5*countsper10cm), 1);
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
    public void Step9(double power, int raise, int timeoutS){
        //the timeout here is how long you are giving the linearslide to raise
        MoveLinearSlide(power, raise, timeoutS);
    }
    public void Step10(double power, int distance, int timeoutS){
        runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(-distance);
        rightFront.setTargetPosition(distance);
        leftRear.setTargetPosition(distance);
        rightRear.setPower(-distance);
        SetToRunToPosition();
        DriveForward(power);
        while((timeoutS > runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
        }
        //stops driving
        StopDriving();
    }
    //move forward to the construction site
    public void Step11(int angle, int timeoutS){
        TurnRightToAngle(angle, timeoutS);
    }
    public void Step12(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
    }
    //move the linearslide down
    public void Step13(double power, int raise, int timeoutS){
        MoveLinearSlide(power, raise, timeoutS);
    }
    //move back to the wall
    public void Step14(double power, int distance, int timeoutS){
        DriveFor(power, distance, timeoutS);
    }
    //lift up the linear slide
    public void Step15(double power, int raise, int timeoutS){
        MoveLinearSlide(power, raise, timeoutS);
    }
    public void Step16(double power, int distance, int timeoutS){
        /* runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(-distance);
        leftRear.setTargetPosition(-distance);
        rightRear.setPower(distance);
        SetToRunToPosition();
        DriveForward(power);
        while((timeoutS > runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
        }
        //stops driving
        StopDriving(); */
        DriveFor(power, -distance, timeoutS);
    }
    public void Step17(double power, int raise, int timeoutS){
        MoveLinearSlide(power, raise, timeoutS);
    }
    public void Step18(double power, int distance, int timeoutS){
        /* runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(-distance);
        leftRear.setTargetPosition(-distance);
        rightRear.setPower(distance);
        SetToRunToPosition();
        DriveForward(power);
        while((timeoutS > runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
        }
        //stops driving
        StopDriving(); */
        DriveFor(power, -distance, timeoutS);
    }
    public void Step19(double power, int distance, int timeoutS){
        runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(-distance);
        rightFront.setTargetPosition(distance);
        leftRear.setTargetPosition(-distance);
        rightRear.setPower(-distance);
        SetToRunToPosition();
        DriveForward(power);
        while((timeoutS > runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
        }
        //stops driving
        StopDriving();
    }
    public void Step20(double power, int distance, int timeoutS){
        runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(-distance);
        leftRear.setTargetPosition(-distance);
        rightRear.setPower(distance);
        SetToRunToPosition();
        DriveForward(power);
        while((timeoutS > runtime.seconds()) && (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy())){
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
            telemetry.addData("hello",runtime.seconds());
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
        SetTargetPosition(turncounts);
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
