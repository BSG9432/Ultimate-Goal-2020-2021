package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Hardware.Robot;


@TeleOp (name = "PID Testing")
public class pidTesting extends LinearOpMode {
    // Copy your PIDF Coefficients here
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(.45, .05, .45, 12.2);
    Robot bsgbot = new Robot();
    double currentVelocity;
    double maxVelocity = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        bsgbot.initRobot(hardwareMap);

        // Turns on bulk reading
        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // RUE limits max motor speed to 85% by default
        // Raise that limit to 100%
        MotorConfigurationType motorConfigurationType = bsgbot.flywheel.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(1.0);
        bsgbot.flywheel.setMotorType(motorConfigurationType);

        // Turn on RUN_USING_ENCODER
        bsgbot.flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set PIDF Coefficients with voltage compensated feedforward value
        bsgbot.flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(
                MOTOR_VELO_PID.p, MOTOR_VELO_PID.i, MOTOR_VELO_PID.d,
                MOTOR_VELO_PID.f * 12 / hardwareMap.voltageSensor.iterator().next().getVoltage()
        ));

        // Insert whatever other initialization stuff you do here

        waitForStart();

        if (isStopRequested()) return;

        while (!isStopRequested()) {
            if (gamepad1.dpad_left){
                bsgbot.flywheel.setVelocity(5000);
            }
            if (gamepad1.dpad_right){
                bsgbot.flywheel.setVelocity(0);
            }

            //TELEOP STUFF

            // Driving forwards and backwards using left_stick_y
            if (Math.abs(gamepad1.left_stick_y) > .1 && Math.abs(gamepad1.left_stick_x) < .3) {
                bsgbot.frontLeft.setPower(-gamepad1.left_stick_y);
                bsgbot.frontRight.setPower(-gamepad1.left_stick_y);
                bsgbot.backLeft.setPower(-gamepad1.left_stick_y);
                bsgbot.backRight.setPower(-gamepad1.left_stick_y);

            } else {
                bsgbot.frontLeft.setPower(0);
                bsgbot.frontRight.setPower(0);
                bsgbot.backLeft.setPower(0);
                bsgbot.backRight.setPower(0);
            }
            //Left strafe when left_stick_x is negative (left)
            if (gamepad1.left_stick_x < -.1 && Math.abs(gamepad1.left_stick_y) < .3) {
                bsgbot.frontLeft.setPower(gamepad1.left_stick_x);
                bsgbot.frontRight.setPower(-gamepad1.left_stick_x);
                bsgbot.backLeft.setPower(-gamepad1.left_stick_x);
                bsgbot.backRight.setPower(gamepad1.left_stick_x);
            } else {
                bsgbot.frontLeft.setPower(0);
                bsgbot.frontRight.setPower(0);
                bsgbot.backLeft.setPower(0);
                bsgbot.backRight.setPower(0);
            }
            //Right strafe when left_stick_x is positive (right)
            if (gamepad1.left_stick_x > .1 && Math.abs(gamepad1.left_stick_y) < .3) {
                bsgbot.frontLeft.setPower(gamepad1.left_stick_x);
                bsgbot.frontRight.setPower(-gamepad1.left_stick_x);
                bsgbot.backLeft.setPower(-gamepad1.left_stick_x);
                bsgbot.backRight.setPower(gamepad1.left_stick_x);
            } else {
                bsgbot.frontLeft.setPower(0);
                bsgbot.frontRight.setPower(0);
                bsgbot.backLeft.setPower(0);
                bsgbot.backRight.setPower(0);
            }
            //Rotate counterclockwise (pivot turn left) when right stick is pressed to the left
            if (gamepad1.right_stick_x < -.1) {
                bsgbot.frontLeft.setPower(gamepad1.right_stick_x);
                bsgbot.frontRight.setPower(-gamepad1.right_stick_x);
                bsgbot.backLeft.setPower(gamepad1.right_stick_x);
                bsgbot.backRight.setPower(-gamepad1.right_stick_x);
            } else {
                bsgbot.frontLeft.setPower(0);
                bsgbot.frontRight.setPower(0);
                bsgbot.backLeft.setPower(0);
                bsgbot.backRight.setPower(0);
            }
            //Rotate clockwise (pivot turn right) when right stick is pressed to the right
            if (gamepad1.right_stick_x > .1) {
                bsgbot.frontLeft.setPower(gamepad1.right_stick_x);
                bsgbot.frontRight.setPower(-gamepad1.right_stick_x);
                bsgbot.backLeft.setPower(gamepad1.right_stick_x);
                bsgbot.backRight.setPower(-gamepad1.right_stick_x);
            } else {
                bsgbot.frontLeft.setPower(0);
                bsgbot.frontRight.setPower(0);
                bsgbot.backLeft.setPower(0);
                bsgbot.backRight.setPower(0);
            }
        }
    }
}