/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2013Java.Robot;
import org.usfirst.frc330.Beachbot2013Java.subsystems.Chassis;
/*
 * $Log: DriveEncoderGyroRel.java,v $
 * Revision 1.4  2013-02-19 10:57:49  jross
 * delete end method, super's end is fine
 *
 * Revision 1.3  2013-02-17 02:53:43  jross
 * update javadocs
 *
 */
/**
 * Drive the robot a specified distance using encoders and the gyro to keep straight. Finish when
 * one of the encoders is within the specified {@link #setParam2(double) tolerance}. 
 * The {@link #setParam1(double) distance} is relative to the
 * Robot's current position. The {@link #setParam3(double) angle} is relative 
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
 * @see DriveEncoder
 * @see DriveEncoderRel
 * @see DriveEncoderGyroRamp
 * 
 * @author Joe
 */
public class DriveEncoderGyroRel extends DriveEncoderRel{
    double angle;
    
    public DriveEncoderGyroRel()
    {
        this(0,0,0,0,true);
    }
    
    public DriveEncoderGyroRel(double distance, double angle)
    {
        this(distance, 0, angle, 0, true);
    }
    
    public DriveEncoderGyroRel(double distance, double tolerance, double angle, double timeout, boolean stopAtEnd)
    {
        super(distance, tolerance, timeout, stopAtEnd);
        this.angle = angle;
    }
        // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
        if (!Robot.chassis.getShiftState())
        {
            Robot.chassis.gyroPID.setGainName(Chassis.DRIVELOW);
        }
        else
        {
            Robot.chassis.gyroPID.setGainName(Chassis.DRIVEHIGH);
        }
        Robot.chassis.gyroPID.setSetpoint(angle);
        Robot.chassis.gyroPID.enable();
    }

    /**
     * The third parameter in the AutoSpreadsheet, angle.
     * The angle is relative 
     * to the angle where the robot started. The angle should be close to the 
     * current angle of the robot (ie this command is not appropriate for both turning and driving).
     * If the robot needs to turn prior to driving straight, use {@link TurnGyroAbs} 
     * first. 
     * @param angle The angle (in degrees) to maintain while driving
     */
    public void setParam3(double angle) {
        this.angle = angle;
    }

    public Command copy() {
        return new DriveEncoderGyroRel();
    }
}
