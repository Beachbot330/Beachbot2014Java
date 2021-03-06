// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc330.Beachbot2014Java.subsystems;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import org.usfirst.frc330.Beachbot2014Java.RobotMap;
import org.usfirst.frc330.Beachbot2014Java.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class LEDs extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    final int ArduinoAddress = 33<<1;
    
    I2C arduino = new I2C(DigitalModule.getInstance(1),ArduinoAddress);  
    int data = 0;
    byte[] receivedData = new byte[4];
    byte[] prevData = new byte[4];
    
    public LEDs() {
        super();
        prevData[0] = 1;
        prevData[1] = -1;
        prevData[2] = 114;
        prevData[3] = 0;
        
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new HandleLEDs());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void readSmartDashboard() {
        
        data = (int) SmartDashboard.getNumber("Arduino/Value",0);
        if (data != 0) {
            receivedData[0] = (byte)((data & 0xFF000000) >> 24);
            receivedData[1] = (byte)((data & 0x00FF0000) >> 16);
            receivedData[2] = (byte)((data & 0x0000FF00) >> 8);
            receivedData[3] = (byte)((data & 0x000000FF));
            System.out.println("ReceivedData: " + data + " " + receivedData[0] + " " + receivedData[1] + " " + receivedData[2] + " " + receivedData[3]);
            arduino.transaction(receivedData, receivedData.length, null, 0);
            SmartDashboard.putNumber("Arduino/Value",0);
            if (receivedData[0] == 1) {
//                prevData = (byte[]) receivedData.clone();
                prevData[0] = receivedData[0];
                prevData[1] = receivedData[1];
                prevData[2] = receivedData[2];
                prevData[3] = receivedData[3];
            }
        }
    }
    
    public void setRed() {
        receivedData[0] = 1;
        receivedData[1] = -1;
        receivedData[2] = 0;
        receivedData[3] = 0;
        arduino.transaction(receivedData, receivedData.length, null, 0);
    }
    
    public void setGreen() {
        receivedData[0] = 1;
        receivedData[1] = 0;
        receivedData[2] = -1;
        receivedData[3] = 0;
//        System.out.println("Green before Transaction");
        arduino.transaction(receivedData, receivedData.length, null, 0);
//        System.out.println("Green after Transaction");
    }
    
    public void setBlue() {
        receivedData[0] = 1;
        receivedData[1] = 0;
        receivedData[2] = 0;
        receivedData[3] = -1;
//        System.out.println("Green before Transaction");
        arduino.transaction(receivedData, receivedData.length, null, 0);
//        System.out.println("Green after Transaction");
    }
    
        public void restorePrevious() {
//        System.out.println("RestorePrevious before Transaction");
        arduino.transaction(prevData, prevData.length, null, 0);
//        System.out.println("RestorePrevious after Transaction");        
    }
}
