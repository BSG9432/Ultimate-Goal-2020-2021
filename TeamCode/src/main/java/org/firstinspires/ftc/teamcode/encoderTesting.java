/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.KNO3AutoTransitioner.AutoTransitioner;


/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Disabled
@Autonomous(name="yoink")
//Code used to refine bias values
public class encoderTesting extends LinearOpMode {
    Robot bsgbot = new Robot();

    private ElapsedTime runtime = new ElapsedTime();

    //counts per motor rev = ticks per rev
    static final double COUNTS_PER_MOTOR_REV = 537.6;    // NeveRest 20
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.5;
    static final double STRAFE_SPEED = 0.4;
    static final double TURN_SPEED = 0.5;

    //For strafing with encoders
    Integer cpr = 7; //counts per rotation
    Integer gearratio = 20; //because NeveRest 40
    Double diameter = 4.0;
    Double cpi = (cpr * gearratio) / (Math.PI * diameter); //counts per inch, 28cpr * gear ratio / (2 * pi * diameter (in inches, in the center))
    Double bias = .57;//default 0.4
    Double meccyBias = 4.0; //change to adjust only strafing movement


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        bsgbot.initRobot(hardwareMap);
        bsgbot.closeClaw();


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
        //
        telemetry.addData("Path0", "Starting at %7d :%7d",
                bsgbot.frontLeft.getCurrentPosition(),
                bsgbot.frontRight.getCurrentPosition(),
                bsgbot.backLeft.getCurrentPosition(),
                bsgbot.backRight.getCurrentPosition());
        telemetry.update();
        //AutoTransitioner.transitionOnStop(this, "Robot Teleop");

