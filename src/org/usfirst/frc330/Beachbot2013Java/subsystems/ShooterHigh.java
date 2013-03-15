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
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.Beachbot2013Java.Robot;
import org.usfirst.frc330.Beachbot2013Java.RobotMap;
import org.usfirst.frc330.Beachbot2013Java.commands.ShootHigh;
/*
 * $Log$
 */
 
/**
 *
 */
public class ShooterHigh extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController shooterHighController = RobotMap.shooterHighShooterHighController;
    DigitalInput shooterHighHallEffectSensor = RobotMap.shooterHighShooterHighHallEffectSensor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    Counter ShooterHighHallEffectCounter = RobotMap.shooterHighShooterHighHallEffectCounter;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ShootHigh());
    }
    double counter = 0;
    public void shoot(double voltage) {
        if (counter%10==0)
            SmartDashboard.putNumber("voltageHigh", voltage);
        counter++;
        shooterHighController.set(voltage);
    }
    
    public double getSpeed()
    {
//        return shooterHighEncoder.getRate();
        return (60/ShooterHighHallEffectCounter.getPeriod());
    }
    
    public void ShooterHighMotorToggle(double voltage)
    {
//        if ( voltage == 0 )
            Robot.shooterHigh.shoot(voltage);
//        else
//            Robot.shooterHigh.shoot(0);
    }
}
