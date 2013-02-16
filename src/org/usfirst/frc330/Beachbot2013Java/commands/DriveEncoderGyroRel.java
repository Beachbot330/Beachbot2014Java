/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import org.usfirst.frc330.Beachbot2013Java.Robot;

/**
 *
 * @author joe
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
            Robot.chassis.gyroPIDLow.setSetpoint(angle);
            Robot.chassis.gyroPIDLow.enable();
        }
        else
        {
            Robot.chassis.gyroPIDHigh.setSetpoint(angle);
            Robot.chassis.gyroPIDHigh.enable();
        }
    }
    // Called once after isFinished returns true
    protected void end() {
        super.end();
        if (stopAtEnd)
        {
            Robot.chassis.gyroPIDLow.disable();
            Robot.chassis.gyroPIDHigh.disable();
        }
    }
    /**
     * The second parameter in the AutoSpreadsheet
     * @param angle The angle to maintain while driving
     */
    public void setParam2(double angle) {
        this.angle = angle;
    }
    
    /**
     * The third parameter in the AutoSpreadsheet
     * @param tolerance The tolerance for how close to be to the distance before stopping
     * @see edu.wpi.first.wpilibj.PIDController#setAbsoluteTolerance
     */
    public void setParam3(double tolerance) {
        this.tolerance = tolerance;
    }
    public AutoSpreadsheetCommand copy() {
        return new DriveEncoderGyroRel();
    }
}
