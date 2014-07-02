/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.Command;
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
        this.x = 0;
        this.y = 0;
        prevDelta = 3;
    }
    


    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double prevAngle;
        delta = (Robot.oi.getRightKinectJoystick().getY() - Robot.oi.getLeftKinectJoystick().getY())/2;

        if (Math.abs(delta - prevDelta) > 0.02)
        {
            System.out.println("delta: " + delta + " prevDelta: " + prevDelta);            
            //may need to scale so that a value above a threshold still gives a full scale output
            prevAngle = angle;
            calcXY(finalX * Math.abs(delta), finalY * delta);
            if (angle > 90)
            {
                angle -= 180;
                //angle = -angle;
                leftDistance = -leftDistance;
                rightDistance = -rightDistance;
            }
            else if (angle < -90)
            {
                angle +=180;
                //angle = -angle;
                leftDistance = -leftDistance;
                rightDistance = -rightDistance;
            }
            if (Math.sqrt(rightDistance*rightDistance + leftDistance*leftDistance) < 6)
            {
//                angle = prevAngle;
            }
            Robot.chassis.gyroPID.setSetpoint(angle);
            Robot.chassis.leftDrivePID.setSetpoint(leftDistance);
            Robot.chassis.rightDrivePID.setSetpoint(rightDistance);
            prevDelta = delta;
        }
        
        super.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    public Command copy() {
        return new KinectDrivePercentageOnPath(0,0,0,0,false);
    }
     /**
     * The first parameter in the AutoSpreadsheet, X
     * @param x The X component of the end of the path in inches
     */
    public void setParam1(double x) {
        this.finalX = x;
        this.x = 0;
    }
    /**
     * The second parameter in the AutoSpreadsheet, Y
     * @param y The Y component of the end of the path in inches
     */
    public void setParam2(double y) {
        this.finalY = y;
        this.y = 0;
    }
}
