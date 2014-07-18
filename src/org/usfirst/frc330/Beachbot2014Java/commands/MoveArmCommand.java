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
    double origDecelDistance = 0;
    double maxSpeed = 0;
    double minSpeed = 0;

    public MoveArmCommand(double position) {
        this(position, 1.0, 0.4, 0.4, 1.2);
    }
    
//maxSpeed       _______
//              /       \
//             /         \
//minSpeed    |           |
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
        this.origDecelDistance = decelDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (!Robot.arm.areWingsSafeToClose(setpoint))
            Robot.wings.setWingsOpen();
        started = false;
        startPosition = Robot.arm.getArmPosition();
        outputRange = minSpeed;
        if (Math.abs(setpoint - startPosition) < accelDistance+origDecelDistance)
            decelDistance = Math.abs(setpoint - startPosition) - accelDistance;
        else 
            decelDistance = origDecelDistance;
        if (decelDistance <= 0)
            decelDistance = 0.001;
        if (accelDistance <= 0)
            accelDistance = 0.001;
//        System.out.println("MoveArmCommand Initialize decelDistance= " + decelDistance + " setpoint " + setpoint + " startPosition " + startPosition + " accelDistance " + accelDistance + " origDecelDistance " + origDecelDistance);
    }

    // Called repeatedly when this Command is scheduled to run
    final protected void execute() {
        double x, y;
        if ((Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) && !started) {
                Robot.arm.setArmSetPoint(setpoint);
                Robot.arm.setPIDOutputRange(minSpeed); 
                outputRange = minSpeed;
                Robot.arm.enable();
                started = true;

//                System.out.println("outputRange: " + outputRange);                

        } else if (started) {
            if (Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) {

                if (setpoint > startPosition) {
                    if (Robot.arm.getArmPosition() > setpoint) {  //past setpoint
                        outputRange = minSpeed;
                    } else if (Robot.arm.getArmPosition() <= (startPosition + accelDistance)) { //in accell range
                        x = Math.abs(Robot.arm.getArmPosition() - startPosition)/accelDistance;
                        y = maxSpeed - minSpeed;
                        outputRange = y*x+minSpeed;
                    } else if (Robot.arm.getArmPosition() >= (setpoint - decelDistance)) { //in decell range
                        x = Math.abs(setpoint - Robot.arm.getArmPosition())/decelDistance;
                        y = maxSpeed - minSpeed;
                        outputRange = x*y + minSpeed;
        //                System.out.println("In Decel Condition x= " + x);
                    } else { //in middle range
                       outputRange = maxSpeed;
                    }   
                } else {
                    if (Robot.arm.getArmPosition() < setpoint) {  //past setpoint
                        outputRange = minSpeed;
                    } else if (Robot.arm.getArmPosition() >= (startPosition - accelDistance)) { //in accell range
                        x = (startPosition -Robot.arm.getArmPosition())/accelDistance;
                        y = maxSpeed - minSpeed;
                        outputRange = y*x+minSpeed;
                    } else if (Robot.arm.getArmPosition() <= (setpoint + decelDistance)) { //in decell range
                        x = (Robot.arm.getArmPosition() - setpoint )/decelDistance;
                        y = maxSpeed - minSpeed;
                        outputRange = x*y + minSpeed;
        //                System.out.println("In Decel Condition x= " + x);
                    } else { //in middle range
                       outputRange = maxSpeed;
                    }
                }
                if (!Robot.arm.isEnable())
                    Robot.arm.enable();
            }
            else {
                if (Robot.arm.isEnable())
                    Robot.arm.disable();
            }
            
        }
//        System.out.println("ArmPosition: " + Robot.arm.getArmPosition() + " outputRange: " + outputRange);
        if (outputRange < minSpeed)
        {
//            System.out.println("outputRange < minSpeed" + outputRange + " " + minSpeed);
            outputRange = minSpeed;

        }
        if (outputRange > maxSpeed)
            outputRange = maxSpeed;
        Robot.arm.setPIDOutputRange(outputRange);
//      System.out.println("outputRange: " + outputRange);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    final protected boolean isFinished() {
        return (Robot.arm.onTarget() && started) || isTimedOut();
    }

    // Called once after isFinished returns true
    final protected void end() {
        Robot.arm.setPIDOutputRangeDefault();
        started=false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    final protected void interrupted() {
        end();
    }
    
}
