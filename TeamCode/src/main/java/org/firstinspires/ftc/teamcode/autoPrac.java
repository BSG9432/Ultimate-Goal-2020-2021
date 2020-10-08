package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous
public class autoPrac extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft=hardwareMap.dcMotor.get("frontLeft");
        frontRight=hardwareMap.dcMotor.get("frontLeft");
        backLeft=hardwareMap.dcMotor.get("frontLeft");
        backRight=hardwareMap.dcMotor.get("frontLeft");

        waitForStart();
        drive(1, 1000);
        strafe(-.75, 1000);
        diagonalRight(1, 1000);
        diagonalLeft(1, 1000);

    }
    public void drive (double power, long sleep) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
        sleep(sleep);
    }
    public void strafe (double power, long sleep) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(sleep);

    }
    public void diagonalRight (double power, long sleep) {
        frontLeft.setPower(-power);
        backRight.setPower(power);
        sleep(sleep);
    }
    public void diagonalLeft (double power, long sleep) {
        frontRight.setPower(power);
        backLeft.setPower(-power);
        sleep(sleep);
    }
}