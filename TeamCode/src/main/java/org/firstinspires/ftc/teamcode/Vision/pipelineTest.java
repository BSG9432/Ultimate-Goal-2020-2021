package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

@TeleOp
public class pipelineTest extends OpenCvPipeline {
    int lastResult = 0;

    public enum ringStack {
        ZERO, ONE, FOUR
    }

    /*
     * Some color constants
     */
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);

    /*
     * The core values which define the location and size of the sample regions
     */
    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(109,98);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(181,98);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(253,98);
    static final int REGION_WIDTH = 20;
    static final int REGION_HEIGHT = 20;

    @Override
    public Mat processFrame(Mat input) {

        //image processing here and store results
        /* if(){
            lastResult = 1;
           else if(){
            lastResult = 2;
           else if(){
            lastResult = 3;
           }
         */

        return input;
    }

    public int getLatestResults(){
        return lastResult;
    }
}
