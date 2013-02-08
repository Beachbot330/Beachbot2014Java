/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.command;

/*
 * $Log: AutoSpreadsheetCommand.java,v $
 * Revision 1.1  2013-01-01 19:53:50  jross
 * Import code from Beachbot2012JavaBeta Project
 *
 * Revision 1.3  2012-10-24 03:27:58  jross
 * Add third parameter
 *
 * Revision 1.2  2012-10-21 22:10:32  jross
 * Add copy method so that multiple instances of the same command can be used
 *
 * Revision 1.1  2012-10-21 04:01:43  jross
 * Create abstract class to make sure that all commands implement required parameters for AutoSpreadsheet
 *
 */

/**
 *
 * @author joe
 */
public abstract class AutoSpreadsheetCommand extends Command {
    public abstract void setParam1(double param1);
    public abstract void setParam2(double param2);
    public abstract void setParam3(double param3);
    public abstract void setStopAtEnd(boolean stopAtEnd);
    public abstract AutoSpreadsheetCommand copy();
    
    
    public AutoSpreadsheetCommand()
    {
        super();
    }
    
    public AutoSpreadsheetCommand(String name)
    {
        super(name);
    }
}
