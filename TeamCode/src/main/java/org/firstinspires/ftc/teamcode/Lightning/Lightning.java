package org.firstinspires.ftc.teamcode.Lightning;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "Lightning_TeleOp_0")
public class Lightning extends LinearOpMode {

    private DcMotor FrontLeft;

    private DcMotor FrontRight;

    private DcMotor BackLeft;

    private DcMotor BackRight;

    private DcMotor Lift;

    private DcMotor Wench;

    private DcMotor Arm;

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor Wrist;

    private Servo ServoLeft;

    private Servo ServoRight;

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
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        Wench = hardwareMap.get(DcMotor.class, "Wench");
        Arm = hardwareMap.get(DcMotor.class, "Arm");
        Wrist = hardwareMap.get(DcMotor.class, "Wrist");
        ServoLeft = hardwareMap.get(Servo.class, "ServoLeft");
        ServoRight = hardwareMap.get(Servo.class, "ServoRight");

        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Wench.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Wench.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Wench.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // put initialization code here
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        Lift.setDirection(DcMotorSimple.Direction.REVERSE);


        //wait for start button to be pressed
        waitForStart();

        //put run code here
        while (opModeIsActive()) {

            double forward = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double turn = -gamepad1.right_stick_x;
            //double inout = gamepad2.right_stick_y;


            double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 2);

            FrontRight.setPower((forward - strafe - turn) / denominator);
            FrontLeft.setPower((forward + strafe + turn) / denominator);
            BackLeft.setPower((forward - strafe + turn) / denominator);
            BackRight.setPower((forward + strafe - turn) / denominator);


            Lift.setPower(-gamepad2.left_stick_y);
            Arm.setPower(gamepad2.right_stick_y);

            if (gamepad2.right_bumper) {
                Wench.setPower(1);
            } else if (gamepad2.left_bumper) {
                Wench.setPower(-1);
            } else {
                Wench.setPower(0);
            }
        }
    }
}


