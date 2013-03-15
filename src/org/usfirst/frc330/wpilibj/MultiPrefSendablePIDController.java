/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.wpilibj;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
/*
 * $Log$
 */
 
/**
 *
 * @author joe
 */
public class MultiPrefSendablePIDController extends PrefSendablePIDController{
    double p, i, d;
    String gainName;

    public MultiPrefSendablePIDController(double p, double i, double d, PIDSource source, PIDOutput output, double period, String name)
    {
        super(p,i,d,source,output,period, name);
        this.gainName = "default";
        readPIDPref(p,i,d,gainName);
    }
    
    public MultiPrefSendablePIDController(double p, double i, double d, PIDSource source, PIDOutput output, String name)
    {
        super(p,i,d,source,output,name);  
        this.gainName = "default";
        readPIDPref(p,i,d, gainName);
    }
    protected void savePIDPref()
    {
//        System.out.println("savePIDPref: " + name+gainName);
        Preferences.getInstance().putDouble(name+gainName+"P", getP());
        Preferences.getInstance().putDouble(name+gainName+"I", getI());
        Preferences.getInstance().putDouble(name+gainName+"D", getD());
        Preferences.getInstance().save();
//        System.out.println("Saved PID Preferences: " + this.name);
    }

    protected void readPIDPref(double p, double i, double d, String gainName) {
        String savedName = name;
        this.gainName = gainName;
        name = savedName+gainName;
//        System.out.println("readPIDPref: " +name);
        super.readPIDPref(p, i, d);
        name = savedName;
        if (table != null && !gainName.equals(table.getString("gainName", gainName)))
            table.putString("gainName", gainName);
    }
    
    public void setGainName(String gainName)
    {
        readPIDPref(0,0,0,gainName);

    }
    private ITable table;
    
    public String getSmartDashboardType()
    {
        return "MultiPrefPIDController";
    }
    
    public void initTable(ITable table)
    {
        if(this.table!=null)
            this.table.removeTableListener(listener);
        this.table = table;
        super.initTable(table);
        if(table!=null){
            table.putString("gainName", "default");
            table.addTableListener(listener, false);
        }
    }
    
    private ITableListener listener = new ITableListener() {
                boolean prevSave = false;

                public void valueChanged(ITable table, String key, Object value, boolean isNew) {
//                    System.out.println(key + " changed");
                    if (key.equals("gainName"))
                    {
//                        System.out.println("prevSave: " + prevSave + "curSave: " + ((Boolean) value).booleanValue());
                        MultiPrefSendablePIDController.this.setGainName((String)value);
                    }                
                }
            };
    
    public ITable getTable() {
        return table;    
    }
}
