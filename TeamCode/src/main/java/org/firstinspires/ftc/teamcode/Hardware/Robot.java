package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    //Hardware
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor lift; //Lifting the wobble goal over the perimeter
    public DcMotor intake;
    public DcMotor flywheel;
    public DcMotor conveyor;

    public Servo leftGrab;
    public Servo rightGrab;
    public Servo leftFlap; //not finalized (we don't know if it'll be able to support wobble)
    public Servo rightFlap;
<<<<<<< HEAD

=======
>>>>>>> ce03afe3c21ff46cdcc468694546043d49b1ec9c
    Telemetry telemetry;

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

        leftGrab = hardwareMap.servo.get("leftGrab");
        rightGrab = hardwareMap.servo.get("rightGrab");
        leftFlap = hardwareMap.servo.get("leftFlap");
        rightFlap = hardwareMap.servo.get("rightFlap");

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
    public void openLeftGrab (){
        leftGrab.setPosition(0);//We can't test these values yet but this is supposed to grab
    }
    public void openRightGrab (){
        rightGrab.setPosition(0);// We can't test these values yet but this is supposed to grab
    }
    public void closeLeftGrab () {
        leftGrab.setPosition(1);//We can't test these values yet but this is supposed to grab
    }
    public void closeRightGrab () {
        rightGrab.setPosition(1);// We can't test these values yet but this is supposed to grab
    }
    public void useLeftFlap (double position){
        leftFlap.setPosition(position);
    }
    public void useRightFlap (double position) {
        rightFlap.setPosition(position);
    }
}
