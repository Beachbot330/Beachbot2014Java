/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc0330.wpilibj;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.tables.ITable;

/*
 * $Log: BeachbotPrefSendablePIDController.java,v $
 * Revision 1.1  2012-12-28 03:49:46  jross
 * Import from 2012 Java Beta Project
 *
 * Revision 1.5  2012-11-14 05:07:28  jross
 * don't call onTarget until tolerance is set
 *
 * Revision 1.4  2012-10-21 22:35:39  jross
 * Fix case of SmartDashboard data
 *
 * Revision 1.3  2012-10-13 19:15:46  jross
 * Update to new format for 2013 beta
 *
 * Revision 1.2  2012-10-06 23:51:44  jross
 * Changes for 2013 beta library
 *
 * Revision 1.2  2012-10-05 01:48:18  jross
 * fixes from 10/4
 *
 * Revision 1.1  2012-10-02 03:30:56  jross
 * BeachbotPrefSendablePIDController extends PrefSendablePIDController by adding  automatic sending of the error, output, and OnTarget to SmartDashboard.
 *
 */

/**
 * BeachbotPrefSendablePIDController extends PrefSendablePIDController by adding
 * automatic sending of the error, output, and OnTarget to SmartDashboard.
 * 
 * @author Joe Ross
 */
public class BeachbotPrefSendablePIDController extends PrefSendablePIDController {
    
        public BeachbotPrefSendablePIDController(double p, double i, double d, PIDSource source, PIDOutput output, double period, String name)
        {
            super(p, i, d, source, output, period, name);
        }
        
        public BeachbotPrefSendablePIDController(double p, double i, double d, PIDSource source, PIDOutput output, String name)
        {
            super(p, i, d, source, output, name);
        }
        
        private ITable table;
        
        public String getSmartDashboardType()
        {
            return "BeachbotPrefPIDController";
        }
        
        public void initTable(ITable table)
        {
            this.table = table;
            super.initTable(table);
            if (table != null)
            {
                table.putNumber("error", super.getError());
                table.putNumber("output", super.get());
                table.putBoolean("onTarget", false);
                new Thread()
                {
                    public void run()
                    {
                        while (true)
                        {
                            Timer.delay(0.2);
                            BeachbotPrefSendablePIDController.this.table.putNumber("error", BeachbotPrefSendablePIDController.super.getError());
                            BeachbotPrefSendablePIDController.this.table.putNumber("output", BeachbotPrefSendablePIDController.super.get());
                            if (tolerance_set)
                                BeachbotPrefSendablePIDController.this.table.putBoolean("onTarget", BeachbotPrefSendablePIDController.super.onTarget());
                        }
                    }
                }.start();
            }
        }
        
        public ITable getTable()
        {
            return table;
        }
        
    private boolean tolerance_set = false; 
    
    /**
     * @deprecated 
     * @param percent 
     */
    public synchronized void setTolerance(double percent) {
        super.setTolerance(percent);
        tolerance_set = true;
    }

    public synchronized void setAbsoluteTolerance(double absvalue) {
	super.setAbsoluteTolerance(absvalue);
        tolerance_set = true;
    }
    
     public synchronized void setPercentTolerance(double percentage) {
	super.setPercentTolerance(percentage);
        tolerance_set = true;
    }
}
