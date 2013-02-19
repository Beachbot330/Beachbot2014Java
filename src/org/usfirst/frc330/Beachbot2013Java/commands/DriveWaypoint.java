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
 * $Log: DriveWaypoint.java,v $
 * Revision 1.5  2013-02-19 01:51:16  jross
 * make angle work for any number of robot rotations
 *
 * Revision 1.4  2013-02-18 21:10:13  jross
 * fix tolerance and passing angle parameters
 *
 * Revision 1.3  2013-02-17 02:53:43  jross
 * update javadocs
 *
 */
/**
 * Drive the robot to a specified waypoint (relative to where the robot started) 
 * using the encoders and the gyro to keep straight and ramp up to full speed. Finish when
 * one of the encoders is within the specified {@link #setParam3(double) tolerance}. 
 * The {@link #setParam2(double) Y} location is positive to the front of the robot. 
 * The {@link #setParam1(double) X} location is positive to the right of the robot.
 * The robot's current angle should be close to the angle to drive to the waypoint
 * (ie this command is not appropriate for both turning and driving).
 * If the robot needs to turn prior to driving to the waypoint, use {@link TurnWaypoint} 
 * first.
 * <p>
 * For example, to drive 10 feet forward from where the robot started, set Y to 
 * 120 (inches) and set X to 0.
 * A reasonable tolerance is 3 inches for normal movements. This will stop the robot
 * when it is between 117 - 123 inches. If a smaller
 * tolerance is used, the robot may not ever reach the tolerance, and the 
 * {@link #setTimeout(double) timeout} may be exceeded. This will slow down the
 * execution of future commands.
 * 
 * @see DriveEncoderGyroRamp
 * @see TurnGyroWaypoint
 * 
 * @author Joe
 */
//TODO need to be able to drive forward or backward. Right now, it always drives forward.
public class DriveWaypoint extends DriveEncoderGyroRampRel {
    double x,y;
    public DriveWaypoint(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super();
        this.x = x;
        this.y = y;
        calcXY(x, y);
        super.setParam2(tolerance);
        this.setStopAtEnd(stopAtEnd);
        this.setTimeout(timeout);
    }

    protected void initialize() {
        calcXY(x,y);
        super.initialize();
    }
    
    
    
     /**
     * The first parameter in the AutoSpreadsheet, X
     * @param X The X component of the waypoint
     */
    public void setParam1(double x) {
        this.x = x;
    }
    /**
     * The second parameter in the AutoSpreadsheet, Y
     * @param Y The Y component of the waypoint
     */
    public void setParam2(double y) {
        this.y = y;
    }
    
     /**
     * The third parameter in the AutoSpreadsheet, tolerance. 
     * The tolerance in inches for how close to be to the distance before stopping.
     * 3 inches is a reasonable tolerance for normal movements. If a smaller
     * tolerance is used, the robot may not ever reach the tolerance, and the 
     * {@link #setTimeout(double) timeout} may be reached.
     * @param tolerance in inches
     * @see edu.wpi.first.wpilibj.PIDController#setAbsoluteTolerance
     */
    public void setParam3(double tolerance) {
        super.setParam2(tolerance);
    }

    private void calcXY(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, calcDistance, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcDistance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
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
        System.out.println("distance: " + calcDistance);
        System.out.println("angle: " + calcAngle);
        
        super.setParam1(calcDistance);
        super.setParam3(calcAngle);
    }
    
    public Command copy() {
        return new DriveWaypoint(0,0,0,0,false);
    }

}
