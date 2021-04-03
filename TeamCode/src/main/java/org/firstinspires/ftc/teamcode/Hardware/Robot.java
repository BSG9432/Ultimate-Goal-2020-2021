package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    public Servo leftClaw;
    public Servo rightClaw;
    public DcMotor leftWing;
    public DcMotor rightWing;

    public Telemetry telemetry;

    //Constructor
    public Robot() {

    }
//Claws need to be changed to motors
    //initialize our hardware
    public void initRobot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        intake = hardwareMap.dcMotor.get("intake");
        conveyor = hardwareMap.dcMotor.get("conveyor");

        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");
        leftWing = hardwareMap.dcMotor.get("leftWing");
        rightWing = hardwareMap.dcMotor.get("rightWing");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void encoderDrive(double speed, double leftInches,
                             double rightInches, double timeout) {
        //Stay tuned :D
    }

    public void drive(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
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

    //open Claws
    public void openLeftClaw() {
        leftClaw.setPosition(.8);//We can't test these values yet but this is supposed to grab
    }
    public void openRightClaw() {
        rightClaw.setPosition(.4);// We can't test these values yet but this is supposed to grab
    }

    //close Claws
    public void closeLeftClaw () {
        leftClaw.setPosition(.4);//We can't test these values yet but this is supposed to grab
    }
    public void closeRightClaw () {
        rightClaw.setPosition(.77);// We can't test these values yet but this is supposed to grab
    }
    //Wings Up
    public void leftWingUp (){
        leftWing.setPower(-.4);
    }
    public void rightWingUp () {
        rightWing.setPower(.4);
    }
    //Wings Down
    public void leftWingDown (){
        leftWing.setPower(.4);
    }
    public void rightWingDown () {
        rightWing.setPower(-.4);
    }

}
