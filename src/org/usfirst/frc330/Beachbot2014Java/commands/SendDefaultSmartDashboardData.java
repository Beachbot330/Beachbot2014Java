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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.Beachbot2014Java.Robot;
/**
 *
 */
public class  SendDefaultSmartDashboardData extends Command {
    public SendDefaultSmartDashboardData() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.smartDashboardSender);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        setRunWhenDisabled(true);
    }
    
    int count = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
        count = 0;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (count % 10 == 0)
        {
            SmartDashboard.putNumber("PickupCurrent", Robot.pickup.getCurrent());
            SmartDashboard.putNumber("ArmPosition", Robot.arm.getArmPosition());
            SmartDashboard.putNumber("ChassisX", Robot.chassis.getX());
            SmartDashboard.putNumber("ChassisY", Robot.chassis.getY());
            SmartDashboard.putNumber("GyroAngle", Robot.chassis.getAngle());
        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
