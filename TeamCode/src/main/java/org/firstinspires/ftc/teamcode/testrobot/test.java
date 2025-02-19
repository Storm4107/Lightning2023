package org.firstinspires.ftc.teamcode.testrobot;


//import com.google.blocks.ftcrobotcontroller.runtime.ColorRangeSensorAccess;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.exception.TargetPositionNotSetException;


@TeleOp(name = "test")
public class test extends LinearOpMode {

    private DcMotor FrontLeft;

    private DcMotor FrontRight;

    private DcMotor BackLeft;

    private DcMotor BackRight;

    private Servo ServoLeft;

    int degrees = 250;






    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // REV 40:1  1120
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 2.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 1.0;
    static final double TURN_SPEED = 0.5;


    // This function is executed when this Op Mode is selected from the Driver Station.

    @Override
    public void runOpMode() {

        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
        ServoLeft = hardwareMap.get(Servo.class, "ServoLeft");

        //speed = 0.1;

        //servoPos = 0;
        //oldServoButton = false;



        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


       /* FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        */


        // put initialization code here
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);




        //wait for start button to be pressed
        waitForStart();

        if (opModeIsActive()) {

            //put run blocks here
            while (opModeIsActive()) {


                double forward = gamepad1.left_stick_y;
                double strafe = -gamepad1.left_stick_x;
                double turn = -gamepad1.right_stick_x;
                double inout = -gamepad2.right_stick_y;

                double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 2);

               /* FrontRight.setPower((forward -strafe - turn) / denominator);
                FrontLeft.setPower((forward +strafe + turn) / denominator);
                BackLeft.setPower((forward -strafe + turn) / denominator);
                BackRight.setPower((forward +strafe - turn) / denominator);

                */



           /* FrontLeft.setPower(0.25);
            FrontRight.setPower(0.25);
            BackLeft.setPower(0.25);
            BackRight.setPower(0.25);
            sleep(5000);
            FrontLeft.setPower(-0.25);
            FrontRight.setPower(-0.25);
            BackLeft.setPower(-0.25);
            BackRight.setPower(-0.25);
            sleep(2000);
            FrontRight.setPower(0);
            FrontLeft.setPower(0);
            BackRight.setPower(0);
            BackLeft.setPower(0);
            */


                //trigger stuff
                if (gamepad1.left_trigger > 0) {
                    FrontRight.setPower(-1);

                } else if (gamepad1.right_trigger > 0) {
                    FrontRight.setPower(1);
                } else FrontRight.setPower(0);

                if(gamepad2.a){
                    ServoLeft.setPosition(1);
                }
                if(gamepad2.b){
                    ServoLeft.setPosition(0);
                }



                telemetry.addData("FrontLeft", FrontLeft.getCurrentPosition());
                telemetry.addData("FrontRight", FrontRight.getCurrentPosition());
                telemetry.addData("BackLeft", BackLeft.getCurrentPosition());
                telemetry.addData("BackRight", BackRight.getCurrentPosition());
                telemetry.update();

               /* boolean motorButton = gamepad2.a;
                boolean servoButton = gamepad2.b;


                if(servoButton && !oldServoButton) {
                    if(servoPos == 0 && gamepad2.b ) {
                        ServoLeft.setPosition(1);
                        servoPos = 1;
                    }
                    else {
                        ServoLeft.setPosition(0.5);
                        servoPos = 0;
                    }

                */



               // oldServoButton = servoButton;

                }
            }
            //else ServoLeft.setPosition(0);

                /*Right servo
                if (gamepad2.right_bumper) {
                    ServoRight.setPosition(-1);
                }
                if (gamepad2.right_trigger > 0) {                    ServoRight.setPosition(1);
                }
                //else ServoRight.setPosition(0);
                 */

                if (gamepad2.a);
                FrontLeft.setTargetPosition(degrees);
                FrontLeft.setPower(0.5);


        }
    }





