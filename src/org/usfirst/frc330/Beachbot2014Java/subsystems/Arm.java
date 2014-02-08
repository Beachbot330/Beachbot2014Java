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
import org.usfirst.frc330.wpilibj.BeachbotPrefSendablePIDController;
/**
 *
 */
public class Arm extends Subsystem implements PIDSource, PIDOutput{
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController arm1 = RobotMap.armArm1;
    SpeedController arm2 = RobotMap.armArm2;
    AnalogChannel armPotentiometer = RobotMap.armArmPotentiometer;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private BeachbotPrefSendablePIDController armPID;
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
        armPID = new BeachbotPrefSendablePIDController(0,0,0,this,this, "armPID");
//        armPID = new PIDController(0,0,0,this,this);
        armPID.setAbsoluteTolerance(0.1);
        Preferences.getInstance().putDouble("ArmAbsoluteTolerance", 0.1);
        SmartDashboard.putData("ArmPID", armPID);
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
        if (!Preferences.getInstance().containsKey("armSetpointPickup"))
        {
            Preferences.getInstance().putDouble("armSetpointPickup", 1.7);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointPickup", 1.7);
    }
    
    public double getArmBackPickup() {
        return -Robot.arm.getArmFrontPickup();
    }
    
    public double getArmFrontCheckPickup() {
        if (!Preferences.getInstance().containsKey("armSetpointCheckPickup"))
        {
            Preferences.getInstance().putDouble("armSetpointCheckPickup", 1.6);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointCheckPickup", 1.6);
    }
    
    public double getArmBackCheckPickup() {
        return -Robot.arm.getArmFrontCheckPickup();
    }
    
    public double getArmFrontLoading() {
        if (!Preferences.getInstance().containsKey("armSetpointLoading"))
        {
            Preferences.getInstance().putDouble("armSetpointLoading", 1.0);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointLoading", 1.0);
    }
    
    public double getArmBackLoading() {
        return -Robot.arm.getArmFrontLoading();
    }
    
    public double getArmFrontCatching() {
        if (!Preferences.getInstance().containsKey("armSetpointCatching"))
        {
            Preferences.getInstance().putDouble("armSetpointCatching", .9);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("armSetpointCatching", .9);
    }
    
    public double getArmBackCatching() {
        return -Robot.arm.getArmFrontCatching();
    }
    
    public void manualArm() {
        double armCommand = Robot.oi.operatorJoystick.getY();
        /*
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
            set(0);
        }
        */
        set(armCommand);
    }
    public double getArmPosition()
    {
        return armPotentiometer.getAverageVoltage()-getArmZero();
    }
    
    public void set(double output){
        if (output > 0 && getArmPosition() > armUpperLimit())
        {
            arm1.set(0);
            arm2.set(0);
        }
        else if (output < 0 && getArmPosition() < armLowerLimit())
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
    
    public double armLowerLimit() {
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
    
    public void armDashPosition() {
        SmartDashboard.putNumber("armDashPosition", getArmPosition());
    }
    
    public void armSetPoint(double setpoint) {
       //armPID.setSetpoint(setpoint);
    }
    
    public void armSetPointFrontPickup() {
        armSetPoint(getArmFrontPickup());
    }
    
    public void armSetPointBackPickup() {
        armSetPoint(getArmBackPickup());
    }
    
    public void armSetPointFrontCheckPickup() {
        armSetPoint(getArmFrontCheckPickup());
    }
    
    public void armSetPointBackCheckPickup() {
        armSetPoint(getArmBackCheckPickup());
    }
    
    public void armSetPointFrontLoading() {
        armSetPoint(getArmFrontLoading());
    }
    
    public void armSetPointBackLoading() {
        armSetPoint(getArmBackLoading());
    }
    
    public void armSetpointFrontCatching() {
        armSetPoint(getArmFrontCatching());
    }
    
    public void armSetpointBackCatching() {
        armSetPoint(getArmBackCatching());
    }
    
    
    public double pidGet() {
        return getArmPosition();
    }
    public void pidWrite(double output) {
        set(output);
    }
}
