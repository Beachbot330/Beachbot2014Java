/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2013Java.Robot;
/*
 * $Log$
 */
 
/**
 *
 * @author joe
 */
public class TurnGyroWaypoint extends TurnGyroAbs {
    double x, y;
    public TurnGyroWaypoint()
    {
        super(0,0,0,true);
    }

    protected void initialize() {
        calcAngle(x, y);
        super.initialize();
    }

    public Command copy() {
        return new TurnGyroWaypoint();
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
    
    public void setParam3(double tolerance)
    {
        super.setParam2(tolerance);
    }
    
    
    protected void calcAngle(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcAngle = Math.toDegrees(MathUtils.atan2(deltaX, deltaY));
        robotAngle = Robot.chassis.getAngle();
        if (Math.abs(robotAngle-calcAngle)<180)
        {
            //do nothing
        }
        else if (robotAngle > calcAngle)
        {
            while (robotAngle > calcAngle)
                calcAngle += 360;
        }
        else 
        {
            while (robotAngle < calcAngle)
                calcAngle -= 360;
        }
        System.out.println("angle: " + calcAngle);
        
        super.setParam1(calcAngle);
    }
}
