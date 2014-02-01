/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import org.usfirst.frc330.Beachbot2013Java.Robot;
import edu.wpi.first.wpilibj.command.Command;
/*
 * $Log$
 */
/**
 * Drive the robot a specified distance using encoders and the gyro to keep 
 * straight and ramp up to full speed. Finish when
 * one of the encoders is within the specified {@link #setParam3(double) tolerance}. 
 * The {@link #setParam1(double) distance} is relative to the
 * Robot's current position. The {@link #setParam2(double) angle} is relative 
 * to the angle where the robot started. The angle should be close to the 
 * current angle of the robot (ie this command is not appropriate for both turning and driving).
 * If the robot needs to turn prior to driving straight, use {@link TurnGyroAbs} 
 * first. 
 * <p>
 * For example, to drive 10 feet forward, set distance to 120 (inches) and set angle to 0.
 * A reasonable tolerance is 3 inches for normal movements. This will stop the robot
 * when it is between 117 - 123 inches. If a smaller
 * tolerance is used, the robot may not ever reach the tolerance, and the 
 * {@link #setTimeout(double) timeout} will be exceeded. This will slow down the
 * execution of future commands.
 * 
 * @see DriveEncoderGyroRel
 * @see DriveEncoderGyroRamp
 * @see DriveWaypoint
 * 
 * @author Joe
 */
public class DriveEncoderGyroRampRel extends DriveEncoderGyroRamp {
    public DriveEncoderGyroRampRel(double distance, double tolerance, double angle, double timeout, boolean stopAtEnd)
    {
        super(distance, tolerance, angle, timeout, stopAtEnd);
    }
    
    public DriveEncoderGyroRampRel()
    {
        super(0,0,0,0,true);
    }

    protected void initialize() {
        leftDistance = leftDistance + Robot.chassis.getLeftDistance();
        rightDistance = rightDistance + Robot.chassis.getRightDistance();
        super.initialize();
    }

    public Command copy() {
        return new DriveEncoderGyroRampRel();
    }
}
