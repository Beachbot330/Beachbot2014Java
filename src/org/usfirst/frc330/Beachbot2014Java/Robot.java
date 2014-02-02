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
    public static Pickup pickup;
    public static Wings wings;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        arm = new Arm();
        shooter = new Shooter();
        pickup = new Pickup();
        wings = new Wings();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	auto = new AutoSpreadsheet();
        auto.readScripts();
        
        auto.addCommand(new TurnGyroAbs(0));
        auto.addCommand(new TurnGyroRel(0));
        auto.addCommand(new Wait(0));
        auto.addCommand(new ShiftLow());
        auto.addCommand(new ShiftHigh());
        auto.addCommand(new DriveEncoder(0));
        auto.addCommand(new DriveEncoderRel());
        auto.addCommand(new DriveEncoderGyro(0,0));
        auto.addCommand(new DriveEncoderGyroRel());
        auto.addCommand(new DriveTime());
        auto.addCommand(new DriveEncoderGyroRamp(0,0));
        auto.addCommand(new DriveEncoderGyroRampRel());
        auto.addCommand(new TurnGyroWaypoint());
        auto.addCommand(new DriveWaypoint(0,0,0,0,false));
        auto.addCommand(new DriveWaypointBackward(0,0,0,0,true));
        auto.addCommand(new setGyroComp());
        auto.addCommand(new TurnGyroAbs(0));
        auto.addCommand(new TurnGyroRel(0));
        
        SmartDashboard.putData("Scheduler", Scheduler.getInstance());
        
        
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }
    public void autonomousInit() {
        System.out.println("In Autonomous Init");
        chassis.resetPosition();
        autonomousCommand = auto.getSelected();
        // schedule the autonomous command (example)
        /*if (autonomousCommand != null)*/ autonomousCommand.start();
        System.out.println(autonomousCommand.getName());
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
//        System.out.println("AP");
        chassis.calcPeriodic();
        Scheduler.getInstance().run();
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
        chassis.calcPeriodic();
        Scheduler.getInstance().run();
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        auto.readScripts();
        Robot.chassis.stopDrive();
        LiveWindow.run();
    }
}
