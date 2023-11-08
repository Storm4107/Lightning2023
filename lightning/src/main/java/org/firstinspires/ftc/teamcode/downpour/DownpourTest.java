
package org.firstinspires.ftc.teamcode.downpour;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;


@TeleOp(name = "DownpourTest")
public class DownpourTest extends LinearOpMode {

    private DcMotor FrontLeft;

    private DcMotor FrontRight;

    private DcMotor BackLeft;

    private DcMotor BackRight;

    private DcMotor LeftArmM;

    private DcMotor RightArmM;

    private DcMotor Elbow;

    private DcMotor Wrist;

    private ElapsedTime runtime = new ElapsedTime();

    private CRServo ServoLeft;

    private CRServo ServoRight;

    private int LeftPos;

    private int RightPos;

    static final double COUNTS_PER_MOTOR_REV = 1120;    // REV 40:1  1120
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 2.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 1.0;
    static final double TURN_SPEED = 0.5;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
        LeftArmM = hardwareMap.get(DcMotor.class, "LeftArmM");
        RightArmM = hardwareMap.get(DcMotor.class, "RightArmM");
        Elbow = hardwareMap.get(DcMotor.class, "Elbow");
        Wrist = hardwareMap.get(DcMotor.class, "Wrist");
        ServoLeft = hardwareMap.get(CRServo.class, "ServoLeft");
        ServoRight = hardwareMap.get(CRServo.class, "ServoRight");


        LeftArmM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightArmM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftArmM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightArmM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftArmM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightArmM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        // put initialization code here
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftArmM.setDirection(DcMotorSimple.Direction.FORWARD);

        LeftPos = 0;
        RightPos = 0;


        //wait for start button to be pressed
        waitForStart();


        //Negative is forward. Positive is backwards
        drive(-1000, -1000, .25);
        drive(1000, 1000, .25);
    }
    private void drive(int leftTarget, int rightTarget, double speed) {
        LeftPos += leftTarget;
        RightPos += rightTarget;

        FrontLeft.setTargetPosition(LeftPos);
        FrontRight.setTargetPosition(RightPos);
        BackLeft.setTargetPosition(LeftPos);
        BackRight.setTargetPosition(RightPos);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLeft.setPower(speed);
        FrontRight.setPower(speed);
        BackRight.setPower(speed);
        BackLeft.setPower(speed);

        while (opModeIsActive() && FrontRight.isBusy() && FrontLeft.isBusy() && BackLeft.isBusy() && BackRight.isBusy()) {
            idle();
        }
    }




                //put run blocks here
                while (opModeIsActive()) {

                    double forward = gamepad1.left_stick_y;
                    double strafe = -gamepad1.left_stick_x;
                    double turn = -gamepad1.right_stick_x;
                    double inout = -gamepad2.right_stick_y;


                    double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 2);

                    FrontRight.setPower((forward - strafe - turn) / denominator);
                    FrontLeft.setPower((forward + strafe + turn) / denominator);
                    BackLeft.setPower((forward - strafe + turn) / denominator);
                    BackRight.setPower((forward + strafe - turn) / denominator);

                    LeftArmM.setPower(gamepad2.right_stick_y);
                    RightArmM.setPower(gamepad2.right_stick_y);

                    Elbow.setPower(gamepad2.left_stick_y);


                    if (gamepad2.dpad_right) {
                        Wrist.setPower(1);
                    } else if (gamepad2.dpad_down) {
                        Wrist.setPower(-1);
                    } else {
                        Wrist.setPower(0);

                        //Left servo
                        if (gamepad2.left_bumper) {
                            ServoLeft.setPower(1);
                        } else if (gamepad2.left_trigger > 0) {
                            ServoLeft.setPower(-1);
                        } else ServoLeft.setPower(0);

                        //Right servo
                        if (gamepad2.right_bumper) {
                            ServoRight.setPower(-1);
                        } else if (gamepad2.right_trigger > 0) {
                            ServoRight.setPower(1);
                        } else ServoRight.setPower(0);











                            }
                        }


                }
            }
        }





