package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp (name = "arcadeMode")

public class arcadeMode extends OpMode {
    Robot dragonbot = new Robot();



    @Override
    public void init() {
        dragonbot.initRobot(hardwareMap);
        dragonbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //dragonbot.leftWingUp();
        //dragonbot.rightWingUp();
        //dragonbot.rightWing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.leftWing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        dragonbot.openLeftClaw();
        //dragonbot.openRightClaw();


    }

    @Override
    public void loop() {
        //telemetry to show output values for sticks (for testing purposes)
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick Y", gamepad1.right_stick_y);

        //telemetry.addData("Right Wing", dragonbot.rightWing);
        telemetry.addData("Left Wing", dragonbot.leftWing);

        telemetry.update();

        // Driving forwards and backwards using left_stick_y
        if (Math.abs(gamepad1.left_stick_y) > .1 && Math.abs(gamepad1.left_stick_x) < .3) {
            dragonbot.frontLeft.setPower(gamepad1.left_stick_y);
            dragonbot.frontRight.setPower(-gamepad1.left_stick_y);
            dragonbot.backLeft.setPower(-gamepad1.left_stick_y);
            dragonbot.backRight.setPower(-gamepad1.left_stick_y);

        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
        //Left strafe when left_stick_x is negative (left)
        if (gamepad1.left_stick_x < -.1 && Math.abs(gamepad1.left_stick_y) < .3) {
            dragonbot.frontLeft.setPower(-gamepad1.left_stick_x);
            dragonbot.frontRight.setPower(-gamepad1.left_stick_x);
            dragonbot.backLeft.setPower(-gamepad1.left_stick_x);
            dragonbot.backRight.setPower(gamepad1.left_stick_x);
        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
        //Right strafe when left_stick_x is positive (right)
        if (gamepad1.left_stick_x > .1 && Math.abs(gamepad1.left_stick_y) < .3) {
            dragonbot.frontLeft.setPower(-gamepad1.left_stick_x);
            dragonbot.frontRight.setPower(-gamepad1.left_stick_x);
            dragonbot.backLeft.setPower(-gamepad1.left_stick_x);
            dragonbot.backRight.setPower(gamepad1.left_stick_x);
        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
        //Rotate counterclockwise (pivot turn left) when right stick is pressed to the left
        if (gamepad1.right_stick_x < -.1) {
            dragonbot.frontLeft.setPower(-gamepad1.right_stick_x);
            dragonbot.frontRight.setPower(-gamepad1.right_stick_x);
            dragonbot.backLeft.setPower(gamepad1.right_stick_x);
            dragonbot.backRight.setPower(-gamepad1.right_stick_x);
        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
        //Rotate clockwise (pivot turn right) when right stick is pressed to the right
        if (gamepad1.right_stick_x > .1) {
            dragonbot.frontLeft.setPower(-gamepad1.right_stick_x);
            dragonbot.frontRight.setPower(-gamepad1.right_stick_x);
            dragonbot.backLeft.setPower(gamepad1.right_stick_x);
            dragonbot.backRight.setPower(-gamepad1.right_stick_x);
        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
        //Intake Forward Controls
        if (gamepad1.right_bumper) {
            dragonbot.intake.setPower(1);
        } else if (gamepad1.left_bumper) {
            //Intake  Backwards Controls
            dragonbot.intake.setPower(-1);
        }
        else {
            dragonbot.intake.setPower(0);
        }


        //Conveyor Down
        if (Math.abs(gamepad1.left_trigger) > .1) {
            dragonbot.conveyor.setPower(-gamepad1.left_trigger);
        } else if (Math.abs(gamepad1.right_trigger) > .1) {
            //Conveyor Up
            dragonbot.conveyor.setPower(gamepad1.right_trigger);
        }
        else {
            dragonbot.conveyor.setPower(0);
        }

        //Open/Close Claws
        /*if(gamepad1.dpad_left){
            dragonbot.closeRightClaw();
        }

         */
        if(gamepad1.dpad_up){
            dragonbot.closeLeftClaw();
        }
        if(gamepad1.dpad_down){
            dragonbot.openLeftClaw();
        }
        /*
        if(gamepad1.dpad_right){
            dragonbot.openRightClaw();
        }

         */



        //left Wing Up
        if (gamepad1.x) {
            dragonbot.leftWing.setPower(-.9);
        } else {
            dragonbot.leftWing.setPower(0);
        }

        if(gamepad1.a) {
            //right wing down
            dragonbot.leftWing.setPower(.3);
        } else {
            dragonbot.leftWing.setPower(0);
        }


        /*
        //Right Wing Up
        if (gamepad1.y) {
            dragonbot.rightWing.setPower(.9);
        } else {
            dragonbot.rightWing.setPower(0);
        }
        if (gamepad1.b) {
            //left wing down
            dragonbot.rightWing.setPower(-.3);
        } else {
            dragonbot.rightWing.setPower(0);
        }

         */



    }
}
