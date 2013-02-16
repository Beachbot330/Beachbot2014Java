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
        System.out.println("StartInitialize LeftDistance: " + leftDistance);
        leftDistance = leftDistance + Robot.chassis.getLeftDistance();
        rightDistance = rightDistance + Robot.chassis.getRightDistance();
        super.initialize();
        System.out.println("EndInitialize LeftDistance: " + leftDistance);
    }
    
    public AutoSpreadsheetCommand copy() {
        return new DriveEncoderRel();
    }
    
    
}
