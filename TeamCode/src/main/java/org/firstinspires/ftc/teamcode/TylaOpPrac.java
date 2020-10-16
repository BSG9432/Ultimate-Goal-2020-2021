package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp (name = "TylaOpPrac")
public class TylaOpPrac extends OpMode {
    Robot bsgbot = new Robot();
    @Override
    public void init() {
         bsgbot.initRobot(hardwareMap);


         
        bsgbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bsgbot.backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bsgbot.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            bsgbot.backLeft.setPower(-gamepad1.left_stick_y);
            bsgbot.frontLeft.setPower(-gamepad1.left_stick_y);


        } else {

            bsgbot.backLeft.setPower(0);
            bsgbot.frontLeft.setPower(0);
        }
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            bsgbot.backRight.setPower(-gamepad1.right_stick_y);
            bsgbot.frontRight.setPower(-gamepad1.right_stick_y);

        } else {
            bsgbot.backRight.setPower(0);
            bsgbot.frontRight.setPower(0);
        }
        if (gamepad1.left_trigger > .1) {
            bsgbot.diagonalLeft(gamepad1.left_trigger);
        }
        if (gamepad1.right_trigger > .1) {
            bsgbot.diagonalRight(gamepad1.right_trigger);
        }

    }


    public void diagonalLeft (double power) {
        bsgbot.frontRight.setPower(power);
        bsgbot.backLeft.setPower(-power);
}
    public void diagonalRight (double power) {
        bsgbot.frontLeft.setPower(-power);
        bsgbot.backRight.setPower(power);
    }


}
