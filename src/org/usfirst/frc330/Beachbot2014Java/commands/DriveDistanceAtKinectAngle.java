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
import org.usfirst.frc330.Beachbot2014Java.Robot;
import org.usfirst.frc330.Beachbot2014Java.subsystems.Chassis;
/**
 *
 */
public class  DriveDistanceAtKinectAngle extends DriveDistance{
    double angle;
    
    public DriveDistanceAtKinectAngle()
    {
        this(0,0,0,0,true);
    }
    
    public DriveDistanceAtKinectAngle(double distance, double angle)
    {
        this(distance, 0, angle, 0, true);
    }
    
    public DriveDistanceAtKinectAngle(double distance, double tolerance, double angle, double timeout, boolean stopAtEnd)
    {
        super(distance, tolerance, timeout, stopAtEnd);
        this.angle = angle;
    }
    
    double direction;
    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
        if (!Robot.chassis.getShiftState())
        {
            Robot.chassis.gyroPID.setGainName(Chassis.DRIVELOW);
        }
        else
        {
            Robot.chassis.gyroPID.setGainName(Chassis.DRIVEHIGH);
        }
        if (Robot.oi.getLeftKinectJoystick().getY() > Robot.oi.getRightKinectJoystick().getY())
        {
            direction = 1;
            System.out.println("LEFT KINECT");
            System.out.println("Joystick Left angle: " + 
                    Robot.oi.getLeftKinectJoystick().getY() + "Joystick Right angle: " + Robot.oi.getRightKinectJoystick().getY());
        }
        else
        {
            direction = -1;
            System.out.println("RIGHT KINECT");
            System.out.println("Joystick Left angle: " + 
                    Robot.oi.getLeftKinectJoystick().getY() + "Joystick Right angle: " + Robot.oi.getRightKinectJoystick().getY());
        }
        angle = angle*direction;
        System.out.println("Angle: " + angle);
        Robot.chassis.gyroPID.setSetpoint(angle);
        Robot.chassis.gyroPID.enable();            
    }
    
    public void setParam3(double angle) {
        this.angle = angle;
    }
    public Command copy() {
        return new DriveDistanceAtKinectAngle();
    }
}
