/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.wpilibj;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SPIDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc330.wpilibj.LCDInterface.Line;
/*
 * $Log: SmartDashboardLCD.java,v $
 * Revision 1.1  2013-02-09 02:07:19  jross
 * Add LCD
 *
 * Revision 1.1  2013-01-28 03:14:46  jross
 * Working implementation of printing SmartDashboardLCD Variables to the CGA-634 LCD over SPI
 *
 */
/**
 *
 * @author joe
 */
public class SmartDashboardLCD { 
    
    
    private LCDInterface lcd;
    
    /**
     * Create a new device on the SPI bus.<br>The chip select line is active low
     *
     * @param lcd
     */
    public SmartDashboardLCD(LCDInterface lcd) {
        this.lcd = lcd;
        this.lcd.clearScreen();
        this.lcd.hideCursor();
        this.lcd.scrollOff();
        this.lcd.wrapOff();
    }
    
    
    String[] lines = new String[4];
    
    public void addLine(Line row, String variable)
    {
        lines[row.value] = variable;
        lcd.println(row, 0, variable + ":");
    }
    
    public void updateLCD()
    {

        lcd.println(CFA634SPI.Line.kUser1, 15, getDisplayValue(SmartDashboard.getNumber(lines[0], 0)));
        lcd.println(CFA634SPI.Line.kUser2, 15, getDisplayValue(SmartDashboard.getNumber(lines[1], 0)));
        lcd.println(CFA634SPI.Line.kUser3, 15, getDisplayValue(SmartDashboard.getNumber(lines[2], 0)));
        lcd.println(CFA634SPI.Line.kUser4, 15, getDisplayValue(SmartDashboard.getNumber(lines[3], 0)));

        
    }
    
    private String getDisplayValue(double number)
    {
        String value = String.valueOf(number);
        if (value.length() > 4)
            value = value.substring(0,4);
        return value;
    }
    
    
    
}
