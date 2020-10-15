package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class RobotTeleOpExample extends OpMode {
    Robot bsgbot = new Robot();

    @Override
    public void init() {
        bsgbot.initDriveTrain(hardwareMap);
        
    }

    @Override
    public void loop() {

    }
}
