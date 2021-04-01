package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp (name = "arcadeMode")

public class arcadeMode extends OpMode {
    Robot dragonbot = new Robot();
    int rightWingToggle = 0;
    int leftWingToggle = 0;
<<<<<<< HEAD
    int rightClawToggle = 0;
    int leftClawToggle = 0;
=======
    int rightGrabberToggle = 0;
    int leftGrabberToggle = 0;
    int buttonPress = 10; //check if counter increased to signify a button press
    boolean rightWingToggleStatus = false;
    boolean leftWingToggleStatus = false;
    boolean rightGrabberToggleStatus = false;
    boolean leftGrabberToggleStatus = false;
>>>>>>> 6927cef7333c482c5344ba1e9be00be4700dab0f


    @Override
    public void init() {
        dragonbot.initRobot(hardwareMap);
        dragonbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        dragonbot.frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        dragonbot.backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        dragonbot.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        dragonbot.backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        dragonbot.leftWingUp();
        dragonbot.rightWingUp();
        dragonbot.openLeftClaw();
        dragonbot.openRightClaw();

    }

    @Override
    public void loop() {
        //telemetry to show output values for sticks (for testing purposes)
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
        telemetry.addData("RightClaw: ", rightClawToggle);
        telemetry.addData("LeftClaw", leftClawToggle);
        telemetry.addData("RightWing: ", rightWingToggle);
        telemetry.addData("LeftWing", leftWingToggle);

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



        //Right Wing Toggle
        if (gamepad1.y) {
            //add to counter every time button is pressed
            rightWingToggle++;
        }
        if (rightWingToggle % 2 == 0) {
            //toggle to bring wing up
            dragonbot.rightWingUp();
        } else {
            //toggle to bring wing up
            dragonbot.rightWingDown();
        }

        //Left Wing Toggle
        if (gamepad1.x) {
            //add to counter every time button is pressed
            leftWingToggle++;
        }
        if (leftWingToggle % 2 == 0) {
            //toggle to open
            dragonbot.leftWingUp();
        } else {
            //turn off the right wing toggle
            dragonbot.leftWingDown();
        }

        //Right Claw Toggle
        if (gamepad1.b) {
            //add to counter every time button is pressed
            rightClawToggle++;
        }
        if (rightClawToggle % 2 == 0) {
            //toggle to open
            dragonbot.openRightClaw();
        } else {
            //turn off the right wing toggle
            dragonbot.closeRightClaw();
        }

        //Left Claw Toggle
        if (gamepad1.a) {
            //add to counter every time button is pressed
            leftClawToggle++;
        }
        if (leftClawToggle % 2 == 0) {
            //toggle to open
            dragonbot.openLeftClaw();
        } else {
            //turn off the right wing toggle
            dragonbot.closeLeftClaw();
        }


        //trying different toggle logic
        
       if (gamepad2.a){
           leftGrabberToggle++;
           if (leftGrabberToggle > buttonPress){
               if(leftGrabberToggleStatus == true){
                   dragonbot.openLeftGrab();
                   leftGrabberToggleStatus = false;
                   leftGrabberToggle = 0;
               } else if (leftGrabberToggleStatus == false){
                   if (leftGrabberToggle > buttonPress){
                       dragonbot.closeLeftGrab();
                       leftGrabberToggleStatus = true;
                       leftGrabberToggle = 0;
                   }
                   dragonbot.closeLeftGrab();
                   leftGrabberToggleStatus = true;
               }
           }
       }
    }
}


