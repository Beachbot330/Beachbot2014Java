// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc330.Beachbot2014Java;
import org.usfirst.frc330.Beachbot2014Java.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shiftHighButton;
    public Joystick leftJoystick;
    public JoystickButton shiftLowButton;
    public Joystick rightJoystick;
    public JoystickButton pickupUpButton;
    public JoystickButton pickupDownButton;
    public JoystickButton shootButton;
    public JoystickButton manualArmButton;
    public JoystickButton wingsToggleButton;
    public JoystickButton pickupOffButton;
    public JoystickButton pickupForwardButton;
    public JoystickButton pickupReverseButton;
    public JoystickButton triggerCloseButton;
    public JoystickButton triggerReleaseButton;
    public Joystick operatorJoystick;
    public KinectStick kinectJoystick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        kinectJoystick = new KinectStick(1);
        
        operatorJoystick = new Joystick(3);
        
//        triggerReleaseButton = new JoystickButton(operatorJoystick, 1);
//        triggerReleaseButton.whenPressed(new ReleaseShooterPistons());
        triggerCloseButton = new JoystickButton(operatorJoystick, 10);
        triggerCloseButton.whileHeld(new TrapShooterPistons());
        pickupReverseButton = new JoystickButton(operatorJoystick, 8);
        pickupReverseButton.whileHeld(new PickupReverse());
        pickupForwardButton = new JoystickButton(operatorJoystick, 7);
        pickupForwardButton.whenPressed(new PickupForwardPulse());
        pickupOffButton = new JoystickButton(operatorJoystick, 5);
        pickupOffButton.whileHeld(new PickupOff());
        wingsToggleButton = new JoystickButton(operatorJoystick, 9);
        wingsToggleButton.whenPressed(new WingsToggle());
        manualArmButton = new JoystickButton(operatorJoystick, 2);
        manualArmButton.whileHeld(new ManualArm());
        shootButton = new JoystickButton(operatorJoystick, 1);
        shootButton.whenPressed(new Shoot());
        pickupDownButton = new JoystickButton(operatorJoystick, 5);
        pickupDownButton.whenPressed(new PickupDown());
        pickupUpButton = new JoystickButton(operatorJoystick, 4);
        pickupUpButton.whenPressed(new PickupUp());
        rightJoystick = new Joystick(2);
        
        shiftLowButton = new JoystickButton(rightJoystick, 1);
        shiftLowButton.whenPressed(new ShiftLow());
        leftJoystick = new Joystick(1);
        
        shiftHighButton = new JoystickButton(leftJoystick, 1);
        shiftHighButton.whenPressed(new ShiftHigh());
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("MarsRock", new MarsRock());
        SmartDashboard.putData("PickupUp", new PickupUp());
        SmartDashboard.putData("PickupDown", new PickupDown());
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("ManualArm", new ManualArm());
        SmartDashboard.putData("WingsOpen", new WingsOpen());
        SmartDashboard.putData("WingsClose", new WingsClose());
        SmartDashboard.putData("WingsToggle", new WingsToggle());
        SmartDashboard.putData("PickupForward", new PickupForward());
        SmartDashboard.putData("PickupOff", new PickupOff());
        SmartDashboard.putData("PickupReverse", new PickupReverse());
        SmartDashboard.putData("ReleaseShooterPistons", new ReleaseShooterPistons());
        SmartDashboard.putData("TrapShooterPistons", new TrapShooterPistons());
        SmartDashboard.putData("AutoPickupOn", new AutoPickupOn());
        SmartDashboard.putData("PickupForwardPulse", new PickupForwardPulse());
        SmartDashboard.putData("SendDefaultSmartDashboardData", new SendDefaultSmartDashboardData());
        SmartDashboard.putData("ShooterOn", new ShooterOn());
        SmartDashboard.putData("ShooterOff", new ShooterOff());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getLeftJoystick() {
        return leftJoystick;
    }
    public Joystick getRightJoystick() {
        return rightJoystick;
    }
    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }
    public KinectStick getKinectJoystick() {
        return kinectJoystick;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
