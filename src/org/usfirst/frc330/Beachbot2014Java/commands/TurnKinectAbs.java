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
 * is relative to the starting angle of the robot. The height of the right and left
 * hands is used to choose the direction.
 * 
 * @see TurnGyroAbs
 */
public class TurnKinectAbs extends TurnGyroAbs {

    public TurnKinectAbs(double angle) {
        super(angle);
    }

    public TurnKinectAbs(double angle, double tolerance) {
        super(angle, tolerance);
    }

    public TurnKinectAbs(double angle, double tolerance, double timeout, boolean stopAtEnd) {
        super(angle, tolerance, timeout, stopAtEnd);
    }

    public TurnKinectAbs(double angle, double tolerance, double timeout, boolean stopAtEnd, boolean enable) {
        super(angle, tolerance, timeout, stopAtEnd, enable);
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
        return new TurnKinectAbs(0);
    }
}
