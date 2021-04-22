package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

import java.util.Locale;

@Autonomous(name = "imu", group = "testing")
public class imu extends LinearOpMode {

    Robot bsgbot = new Robot();
    Orientation angles;
    double currentAngle;
    double targetAngle;


    @Override
    public void runOpMode() throws InterruptedException {
        bsgbot.initRobot(hardwareMap);
        bsgbot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgbot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgbot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgbot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bsgbot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgbot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgbot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgbot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        composeTelemetry();
        telemetry.update();

        waitForStart();

        rotate(45); //left
        rotate(-45);
    }


    public double getHeading() {
        Orientation angles = bsgbot.imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        double heading = angles.firstAngle;
        return heading;
    }

    private void rotate(int degrees)
    {
        currentAngle = -getHeading();
        targetAngle = currentAngle + degrees;
        double power = .5;

        // restart imu movement tracking.
        if (degrees < 0)
        {   // turn left.
            while (opModeIsActive() && currentAngle >= targetAngle){
                currentAngle = -getHeading();
                // set power to rotate.
                bsgbot.frontLeft.setPower(-power);
                bsgbot.backLeft.setPower(-power);
                bsgbot.frontRight.setPower(power);
                bsgbot.backRight.setPower(power);
                telemetry.addData("Current Angle: ", currentAngle);
                telemetry.addData("Target Angle: ", targetAngle);
                telemetry.update();
            }
        }
        else if (degrees > 0)
        {
            //turn right
            while (opModeIsActive() && currentAngle <= targetAngle){
                currentAngle = -getHeading();
                // set power to rotate.
                bsgbot.frontLeft.setPower(power);
                bsgbot.backLeft.setPower(power);
                bsgbot.frontRight.setPower(-power);
                bsgbot.backRight.setPower(-power);
                telemetry.addData("Current Angle: ", currentAngle);
                telemetry.addData("Target Angle: ", targetAngle);
                telemetry.update();
            }
        }
        else return;

        //turn the motors off.
        bsgbot.frontLeft.setPower(0);
        bsgbot.backLeft.setPower(0);
        bsgbot.frontRight.setPower(0);
        bsgbot.backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);
    }


    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            // Acquiring the angles is relatively expensive; we don't want
            // to do that in each of the three items that need that info, as that's
            // three times the necessary expense.
            angles   = bsgbot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return bsgbot.imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return bsgbot.imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });
    }
    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
    public double getCurrentAngle(){
        return currentAngle;
    }
    public double getTargetAngle(){
        return targetAngle;
    }
}
