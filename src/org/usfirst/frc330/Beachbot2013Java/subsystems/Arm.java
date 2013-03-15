// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc330.Beachbot2013Java.subsystems;
import org.usfirst.frc330.Beachbot2013Java.RobotMap;
import org.usfirst.frc330.Beachbot2013Java.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.wpilibj.BeachbotPrefSendablePIDController;
import org.usfirst.frc330.Beachbot2013Java.Robot;
/**
 *
 */
public class Arm extends Subsystem implements PIDSource, PIDOutput{
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController armSpeedController = RobotMap.armArmSpeedController;
    AnalogChannel potentiometer = RobotMap.armPotentiometer;
    DoubleSolenoid brakeArmSolenoid = RobotMap.armBrakeArmSolenoid;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private BeachbotPrefSendablePIDController armPID;
//    private PIDController armPID;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ManualArm());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private static final String PREF_Arm_ArmPickupTimeToWait = "ArmPickupTimeToWait";
    private static final String PREF_Arm_ArmShootingTimeToWait = "ArmShootingTimeToWait";
    private static final String PREF_Arm_ArmPositionLowerLimit = "ArmPositionLowerLimit";
    private static final String PREF_Arm_ArmPositionUpperLimit = "ArmPositionUpperLimit";
    
    public Arm(){
        armPID = new BeachbotPrefSendablePIDController(0,0,0,this,this, "armPID");
//        armPID = new PIDController(0,0,0,this,this);
        armPID.setAbsoluteTolerance(0.1);
        Preferences.getInstance().putDouble("ArmAbsoluteTolerance", 0.1);
        SmartDashboard.putData("ArmPID", armPID);
    }
    
    //TODO remove holdArmOn and holdArmOff and remove brakeArmSolenoid, they won't be used
    public void holdArmOn() {
        brakeArmSolenoid.set(DoubleSolenoid.Value.kForward);
        set(0);
    }
    
    public void holdArmOff() {
        brakeArmSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    //TODO if practice robot, get PracticeArmPositionZero. If Competition robot, get CompetitionArmPositionZero.
    public double getArmZero()
    {
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeArmPositionZero";
        else
            name = "CompetitionArmPositionZero";
        if (!Preferences.getInstance().containsKey(name))
        {
            Preferences.getInstance().putDouble(name, 0.0);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble(name, 0.0);
    }
    /**
     * Set the arm zero at the current position of the arm.
     */
    public void setArmZero()
    {        
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeArmPositionZero";
        else
            name = "CompetitionArmPositionZero";
        
        Preferences.getInstance().putDouble(name, potentiometer.getAverageVoltage());
        Preferences.getInstance().save();
    }
    public double getArmHighShooting() {
        if (!Preferences.getInstance().containsKey("armSetpointHighShooting"))
        {
            Preferences.getInstance().putDouble("armSetpointHighShooting", 1.5);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointHighShooting", 1.5);
    }
    public double getArmLowShooting() {
        if (!Preferences.getInstance().containsKey("armSetpointLowShooting"))
        {
            Preferences.getInstance().putDouble("armSetpointLowShooting", .1);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointLowShooting", .1);
    }
    public double getArmLowPickup() {
        if (!Preferences.getInstance().containsKey("armSetpointLowPickup"))
        {
            Preferences.getInstance().putDouble("armSetpointLowPickup", .1);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointLowPickup", .1);
    }
    public double getArmClimbing() {
        if (!Preferences.getInstance().containsKey("armSetpointClimbing"))
        {
            Preferences.getInstance().putDouble("armSetpointClimbing", 2);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointClimbing", 2);
    }
    
    public void manualArm() {
        double armCommand = Robot.oi.operatorJoystick.getY();
        if (Math.abs(armCommand) > 0.05)
        {
            if (armPID.isEnable())
                armPID.disable();
            set(armCommand);
        }
    }
    
    public double getArmPosition()
    {
        return potentiometer.getAverageVoltage()-getArmZero();
    }
    
    public void armSetPoint(double setpoint) {
        armPID.setSetpoint(setpoint);
    }
    
    public void armSetPointHighShooting() {
        armSetPoint(getArmHighShooting());
    }
    
    public void armSetPointLowShooting() {
        armSetPoint(this.getArmLowShooting());
    }
    
    public void armSetPointLowPickup() {
        armSetPoint(getArmLowPickup());
    }
    
    public void armSetPointClimbing() {
        armSetPoint(getArmClimbing());
    }
    
    public double armWaitPickup() {
        double armpickuptimetowait = 0.5;
        if (Preferences.getInstance().containsKey(PREF_Arm_ArmPickupTimeToWait))
        {
            armpickuptimetowait = Preferences.getInstance().getDouble(
                            PREF_Arm_ArmPickupTimeToWait,armpickuptimetowait);
        } else 
        {
            Preferences.getInstance().putDouble(PREF_Arm_ArmPickupTimeToWait, 
                                                armpickuptimetowait);
            Preferences.getInstance().save();
        }
        return armpickuptimetowait;
    }
    
    public double armWaitShooting() {
        double armshootingtimetowait = 0.5;
        if (Preferences.getInstance().containsKey(PREF_Arm_ArmShootingTimeToWait))
        {
            armshootingtimetowait = Preferences.getInstance().getDouble(
                            PREF_Arm_ArmShootingTimeToWait,armshootingtimetowait);
        } else 
        {
            Preferences.getInstance().putDouble(PREF_Arm_ArmShootingTimeToWait, 
                                                armshootingtimetowait);
            Preferences.getInstance().save();
        }
        return armshootingtimetowait;
    }
    
    public double armLowerLimit() {
        double armpositionlowerlimit = 0;
        if (Preferences.getInstance().containsKey(PREF_Arm_ArmPositionLowerLimit))
        {
            armpositionlowerlimit = Preferences.getInstance().getDouble(
                            PREF_Arm_ArmPositionLowerLimit,armpositionlowerlimit);
        } else 
        {
            Preferences.getInstance().putDouble(PREF_Arm_ArmPositionLowerLimit, 
                                                armpositionlowerlimit);
            Preferences.getInstance().save();
        }
        return armpositionlowerlimit;
    }
    
    public double armUpperLimit() {
        double armpositionupperlimit = 2;
        if (Preferences.getInstance().containsKey(PREF_Arm_ArmPositionUpperLimit))
        {
            armpositionupperlimit = Preferences.getInstance().getDouble(
                            PREF_Arm_ArmPositionUpperLimit,armpositionupperlimit);
        } else 
        {
            Preferences.getInstance().putDouble(PREF_Arm_ArmPositionUpperLimit, 
                                                armpositionupperlimit);
            Preferences.getInstance().save();
        }
        return armpositionupperlimit;
    }
    
    public synchronized double getSetpoint() {
        return armPID.getSetpoint();
    }
    public synchronized boolean onTarget() {
        return armPID.onTarget();
    }
    public synchronized boolean isEnable() {
        return armPID.isEnable();
    }
    public synchronized void enable() {
        armPID.enable();
    }
    public synchronized void disable() {
        armPID.disable();
    }
    public double pidGet() {
        return getArmPosition();
    }
    public void pidWrite(double output) {
        set(output);
    }
    
    //TODO set different high limit whether pickup is up or down
    public void set(double output){
        if (output > 0 && getArmPosition() > armUpperLimit())
            armSpeedController.set(0);
        else if (output < 0 && getArmPosition() < armLowerLimit())
            armSpeedController.set(0);
        else
            armSpeedController.set(output);
    }
}
