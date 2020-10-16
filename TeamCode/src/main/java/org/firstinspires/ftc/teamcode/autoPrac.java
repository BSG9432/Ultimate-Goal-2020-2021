package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.KNO3AutoTransitioner.AutoTransitioner;

@Autonomous
public class autoPrac extends LinearOpMode {
   Robot bsgbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
       bsgbot.initRobot(hardwareMap);
      
        waitForStart();
        bsgbot.drive(1);
        sleep(1000);

        bsgbot.strafe(-.75);
        sleep(1000);

        bsgbot.diagonalRight(1);
        sleep(1000);

        bsgbot.diagonalLeft(1);
        sleep(1000);

        AutoTransitioner.transitionOnStop(this, "Robot Teleop");

    }
  
}