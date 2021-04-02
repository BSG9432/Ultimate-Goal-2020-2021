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
    int rightClawToggle = 0;
    int leftClawToggle = 0;
    int buttonPress = 23; //check if counter increased to signify a button press
    boolean rightWingToggleStatus = false;
    boolean leftWingToggleStatus = false;
    boolean rightClawToggleStatus = false;
    boolean leftClawToggleStatus = false;


    @Override
    public void init() {
        dragonbot.initRobot(hardwareMap);
        dragonbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        dragonbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //dragonbot.leftWingUp();
        //dragonbot.rightWingUp();
        dragonbot.rightWing.setPower(0);
        dragonbot.leftWing.setPower(0);

        dragonbot.openLeftClaw();
        dragonbot.openRightClaw();
        dragonbot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dragonbot.frontLeft.getMotorType().setAchieveableMaxRPMFraction(1.0);
        dragonbot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dragonbot.frontRight.getMotorType().setAchieveableMaxRPMFraction(1.0);
        dragonbot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dragonbot.backLeft.getMotorType().setAchieveableMaxRPMFraction(1.0);
        dragonbot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dragonbot.backRight.getMotorType().setAchieveableMaxRPMFraction(1.0);

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



        /*//Right Wing Toggle
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
        }*/

        //Right Claw Toggle
        if (gamepad1.b) {
            //add to counter every time button is pressed
            rightClawToggle++;
        }
        if (rightClawToggle % 2 == 0) {
            //toggle to open
            dragonbot.openRightClaw();
            rightClawToggle = 0;
        } else if (rightClawToggle % 2 == 1){
            //turn off the right wing toggle
            dragonbot.closeRightClaw();
            rightClawToggle = 0;
        }

        //Left Claw Toggle
        if (gamepad1.a) {
            //add to counter every time button is pressed
            leftClawToggle++;

        }
        if (leftClawToggle > buttonPress && leftClawToggle % 2 == 0) {
            //toggle to open
            dragonbot.openLeftClaw();
            leftClawToggle = 0;
        } else if (leftClawToggle > buttonPress && leftClawToggle % 2 == 1){
            //turn off the right wing toggle
            dragonbot.closeLeftClaw();
            leftClawToggle = 0;
        }


        //trying different toggle logic

       /*if (gamepad2.a){
           telemetry.addData("Gamepad2", "TRUE");
           rightClawToggle++;
           if (rightClawToggle > buttonPress){
               if(rightClawToggleStatus == true){
                   dragonbot.openRightClaw();
                   rightClawToggleStatus = false;
                   rightClawToggle = 0;
               } else if (rightClawToggleStatus == false){
                   if (rightClawToggle > buttonPress){
                       dragonbot.closeRightClaw();
                       rightClawToggleStatus = true;
                       rightClawToggle = 0;
                   }
                   dragonbot.closeLeftClaw();
                   rightClawToggleStatus = true;
               }
           }
           rightClawToggle = 0;
       }*/
        //Right Claw Toggle
        if (gamepad2.b) {
            //add to counter every time button is pressed
            rightClawToggle = rightClawToggle++;

            if (rightClawToggle > buttonPress && rightClawToggleStatus == true) {
                //toggle to open
                telemetry.addData("Yoink", "IF");

                dragonbot.openRightClaw();
                rightClawToggleStatus = false;
                rightClawToggle = 0;
            } else if (rightClawToggle > buttonPress && rightClawToggleStatus == false){
                //turn off the right wing toggle
                telemetry.addData("Yoink", "ELSE IF");

                dragonbot.closeRightClaw();
                rightClawToggleStatus = true;
                rightClawToggle = 0;
            } else {
                telemetry.addData("Yoink", "ELSE");

                rightClawToggle++;
            }
        }

        //Left Claw Toggle
        if (gamepad1.a) {
            //add to counter every time button is pressed
            leftClawToggle++;

        }
        if (leftClawToggle > buttonPress && leftClawToggle % 2 == 0) {
            //toggle to open
            dragonbot.openLeftClaw();
            leftClawToggle = 0;
        } else if (leftClawToggle > buttonPress && leftClawToggle % 2 == 1){
            //turn off the right wing toggle
            dragonbot.closeLeftClaw();
            leftClawToggle = 0;
        }
    }
}
