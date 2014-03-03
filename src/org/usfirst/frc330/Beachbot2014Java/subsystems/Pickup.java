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
import org.usfirst.frc330.Beachbot2014Java.Robot;
/**
 *
 */
public class Pickup extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController pickup1 = RobotMap.pickupPickup1;
    SpeedController pickup2 = RobotMap.pickupPickup2;
    AnalogChannel currentSensor = RobotMap.pickupCurrentSensor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    static final int PICKUP_OFF = 0;
    static final int PICKUP_ON = 1;
    static final int PICKUP_PULSE = 2;
    static final int PICKUP_SLOW = 3;
    
    private int pickupState = 0;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPickupMotorOff() {
        pickup1.set(0);
        pickup2.set(0);
        pickupState = PICKUP_OFF;
    }
    
    public void setPickupMotorForward() {
        double speed = 1;
        if (!Preferences.getInstance().containsKey("PickupMotorOutput"))
        {
            Preferences.getInstance().putDouble("PickupMotorOutput", speed);
            Preferences.getInstance().save();
        }
        speed = Preferences.getInstance().getDouble("PickupMotorOutput",
                                                        speed);
        pickup1.set(speed);
        pickup2.set(-speed);
        pickupState = PICKUP_ON;
    }
    
    public void setPickupMotorReverse() {
        double speed = 1;
        if (!Preferences.getInstance().containsKey("PickupMotorOutput"))
        {
            Preferences.getInstance().putDouble("PickupMotorOutput", speed);
            Preferences.getInstance().save();
        }
        speed = Preferences.getInstance().getDouble("PickupMotorOutput",
                                                        speed);
        pickup1.set(-speed);
        pickup2.set(speed);
        pickupState = PICKUP_ON;
    }
    
    public double getCurrent() {
        return Math.abs(73.3*currentSensor.getAverageVoltage()/5.0-36.7);
    }
    
    public double getCurrentSensorVoltage() {
        return currentSensor.getAverageVoltage();
    }
    
    public double getCurrentLimit() {
        double limit = 20;
        if (!Preferences.getInstance().containsKey("PickupMotorCurrentLimit"))
        {
            Preferences.getInstance().putDouble("PickupMotorCurrentLimit", limit);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("PickupMotorCurrentLimit",
                                                        limit);
    }
    
    private void setPickupMotorSlowForward() {
        double speed = 0.3;
        if (!Preferences.getInstance().containsKey("PickupMotorOutputSlow"))
        {
            Preferences.getInstance().putDouble("PickupMotorOutputSlow", speed);
            Preferences.getInstance().save();
        }
        speed = Preferences.getInstance().getDouble("PickupMotorOutputSlow",
                                                        speed);
        pickup1.set(speed);
        pickup2.set(-speed);
    }
    
    private void setPickupMotorSlowReverse() {
        double speed = 0.3;
        if (!Preferences.getInstance().containsKey("PickupMotorOutputSlow"))
        {
            Preferences.getInstance().putDouble("PickupMotorOutputSlow", speed);
            Preferences.getInstance().save();
        }
        speed = Preferences.getInstance().getDouble("PickupMotorOutputSlow",
                                                        speed);
        pickup1.set(-speed);
        pickup2.set(speed);
    }
    
    public void calcPeriodic() {
        checkBallInPickup();
    }
    
    private int pulseCurrentSensorCount = 0;
    private int onCurrentSensorCount = 0;
    public boolean isBallInPickup() {
        boolean state;
        if (pickupState == PICKUP_OFF)
            state = false;
        if (pickupState == PICKUP_PULSE)
            state = (pulseCurrentSensorCount < 10 && pulseCurrentSensorCount>=0);
        else //PICKUP_ON
            state = (onCurrentSensorCount > 10 && onCurrentSensorCount >= 0);
        return state;
    }
    
    private void checkBallInPickup() {
        if (getCurrent() < getCurrentLimit()) {
            pulseCurrentSensorCount++;
        }
        else {
            if(pulseCurrentSensorCount<0){
                pulseCurrentSensorCount++;
            }
            else{
                pulseCurrentSensorCount = 0;
            }
        }
        if (getCurrent() > getCurrentLimit())
            onCurrentSensorCount++;
        else
            onCurrentSensorCount = 0;
//        System.out.println("onCurrentSensorCount: " + onCurrentSensorCount);
    }
 
    public void resetPulsedPickupBallCounter(){
        pulseCurrentSensorCount = -10;
    }
    
    public void pickupPulse(boolean direction) {
        if (!direction) {
            if (Robot.pickup.getCurrent() > Robot.pickup.getCurrentLimit())
                Robot.pickup.setPickupMotorSlowForward();
            else
                Robot.pickup.setPickupMotorForward();
        }
        else {
            if (Robot.pickup.getCurrent() > Robot.pickup.getCurrentLimit())
                Robot.pickup.setPickupMotorSlowReverse();
            else
                Robot.pickup.setPickupMotorReverse();
        }
        pickupState = PICKUP_PULSE;
    }
    
    public void pickupOn(boolean direction) {
        if (!direction) {
                Robot.pickup.setPickupMotorForward();
        }
        else {
                Robot.pickup.setPickupMotorReverse();
        }
        pickupState = PICKUP_ON;
    }
}
