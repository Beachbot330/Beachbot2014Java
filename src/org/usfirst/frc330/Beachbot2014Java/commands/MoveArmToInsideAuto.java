// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 *
 */
public class  MoveArmToInsideAuto extends Command implements AutoSpreadsheetCommand{

    public MoveArmToInsideAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    boolean started = false;
    double setpoint;
    double outputRange;
    
    // Called just before this Command runs the first time
    protected void initialize() {
        started = false;
        setpoint = Robot.arm.getArmInsideAuto();
        moveArm_old(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        moveArm_old(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.arm.setPIDOutputRangeDefault();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    public void setParam1(double param1) {
    }

    public void setParam2(double param2) {
    }

    public void setParam3(double param3) {
    }

    public void setStopAtEnd(boolean stopAtEnd) {
    }

    public Command copy() {
        return new MoveArmToInsideAuto();
    }
    
    protected void moveArm_old(double setpoint){
        if ((Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) && !started) {
                Robot.arm.setArmSetPoint(setpoint);
                outputRange = 0.40;
                Robot.arm.setPIDOutputRange(outputRange);
                Robot.arm.enable();
                started = true;                
        } else if (started) {
            outputRange = outputRange + 0.01;
            if (outputRange > 0.8)
                outputRange = 0.8;
            Robot.arm.setPIDOutputRange(outputRange);
        }
    }
}
