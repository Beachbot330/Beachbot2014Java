/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2013Java.Robot;
/*
 * $Log$
 */
/**
 * Drive the robot a specified distance using encoders and PID only. Finish when
 * one of the encoders is within the specified {@link #setParam2(double) tolerance}. 
 * The {@link #setParam1(double) distance} is relative to the
 * Robot's current position.
 * <p>
 * For example, to drive 10 feet forward, set distance to 120 (inches).
 * A reasonable tolerance is 3 inches for normal movements. This will stop the robot
 * when it is between 117 - 123 inches. If a smaller
 * tolerance is used, the robot may not ever reach the tolerance, and the 
 * {@link #setTimeout(double) timeout} will be exceeded. This will slow down the
 * execution of future commands.
 * 
 * @see DriveEncoder
 * @see DriveEncoderGyroRel
 * 
 * @author Joe
 */
public class DriveEncoderRel extends DriveEncoder{
    public DriveEncoderRel(double distance, double tolerance, double timeout, boolean stopAtEnd)
    {
        super(distance, tolerance, timeout, stopAtEnd);
    }
    
    public DriveEncoderRel()
    {
        super(0,0,0,true);
    }

    protected void initialize() {
        leftDistance = leftDistance + Robot.chassis.getLeftDistance();
        rightDistance = rightDistance + Robot.chassis.getRightDistance();
        super.initialize();
    }
    
    public Command copy() {
        return new DriveEncoderRel();
    }
    
    
}
