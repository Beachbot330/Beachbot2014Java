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

    public MoveArmCommand(double position) {
        requires(Robot.arm);
        requires(Robot.wings);
        setpoint = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (!Robot.arm.areWingsSafeToClose(setpoint))
            Robot.wings.setWingsOpen();
        started = false;

    }

    // Called repeatedly when this Command is scheduled to run
    final protected void execute() {
        if ((Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) && !started) {
                Robot.arm.setArmSetPoint(setpoint);
                Robot.arm.enable();
                started = true;
                outputRange = 0.30;
                Robot.arm.setPIDOutputRange(outputRange);
//                System.out.println("outputRange: " + outputRange);                
        } else if (started) {
            //TODO make outputRange step a Preference
            outputRange = outputRange + 0.01;
            if (outputRange > 0.8)
                outputRange = 0.8;
            Robot.arm.setPIDOutputRange(outputRange);
//            System.out.println("outputRange: " + outputRange);
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
