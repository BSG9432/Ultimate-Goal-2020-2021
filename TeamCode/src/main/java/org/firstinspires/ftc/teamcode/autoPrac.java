package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous
public class autoPrac extends LinearOpMode {
   Robot bsgbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
       bsgbot.initRobot(hardwareMap);
      
        waitForStart();
        bsgbot.drive(1, 1000);
        bsgbot.strafe(-.75, 1000);
        bsgbot.diagonalRight(1, 1000);
        bsgbot.diagonalLeft(1, 1000);
        AutoTransitioner.transitionOnStop(this, "Robot Teleop");

    }
  
}