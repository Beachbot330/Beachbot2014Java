/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;
/*
 * $Log$
 */
/**
 * Set the offset angle (compensation value) that compensates for where the 
 * robot gyro currently says is 0 versus where the true 0 is (perpendicular to the driver station).
 * This is used if the robot starts at an angle in Autonomous mode. 
 * 
 * @author Joe
 */
public class setGyroComp extends Command implements AutoSpreadsheetCommand{

    protected void initialize() {
        Robot.chassis.setGyroOffset(gyroComp);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    private double gyroComp;
    /**
     * The gyro offset to set.
     * @param gyroComp 
     */
    public void setParam1(double gyroComp) {
        this.gyroComp = gyroComp;
    }

    /**
     * Not Used
     * @param param2 
     */
    public void setParam2(double param2) {
    }

    /**
     * Not Used
     * @param param3 
     */
    public void setParam3(double param3) {
    }

    /**
     * Not Used
     * @param stopAtEnd 
     */
    public void setStopAtEnd(boolean stopAtEnd) {
    }

    public Command copy() {
        return new setGyroComp();
    }
    
}

