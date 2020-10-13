package org.firstinspires.ftc.teamcode.KNO3AutoTransitioner;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Disabled
@Autonomous(name = "AutoTransitioningAuto2")
public class AutoTransitioningAuto2 extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Initializing Here", true);

        AutoTransitioner.transitionOnStop(this, "Robot Teleop");
        // AutoTransitioner used in init()
    }

    @Override
    public void loop() {
        telemetry.addData("Timer", getRuntime());
    }
}
