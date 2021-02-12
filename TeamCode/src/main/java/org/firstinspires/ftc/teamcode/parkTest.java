package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

import org.firstinspires.ftc.teamcode.KNO3AutoTransitioner.AutoTransitioner;

@Autonomous(name = "parkTest")
public class parkTest extends LinearOpMode {
    Robot bsgbot = new Robot();

    //variables for encoderDrive
    private ElapsedTime runtime = new ElapsedTime();

    //counts per motor rev = ticks per rev
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.8;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        bsgbot.initRobot(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        bsgbot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgbot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgbot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bsgbot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bsgbot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgbot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgbot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bsgbot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                bsgbot.frontLeft.getCurrentPosition(),
                bsgbot.frontRight.getCurrentPosition(),
                bsgbot.backLeft.getCurrentPosition(),
                bsgbot.backRight.getCurrentPosition());
        telemetry.update();


        waitForStart();

        encoderDrive(DRIVE_SPEED, 60, 60, 4.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        //KNO3 Auto transitioner to switch to arcadeMode class
        AutoTransitioner.transitionOnStop(this, "arcadeMode");
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = bsgbot.frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = bsgbot.frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftTarget = bsgbot.backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = bsgbot.backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            bsgbot.frontLeft.setTargetPosition(newLeftTarget);
            bsgbot.frontRight.setTargetPosition(newRightTarget);
            bsgbot.backLeft.setTargetPosition(newLeftTarget);
            bsgbot.backRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            bsgbot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgbot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgbot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bsgbot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            bsgbot.frontLeft.setPower(Math.abs(speed));
            bsgbot.frontRight.setPower(Math.abs(speed));
            bsgbot.backLeft.setPower(Math.abs(speed));
            bsgbot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (bsgbot.frontLeft.isBusy() && bsgbot.frontRight.isBusy() && bsgbot.backLeft.isBusy() && bsgbot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        bsgbot.frontLeft.getCurrentPosition(),
                        bsgbot.frontRight.getCurrentPosition(),
                        bsgbot.backLeft.getCurrentPosition(),
                        bsgbot.backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            bsgbot.frontLeft.setPower(0);
            bsgbot.frontRight.setPower(0);
            bsgbot.backLeft.setPower(0);
            bsgbot.backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            bsgbot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgbot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgbot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bsgbot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

             AutoTransitioner.transitionOnStop(this, "Robot Teleop");
             //  sleep(250);   // optional pause after each move
        }
    }
}
