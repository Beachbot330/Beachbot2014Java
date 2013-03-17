// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc330.Beachbot2013Java.subsystems;
import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import org.usfirst.frc330.Beachbot2013Java.RobotMap;
import org.usfirst.frc330.Beachbot2013Java.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.NetworkButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;
/*
 * $Log: Vision.java,v $
 * Revision 1.15  2013-03-17 01:57:22  jdavid
 * Added pickup sensor
 *
 * Revision 1.14  2013-03-16 19:18:39  echan
 * Changed the lookup table to work for y values from high to low
 *
 * Revision 1.13  2013-03-16 18:00:14  echan
 * completed Joe's code
 *
 * Revision 1.12  2013-03-16 04:11:56  jross
 * read vision table from file
 *
 * Revision 1.11  2013-03-16 02:25:38  jross
 * fix preferences angle to get doubles instead of ints
 *
 * Revision 1.10  2013-03-16 01:16:33  jross
 * check if LED preferences key exist
 *
 * Revision 1.9  2013-03-15 04:23:25  echan
 * Added the lookup table
 *
 * Revision 1.8  2013-03-15 02:51:40  echan
 * added cvs log comments
 *
 */
 
/**
 *
 */
public class Vision extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DigitalOutput highShooterLED = RobotMap.visionHighShooterLED;
    DigitalOutput lowShooterLED = RobotMap.visionLowShooterLED;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    boolean highLEDstate, lowLEDstate;
    double[][]aP =   {{10, 15, 20, 25, 30, 35, 40, 45, 50},
                    {2, 1.75, 1.5, 1.25, 1, .75, .5, .25, 0}};
    double leftx = 0, rightx = 0, lefty = 0, righty = 0, dx, dy, m, y;
    
    FileConnection file = null;
    BufferedReader reader = null;
    final String filename = "file:///2013VisionTable.csv";
    
    public Vision() {
            SmartDashboard.putBoolean("LEDEnable", false);
            SmartDashboard.putBoolean("LEDOverride", false);
            highLEDstate = false;
            lowLEDstate = false;
            readVisionFile();
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ControlLEDs());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void turnOnHighShooterLED()
    {
        highShooterLED.set(true);
        highLEDstate = true;
    }
    
    public void turnOffHighShooterLED()
    {
        highShooterLED.set(false);
        highLEDstate = false;
    }
    
    public void turnOnLowShooterLED()
    {
        lowShooterLED.set(true);
        lowLEDstate = true;
    }
    
    public void turnOffLowShooterLED()
    {
        lowShooterLED.set(false);
        lowLEDstate = false;
    }
    
    public boolean getLEDEnable()
    {
        return SmartDashboard.getBoolean("LEDEnable", false);
    }
    
    public boolean getLEDOverride()
    {
        return SmartDashboard.getBoolean("LEDOverride", false);
    }
    
    public boolean getHighLEDState()
    {
        return highLEDstate;
    }
    
    public boolean getLowLEDState()
    {
        return lowLEDstate;
    }
    
    public double turnOffLEDAngle()
    {
        if (!Preferences.getInstance().containsKey("LEDoffAngle"))
        {
            Preferences.getInstance().putDouble("LEDoffAngle", 45);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("LEDoffAngle", 45);
    }
    
    public double turnOnLEDAngle()
    {       
        if (!Preferences.getInstance().containsKey("LEDonAngle"))
        {
            Preferences.getInstance().putDouble("LEDonAngle", 35);
            Preferences.getInstance().save();
        }
        return Preferences.getInstance().getDouble("LEDonAngle", 35);
    }
    
    public double armLookupTable(double x)
    {
        if (x != -1)
        {
            for (int i = 0; aP[0][i] < x; i++)
        {
            leftx = aP[0][i];
            rightx = aP[0][i+1];
            
            lefty = aP[1][i];
            righty = aP[1][i+1];
        }
            System.out.println("X " + x + " lX " + leftx + " rX " + rightx + " lY " + lefty + " rY " + righty);
            dx = rightx - leftx;
            dy = righty - lefty; 
            m = dy/dx;
            y = m*(x-leftx) + lefty;
            return y;
        }
        
        else
        {
            return aP[1][0];
        }
    }
    
    public double getDistance()
    {
        return SmartDashboard.getNumber("distanceToCenter",-1);
    }
    
    public double getAngle()
    {
        return SmartDashboard.getNumber("angleToCenter",0);
    }
    
    public void readVisionFile()
    {
        try {
            file = (FileConnection) Connector.open(filename, Connector.READ);
            reader = new BufferedReader(new InputStreamReader(file.openInputStream()));
            
            String line;
            int comma;
            int lineCount=0;
            double x1, y1;
            
            while ((line = reader.readLine()) != null && lineCount < 9)
            {
                comma = line.indexOf(",");
                x1 = Double.parseDouble(line.substring(0,comma));
                y1 = Double.parseDouble(line.substring(comma+1));
//                System.out.println(x1 + ", " + y1);
                
                aP[0][lineCount] = x1;
                aP[1][lineCount] = y1;
                
                lineCount++;
            }
            while (lineCount < 9)
            {
                aP[0][lineCount] = 55;
                aP[1][lineCount] = 0;
                lineCount++;
            }
            System.out.println("Vision Table");
            
            for (int i=0; i<9;i++)
            {
                System.out.println(aP[0][i] + ", " + aP[1][i]);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
