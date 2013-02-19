/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2013Java.Robot;

/**
 *
 * @author joe
 */
public class TurnGyroWaypointBackward extends TurnGyroWaypoint{

    public Command copy() {
        return new TurnGyroWaypointBackward();
    }

    protected void calcAngle(double x, double y) {
        double gyroAngle;
        super.calcAngle(x, y);
        
        gyroAngle = Robot.chassis.getAngle();
        if (gyroAngle < angle)
            angle = angle-180;
        else
            angle = angle+180;
    }
    
}
