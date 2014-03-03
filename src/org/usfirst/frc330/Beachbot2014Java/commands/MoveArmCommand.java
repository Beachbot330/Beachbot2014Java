/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 *
 * @author Joe-XPS13-W7
 */
public abstract class MoveArmCommand extends Command {
    double setpoint; 
    boolean started = false;
    double outputRange = 0;
    double startPosition = 0;
    double accelDistance = 0;
    double decelDistance = 0;
    double maxSpeed = 0;
    double minSpeed = 0;

    public MoveArmCommand(double position) {
        this(position, 1.0, 0.1, 0.1, 0.1);
    }
    
//maxSpeed       _______
//              /       \
//             /         \
//minSpeed    |           \
//           accel      decel
//         Distance    Distance
    
    public MoveArmCommand(double position, double maxSpeed, double minSpeed, double accelDistance, double decelDistance) {
        requires(Robot.arm);
        requires(Robot.wings);
        setpoint = position;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.accelDistance = accelDistance;
        this.decelDistance = decelDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (!Robot.arm.areWingsSafeToClose(setpoint))
            Robot.wings.setWingsOpen();
        started = false;
        startPosition = Robot.arm.getArmPosition();
        if (setpoint - startPosition < accelDistance+decelDistance)
            decelDistance = setpoint - accelDistance - startPosition;
    }

    // Called repeatedly when this Command is scheduled to run
    final protected void execute() {
        double armPosition = Robot.arm.getArmPosition();
        double x, y;
        if ((Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) && !started) {
                Robot.arm.setArmSetPoint(setpoint);
                Robot.arm.setPIDOutputRange(minSpeed); 
                outputRange = minSpeed;
                Robot.arm.enable();
                started = true;

                System.out.println("outputRange: " + outputRange);                

        } else if (started) {

            if (Robot.arm.getArmPosition() > setpoint) {
                outputRange = minSpeed;
            } else if (Robot.arm.getArmPosition() <= startPosition + accelDistance) {
                x = (armPosition - startPosition)/accelDistance;
                y = maxSpeed - minSpeed;
                outputRange = y*x+minSpeed;
            } else if (Robot.arm.getArmPosition() >= setpoint - decelDistance) {
                x = (setpoint - armPosition)/decelDistance;
                outputRange = x*maxSpeed;
            } else {
               outputRange = maxSpeed;
            }

            Robot.arm.setPIDOutputRange(outputRange);
            System.out.println("outputRange: " + outputRange);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    final protected boolean isFinished() {
        return Robot.arm.onTarget() && started;
    }

    // Called once after isFinished returns true
    final protected void end() {
        Robot.arm.setPIDOutputRangeDefault();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    final protected void interrupted() {
        end();
    }
    
}
