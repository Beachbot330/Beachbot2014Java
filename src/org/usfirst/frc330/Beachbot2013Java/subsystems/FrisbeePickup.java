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
import org.usfirst.frc330.Beachbot2013Java.Robot;
/*
 * $Log: FrisbeePickup.java,v $
 * Revision 1.10  2013-03-15 02:51:28  echan
 * added cvs log comments
 *
 */
 
/**
 *
 */
public class FrisbeePickup extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController frisbeePickupController = RobotMap.frisbeePickupFrisbeePickupController;
    DoubleSolenoid pickupSolenoid = RobotMap.frisbeePickupPickupSolenoid;
    DigitalInput pickupDiscSensor = RobotMap.frisbeePickupPickupDiscSensor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public FrisbeePickup() {
        InitializeSlowFrisbeePickup();
        InitializeFrisbeePickup();
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
//        setDefaultCommand(new PickupUp());
    }
    
    public double getFrisbeePickup() {
        if (!Preferences.getInstance().containsKey("FrisbeePickupSetpoint"))
        {
            Preferences.getInstance().putDouble("FrisbeePickupSetpoint", 0.0);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("FrisbeePickupSetpoint", 0.0);
    }
    
    public void InitializeFrisbeePickup()
    {
        if (!Preferences.getInstance().containsKey("FrisbeePickupMotorOutput"))
        {
            Preferences.getInstance().putDouble("FrisbeePickupMotorOutput", 0.6);
            Preferences.getInstance().save();
        }
    }
    
    public void InitializeSlowFrisbeePickup()
    {
        if (!Preferences.getInstance().containsKey("SlowFrisbeePickupMotorOutput"))
        {
            Preferences.getInstance().putDouble("SlowFrisbeePickupMotorOutput", 0.3);
            Preferences.getInstance().save();
        }
    }
    
    
    public void setFrisbeePickupUp()
    {
      pickupSolenoid.set(DoubleSolenoid.Value.kForward);
//        System.err.println("setFrisbeePickupUp");
    }
    private double pickupDownTime;
    public void setFrisbeePickupDown()
    {
        pickupSolenoid.set(DoubleSolenoid.Value.kReverse);
        if (!isPickupDown())
            pickupDownTime = Timer.getFPGATimestamp();
//        System.err.println("setFrisbeePickupDown");
    }
    
    public boolean isPickupDown()
    {
        if (pickupSolenoid.get() == DoubleSolenoid.Value.kReverse)
        {
            return true;
        }
        else
            return false;
    }
    
    public double getPickupDownTime()
    {
        return pickupDownTime;
    }
    
    public void setFrisbeePickupMotorStop()
    {
        frisbeePickupController.set(0);
//        System.err.println("setFrisbeePickupMotorStop");
    }
    
    public void setFrisbeePickupMotor(double speed)
    {
        frisbeePickupController.set(speed);
//        System.err.println("setFrisbeePickupMotor: " + speed);
    }
    
    public void setFrisbeePickupMotorPickup()
    {
        frisbeePickupController.set(Preferences.getInstance().getDouble("FrisbeePickupMotorOutput", 0.6));
//        System.err.println("setFrisbeePickupMotorPickup");
    }
    
    public void setSlowFrisbeePickupMotorPickup()
    {
        frisbeePickupController.set(Preferences.getInstance().getDouble("SlowFrisbeePickupMotorOutput", 0.3));
//        System.err.println("setSlowFrisbeePickupMotorPickup");
    }
    
    public void setFrisbeePickupMotorReverse()
    {
        frisbeePickupController.set(-Preferences.getInstance().getDouble("FrisbeePickupMotorOutput", 0.6));
//        System.err.println("setFrisbeePickupMotorReverse");
    }
}
