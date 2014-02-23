// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc330.Beachbot2014Java.subsystems;
import org.usfirst.frc330.Beachbot2014Java.RobotMap;
import org.usfirst.frc330.Beachbot2014Java.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.Beachbot2014Java.Robot;
import org.usfirst.frc330.wpilibj.PrefSendablePIDController;
/**
 *
 */
public class Arm extends Subsystem implements PIDSource, PIDOutput{
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController arm1 = RobotMap.armArm1;
    SpeedController arm2 = RobotMap.armArm2;
    AnalogChannel armPotentiometer = RobotMap.armArmPotentiometer;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private PrefSendablePIDController armPID;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private static final String PREF_Arm_ArmPositionLowerLimit = "ArmPositionLowerLimit";
    private static final String PREF_Arm_ArmPositionUpperLimit = "ArmPositionUpperLimit";
    private static final String PREF_Arm_ArmPositionDash = "ArmPositionDash";
    
    public Arm() {
        super();
        armPID = new PrefSendablePIDController(0,0,0,this,this, "armPID");
        armPID.setOutputRange(-0.8, 0.8);
        armPID.setAbsoluteTolerance(0.18);
        Preferences.getInstance().putDouble("ArmAbsoluteTolerance", 0.1);
        SmartDashboard.putData("ArmPID", armPID);
        SmartDashboard.putBoolean("ArmOverride", false);
    }
    
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
    
    public void setArmZero()
    {        
        String name;
        
        if (Robot.isPracticerobot())
            name = "PracticeArmPositionZero";
        else
            name = "CompetitionArmPositionZero";
        
        Preferences.getInstance().putDouble(name, armPotentiometer.getAverageVoltage());
        Preferences.getInstance().save();
    }
    
