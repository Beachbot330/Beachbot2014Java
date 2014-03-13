/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 * If the Kinect arms are to the right, execute the DriveWaypoint command, otherwise return quickly.
 * {@inheritDoc}
 */
public class DriveKinectWaypointRight extends DriveWaypoint {

    public DriveKinectWaypointRight(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        super(x, y, tolerance, timeout, stopAtEnd);
    }

    public Command copy() {
        return new DriveKinectWaypointRight(0,0,0,0,false);
    }
    boolean quit = false;
    protected void initialize() {
        if (Robot.oi.getKinectDirection() == false ) {
            super.initialize(); 
        }
        else
            quit = true;
    }

    protected boolean isFinished() {
        return quit || super.isFinished(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
