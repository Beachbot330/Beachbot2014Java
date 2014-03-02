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

    public MoveArmCommand(double position) {
        requires(Robot.arm);
        requires(Robot.wings);
        setpoint = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//        Robot.arm.disable();
//        Robot.arm.setArmSetPoint(setpoint);
        if (!Robot.arm.areWingsSafeToClose(setpoint))
            Robot.wings.setWingsOpen();

    }

    // Called repeatedly when this Command is scheduled to run
    final protected void execute() {
        if (Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) {
//            if (!Robot.arm.isEnable()) {
                Robot.arm.setArmSetPoint(setpoint);
                Robot.arm.enable();
//            }
//        } else if (Robot.arm.isEnable()) {
//            Robot.arm.disable();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    final protected boolean isFinished() {
        return Robot.arm.onTarget() && Robot.arm.getSetpoint() == setpoint;
    }

    // Called once after isFinished returns true
    final protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    final protected void interrupted() {
    }
    
}
