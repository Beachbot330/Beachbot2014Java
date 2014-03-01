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
import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc330.Beachbot2014Java.Robot;
/**
 *
 */
public class  Shoot extends Command implements AutoSpreadsheetCommand{
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
//        requires(Robot.wings);
    }
    double shootOffTimer;
    boolean shooterOn = false;
    // Called just before this Command runs the first time
    protected void initialize() {
        shooterOn=false;
        shootOffTimer = Timer.getFPGATimestamp() + 5;
//        Robot.wings.setWingsOpen();
//        if (Robot.wings.areWingsOpen() && Robot.shooter.isBallInShooter()) {
//        solenoidOffTime = Robot.shooter.shootSolenoidOffTime() + Timer.getFPGATimestamp();
            Robot.shooter.shootSolenoidOn();
            shootOffTimer = Timer.getFPGATimestamp() + Robot.shooter.shootSolenoidOffTime();
            shooterOn = true;
//        }
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        if (Robot.wings.areWingsOpen() && Robot.shooter.isBallInShooter() && shooterOn == false) {
//            Robot.shooter.shootSolenoidOn();
//            shootOffTimer = Timer.getFPGATimestamp() + Robot.shooter.shootSolenoidOffTime();
//            shooterOn = true;
//        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp() > shootOffTimer);
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.shootSolenoidOff();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.shooter.shootSolenoidOff();
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
        return new Shoot();
    }
}
