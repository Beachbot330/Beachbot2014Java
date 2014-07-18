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
public class  AutoLoadShooter extends Command implements AutoSpreadsheetCommand{
    public AutoLoadShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    private boolean loading;
    double setpoint; 
    boolean started = false;
    double outputRange = 0;
    double startPosition = 0;
    double accelDistance = 0.4;
    double decelDistance = 1.2;
    double origDecelDistance = 1.2;
    double maxSpeed = 1.0;
    double minSpeed = 0.5;
    boolean atPickup = false;
    boolean frontPickuping;
    
    protected void initialize() {
        //System.out.println("Initialize");
        started = false;
        loading = false;
        if(Robot.arm.getIsArmRear()){
            moveArm_old(Robot.arm.getArmBackPickup());
            //System.out.println("Move to Rear Pickup");
        }
        else {
            moveArm_old(Robot.arm.getArmFrontPickup());
            
            //System.out.println("Move to Front Pickup");
        }
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.pickup.isBallInPickup() && loading==false){
            started = false;
            if(Robot.arm.getIsArmRear())
                moveArm(Robot.arm.getArmFrontSafePoint());
            else
                moveArm(Robot.arm.getArmBackSafePoint());
            frontPickuping = Robot.arm.getIsArmFront();
            loading = true;
        }
        if(loading==true){
            moveArm(Robot.arm.getSetpoint()); //Don't care about location, just update speed
            if ((!frontPickuping &&                //Not past retry threshold (on current side)
                    Robot.arm.getArmPosition() > Robot.arm.getArmLoadRetryThresholdRear()) ||
                    (frontPickuping &&
                    Robot.arm.getArmPosition() < Robot.arm.getArmLoadRetryThresholdFront()))
            {
                //System.out.println("Checking for Ball");
                if(!Robot.pickup.isBallInPickup()){         //dropped ball
                    //System.out.println("I dropped the ball!");
                    initialize();
                }
                //else
                    //System.out.println("I still have the ball"); 
            }
            //else
                //System.out.println("I no longer care if I have the ball  :P");
        }
            
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.arm.onTarget() && 
                (Robot.arm.getSetpoint() == Robot.arm.getArmFrontSafePoint() ||
                Robot.arm.getSetpoint() == Robot.arm.getArmBackSafePoint()));
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.arm.setPIDOutputRangeDefault();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //System.out.println("autoLoadShooter Interrupted");
        end();
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
    protected void moveArm(double setpoint){
        if(!started){
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
            //System.out.println("MoveArmCommand Initialize decelDistance= " + decelDistance + " setpoint " + setpoint + " startPosition " + startPosition + " accelDistance " + accelDistance + " origDecelDistance " + origDecelDistance);
            
        }
        double x, y;
        if ((Robot.wings.areWingsOpen() || Robot.arm.areWingsSafeToClose(setpoint)) && !started) {
                Robot.arm.setArmSetPoint(setpoint);
                Robot.arm.setPIDOutputRange(minSpeed); 
                outputRange = minSpeed;
                Robot.arm.enable();
                started = true;
                //System.out.println("outputRange: " + outputRange);                
        } else if (started) {
            if (setpoint > startPosition) {
                if (Robot.arm.getArmPosition() > setpoint) {  //past setpoint
                    outputRange = minSpeed;
                } else if (Robot.arm.getArmPosition() <= (startPosition + accelDistance)) { //in accell range
                    x = Math.abs(Robot.arm.getArmPosition() - startPosition)/accelDistance;
                    y = maxSpeed - minSpeed;
                    outputRange = y*x+minSpeed;
                    //System.out.println(Robot.arm.getArmPosition()+" start: "+startPosition+" accelDistance: "+accelDistance);
                    //System.out.println("In Accel Condition x= " + x);
                } else if (Robot.arm.getArmPosition() >= (setpoint - decelDistance)) { //in decell range
                    x = Math.abs(setpoint - Robot.arm.getArmPosition())/decelDistance;
                    y = maxSpeed - minSpeed;
                    outputRange = x*y + minSpeed;
                    //System.out.println(Robot.arm.getArmPosition()+" start: "+startPosition+" accelDistance: "+accelDistance);
                    //System.out.println("In Decel Condition x= " + x);
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
            
        }
        //System.out.println("ArmPosition: " + Robot.arm.getArmPosition() + " outputRange: " + outputRange);
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
    public void setParam1(double param1) {
       
    }
    public void setParam2(double param2) {
       
    }
    public void setParam3(double param3) {
      
    }
    public void setStopAtEnd(boolean stopAtEnd) {
        
    }
    public Command copy() {
        return new AutoLoadShooter();
    }
}
