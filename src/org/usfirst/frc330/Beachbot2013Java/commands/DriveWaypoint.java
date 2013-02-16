/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2013Java.Robot;

/**
 *
 * @author joe
 */
public class DriveWaypoint extends DriveEncoderGyroRampRel {
    double x,y;
    public DriveWaypoint(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super();
        this.x = x;
        this.y = y;
        calcXY(x, y);
        this.setParam3(tolerance);
        this.setStopAtEnd(stopAtEnd);
        this.setTimeout(timeout);
    }

    protected void initialize() {
        calcXY(x,y);
        super.initialize();
    }
    
    
    
     /**
     * The first parameter in the AutoSpreadsheet
     * @param distance The distance to drive
     */
    public void setParam1(double x) {
        this.x = x;
    }
    /**
     * The second parameter in the AutoSpreadsheet
     * @param angle The angle to maintain while driving
     */
    public void setParam2(double y) {
        this.y = y;
    }

    private void calcXY(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, calcDistance;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcDistance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        calcAngle = Math.toDegrees(MathUtils.atan2(deltaX, deltaY));
        
        if (Math.abs(Robot.chassis.getAngle()-calcAngle)<180)
        {
            calcAngle=calcAngle;
        }
        else if (Robot.chassis.getAngle() > calcAngle)
        {
            calcAngle += 360;
        }
        else
        {
            calcAngle -= 360;
        }
        System.out.println("distance: " + calcDistance);
        System.out.println("angle: " + calcAngle);
        
        super.setParam1(calcDistance);
        super.setParam2(calcAngle);
    }
    
    public Command copy() {
        return new DriveWaypoint(0,0,0,0,false);
    }

}
