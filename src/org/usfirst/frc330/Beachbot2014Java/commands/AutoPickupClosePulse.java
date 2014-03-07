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
import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPickupClosePulse extends CommandGroup implements AutoSpreadsheetCommand{
    
    public  AutoPickupClosePulse() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        addSequential(new MoveArmToPickupClose());
        addParallel(new PickupPulse());
    }
    public void setParam1(double param1) {
    }

    public void setParam2(double param2) {
    }

    public void setParam3(double param3) {
    }

    public void setStopAtEnd(boolean stopAtEnd) {
    }

    public Command copy() {
        return new AutoPickupClosePulse();
    }

    protected boolean isFinished() {
        return super.isFinished() || isTimedOut(); //To change body of generated methods, choose Tools | Templates.
    }
}
