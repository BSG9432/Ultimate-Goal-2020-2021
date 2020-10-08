package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp (name = "TylaOpPrac")
public class TylaOpPrac extends OpMode {
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontRight;
    DcMotor frontLeft;

    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("backLeft");
        frontLeft = hardwareMap.dcMotor.get("backLeft");

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            backLeft.setPower(-gamepad1.left_stick_y);
            frontLeft.setPower(-gamepad1.left_stick_y);


        } else {

            backLeft.setPower(0);
            frontLeft.setPower(0);
        }
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            backRight.setPower(-gamepad1.right_stick_y);
            frontRight.setPower(-gamepad1.right_stick_y);

        } else {
            backRight.setPower(0);
            frontRight.setPower(0);
        }
        if (gamepad1.left_trigger > .1) {
            diagonalLeft(gamepad1.left_trigger);
        }
        if (gamepad1.right_trigger > .1) {
            diagonalRight(gamepad1.right_trigger);
        }

    }


    public void diagonalLeft (double power) {
        frontRight.setPower(power);
        backLeft.setPower(-power);
}
    public void diagonalRight (double power) {
        frontLeft.setPower(-power);
        backRight.setPower(power);
    }


}
