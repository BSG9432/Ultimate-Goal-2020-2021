package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

@TeleOp (name = "arcadeMode")

public class arcadeMode extends OpMode {
    Robot dragonbot = new Robot();
    boolean flywheelToggle = false;
<<<<<<< HEAD
    boolean wingRightToggle = false;
    boolean wingLeftToggle = false;
    boolean grabberLeftToggle = false;
    boolean grabberRightToggle = false;
=======
>>>>>>> ce03afe3c21ff46cdcc468694546043d49b1ec9c

    @Override
    public void init() {
        dragonbot.initRobot(hardwareMap);
        dragonbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        //telemetry to show output values for sticks (for testing purposes)
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick Y", gamepad1.right_stick_y);

        telemetry.update();

        //Austin Controls (SHOOT; Inverted to make shooter the "front" of the robot)
        //Driving forwards and backwards using left_stick_y
        if (Math.abs(gamepad1.left_stick_y) > .1 && Math.abs(gamepad1.left_stick_x) < .3) {
            dragonbot.frontLeft.setPower(-gamepad1.left_stick_y);
            dragonbot.frontRight.setPower(gamepad1.left_stick_y);
            dragonbot.backLeft.setPower(-gamepad1.left_stick_y);
            dragonbot.backRight.setPower(gamepad1.left_stick_y);

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
            dragonbot.frontLeft.setPower(-gamepad1.right_stick_x);
            dragonbot.frontRight.setPower(gamepad1.right_stick_x);
            dragonbot.backLeft.setPower(-gamepad1.right_stick_x);
            dragonbot.backRight.setPower(gamepad1.right_stick_x);
        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
        //Rotate clockwise (pivot turn right) when right stick is pressed to the right
        if (gamepad1.right_stick_x > .1) {
            dragonbot.frontLeft.setPower(-gamepad1.right_stick_x);
            dragonbot.frontRight.setPower(gamepad1.right_stick_x);
            dragonbot.backLeft.setPower(-gamepad1.right_stick_x);
            dragonbot.backRight.setPower(gamepad1.right_stick_x);
        } else {
            dragonbot.frontLeft.setPower(0);
            dragonbot.frontRight.setPower(0);
            dragonbot.backLeft.setPower(0);
            dragonbot.backRight.setPower(0);
        }
<<<<<<< HEAD
        //Flywheel Controls
        if (gamepad1.right_bumper) {
            if (flywheelToggle == false) {
                //turning on the flywheel
=======
        if(gamepad1.a) {
            if(flywheelToggle == false){
                //turn on the flywheel
>>>>>>> ce03afe3c21ff46cdcc468694546043d49b1ec9c
                flywheelToggle = true;
            } else {
                //turn off the flywheel
                flywheelToggle = false;
            }
        }
<<<<<<< HEAD
        //Conveyor Down
        if (Math.abs(gamepad1.left_trigger) > .1) {
            dragonbot.conveyor.setPower(-gamepad1.left_trigger);
        }
        //Conveyor Up
        if (Math.abs(gamepad1.right_trigger) > .1) {
            dragonbot.conveyor.setPower(gamepad1.right_trigger);
            //Wing Right Up
            if (gamepad1.b) {
                if (wingRightToggle == false) {
                    //turning on the right wing toggle
                    wingRightToggle = true;
                } else {
                    //turn off the right wing toggle
                    wingRightToggle = false;
                }
            }
            //Wing Left Up
            if (gamepad1.x) {
                if (wingLeftToggle == false) {
                    //turning on the right wing toggle
                    wingLeftToggle = true;
                } else {
                    //turn off the right wing toggle
                    wingLeftToggle = false;
                }
            }
            //Right Grabber
            if (gamepad1.y) {
                if (grabberRightToggle == false) {
                    //turning on the right wing toggle
                    wingRightToggle = true;
                } else {
                    //turn off the right wing toggle
                    wingRightToggle = false;
                }
            }
            //Left Grabber
            if (gamepad1.a) {
                if (grabberLeftToggle == false) {
                    //turning on the right wing toggle
                    wingLeftToggle = true;
                } else {
                    //turn off the right wing toggle
                    wingLeftToggle = false;
                }
            }
=======
>>>>>>> ce03afe3c21ff46cdcc468694546043d49b1ec9c

            //Aila Controls
            // Driving forwards and backwards using left_stick_y
            if (Math.abs(gamepad1.left_stick_y) > .1 && Math.abs(gamepad1.left_stick_x) < .3) {
                dragonbot.frontLeft.setPower(gamepad2.left_stick_y);
                dragonbot.frontRight.setPower(-gamepad2.left_stick_y);
                dragonbot.backLeft.setPower(gamepad2.left_stick_y);
                dragonbot.backRight.setPower(-gamepad2.left_stick_y);

            } else {
                dragonbot.frontLeft.setPower(0);
                dragonbot.frontRight.setPower(0);
                dragonbot.backLeft.setPower(0);
                dragonbot.backRight.setPower(0);
            }
            //Left strafe when left_stick_x is negative (left)
            if (gamepad1.left_stick_x < -.1 && Math.abs(gamepad1.left_stick_y) < .3) {
                dragonbot.frontLeft.setPower(-gamepad1.left_stick_x);
                dragonbot.frontRight.setPower(gamepad1.left_stick_x);
                dragonbot.backLeft.setPower(gamepad1.left_stick_x);
                dragonbot.backRight.setPower(-gamepad1.left_stick_x);
            } else {
                dragonbot.frontLeft.setPower(0);
                dragonbot.frontRight.setPower(0);
                dragonbot.backLeft.setPower(0);
                dragonbot.backRight.setPower(0);
            }
            //Right strafe when left_stick_x is positive (right)
            if (gamepad1.left_stick_x > .1 && Math.abs(gamepad1.left_stick_y) < .3) {
                dragonbot.frontLeft.setPower(-gamepad1.left_stick_x);
                dragonbot.frontRight.setPower(gamepad1.left_stick_x);
                dragonbot.backLeft.setPower(gamepad1.left_stick_x);
                dragonbot.backRight.setPower(-gamepad1.left_stick_x);
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
            //Intake Controls
            if (gamepad2.right_bumper) {
                dragonbot.intake.setPower(1);
            }
            //Conveyor Down
            if (Math.abs(gamepad2.left_trigger) > .1) {
                dragonbot.conveyor.setPower(-gamepad1.left_trigger);
            }
            //Conveyor Up
            if (Math.abs(gamepad2.right_trigger) > .1) {
                dragonbot.conveyor.setPower(gamepad1.right_trigger);
            }
        }
    }
}
