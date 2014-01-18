/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.wpilibj;

import edu.wpi.first.wpilibj.SPIDevice;
import edu.wpi.first.wpilibj.*;
/*
 * $Log: CFA634SPI.java,v $
 * Revision 1.1  2013-02-09 02:07:19  jross
 * Add LCD
 *
 * Revision 1.1  2013-01-28 03:14:08  jross
 * Working implementation of the CFA-634 spi API
 *
 */
/**
 *
 * @author joe
 */
public class CFA634SPI extends LCDInterface {

    private SPIDevice spi = null;
    

    /**
     * Create a new device on the SPI bus.<br>The chip select line is active low
     *
     * @param slot The module of the digital output for the device's chip select pin
     * @param csChannel	The channel for the digital output for the device's chip select pin
     */
    public CFA634SPI(int slot, int csChannel) {
        spi = new SPIDevice(slot, csChannel, SPIDevice.CS_ACTIVE_LOW);
        setSPIParams();
    }
    

    /**
     * Create a new device on the SPI bus.<br>The chip select line is active low
     *
     * @param cs	The digital output for the device's chip select pin
     */
    public CFA634SPI(DigitalOutput cs) {
        spi = new SPIDevice(cs, SPIDevice.CS_ACTIVE_LOW);
        setSPIParams();
    }
    
    public CFA634SPI(DigitalOutput clk, DigitalOutput mosi, DigitalInput miso, DigitalOutput cs)
    {
        spi = new SPIDevice(clk, mosi, miso, cs, SPIDevice.CS_ACTIVE_LOW);
        setSPIParams();
    }
    
    public CFA634SPI(int slot, int clkChannel, int mosiChannel, int misoChannel, int csChannel) {
        spi = new SPIDevice(slot, clkChannel, mosiChannel, misoChannel, csChannel, SPIDevice.CS_ACTIVE_LOW);
        setSPIParams();
    }
    
    private void setSPIParams() {
        spi.setBitOrder(SPIDevice.BIT_ORDER_MSB_FIRST);
        spi.setClockPolarity(SPIDevice.CLOCK_POLARITY_ACTIVE_LOW);
        spi.setDataOnTrailing(SPIDevice.DATA_ON_TRAILING_EDGE);
        spi.setFrameMode(SPIDevice.FRAME_MODE_CHIP_SELECT);

        spi.setClockRate(8000);
    }

    public void println(Line line, int startingColumn, String text)
    {
        int[] numBits = new int[20-startingColumn];
        long[] bytes = new long[20-startingColumn];
        
        for (int i = 0; i < text.length(); i++)
        {
            numBits[i] = 8;
            bytes[i] = text.charAt(i);
        }
        for (int i = text.length(); i < 20-startingColumn; i++)
        {
//            if (i<20)
            {
                numBits[i] = 8;
                bytes[i] = 0x20;
            }
        }
       
        setCursorPosition(line, startingColumn);

        spi.transfer(bytes, numBits);
    }

    
    public void print(Line line, int startingColumn, String text) {
        int[] numBits = new int[text.length()];
        long[] bytes = new long[text.length()];
        
        for (int i = 0; i < text.length(); i++)
        {
            numBits[i] = 8;
            bytes[i] = text.charAt(i);
        }
       
        setCursorPosition(line, startingColumn);

        spi.transfer(bytes, numBits);
    }
    
    
    public void cursorHome()
    {
        spi.transfer(1, 8);
    }
    
    
    public void hideCursor()
    {
        spi.transfer(4, 8);
    }
    
    
    public void backspace()
    {
        spi.transfer(8, 8);
    }
    
    
    public void lineFeed()
    {
        spi.transfer(10, 8);
    }
    
    
    public void deleteInPlace()
    {
        spi.transfer(11, 8);
    }
    
    
    public void clearScreen()
    {
        spi.transfer(12, 8);
    }
    
    
    public void carriageReturn()
    {
        spi.transfer(13, 8);
    }
    
    
    public void scrollOff()
    {
        spi.transfer(20, 8);
    }
    
    
    public void wrapOff()
    {
        spi.transfer(24, 8);
    }
    
    private void setCursorPosition(Line line, int column)
    {
        spi.transfer(17, 8);
        spi.transfer(column, 8);
        spi.transfer(line.value, 8);
    }
    
    
}
