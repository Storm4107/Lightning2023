package org.firstinspires.ftc.teamcode.downpour;

//import com.google.blocks.ftcrobotcontroller.runtime.ColorRangeSensorAccess;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;


@Autonomous(name = "DownpourAuto")
public class DownpourAuto extends LinearOpMode {

    private DcMotor FrontLeft;

    private DcMotor FrontRight;

    private DcMotor BackLeft;

    private DcMotor BackRight;

    private int LeftPos;

    private int RightPos;








    public void runOpMode() {

        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");


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

        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftPos = 0;
        RightPos = 0;

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













}
