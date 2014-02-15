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
import org.usfirst.frc330.wpilibj.DummyPIDOutput;
import org.usfirst.frc330.wpilibj.MultiPrefSendablePIDController;
/**
 *
 */
public class Chassis extends Subsystem implements PIDSource {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController leftDrive1 = RobotMap.chassisLeftDrive1;
    SpeedController leftDrive2 = RobotMap.chassisLeftDrive2;
    SpeedController leftDrive3 = RobotMap.chassisLeftDrive3;
    SpeedController rightDrive1 = RobotMap.chassisRightDrive1;
    SpeedController rightDrive2 = RobotMap.chassisRightDrive2;
    SpeedController rightDrive3 = RobotMap.chassisRightDrive3;
    Compressor compressor = RobotMap.chassisCompressor;
    Relay shiftSpike = RobotMap.chassisShiftSpike;
    Encoder leftDriveEncoder = RobotMap.chassisLeftDriveEncoder;
    Encoder rightDriveEncoder = RobotMap.chassisRightDriveEncoder;
    Gyro gyro = RobotMap.chassisGyro;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public MultiPrefSendablePIDController gyroPID, leftDrivePID, rightDrivePID;
    private DummyPIDOutput gyroOutput, leftDriveOutput, rightDriveOutput;
    
    
    public static final String DRIVELOW = "DriveLow";
    public final static String DRIVEHIGH = "DriveHigh";
    public final static String TURNLOW = "TurnLow";
    public final static String TURNHIGH = "TurnHigh";
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TankDrive());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Chassis (){ 
        compressor.start();
        
        gyroOutput = new DummyPIDOutput();
//        gyroOutputHigh = new DummyPIDOutput();
        leftDriveOutput = new DummyPIDOutput();
        rightDriveOutput = new DummyPIDOutput();
//        leftDriveOutputHigh = new DummyPIDOutput();
//        rightDriveOutputHigh = new DummyPIDOutput();
        
        gyroPID = new MultiPrefSendablePIDController(0.11,0,0,this,gyroOutput,"gyro");
//      gyroPIDHigh = new MultiPrefSendablePIDController(0.05,0,0,gyro,gyroOutputHigh,"gyroHigh");
        leftDrivePID = new MultiPrefSendablePIDController(0.2,0,0,leftDriveEncoder,leftDriveOutput,"leftDrive");
        rightDrivePID = new MultiPrefSendablePIDController(0.2,0,0,rightDriveEncoder,rightDriveOutput,"rightDrive");
//        leftDrivePIDHigh = new BeachbotPrefSendablePIDController(0.013,0,0,leftDriveEncoder,leftDriveOutputHigh,"leftDriveHigh");
//        rightDrivePIDHigh = new BeachbotPrefSendablePIDController(0.013,0,0,rightDriveEncoder,rightDriveOutputHigh,"rightDriveHigh");
        
        leftDrivePID.setOutputRange(-0.8, 0.8);
        rightDrivePID.setOutputRange(-0.8, 0.8);
//        leftDrivePIDHigh.setOutputRange(-0.8, 0.8);
//        rightDrivePIDHigh.setOutputRange(-0.8, 0.8);
        
        SmartDashboard.putData("gyroPID", gyroPID);
//        SmartDashboard.putData("gyroPIDHigh", gyroPIDHigh);
        SmartDashboard.putData("leftDrivePID", leftDrivePID);
        SmartDashboard.putData("rightDrivePID", rightDrivePID);
//        SmartDashboard.putData("leftDrivePIDHigh", leftDrivePIDHigh);
//        SmartDashboard.putData("rightDrivePIDHigh", rightDrivePIDHigh);
        final double diameter = 4;
        final double PulseperRevolution = 250;
        final double leftPracticePulsePerRevolution = 360;
        final double encoderGearRatio = 3;
        final double gearRatio = 54.0/30.0;
        final double Fudgefactor = 1.06;
        final double distanceperpulse = Math.PI*diameter/PulseperRevolution/encoderGearRatio/gearRatio * Fudgefactor;
        final double leftPracticedistanceperpulse = Math.PI*diameter/leftPracticePulsePerRevolution/encoderGearRatio/gearRatio * Fudgefactor;
//        if (Robot.isPracticerobot())
//            leftDriveEncoder.setDistancePerPulse(leftPracticedistanceperpulse);
//        else
            leftDriveEncoder.setDistancePerPulse(distanceperpulse);
        rightDriveEncoder.setDistancePerPulse(distanceperpulse);
        
        setGyroOffset(0);
    }
    
     public double getDriveRampStepHigh() {
        if (!Preferences.getInstance().containsKey("DriveRampMaxOutpuStepHigh"))
        {
            Preferences.getInstance().putDouble("DriveRampMaxOutpuStepHigh", 
                                                0.02);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("DriveRampMaxOutpuStepHigh",
                                                   0.02);
    }
    public double getDriveRampStepLow() {
        if (!Preferences.getInstance().containsKey("DriveRampMaxOutpuStepLow"))
        {
            Preferences.getInstance().putDouble("DriveRampMaxOutpuStepLow", 
                                                0.02);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("DriveRampMaxOutpuStepLow", 
                                                   0.02);
    }
    
     public void tankDrive(Joystick leftJoystick, Joystick rightJoystick)
    {
        tankDrive(leftJoystick.getY(),rightJoystick.getY());
    }
    
    public void tankDrive(double left, double right)
    {
        leftDrive1.set(left);
        leftDrive2.set(left);
        leftDrive3.set(left);
        rightDrive1.set(-right);
        rightDrive2.set(-right);
        rightDrive3.set(-right);
    }
    
    public void shiftHigh()
    {
        shiftSpike.set(Relay.Value.kForward);
        System.out.println("Shift High");
    }
    
    public void shiftLow()
    {
        shiftSpike.set(Relay.Value.kReverse);
        System.out.println("Shift Low");
    }
    
    public boolean getShiftState()
    {
//        System.out.println("Shift State: " + shiftSolenoid.get());
        if(shiftSpike.get() == Relay.Value.kForward) 
            return true;
        else
            return false;
    }
    
    public double getRightDistance() {
        return rightDriveEncoder.getDistance();
    }
    
    public double getLeftDistance() {
        return leftDriveEncoder.getDistance();
    }
    
    public double getAngle() {
        return gyro.getAngle() + gyroComp;
    }
    
    double maxGyroRate = 0;
    
    public void pidDrive()
    {
        double left, right;
        left = Robot.oi.leftJoystick.getY();
        right = Robot.oi.rightJoystick.getY();
        if (DriverStation.getInstance().isDisabled())
        {
            stopDrive();
        }
        else
        {
            left = left-leftDriveOutput.getOutput() - gyroOutput.getOutput();
            right = right-rightDriveOutput.getOutput() + gyroOutput.getOutput();
            tankDrive(left, right);
        }
    
    }
    
    private double gyroComp = 0;
    public void setGyroOffset(double gyroComp) {
        this.gyroComp = gyroComp;
        SmartDashboard.putNumber("gyroComp", gyroComp);
    }
    
    private double x=0, y=0;
    private double prevLeftEncoderDistance=0, prevRightEncoderDistance=0;
    
    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    int counter = 0;
    public void calcXY()
    {
        double distance, leftEncoderDistance, rightEncoderDistance, gyroAngle;
        
        leftEncoderDistance = leftDriveEncoder.getDistance();
        rightEncoderDistance = rightDriveEncoder.getDistance();
        gyroAngle = getAngle();
        distance =  ((leftEncoderDistance - prevLeftEncoderDistance) + (rightEncoderDistance - prevRightEncoderDistance))/2;
        x = x + distance * Math.sin(Math.toRadians(gyroAngle));
        y = y + distance * Math.cos(Math.toRadians(gyroAngle));
        prevLeftEncoderDistance = leftEncoderDistance;
        prevRightEncoderDistance = rightEncoderDistance;
    }
    
    public void calcPeriodic()
    {
        calcXY();
        pidDrive();
        counter++;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    
    public void resetPosition()
    {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
        gyro.reset();
        setXY(0,0);
        this.prevLeftEncoderDistance = 0;
        this.prevRightEncoderDistance = 0;
    }
    
    public void stopDrive()
    {
        if (gyroPID.isEnable())
            gyroPID.disable();
//        gyroPIDHigh.disable();
        if (leftDrivePID.isEnable())
            leftDrivePID.disable();
        if (rightDrivePID.isEnable())
            rightDrivePID.disable();
//        leftDrivePIDHigh.disable();
//        rightDrivePIDHigh.disable();            
        tankDrive(0, 0);  
    }
    public double pidGet() {
        return getAngle();
    }
}
