/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012, Joe Ross 2014. All Rights Reserved.         */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

/**
 * Use a rate gyro to return the robots heading relative to a starting position.
 * The Gyro class tracks the robots heading based on the starting position. As the robot
 * rotates the new heading is computed by integrating the rate of rotation returned
 * by the sensor. When the class is instantiated, it does a short calibration routine
 * where it samples the gyro while at rest to determine the default offset. 
 * The BackgroundCalibratingGyro also calibrates when disabled, as long as calibrateGyro
 * is called. startGyro should be called when first enabled to finish the calibration 
 * process. This calibration value is subtracted from each sample to determine the heading.
 * 
 * @author Joe Ross, Team 330
 */
public class BackgroundCalibratingGyro extends Gyro {

    static final int kCalibrateSeconds = 5;
   
    private AccumulatorResult[] calibratingResult;
    private AccumulatorResult[] prevCalibratingResult;
    private int calibrateIndex = 0;
    private boolean calibrated = false;
    private double stopTime = 0;
    private AccumulatorResult tempAccumulation;

    /**
     * Initialize the gyro.
     * Calibrate the gyro by running for a number of samples and computing the center value for this
     * part. Then use the center value as the Accumulator center value for subsequent measurements.
     * It's important to make sure that the robot is not moving while the centering calculations are
     * in progress, this is typically done when the robot is first turned on while it's sitting at
     * rest before the competition starts.
     */
    protected void initGyro() {
        calibratingResult = new AccumulatorResult[kCalibrateSeconds];
        prevCalibratingResult = new AccumulatorResult[kCalibrateSeconds];
        for (int i=0;i<kCalibrateSeconds; i++) {
            calibratingResult[i] = new AccumulatorResult();
            prevCalibratingResult[i] = new AccumulatorResult();
        }
        tempAccumulation = new AccumulatorResult();
        super.initGyro();
        stopTime = Timer.getFPGATimestamp() + 1.0;
        

    }

    /**
     * Gyro constructor given a slot and a channel.
    .
     * @param slot The cRIO slot for the analog module the gyro is connected to.
     * @param channel The analog channel the gyro is connected to.
     */
    public BackgroundCalibratingGyro(int slot, int channel) {
        super(slot, channel);
    }

    /**
     * Gyro constructor with only a channel.
     *
     * Use the default analog module slot.
     *
     * @param channel The analog channel the gyro is connected to.
     */
    public BackgroundCalibratingGyro(int channel) {
        super(channel);
    }

    /**
     * Gyro constructor with a precreated analog channel object.
     * Use this constructor when the analog channel needs to be shared. There
     * is no reference counting when an AnalogChannel is passed to the gyro.
     * @param channel The AnalogChannel object that the gyro is connected to.
     */
    public BackgroundCalibratingGyro(AnalogChannel channel) {
        super(channel);
    }
    
    /**
     * Calibrate the gyro in the background. calibrateGyro should be called 
     * when disabled.
     */
    public void calibrateGyro() {
        if (!calibrated)
        {
            if (Timer.getFPGATimestamp() > stopTime) {
                prevCalibratingResult[calibrateIndex] = calibratingResult[calibrateIndex];
                m_analog.getAccumulatorOutput(calibratingResult[calibrateIndex]);
                tempAccumulation.count += calibratingResult[calibrateIndex].count;
                tempAccumulation.value += calibratingResult[calibrateIndex].value;
                
                calibrateIndex++;
                if (calibrateIndex >= kCalibrateSeconds) {
                    calibrateIndex = 0;
                }
                stopTime = Timer.getFPGATimestamp() + 1.0;
            }
        }
    }
    
    /**
     * Use the latest gyro data to complete the calibration. This should be called
     * when transitioning to enabled (for example teleopInit and autonomousInit.
     * If the less then 5 seconds elapsed from the time calibrateGyro is first called
     * and startGyro is called, the previous calibration value is used.
     */
    public void startGyro() {
        
        if (!calibrated)
        {
            long value = 0;
            long count = 0;
            int newCenter = 0;
            
            long lowestCount = calibratingResult[0].count;
            int lowestCountIndex = 0;
            int highestCountIndex = 0;

            for (int i=0;i<kCalibrateSeconds;i++) {
                if (calibratingResult[i].count < lowestCount) {
                    lowestCount = calibratingResult[i].count;
                    lowestCountIndex = i;
                }
            }
            if (lowestCountIndex == 0)
                highestCountIndex = kCalibrateSeconds - 1;
            else
                highestCountIndex = lowestCountIndex - 1;
            
//            System.out.println("highestCountIndex: " + highestCountIndex);
//            System.out.println("lowestCountIndex: " + lowestCountIndex);
//            System.out.println("calibratingResult[highestCountIndex].value: " + calibratingResult[highestCountIndex].value);
//            System.out.println("calibratingResult[lowestCountIndex].value: " + calibratingResult[lowestCountIndex].value);
//            System.out.println("calibratingResult[highestCountIndex].count: " + calibratingResult[highestCountIndex].count);
//            System.out.println("calibratingResult[lowestCountIndex].count: " + calibratingResult[lowestCountIndex].count);
            
            value = calibratingResult[highestCountIndex].value - calibratingResult[lowestCountIndex].value;
            count = calibratingResult[highestCountIndex].count - calibratingResult[lowestCountIndex].count;
            if (count > (kCalibrateSeconds - 1) * kSamplesPerSecond) {
                newCenter = m_center + (int) ((double)value / (double)count + .5);

                m_offset = ((double)value / (double)count) - (int)value/count;
                m_center = newCenter;
                m_analog.setAccumulatorCenter(m_center);

                m_analog.setAccumulatorDeadband(0); ///< TODO: compute / parameterize this
                calibrated = true;
                tempAccumulation.count = 0;
                tempAccumulation.value = 0;
            } else {
                calibrated = false;
            }
            m_analog.resetAccumulator();
        }
    }    
    
    /**
     * Restart the calibration process. Calibration will stop when startGyro() is called.
     */
    public void restartCalibration() {
        stopTime = Timer.getFPGATimestamp() + 1.0;
        m_analog.resetAccumulator();

        calibrated = false;
    }
    
}
