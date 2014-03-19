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

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;

/**
 *
 */
public class  DropBall extends Command {

    public DropBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    double setpoint = 0;
    boolean direction = false;
    boolean isArmPastPosition;
    double dropoffTimer;
    boolean isDroppedOff;
    // Called just before this Command runs the first time
    protected void initialize() {
        isDroppedOff = false;
        direction = Robot.arm.getIsArmFront();
        Robot.pickup.pickupOn(!direction);
        if (direction) {
            setpoint = Robot.arm.getArmFrontLoading();
            //System.out.println("ArmFrontLoading");
        }
            else {
            setpoint = Robot.arm.getArmBackLoading();
            //System.out.println("ArmBackLoading");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println(Robot.arm.getArmPosition());
        if (direction) {
            isArmPastPosition = Robot.arm.getArmPosition() > setpoint;
            //if (isArmPastPosition)
                //System.out.println("Not Past");
            //else
                //System.out.println("PastPositionFront");
        }
        else {
            isArmPastPosition = Robot.arm.getArmPosition() < setpoint;
            //System.out.println("PastPositionBack");
        }
        
        if (isArmPastPosition && !isDroppedOff) {
            //System.out.println("REVERSING");
            if (direction) {
                Robot.pickup.setPickupMotorReverseDropoff();
            }
            else
                Robot.pickup.setPickupMotorForwardDropoff();
            dropoffTimer = Timer.getFPGATimestamp();
            isDroppedOff = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isDroppedOff && (Timer.getFPGATimestamp() > dropoffTimer + .5));
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.pickup.setPickupMotorOff();
        //System.out.println("DropBallEnd");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
