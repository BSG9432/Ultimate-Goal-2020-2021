package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class KMS extends LinearOpMode {
    DcMotor frontLeft,frontRight,backLeft,backRight;

    Servo grabberLeft,grabberRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        grabberLeft = hardwareMap.servo.get("grabberLeft");
        grabberRight = hardwareMap.servo.get("grabberRight");

        waitForStart();
        drive(0.5, 1000);
        drive(1.0,3000);
        drive(-0.5, 1000);
        drive(-1.0,3000);

    }
    public void drive(double power, long sleep){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
        sleep(sleep);
    }
}

