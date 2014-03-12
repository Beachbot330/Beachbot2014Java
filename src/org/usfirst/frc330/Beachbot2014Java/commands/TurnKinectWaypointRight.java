/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 * If the arm is to the right, Turn to a Waypoint, otherwise advance to the next command.
 * 
 */
public class TurnKinectWaypointRight extends TurnGyroWaypoint {

    public Command copy() {
        return new TurnKinectWaypointRight(); 
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
        return super.isFinished() || quit;
    }
    
    
}
