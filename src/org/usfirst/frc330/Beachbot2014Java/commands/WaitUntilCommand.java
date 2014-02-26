/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.Beachbot2014Java.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.AutoSpreadsheetCommand;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Wait until a certain amount of time in the match has elapsed. 
 */
public class WaitUntilCommand extends Command implements AutoSpreadsheetCommand {
    double timeToFinish = 0;
    
    public WaitUntilCommand() {
    }

    public void setParam1(double timeToFinish) {
        this.timeToFinish = timeToFinish;
    }

    public void setParam2(double param2) {
    }

    public void setParam3(double param3) {
    }

    public void setStopAtEnd(boolean stopAtEnd) {
    }

    public Command copy() {
        return new WaitUntilCommand();
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return DriverStation.getInstance().getMatchTime() >= timeToFinish;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
