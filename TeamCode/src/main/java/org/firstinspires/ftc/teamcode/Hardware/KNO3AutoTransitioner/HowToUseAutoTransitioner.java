package org.firstinspires.ftc.teamcode.KNO3AutoTransitioner;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.KNO3AutoTransitioner.AutoTransitioner;

@Autonomous(name = "HowToUseAutoTransitioner")
public class HowToUseAutoTransitioner extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initializing Here", true);
        telemetry.update();


        AutoTransitioner.transitionOnStop(this, "Robot Teleop");
        // AutoTransitioner used before waitForStart()
        waitForStart();


        telemetry.addData("Timer", new Func<Double>() {
            @Override
            public Double value() {
                return getRuntime();
            }
        });
        while (opModeIsActive()) {
            telemetry.update();
        }
    }
}