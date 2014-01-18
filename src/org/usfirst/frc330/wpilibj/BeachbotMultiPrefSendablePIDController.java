/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.wpilibj;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.tables.ITable;
import java.util.Timer;
import java.util.TimerTask;
/*
 * $Log: BeachbotMultiPrefSendablePIDController.java,v $
 * Revision 1.3  2013-03-21 07:07:50  jross
 * add feedforward term to PID controllers
 *
 * Revision 1.2  2013-03-15 02:51:46  echan
 * added cvs log comments
 *
 */
 
/**
 *
 * @author joe
 */

    public class BeachbotMultiPrefSendablePIDController extends MultiPrefSendablePIDController {
    
        public BeachbotMultiPrefSendablePIDController(double p, double i, double d, PIDSource source, PIDOutput output, double period, String name)
        {
            super(p, i, d, source, output, period, name);
        }
        
        public BeachbotMultiPrefSendablePIDController(double p, double i, double d, PIDSource source, PIDOutput output, String name)
        {
            super(p, i, d, source, output, name);
        }
        
        public BeachbotMultiPrefSendablePIDController(double p, double i, double d, double f, PIDSource source, PIDOutput output, double period, String name)
        {
            super(p, i, d, f, source, output, period, name);
        }
        
        public BeachbotMultiPrefSendablePIDController(double p, double i, double d, double f, PIDSource source, PIDOutput output, String name)
        {
            super(p, i, d, f, source, output, name);
        }        
        
        private ITable table;
        
        public String getSmartDashboardType()
        {
            return "BeachbotMultiPrefPIDController";
        }
        
        Timer timer;
        private class SendDataTask extends TimerTask {
            public void run()
            {
                org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.this.table.putNumber("error", org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.super.getError());
                org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.this.table.putNumber("output", org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.super.get());
                if (tolerance_set)
                    org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.this.table.putBoolean("onTarget", org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.super.onTarget());
            }
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
//                timer = new Timer();
//                timer.schedule(new org.usfirst.frc330.wpilibj.BeachbotMultiPrefSendablePIDController.SendDataTask(), 0, 250);
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