    public double getArmFrontPickup() {
        if (!Preferences.getInstance().containsKey("armSetpointFrontPickup"))
        {
            Preferences.getInstance().putDouble("armSetpointFrontPickup", .1);
            Preferences.getInstance().save();
        }
        System.out.println("armSetpointFrontPickup: " + Preferences.getInstance().getDouble("armSetpointFrontPickup", .1));
        return Preferences.getInstance().getDouble("armSetpointFrontPickup", .1);
    }
    public double getArmBackPickup() {
        if (!Preferences.getInstance().containsKey("armSetpointBackPickup"))
        {
            Preferences.getInstance().putDouble("armSetpointBackPickup", 2.0);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointBackPickup", 2.0);
    }
    
    public double getArmFrontBumper() {
        if (!Preferences.getInstance().containsKey("armSetpointFrontBumper"))
        {
            Preferences.getInstance().putDouble("armSetpointFrontBumper", 0.2);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointCheckPickup", 0.2);
    }
    
    public double getArmBackBumper() {
        if (!Preferences.getInstance().containsKey("armSetpointBackBumper"))
        {
            Preferences.getInstance().putDouble("armSetpointBackBumper", 1.6);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointBackBumper", 1.6);
    }
    
    public double getArmFrontLoading() {
        if (!Preferences.getInstance().containsKey("armSetpointFrontLoading"))
        {
            Preferences.getInstance().putDouble("armSetpointFrontLoading", 0.8);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointFrontLoading", 0.8);
    }
    
    public double getArmVertical() {
        if (!Preferences.getInstance().containsKey("armSetpointVertical"))
        {
            Preferences.getInstance().putDouble("armSetpointVertical", 1.6);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointVertical", 1.6);
    }
    
    public double getArmBackLoading() {
        if (!Preferences.getInstance().containsKey("armSetpointBackLoading"))
        {
            Preferences.getInstance().putDouble("armSetpointBackLoading", 1.2);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointBackLoading", 1.2);
    }
    
    public double getArmFrontCatching() {
        if (!Preferences.getInstance().containsKey("armSetpointFrontCatching"))
        {
            Preferences.getInstance().putDouble("armSetpointFrontCatching", .9);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointFrontCatching", .9);
    }
    
    public double getArmBackCatching() {
        if (!Preferences.getInstance().containsKey("armSetpointBackCatching"))
        {
            Preferences.getInstance().putDouble("armSetpointBackCatching", 1.1);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointBackCatching", 1.1);
    }
    
    public double getArmFrontSafePoint() {
        if (!Preferences.getInstance().containsKey("armFrontSafePoint"))
        {
            Preferences.getInstance().putDouble("armFrontSafePoint", .4);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armFrontSafePoint", .4);
    }
    
    public double getArmBackSafePoint() {
        if (!Preferences.getInstance().containsKey("armBackSafePoint"))
        {
            Preferences.getInstance().putDouble("armBackSafePoint", 1.6);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armBackSafePoint", 1.6);
    }
    
    
    public void manualArm() {
        double armCommand = Robot.oi.operatorJoystick.getY();
        if (armCommand < 0) 
            armCommand = -(armCommand*armCommand);
        else
            armCommand = armCommand*armCommand;
        if (Math.abs(armCommand) > 0.10 && armPID.isEnable())
        {
            armPID.disable();
            set(armCommand);
        }
        else if (!armPID.isEnable() && Math.abs(armCommand) > 0.10)
        {
            set(armCommand);
        }
        else if (!armPID.isEnable())
        {
            armPID.setSetpoint(getArmPosition());
            armPID.enable();
        }
//        set(armCommand);    
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
    
    public double getArmPosition()
    {
        return armPotentiometer.getAverageVoltage()-getArmZero();
    }
    
    public void set(double output){
        if (output > 0 && getArmPosition() > getArmUpperLimit())
        {
            arm1.set(0);
            arm2.set(0);
        }
        else if (output < 0 && getArmPosition() < getArmLowerLimit())
        {
            arm1.set(0);
            arm2.set(0);
        }
        else
        {
            arm1.set(output);
            arm2.set(-output);
        }
    }
    
    public boolean isArmInBackSafePoint() {
        if (Robot.arm.getArmPosition() > Robot.arm.getArmBackSafePoint())
            return true;
        else
            return false;
    }
    
    public boolean isArmInFrontSafePoint() {
        if (Robot.arm.getArmPosition() < Robot.arm.getArmFrontSafePoint())
            return true;
        else
            return false;
    }
    
    public double getArmUpperLimit() {
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
    
    public double getArmLowerLimit() {
        double armpositionlowerlimit = -2;
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
    
    public void setArmSetPoint(double setpoint) {
       armPID.setSetpoint(setpoint);
    }
    
    public void setArmVertical() {
        setArmSetPoint(getArmVertical());
    }
    public void setArmSetPointFrontPickup() {
        setArmSetPoint(getArmFrontPickup());
    }
    
    public void setArmSetPointBackPickup() {
        setArmSetPoint(getArmBackPickup());
    }
    
    public void setArmSetPointFrontBumper() {
        setArmSetPoint(getArmFrontBumper());
    }
    
    public void setArmSetPointBackBumper() {
        setArmSetPoint(getArmBackBumper());
    }
    
    public void setArmSetPointFrontLoading() {
        setArmSetPoint(getArmFrontLoading());
    }
    
    public void setArmSetPointBackLoading() {
        setArmSetPoint(getArmBackLoading());
    }
    
    public void setArmSetpointFrontCatching() {
        setArmSetPoint(getArmFrontCatching());
    }
    
    public void setArmSetpointBackCatching() {
        setArmSetPoint(getArmBackCatching());
    }
    
    public double pidGet() {
        return getArmPosition();
    }
    public void pidWrite(double output) {
        set(output);
    }
    
    public boolean areWingsSafeToClose() {
        return areWingsSafeToClose(getSetpoint());
    }
    
    public boolean areWingsSafeToClose(double setpoint) {
        boolean disabled, front, back, override;
        disabled = (getArmPosition() < getArmFrontSafePoint() ||  getArmPosition() > getArmBackSafePoint()) && !armPID.isEnable();
        front = (getArmPosition() < getArmFrontSafePoint() && setpoint < getArmFrontSafePoint());
        back = (getArmPosition() > getArmBackSafePoint() && setpoint > getArmBackSafePoint());
        override = SmartDashboard.getBoolean("ArmOverride", false);
//        System.out.println("AreWingsSafeToClose: " + disabled + " " + front + " " + back + " " + override);
        return ( disabled || front || back || override);
    }
    
    public void stopArm() {
        armPID.disable();
        set(0);
    }
}
