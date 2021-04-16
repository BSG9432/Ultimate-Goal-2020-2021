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

    public Servo claw;
    public DcMotor wing;
    //public DcMotor flywheel;

    public Telemetry telemetry;

    //Constructor
    public Robot() {

    }
    //initialize our hardware
    public void initRobot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        intake = hardwareMap.dcMotor.get("intake"); //3
        conveyor = hardwareMap.dcMotor.get("conveyor"); //2

        claw = hardwareMap.servo.get("claw"); //5
        wing = hardwareMap.dcMotor.get("wing");

        //flywheel = hardwareMap.dcMotor.get("flywheel");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

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
    public void openClaw() {
        claw.setPosition(.8);//We can't test these values yet but this is supposed to grab
    }

    //close Claws
    public void closeClaw () {
        claw.setPosition(.4);//We can't test these values yet but this is supposed to grab
    }

    //Wings Up
    public void wingUp (){
        wing.setPower(-.4);
    }

    //Wings Down
    public void wingDown () {
        wing.setPower(.4);
    }

}
