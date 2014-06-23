/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2014Java.commands;

import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 *
 * @author Joe-XPS13-W7
 */
public class KinectDrivePercentageOnPath extends DriveWaypoint {
    double finalX, finalY, prevDelta, delta;
    
    public KinectDrivePercentageOnPath(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        super(0, 0, tolerance, timeout, stopAtEnd);
        finalX = x;
        finalY = y;
        prevDelta = 0;
    }
    


    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        delta = Robot.oi.getRightKinectJoystick().getY() - Robot.oi.getLeftKinectJoystick().getY();
        if (Math.abs(delta - prevDelta) > 0.05)
        {
            //may need to scale so that a value above a threshold still gives a full scale output
            calcXY(finalX * delta, finalY * Math.abs(delta));
            Robot.chassis.gyroPID.setSetpoint(angle);
            Robot.chassis.leftDrivePID.setSetpoint(x);
            Robot.chassis.rightDrivePID.setSetpoint(y);
        }
        prevDelta = delta;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
