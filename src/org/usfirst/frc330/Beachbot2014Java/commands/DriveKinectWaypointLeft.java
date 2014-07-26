/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 * If the Kinect arms are to the left, execute the DriveWaypoint command, otherwise return quickly.
 * The DriveWaypoint command will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
public class DriveKinectWaypointLeft extends DriveWaypoint {

    public DriveKinectWaypointLeft(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        super(x, y, tolerance, timeout, stopAtEnd);
    }

    public Command copy() {
        return new DriveKinectWaypointLeft(0,0,0,0,false);
    }
    boolean quit = false;
    double offset = 0;
    protected void initialize() {
        offset = SmartDashboard.getNumber("KinectLeftOffset", 0);
        System.out.println("KinectLeftOffset " + offset);
        setParam1(x + offset);
        if (Robot.oi.getKinectDirection() == true ) {
            super.initialize(); 
        }
        else
            quit = true;
    }

    protected boolean isFinished() {
        return quit || super.isFinished(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
