/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc330.wpilibj;

/**
 *
 * @author Joe-XPS13-W7
 */
public abstract class LCDInterface {

    abstract void backspace();

    abstract void carriageReturn();

    abstract void clearScreen();

    abstract void cursorHome();

    abstract void deleteInPlace();

    abstract void hideCursor();

    abstract void lineFeed();

    abstract void print(Line line, int startingColumn, String text);

    abstract void println(Line line, int startingColumn, String text);

    abstract void scrollOff();

    abstract void wrapOff();
    
    public static class Line {

        /**
         * The integer value representing this enumeration
         */
        public final int value;

	static final int kUser1_val = 0;
        static final int kUser2_val = 1;
        static final int kUser3_val = 2;
        static final int kUser4_val = 3;


        /**
         * Line at the Top of the screen
	 */
	public static final CFA634SPI.Line kUser1 = new CFA634SPI.Line(kUser1_val);
        /**
         * Line on the user screen
         */
        public static final CFA634SPI.Line kUser2 = new CFA634SPI.Line(kUser2_val);
        /**
         * Line on the user screen
         */
        public static final CFA634SPI.Line kUser3 = new CFA634SPI.Line(kUser3_val);
        /**
         * Line on the user screen
         */
        public static final CFA634SPI.Line kUser4 = new CFA634SPI.Line(kUser4_val);


        private Line(int value) {
            this.value = value;
        }
    }
    
}
