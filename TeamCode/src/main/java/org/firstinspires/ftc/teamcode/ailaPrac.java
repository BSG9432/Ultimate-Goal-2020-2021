package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class ailaPrac extends OpMode {
    Robot dragonbot = new Robot();
    @Override
    public void init() {
      dragonbot.initRobot(hardwareMap);
        dragonbot.left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dragonbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
      dragonbot.frontLeft.setPower(.25);
      dragonbot.backLeft.setPower(.25);
      dragonbot.frontRight.setPower(.25);
      dragonbot.backRight.setPower(.25);


    }
}
  