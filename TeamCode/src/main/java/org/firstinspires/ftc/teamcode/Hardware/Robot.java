package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {
    //Hardware
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor lift;
    public DcMotor intake;
    public DcMotor flywheel;
    public DcMotor conveyor;
    public Servo claw;
    public Servo tilt; //not finalized (we don't know if it'll be able to support wobble)

    //Constructor
    public Robot() {

    }

    //initialize our hardware
    public void initRobot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        lift = hardwareMap.dcMotor.get("lift");
        intake = hardwareMap.dcMotor.get("intake");
        flywheel = hardwareMap.dcMotor.get("flywheel");
        conveyor = hardwareMap.dcMotor.get("conveyor");

        claw = hardwareMap.servo.get("claw");
        tilt = hardwareMap.servo.get("tilt");

    }

    public void encoderDrive(double speed, double leftInches,
                             double rightInches, double timeout){
        //Stay tuned :D
    }

    public void drive (double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }
    public void strafe (double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    public void diagonalRight (double power) {
        frontLeft.setPower(-power);
        backRight.setPower(power);
    }
    public void diagonalLeft (double power) {
        frontRight.setPower(power);
        backLeft.setPower(-power);
    }
}
