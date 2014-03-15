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
import edu.wpi.first.wpilibj.buttons.*;
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
    public JoystickButton shootButton;
    public JoystickButton wingOpenButton;
    public Joystick rightJoystick;
    public JoystickButton pickupOffButton;
    public JoystickButton manualArmButton;
    public JoystickButton catchButton;
    public JoystickButton moveArmToOppositeSafeButton;
    public JoystickButton conditionalHerdButton;
    public JoystickButton pickupCloseButton;
    public JoystickButton wingsOpenButton;
    public JoystickButton wingsCloseButton;
    public JoystickButton condititionalPickupButton;
    public JoystickButton squareButtonHeld;
    public Joystick operatorJoystick;
    public KinectStick leftKinectJoystick;
    public KinectStick rightKinectJoystick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        rightKinectJoystick = new KinectStick(2);
        
        leftKinectJoystick = new KinectStick(1);
        
        operatorJoystick = new Joystick(3);
        
        squareButtonHeld = new JoystickButton(operatorJoystick, 1);
        squareButtonHeld.whileHeld(new AutoPickupClose());
        condititionalPickupButton = new JoystickButton(operatorJoystick, 2);
        condititionalPickupButton.whenReleased(new ConditionalLoad());
        wingsCloseButton = new JoystickButton(operatorJoystick, 6);
        wingsCloseButton.whenPressed(new WingsClose());
        wingsOpenButton = new JoystickButton(operatorJoystick, 8);
        wingsOpenButton.whenPressed(new WingsOpen());
        pickupCloseButton = new JoystickButton(operatorJoystick, 2);
        pickupCloseButton.whileHeld(new AutoPickupClose());
        conditionalHerdButton = new JoystickButton(operatorJoystick, 1);
        conditionalHerdButton.whenReleased(new ConditionalHerd());
        moveArmToOppositeSafeButton = new JoystickButton(operatorJoystick, 3);
        moveArmToOppositeSafeButton.whenPressed(new MoveArmToOppositeSafe());
        catchButton = new JoystickButton(operatorJoystick, 5);
        catchButton.whenPressed(new AutoCatch());
        manualArmButton = new JoystickButton(operatorJoystick, 7);
        manualArmButton.whileHeld(new ManualArm());
        pickupOffButton = new JoystickButton(operatorJoystick, 4);
        pickupOffButton.whileHeld(new PickupReverseThenStop());
        rightJoystick = new Joystick(2);
        
        wingOpenButton = new JoystickButton(rightJoystick, 3);
        wingOpenButton.whenPressed(new WingsOpen());
        shootButton = new JoystickButton(rightJoystick, 1);
        shootButton.whenPressed(new Shoot());
        shiftLowButton = new JoystickButton(rightJoystick, 2);
        shiftLowButton.whenPressed(new ShiftLow());
        leftJoystick = new Joystick(1);
        
        shiftHighButton = new JoystickButton(leftJoystick, 2);
        shiftHighButton.whenPressed(new ShiftHigh());
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("ShiftHigh", new ShiftHigh());
        SmartDashboard.putData("ShiftLow", new ShiftLow());
        SmartDashboard.putData("MarsRock", new MarsRock());
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("ManualArm", new ManualArm());
        SmartDashboard.putData("WingsOpen", new WingsOpen());
        SmartDashboard.putData("WingsClose", new WingsClose());
        SmartDashboard.putData("WingsToggle", new WingsToggle());
        SmartDashboard.putData("PickupForward", new PickupForward());
        SmartDashboard.putData("PickupReversePulse", new PickupReversePulse());
        SmartDashboard.putData("PickupOff", new PickupOff());
        SmartDashboard.putData("PickupReverse", new PickupReverse());
        SmartDashboard.putData("PickupForwardPulse", new PickupForwardPulse());
        SmartDashboard.putData("MoveArmTo2BallHoldingPosition", new MoveArmTo2BallHoldingPosition());
        SmartDashboard.putData("MoveArmToFrontPickupPosition", new MoveArmToFrontPickupPosition());
        SmartDashboard.putData("MoveArmToFrontBumperPosition", new MoveArmToFrontBumperPosition());
        SmartDashboard.putData("MoveArmToFrontLoadingPosition", new MoveArmToFrontLoadingPosition());
        SmartDashboard.putData("MoveArmToFrontCatchingPosition", new MoveArmToFrontCatchingPosition());
        SmartDashboard.putData("MoveArmToVerticalPosition", new MoveArmToVerticalPosition());
        SmartDashboard.putData("MoveArmToRearPickupPosition", new MoveArmToRearPickupPosition());
        SmartDashboard.putData("MoveArmToRearBumperPosition", new MoveArmToRearBumperPosition());
        SmartDashboard.putData("MoveArmToRearCatchingPosition", new MoveArmToRearCatchingPosition());
        SmartDashboard.putData("MoveArmToRearLoadingPosition", new MoveArmToRearLoadingPosition());
        SmartDashboard.putData("MoveArmToLoading", new MoveArmToLoading());
        SmartDashboard.putData("MoveArmToPickupClose", new MoveArmToPickupClose());
        SmartDashboard.putData("MoveArmToAfterLoading", new MoveArmToAfterLoading());
        SmartDashboard.putData("MoveArmToPickupFar", new MoveArmToPickupFar());
        SmartDashboard.putData("AutoPickupForward", new AutoPickupForward());
        SmartDashboard.putData("CatchWithSensor", new CatchWithSensor());
        SmartDashboard.putData("CheesyDrive", new CheesyDrive());
        SmartDashboard.putData("ShootAndCatch", new ShootAndCatch());
        SmartDashboard.putData("AutoCatch", new AutoCatch());
        SmartDashboard.putData("AutoPickupReverse", new AutoPickupReverse());
        SmartDashboard.putData("PickupPulse", new PickupPulse());
        SmartDashboard.putData("AutoPickupClose", new AutoPickupClose());
        SmartDashboard.putData("PickupOn", new PickupOn());
        SmartDashboard.putData("AutoPickupFar", new AutoPickupFar());
        SmartDashboard.putData("SetArmZero", new SetArmZero());
        SmartDashboard.putData("PickupReverseThenStop", new PickupReverseThenStop());
        SmartDashboard.putData("MoveArmToBackSafePosition", new MoveArmToBackSafePosition());
        SmartDashboard.putData("MoveArmToCloseSafePosition", new MoveArmToCloseSafePosition());
        SmartDashboard.putData("MoveArmToFrontSafePosition", new MoveArmToFrontSafePosition());
        SmartDashboard.putData("ConditionalLoad", new ConditionalLoad());
        SmartDashboard.putData("debugOne", new debugOne());
        SmartDashboard.putData("debugTwo", new debugTwo());
        SmartDashboard.putData("MoveArmToOppositeSafe", new MoveArmToOppositeSafe());
        SmartDashboard.putData("ConditionalHerd", new ConditionalHerd());
        SmartDashboard.putData("MoveArmToLimitFront", new MoveArmToLimitFront());
        SmartDashboard.putData("MoveArmToLimitBack", new MoveArmToLimitBack());
        SmartDashboard.putData("MoveArmToCloseLimit", new MoveArmToCloseLimit());
        SmartDashboard.putData("AutoLoadShooter", new AutoLoadShooter());
        SmartDashboard.putData("KinectDrive", new KinectDrive());
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
    public KinectStick getLeftKinectJoystick() {
        return leftKinectJoystick;
    }
    public KinectStick getRightKinectJoystick() {
        return rightKinectJoystick;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
