// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc330.Beachbot2013Java.commands;
import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import org.usfirst.frc330.Beachbot2013Java.Robot;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class  DriveEncoderRamp extends DriveEncoder{
    double maxoutput = 0;
    double maxoutputStep = .02;
    //TODO make maxoutput step work like DriveEncoderGyroRamp
    
    public DriveEncoderRamp(double distance) {
        this(distance, 0, 0, true);
    }
    
    public DriveEncoderRamp(double distance, double tolerance, double timeout, boolean stopAtEnd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        super(distance, tolerance, timeout, stopAtEnd);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        maxoutput = maxoutputStep;
        
        super.initialize();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        maxoutput = maxoutput + maxoutputStep;
        if (maxoutput > .8)
        {
            maxoutput = .8;
        }
        if (!Robot.chassis.getShiftState())
        {
            Robot.chassis.leftDrivePIDLow.setOutputRange(-maxoutput, maxoutput);
            Robot.chassis.rightDrivePIDLow.setOutputRange(-maxoutput, maxoutput);
        }
        else
        {
            Robot.chassis.leftDrivePIDHigh.setOutputRange(-maxoutput, maxoutput);
            Robot.chassis.rightDrivePIDHigh.setOutputRange(-maxoutput, maxoutput);            
        }
    }
    
    public Command copy() {
        return new DriveEncoderRamp(0);
    }
}
