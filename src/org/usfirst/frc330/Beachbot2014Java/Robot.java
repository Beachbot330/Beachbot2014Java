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
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.AutoSpreadsheet;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.Beachbot2014Java.commands.*;
import org.usfirst.frc330.Beachbot2014Java.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    public static OI oi;
    public static AutoSpreadsheet auto;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static Arm arm;
    public static Shooter shooter;
    public static Wings wings;
    public static Pickup pickup;
    public static SmartDashboardSender smartDashboardSender;
    public static LEDs lEDs;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    private static boolean practicerobot;
    public static boolean isPracticerobot() {
        return practicerobot;
    }
    
    public void robotInit() {
        if (!Preferences.getInstance().containsKey("PracticeRobot"))
        {
            Preferences.getInstance().putBoolean("PracticeRobot", false);
            Preferences.getInstance().save();
        }
        practicerobot = Preferences.getInstance().getBoolean("PracticeRobot", false);
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        arm = new Arm();
        shooter = new Shooter();
        wings = new Wings();
        pickup = new Pickup();
        smartDashboardSender = new SmartDashboardSender();
        lEDs = new LEDs();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	auto = new AutoSpreadsheet();
        auto.readScripts();
        
        auto.addCommand(new AutoLoadShooter());
        auto.addCommand(new AutoPickupClosePulse());
        auto.addCommand(new ConditionalLoad());
        auto.addCommand(new TurnGyroAbs(0));
        auto.addCommand(new TurnGyroRel(0));
        auto.addCommand(new Wait(0));
        auto.addCommand(new ShiftLow());
        auto.addCommand(new ShiftHigh());
        auto.addCommand(new DriveDistance(0));
        auto.addCommand(new DriveDistanceRel());
        auto.addCommand(new DriveDistanceAtAngle(0,0));
        auto.addCommand(new DriveDistanceAtRelAngle());
        auto.addCommand(new DriveTime());
        auto.addCommand(new DriveDistanceAtAngleWAccel(0,0));
        auto.addCommand(new DriveDistanceAtRelAngleWAccel());
        auto.addCommand(new TurnGyroWaypoint());
        auto.addCommand(new DriveWaypoint(0,0,0,0,false));
        auto.addCommand(new DriveWaypointBackward(0,0,0,0,true));
        auto.addCommand(new setGyroComp());
        auto.addCommand(new TurnGyroAbs(0));
        auto.addCommand(new TurnGyroRel(0));
        auto.addCommand(new WingsOpen());
        auto.addCommand(new WingsClose());
        auto.addCommand(new MoveArmToFrontLoadingPosition());
        auto.addCommand(new MoveArmToFrontSafePosition());
        auto.addCommand(new MoveArmToFrontBumperPosition());
        auto.addCommand(new MoveArmToFrontCatchingPosition());
        auto.addCommand(new MoveArmToFrontPickupPosition());
        auto.addCommand(new MoveArmToInsideAuto());
        auto.addCommand(new MoveArmToBackSafePosition());
        auto.addCommand(new MoveArmToRearBumperPosition());
        auto.addCommand(new MoveArmToRearCatchingPosition());
        auto.addCommand(new MoveArmToRearLoadingPosition());
        auto.addCommand(new MoveArmToRearPickupPosition());
        auto.addCommand(new MoveArmToVerticalPosition());
        auto.addCommand(new Shoot());
        auto.addCommand(new PickupForwardPulse());
        auto.addCommand(new PickupReversePulse());
        auto.addCommand(new PickupOff());
        auto.addCommand(new TurnKinectAbs(0));
        auto.addCommand(new TurnKinectRel(0));
        auto.addCommand(new WaitUntilCommand());
        auto.addCommand(new AutoPickupClose());
        auto.addCommand(new DriveDistanceAtKinectAngle());
        auto.addCommand(new StopDrive());
        auto.addCommand(new CheckKinect());
        auto.addCommand(new DriveKinectWaypointLeft(0,0,0,0,false));
        auto.addCommand(new DriveKinectWaypointRight(0,0,0,0,false));
        auto.addCommand(new TurnKinectWaypointLeft());
        auto.addCommand(new TurnKinectWaypointRight());
        auto.addCommand(new KinectDrive());
        
//        SmartDashboard.putData("Scheduler", Scheduler.getInstance());
        
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }
    public void autonomousInit() {
//        System.out.println("In Autonomous Init");
        chassis.resetPosition();
        autonomousCommand = auto.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        System.out.println(autonomousCommand.getName());
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
//        System.out.println("AP");
        Scheduler.getInstance().run();
        chassis.calcPeriodic();
        pickup.calcPeriodic();
    }
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        chassis.calcPeriodic();
        pickup.calcPeriodic();
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void testInit() {
        auto.readScripts();
        Robot.chassis.stopDrive();
    }
    
    public void disabledPeriodic()
    {
        chassis.calcPeriodic();
        Scheduler.getInstance().run();
    }
    
    public void disabledInit()
    {
//        System.out.println("In Disabled Init");
        auto.readScripts();
        Robot.chassis.stopDrive();     
        Robot.arm.stopArm();
        Robot.pickup.setPickupMotorOff();
        
    }    
}