        bsgbot.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsgbot.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        //encoderDrive(DRIVE_SPEED, 12, 12, 3.0);
        strafeToPosition(12, DRIVE_SPEED);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
/*
    // I may or may not have flipped the measurements for the grabber in relation to the back and sides so the first 3 paths need to be checked
    public void redPathA() {
        //Aila: Red Path --> A (Be on the right of the wobble)
        strafeToPosition(12, STRAFE_SPEED);  //Strafe Right 12 inches

        encoderDrive(DRIVE_SPEED, 60, 60, 3.0);  //Forward 60 inches

        encoderDrive(DRIVE_SPEED, -13.744, 13.744, 3.0);//Pivot Left

        bsgbot.rightWingDown();//Puts the right arm down

        bsgbot.openRightClaw();//drops wobble goal from right side

        bsgbot.rightWingUp();//Puts right arm up

        encoderDrive(DRIVE_SPEED, 33.326, 33.326, 3.0); //Forward 33.326 Inches

        strafeToPosition(-35.326, STRAFE_SPEED);  //Strafe left 35.326 Inches

        bsgbot.leftWingDown();//Puts the left arm down

        bsgbot.closeClaw();//grab new wobble goal with left arm

        strafeToPosition(47.326, STRAFE_SPEED);  //Strafe right 47.326 Inches

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0); //Pivot right

        strafeToPosition(32, STRAFE_SPEED);  //Strafe right 32 Inches

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0); //Pivot right

        bsgbot.openLeftClaw();//drop wobble goal from left

        encoderDrive(DRIVE_SPEED, -13.744, 13.744, 3.0); //Pivot left

        strafeToPosition(-12, STRAFE_SPEED);//Strafe left 12 inches

        encoderDrive(DRIVE_SPEED,72,72,3.0);//Forward 72 Inches

        bsgbot.conveyor.setPower(1);//Run conveyor

        sleep(3000);//Keep running until all 3 have been shot

        encoderDrive(DRIVE_SPEED, -60, -60, 3.0);//Backward 60 Inches
    }
    public void bluePathA() {
        //Aila: Blue Path --> A (Be on the right of the wobble)
        strafeToPosition(-6, STRAFE_SPEED);  //Strafe left 12 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 30, 30, 3.0);  //Forward 60 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0);//Pivot right

        bsgbot.leftWingDown();//Puts the left arm down

        bsgbot.openLeftClaw();//Drop wobble goal from left arm

        bsgbot.leftWingUp();//Puts left arm up

        encoderDrive(DRIVE_SPEED, 33.326, 33.326, 3.0);// Forward 33.326 Inches with 3 Sec timeout

        strafeToPosition(35.326, STRAFE_SPEED);//Strafe right 35.326 Inches with 3 Sec timeout

        bsgbot.rightWingDown();//Puts the right arm down

        bsgbot.closeRightClaw();//Grab new wobble goal with right arm

        strafeToPosition(-47.326, STRAFE_SPEED);  //Strafe left 47.326 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0); //Pivot right

        strafeToPosition(-32, STRAFE_SPEED);  //Strafe left 32 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, -13.744, 13.744, 3.0); //Pivot left

        bsgbot.openRightClaw();//Drop wobble goal with right side

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0); //Pivot right

        strafeToPosition(-12, STRAFE_SPEED);//Strafe left 12 inches

        encoderDrive(DRIVE_SPEED,72,72,3.0);//Forward 72 Inches

        bsgbot.conveyor.setPower(1);//Run conveyor

        sleep(3000);//Keep running until all 3 have been shot

        encoderDrive(DRIVE_SPEED, -60, -60, 3.0);//Backward 60 Inches
    }
    public void redPathB() {
        //Aila: Red Path--> B
        strafeToPosition(12, STRAFE_SPEED);  //Strafe right 12 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 84, 84, 3.0);  //Forward 84 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, -13.744, 13.744, 3.0); //Pivot left

        encoderDrive(DRIVE_SPEED,24,24,3.0);//Forward 24 Inches

        bsgbot.rightWingDown();//Puts the right arm down

        bsgbot.openRightClaw();//Drop wobble goal right arm

        bsgbot.rightWingUp();//Puts the right arm up

        encoderDrive(DRIVE_SPEED, 45.366, 45.366, 3.0);  //Move forward 45.366 Inches with 3 Sec timeout

        strafeToPosition(-35.326, STRAFE_SPEED);  //Strafe left 35.326 Inches with 3 Sec timeout

        bsgbot.leftWingDown();//Puts left wing down

        bsgbot.closeClaw();//Grab new wobble goal with left arm

        strafeToPosition(35.326, STRAFE_SPEED);  //Strafe right 35.326 Inches with 3 Sec timeout

        encoderDrive(-DRIVE_SPEED,-12,-12,3.0); //Backwards 12 inches

        encoderDrive(DRIVE_SPEED,27.548, -27.548, 3.0); //Pivot Right 180 degrees

        bsgbot.openLeftClaw();//Drop wobble goal with left arm

        encoderDrive(DRIVE_SPEED,-13.774, 13.774, 3.0);//Pivot left

        encoderDrive(DRIVE_SPEED, 48, 48, 3.0); //Forwards 48 Inches

        bsgbot.conveyor.setPower(1);//S: Run conveyor

        sleep(3000);//Run conveyor until all 3 donuts have been shot

        encoderDrive(DRIVE_SPEED, -60, -60, 3.0); //Backwards 60 Inches
    }

    public void bluePathB() {
        //Aila: Blue Path--> B
        strafeToPosition(-12, STRAFE_SPEED);  //Strafe left 12 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 84, 84, 3.0);  //Forward 84 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0); //Pivot right

        encoderDrive(DRIVE_SPEED,24,24,3.0);//Forward 24 Inches

        bsgbot.leftWingDown();//Puts the left arm down

        bsgbot.openLeftClaw();//Drop wobble goal from left arm

        bsgbot.leftWingUp();//Puts the left arm up

        encoderDrive(DRIVE_SPEED, 45.366, 45.366, 3.0);  //Move forward 45.366 Inches with 3 Sec timeout

        strafeToPosition(35.326, STRAFE_SPEED);  //Strafe right 35.326 Inches with 3 Sec timeout

        bsgbot.rightWingDown();//Puts right wing down

        bsgbot.closeRightClaw();//Grab new wobble goal with right arm

        strafeToPosition(35.326, STRAFE_SPEED);  //Strafe right 35.326 Inches with 3 Sec timeout

        encoderDrive(-DRIVE_SPEED,-12,-12,3.0); //Backwards 12 inches

        encoderDrive(DRIVE_SPEED,27.548, -27.548, 3.0); //Pivot Right 180 degrees

        bsgbot.openLeftClaw();//Drop wobble goal with left arm

        encoderDrive(DRIVE_SPEED,-13.774, 13.774, 3.0);//Pivot left

        encoderDrive(DRIVE_SPEED, 48, 48, 3.0); //Forwards 48 Inches

        bsgbot.conveyor.setPower(1);//Run conveyor

        sleep(3000);//Run conveyor until all 3 donuts have been shot

        encoderDrive(DRIVE_SPEED, -60, -60, 3.0); //Backwards 60 Inches
    }
    public void redPathC() {
        //Aila: Red Path --> C
        strafeToPosition(12, STRAFE_SPEED);  //Strafe right 12 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 108, 108, 3.0);  ///Forward 108 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, -13.744, 13.744, 3.0); //Pivot left

        bsgbot.rightWingDown();//Puts the right arm down

        bsgbot.openRightClaw();//Drop wobble goal from right arm

        bsgbot.rightWingUp();//Puts the right arm up

        encoderDrive(DRIVE_SPEED, 45.366, 45.366, 3.0);  //Strafe right 45.366 Inches with 3 Sec timeout

        strafeToPosition(-86.634, STRAFE_SPEED);  //Strafe left 86.634 Inches with 3 Sec timeout

        bsgbot.leftWingDown(); //Puts left arm down

        bsgbot.closeClaw();//Grab new wobble goal with left arm

        strafeToPosition(86.634, STRAFE_SPEED);  //Strafe right 35.326 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, -36, 316, 3.0);  //Backward 40.366 inches with 3 sec timeout

        encoderDrive(DRIVE_SPEED,27.548,-27.548,3.0);//Pivot Right 180 degrees

        bsgbot.openLeftClaw();//Drop wobble goal from left arm

        encoderDrive(DRIVE_SPEED,-13.774,13.774,3.0); //Pivot Left

        strafeToPosition(-24,STRAFE_SPEED);//Strafe left 24 inches

        encoderDrive(DRIVE_SPEED,24,24,3.0);//Forwards 24 Inches

        bsgbot.conveyor.setPower(1);//Run conveyor

        sleep(3000);//Run conveyor until all 3 donuts have been shot

        encoderDrive(DRIVE_SPEED, -60, -60, 3.0); //Backwards 60 Inches
    }

    public void bluePathC() {
        //Aila: Blue Path --> C
        strafeToPosition(-12, STRAFE_SPEED);  //Strafe left 12 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 108, 108, 3.0);  ///Forward 108 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, 13.744, -13.744, 3.0); //Pivot right

        bsgbot.leftWingDown();//Puts the left arm down

        bsgbot.openLeftClaw();//Drop wobble goal from left

        bsgbot.leftWingUp();//Puts the left arm up

        encoderDrive(DRIVE_SPEED, 45.366, 45.366, 3.0);  //Forwards 45.366 Inches with 3 Sec timeout

        strafeToPosition(86.634, STRAFE_SPEED);  //Strafe right 86.634 Inches with 3 Sec timeout

        bsgbot.rightWingDown(); //Puts right arm down

        bsgbot.closeRightClaw();//Grab new wobble goal with right arm

        strafeToPosition(-86.634, STRAFE_SPEED);  //Strafe left 35.326 Inches with 3 Sec timeout

        encoderDrive(DRIVE_SPEED, -36, -36, 3.0);  //Backward 36 inches with 3 sec timeout

        encoderDrive(DRIVE_SPEED,-27.548,27.548,3.0);//Pivot Left 180 degrees

        bsgbot.openRightClaw();//Drop wobble goal from right side

        encoderDrive(DRIVE_SPEED,13.774,-13.774,3.0); //Pivot Right

        strafeToPosition(24,STRAFE_SPEED);//Strafe right 24 inches

        encoderDrive(DRIVE_SPEED,24,24,3.0);//Forwards 24 Inches

        bsgbot.conveyor.setPower(1);//Run conveyor

        sleep(3000);//Run conveyor until all 3 donuts have been shot

        encoderDrive(DRIVE_SPEED, -60, -60, 3.0); //Backwards 60 Inches
    }*/

