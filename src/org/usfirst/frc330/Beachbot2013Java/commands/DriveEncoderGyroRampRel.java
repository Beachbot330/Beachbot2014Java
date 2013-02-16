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

    public AutoSpreadsheetCommand copy() {
        return new DriveEncoderGyroRampRel();
    }
    
    
    
    
}
