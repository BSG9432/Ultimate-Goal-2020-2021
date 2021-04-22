package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

import java.util.Locale;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;


@TeleOp(name = "imuTeleTest")
public class imuTeleTest extends OpMode {
    Robot bsgbot = new Robot();

    Orientation angles;
    public double currentAngle = -getHeading(); //get heading of IMU
    public double targetAngle = 0;


    @Override
    public void init() {
        bsgbot.initRobot(hardwareMap);
        bsgbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgbot.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgbot.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //bsgbot.leftWingUp();
        //bsgbot.rightWingUp();
        //bsgbot.rightWing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bsgbot.wing.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        bsgbot.openClaw();
        //bsgbot.openRightClaw();


    }

    @Override
    public void loop() {
        //telemetry to show output values for sticks (for testing purposes)
        bsgbot.telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        bsgbot.telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        bsgbot.telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        bsgbot.telemetry.addData("Right Stick Y", gamepad1.right_stick_y);
        bsgbot.telemetry.addData("Current Angle", currentAngle);
        bsgbot.telemetry.addData("Target Angle", targetAngle);

        //bsgbot.telemetry.addData("Right Wing", bsgbot.rightWing);
        bsgbot.telemetry.addData("Left Wing", bsgbot.wing);

        bsgbot.telemetry.update();

        //Flywheel Testing
        if (gamepad1.dpad_down) {
            rotateTo(90);
        }


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
            while (currentAngle >= targetAngle){
                currentAngle = -getHeading();
                // set power to rotate.
                bsgbot.frontLeft.setPower(-power);
                bsgbot.backLeft.setPower(-power);
                bsgbot.frontRight.setPower(power);
                bsgbot.backRight.setPower(power);
                //telemetry.addData("Current Angle: ", currentAngle);
                //telemetry.addData("Target Angle: ", targetAngle);
                //telemetry.update();
            }
        }
        else if (degrees > 0)
        {
            //turn right
            while (currentAngle <= targetAngle){
                currentAngle = -getHeading();
                // set power to rotate.
                bsgbot.frontLeft.setPower(power);
                bsgbot.backLeft.setPower(power);
                bsgbot.frontRight.setPower(-power);
                bsgbot.backRight.setPower(-power);
                //telemetry.addData("Current Angle: ", currentAngle);
                //telemetry.addData("Target Angle: ", targetAngle);
                //telemetry.update();
            }
        }
        else return;

        //turn the motors off.
        bsgbot.frontLeft.setPower(0);
        bsgbot.backLeft.setPower(0);
        bsgbot.frontRight.setPower(0);
        bsgbot.backRight.setPower(0);
    }

    void rotateTo(double degrees){
        currentAngle = -getHeading();
        double power = .5;
        // restart imu movement tracking.
        if (currentAngle > degrees)
        {   // turn left.
            while (currentAngle >= degrees){
                currentAngle = -getHeading();
                // set power to rotate.
                bsgbot.frontLeft.setPower(-power);
                bsgbot.backLeft.setPower(-power);
                bsgbot.frontRight.setPower(power);
                bsgbot.backRight.setPower(power);
                //telemetry.addData("Current Angle: ", currentAngle);
                //telemetry.addData("Target Angle: ", targetAngle);
                //telemetry.update();
            }
        }
        else if (currentAngle < degrees)
        {
            //turn right
            while (currentAngle <= targetAngle){
                currentAngle = -getHeading();
                // set power to rotate.
                bsgbot.frontLeft.setPower(power);
                bsgbot.backLeft.setPower(power);
                bsgbot.frontRight.setPower(-power);
                bsgbot.backRight.setPower(-power);
                //telemetry.addData("Current Angle: ", currentAngle);
                //telemetry.addData("Target Angle: ", targetAngle);
                //telemetry.update();
            }
        }
        else return;

        //turn the motors off.
        bsgbot.frontLeft.setPower(0);
        bsgbot.backLeft.setPower(0);
        bsgbot.frontRight.setPower(0);
        bsgbot.backRight.setPower(0);

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
