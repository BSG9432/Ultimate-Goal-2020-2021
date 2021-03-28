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

    public DcMotor intake;
    public DcMotor conveyor;

    public Servo leftGrabber;
    public Servo rightGrabber;
    public Servo leftWing; //not finalized (we don't know if it'll be able to support wobble)
    public Servo rightWing;

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

        intake = hardwareMap.dcMotor.get("intake");
        conveyor = hardwareMap.dcMotor.get("conveyor");

        leftGrabber = hardwareMap.servo.get("leftGrabber");
        rightGrabber = hardwareMap.servo.get("rightGrabber");
        leftWing = hardwareMap.servo.get("leftWing");
        rightWing = hardwareMap.servo.get("rightWing");

    }

    public void encoderDrive(double speed, double leftInches,
                             double rightInches, double timeout) {
        //Stay tuned :D
    }

    public void drive(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }

    public void strafe(double power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    public void diagonalRight(double power) {
        frontLeft.setPower(-power);
        backRight.setPower(power);
    }

    public void diagonalLeft(double power) {
        frontRight.setPower(power);
        backLeft.setPower(-power);
    }

    //open grabbers
    public void openLeftGrab() {
        leftGrabber.setPosition(.8);//We can't test these values yet but this is supposed to grab
    }

    public void openRightGrab() {
        rightGrabber.setPosition(.4);// We can't test these values yet but this is supposed to grab
    }

    //close grabbers
    public void closeLeftGrab () {
        leftGrabber.setPosition(.4);//We can't test these values yet but this is supposed to grab
    }
    public void closeRightGrab () {
        rightGrabber.setPosition(.8);// We can't test these values yet but this is supposed to grab
    }
    //Wings Up
    public void leftWingUp (){
        leftWing.setPosition(.7);
    }
    public void rightWingUp () {
        rightWing.setPosition(0);
    }
    //Wings Down
    public void leftWingDown (){
        leftWing.setPosition(.2);
    }
    public void rightWingDown () {
        rightWing.setPosition(.5);
    }

}
