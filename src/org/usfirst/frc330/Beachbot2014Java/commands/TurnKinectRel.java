/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 * Turns to an angle using the Gyro; The Kinect chooses the direction. The angle 
 * is relative to the current angle of the robot. The height of the right and left
 * hands is used to choose the direction.
 * 
 * @see TurnGyroRel
 */
public class TurnKinectRel extends TurnGyroRel {

    public TurnKinectRel(double angle) {
        super(angle);
    }

    public TurnKinectRel(double angle, double tolerance) {
        super(angle, tolerance);
    }

    public TurnKinectRel(double angle, double tolerance, double timeout, boolean stopAtEnd) {
        super(angle, tolerance, timeout, stopAtEnd);
    }

    double direction;
    // Called just before this Command runs the first time
    protected void initialize() {
        if (Robot.oi.getLeftKinectJoystick().getY() > Robot.oi.getRightKinectJoystick().getY())
            direction = 1;
        else
            direction = -1;
        angle = angle*direction;
        super.initialize();
    }
    
    public Command copy() {
        return new TurnKinectRel(0);
    }
}
