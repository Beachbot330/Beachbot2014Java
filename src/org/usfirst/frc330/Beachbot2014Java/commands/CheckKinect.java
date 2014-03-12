/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 * Check the Kinect arm positions and save the value in the OI class so that
 * it can be utilized for subsequent commands.
 * 
 */
public class CheckKinect extends Command implements AutoSpreadsheetCommand{

    protected void initialize() {
    }

    protected void execute() {
        Robot.oi.checkKinect();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public void setParam1(double param1) {
    }

    public void setParam2(double param2) {
    }

    public void setParam3(double param3) {
    }

    public void setStopAtEnd(boolean stopAtEnd) {
    }

    public Command copy() {
        return new CheckKinect();
    }
    
}
