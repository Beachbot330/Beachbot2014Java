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
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;
/**
 *
 */
public class  MoveArmToCloseCheckBallSlow extends Command {
    double setpoint; 
    boolean started = false;
    double outputRange = 0;
    
    public MoveArmToCloseCheckBallSlow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.wings);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        if (Robot.arm.getArmDirection())
            setpoint = Robot.arm.getArmFrontBumper();
        else
            setpoint = Robot.arm.getArmBackBumper();
        if (!Robot.arm.areWingsSafeToClose(setpoint))
            Robot.wings.setWingsOpen();
        started = false;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if ((Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) && !started) {
                Robot.arm.setArmSetPoint(setpoint);
                outputRange = Robot.arm.getArmSlowSpeed();
                Robot.arm.setPIDOutputRange(outputRange);
                Robot.arm.enable();
                started = true;
//                System.out.println("outputRange: " + outputRange);                
        } 
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.onTarget() && started;
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
}
