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
 * If the arm is to the left, execute the TurnGyroWaypoint command, otherwise advance to the next command.
 * The TurnGyroWaypoint command turns in place towards a waypoint using the gyro.
 */
public class TurnKinectWaypointLeft extends TurnGyroWaypoint {

    public Command copy() {
        return new TurnKinectWaypointLeft(); 
    }
    boolean quit = false;
    double offset = 0;
    protected void initialize() {
        offset = SmartDashboard.getNumber("KinectLeftOffset", 0);
        setParam1(x + offset);
        System.out.println("KinectLeftOffset " + offset);
        if (Robot.oi.getKinectDirection() == true ) {
            super.initialize(); 
        }
        else
            quit = true;
    }

    protected boolean isFinished() {
        return quit || super.isFinished();
    }
    
    
}
