package ex1;

import fyw.turtles.*;

public class Ex1Measurements {
    
    /**
     * create an input frame
     * @param args strings from command line
     */
    public static void main(String[] args) {
        InputFrame theInputFrame = new InputFrame();
        
        /* Write your own code and comments within the area below
         *vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
         */
        
        /* Get input from the input frame, prompt for no. of inches */
        int inches = theInputFrame.getInt("Enter number of inches to convert.");
        if(inches<0){
        	/* Negative number, return error*/
        	System.out.println("ERROR: Negative number input.");
        }else{
        	/* Positive number, continue. */
        	
        	/* Display the original no. of inches */
        	String Output = inches + " inches = ";
        	/* If there are > 1mile then count num of miles, add to string and reduce number of inches */
        	if(inches > 63360){Output =(Output + (inches / 63360)); if((inches / 63360) > 1){Output = Output + " Miles";}else{Output = Output + " Mile";}; inches = inches - ((inches / 63360) * 63360 );}
        	/* If there are > 1furlong then count num of furlong, add to string and reduce number of inches */
        	if(inches > 7920){Output = Output + ", " +  (inches / 7920); if((inches / 7920)>1){Output = Output + " Furlongs";}else{Output = Output + " Furlong";}; inches = inches - ((inches / 7920) * 7920 );}
        	/* If there are > 1chains then count num of chains, add to string and reduce number of inches */
        	if(inches > 792){Output = Output + ", " +  (inches / 792 ); if ((inches / 792) >1) {Output = Output + " Chains";}else{Output = Output + " Chain";}; inches = inches - ((inches / 792 ) * 792  );}
        	/* If there are > 1yards then count num of yards, add to string and reduce number of inches */
        	if(inches > 36){Output = Output + ", " +  (inches / 36 ); if ((inches / 36)>1){Output = Output + " Yards";}else{Output = Output + " Yard";}; inches = inches - ((inches / 36 ) * 36  );}
        	/* If there are > 1feet then count num of feet, add to string and reduce number of inches */
        	if(inches > 12){Output = Output + ", " +  (inches / 12 ); if((inches/12)>1){Output = Output + " Feet";}else{Output=Output+" Foot";}; inches = inches - ((inches / 12 ) * 12  );}
        	/* If there are > 1inches then count num of inches, add to string */
        	if(inches > 0){Output = Output + ", " +  inches; if(inches > 1){Output = Output + " Inches";}else{Output = Output + " Inch";};}
        	
        	/* neaten the output string */
        	Output = Output.replace(" , "," ");
        	
        	/* print output */
        	System.out.println(Output);
        }
        



        /* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         * Write your own code and comments within the area above
         */
    }
    
    
    }