    public void encoderDrive(double speed, double leftInches,
                             double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = bsgbot.frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH * bias);
            newRightTarget = bsgbot.frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH * bias);
            newLeftTarget = bsgbot.backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH * bias);
            newRightTarget = bsgbot.backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH * bias);

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
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
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


            //  sleep(250);   // optional pause after each move
        }
    }

    //strafing with encoders
    public void strafeToPosition(double inches, double speed) {
        int move = (int) (Math.round(inches * cpi * meccyBias * 1.265));

        bsgbot.backLeft.setTargetPosition(bsgbot.backLeft.getCurrentPosition() - move);
        bsgbot.frontLeft.setTargetPosition(bsgbot.frontLeft.getCurrentPosition() + move);
        bsgbot.backRight.setTargetPosition(bsgbot.backRight.getCurrentPosition() + move);
        bsgbot.frontRight.setTargetPosition(bsgbot.frontRight.getCurrentPosition() - move);

        bsgbot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bsgbot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bsgbot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bsgbot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        bsgbot.frontLeft.setPower(speed);
        bsgbot.backLeft.setPower(speed);
        bsgbot.frontRight.setPower(speed);
        bsgbot.backRight.setPower(speed);

        //THEORETICALLY Should display our encoder positions
        while (bsgbot.frontLeft.isBusy() && bsgbot.frontRight.isBusy() &&
                bsgbot.backLeft.isBusy() && bsgbot.backRight.isBusy()) {
            telemetry.addData("Path1", "Running to %7d :%7d",
                    bsgbot.frontLeft.getCurrentPosition() + move,
                    bsgbot.backLeft.getCurrentPosition() - move);
            telemetry.addData("Path2", "Running at %7d :%7d",
                    bsgbot.frontLeft.getCurrentPosition(),
                    bsgbot.backRight.getCurrentPosition(),
                    bsgbot.frontRight.getCurrentPosition(),
                    bsgbot.backLeft.getCurrentPosition());
            telemetry.update();
        }
        bsgbot.frontRight.setPower(0);
        bsgbot.frontLeft.setPower(0);
        bsgbot.backRight.setPower(0);
        bsgbot.backLeft.setPower(0);
        return;
    }
    //encoders for wings
    /*
    public void rightWingEncoder(double speed,
                                 int targetTicks, double timeoutS) {
        int newTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bsgbot.rightWing.getCurrentPosition() + (int) (targetTicks);

            bsgbot.rightWing.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION
            bsgbot.rightWing.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            bsgbot.rightWing.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS)) {

                // Display it for the driver.
                telemetry.addData("Path1", "Right Wing Running to  :%7d", targetTicks);
                telemetry.addData("Path2", "Running at 3 :%7d",
                        bsgbot.rightWing.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            bsgbot.rightWing.setPower(0);

            // Turn off RUN_TO_POSITION
            bsgbot.rightWing.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            sleep(250);   // optional pause after each move
        }
    }


 */

}