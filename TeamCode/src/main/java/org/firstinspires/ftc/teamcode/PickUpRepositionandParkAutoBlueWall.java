package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class PickUpRepositionandParkAutoBlueWall extends LinearOpMode {
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
        //was 5400 then was 12600
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


        //drives forward to the yellow bricks and opens the wrist
        //Step1(1, (int)2.5 *countsper10cm);
        //closes the wrist
        //Step2();
        //moves back nearer to the wall
    /*  Step3(1, 2 * countsper10cm);
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
        //moves the linear slide up
        Step9(1, 290, 1);
        //moves forward
        DriveFor(1, (countsper10cm), 1);
        //(int)(2.6 * countsper10cm)
        //strafe right
        Step10(1, (int)(.9 * countsper10cm), 1);
        //drive to site
        Step12(1, (int)(1.9*countsper10cm), 2);
        //lower the linear slide
        Step13(1, -280, 2);
        //robot moves back slowly with the site in tow
        Step14(.5,(-24*countsper10cm), 3);
        //turns the robot 90 degrees to the right; value was 210
        //Step11(210, 3);
        //raises the linear slide
        Step15(1, 275, (int)1);
        //moves a touch off the wall
        //DriveFor(1, (int)(.2 * countsper10cm), 1);
        StrafeRight(1, (int)(2.5*countsper10cm), 3);
        //moves away from the construction site
        //DriveFor(.5, countsper10cm, 1);
        //strafes to the wall
        //StrafeRight(1, (int)(2*countsper10cm), 2);
        //lowers the linear slide
        Step17(1, -275, 1);
        //Step16(1,(int)(.3 * countsper10cm), (int)1);
        StrafeRight(1, (int)(1.6 * countsper10cm), 1);
        //moves back to under the tape
        Step16(.5,(int)(-.5 * countsper10cm), (int)1);
        //total should equal 3.8
        //lowers the linear slide
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
        StrafeLeft(power, distance, timeoutS);
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
    //strafe left to the tape
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
        DriveFor(power, distance, timeoutS);
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
        DriveFor(power, distance, timeoutS);
    }
    public void Step19(double power, int distance, int timeoutS){
        runtime.reset();
        ResetEncoders();
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(-distance);
        leftRear.setTargetPosition(distance);
        rightRear.setPower(distance);
        SetToRunToPosition();
        DriveForward(power);
        while ((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {
        }
        //stops driving
        StopDriving();
    }
    public void Step20(double power, int distance, int timeoutS){
        StrafeLeft(power, distance, timeoutS);
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
        while ((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {
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
        while ((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {
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
        while ((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {
        }
        //stops driving
        StopDriving();
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
        while ((leftFront.isBusy() || leftRear.isBusy() || rightFront.isBusy() || rightRear.isBusy()) && (runtime.seconds() < timeoutS)) {
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
