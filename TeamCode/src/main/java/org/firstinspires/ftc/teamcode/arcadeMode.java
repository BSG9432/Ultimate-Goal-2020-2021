package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp (name = "arcadeMode")

public class arcadeMode extends OpMode {
    Robot dragonbot = new Robot();
    ElapsedTime timer = new ElapsedTime();



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
        dragonbot.wing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        dragonbot.openClaw();
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
        telemetry.addData("Flywheel Velocity", dragonbot.flywheel.getVelocity());
        telemetry.addData("Flywheel Ticks", dragonbot.flywheel.getCurrentPosition());
        telemetry.addData("Flywheel Power", dragonbot.flywheel.getPower());
        telemetry.addData("Revs per Sec", (float) ((dragonbot.flywheel.getCurrentPosition() / 537.6) / timer.seconds()));

        telemetry.update();

        // Driving forwards and backwards using left_stick_y
        if (Math.abs(gamepad1.left_stick_y) > .1 && Math.abs(gamepad1.left_stick_x) < .3) {
            dragonbot.frontLeft.setPower(-gamepad1.left_stick_y);
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
            dragonbot.frontLeft.setPower(gamepad1.left_stick_x);
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
            dragonbot.frontLeft.setPower(gamepad1.left_stick_x);
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
            dragonbot.frontLeft.setPower(gamepad1.right_stick_x);
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
            dragonbot.frontLeft.setPower(gamepad1.right_stick_x);
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
            dragonbot.intake.setPower(-1);
        } else if (gamepad1.left_bumper) {
            //Intake  Backwards Controls
            dragonbot.intake.setPower(1);
        }
        else {
            dragonbot.intake.setPower(0);
        }


        //Conveyor Down
        if (Math.abs(gamepad1.left_trigger) > .1) {
            dragonbot.conveyor.setPower(gamepad1.left_trigger);
        } else if (Math.abs(gamepad1.right_trigger) > .1) {
            //Conveyor Up
            dragonbot.conveyor.setPower(-gamepad1.right_trigger);
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
            dragonbot.closeClaw();
        }
        if(gamepad1.dpad_down){
            dragonbot.openClaw();
        }
        /*
        if(gamepad1.dpad_right){
            dragonbot.openRightClaw();
        }

         */



        //left Wing Up
        if (gamepad1.x) {
            dragonbot.wing.setPower(-1);
        } else {
            dragonbot.wing.setPower(0);
        }

        if(gamepad1.a) {
            //right wing down
            dragonbot.wing.setPower(1);
        } else {
            dragonbot.wing.setPower(0);
        }



        //Flywheel Testing
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            dragonbot.flywheel.setPower(gamepad2.left_stick_y);
            timer.startTime();
            /*if(timer.milliseconds() >= 1000){
                timer.reset();
                dragonbot.flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                timer.startTime();
            }*/
           // dragonbot.flywheel.setPower(gamepad2.left_trigger);

        } else {
          //  dragonbot.flywheel.setPower(0);
        }



    }
}
