import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class lineTest{
    public static void main(String[] args){


        try{
            //instantiatle line object which will run through course of program
            Line line1 = new Line(); 
        }

        catch(InvalidInputException e){
            System.out.println(e.getMessage());
        }

        catch(InputMismatchException e){
            System.out.println("Please be sure to enter only intgers.");
        }
        
    }
}