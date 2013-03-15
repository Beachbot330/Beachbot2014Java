/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2013Java.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2013Java.Robot;
/*
 * $Log$
 */

/**
 * Drive the robot backward to a specified waypoint (relative to where the robot started) 
 * using the encoders and the gyro to keep straight and ramp up to full speed. Finish when
 * one of the encoders is within the specified {@link #setParam3(double) tolerance}. 
 * The {@link #setParam2(double) Y} location is positive to the front of the robot. 
 * The {@link #setParam1(double) X} location is positive to the right of the robot.
 * The robot's current angle should be close to the angle to drive to the waypoint
 * (ie this command is not appropriate for both turning and driving).
 * If the robot needs to turn prior to driving to the waypoint, use {@link TurnWaypoint} 
 * first.
 * <p>This is similar to DriveWaypoint, except that the robot drives backwards
 * (pickup first).
 * <p>
 * For example, to drive 10 feet forward from where the robot started, set Y to 
 * 120 (inches) and set X to 0.
 * A reasonable tolerance is 3 inches for normal movements. This will stop the robot
 * when it is between 117 - 123 inches. If a smaller
 * tolerance is used, the robot may not ever reach the tolerance, and the 
 * {@link #setTimeout(double) timeout} may be exceeded. This will slow down the
 * execution of future commands.
 * 
 * @see DriveWaypoint
 * @see TurnGyroWaypointBackward
 * 
 * @author Joe
 */
public class DriveWaypointBackward extends DriveWaypoint {

    public DriveWaypointBackward(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        super(x, y, tolerance, timeout, stopAtEnd);
    }

    protected void calcXY(double x, double y) {
        double gyroAngle;
        
        super.calcXY(x, y);

        leftDistance = -leftDistance;
        rightDistance = -rightDistance;
        
        gyroAngle = Robot.chassis.getAngle();
        if (gyroAngle < angle)
            angle = angle-180;
        else
            angle = angle+180;
        System.out.println("Backward Angle: " + angle);
    }

    public Command copy() {
        return new DriveWaypointBackward(0,0,0,0,true);
    }
    
}
