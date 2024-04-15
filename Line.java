import java.util.Scanner; 
import java.util.ArrayList;

public class Line {

    //represents total number of people standing in a line (N)
    int numberOfPeople; 

    //represents the number of people skipped before elimination (K) (starting from the beginning of the line)
    int elimnationInterval;

    //Represents the starting index (where the elimination begins) in the line (S)
    int startingIndex; 

    //This will store the indexOfElimnation 
    int indexOfElimination;

    //This will store the index of the last person 
    int indexOfLastPerson; 

    //scanner object for user input 
    Scanner input = new Scanner(System.in);

    //this array will keep track of the original index of the last person remaining 
    String[] people;
    
    //this array will handle the eliminations  
    ArrayList <Integer> people2 = new ArrayList <>();

    //this is a default constructor of this class, once Line object is instantiated it will run through the programming
    public Line() throws InvalidInputException{
        
        //this method prompt and receives the N, K, and S values from the user
        userInput();

        //this method will store the people and their indexes to their respective arrays
        storePeople();

        //this method will run through the elimination process
        eliminatePeople();

        //this method will return the IndexOfTheLastPerson 
        indexOfLastPerson = returnIndexOfLastPerson();

        //display index of last person 
        System.out.println("The last person remaining is at index " + indexOfLastPerson);

    }

    //this method prompts and receives the N. K, and S values from the user
    public void userInput() throws InvalidInputException{
        System.out.print("Enter the number of people [ex. 10] (N): ");
        numberOfPeople = input.nextInt();

        System.out.print("Enter the Elimination Interval [ex. 2] (K): ");
        elimnationInterval= input.nextInt();

        System.out.print("Enter the starting Index [ex. 1] (S): ");
        startingIndex = input.nextInt();
 
        //makes sure all inputs are greater than 1 
        if(numberOfPeople < 1 || elimnationInterval < 1 || startingIndex < 1){
            throw new InvalidInputException("Please make sure your inputs are greater than or equal to 1");
        }

        //makes sure that eliminationInterval is greater than the number of people
        if(elimnationInterval > numberOfPeople){
            throw new InvalidInputException("Elimination interval cannot be greater than the number of people");
        }

        //makes sure that the starting index is not greater than the number of people 
        if (startingIndex > numberOfPeople){
            throw new InvalidInputException("Starting index cannot be greater than the number of people");
        }

    }

    //this method will store the people to each of the arrays
    public void storePeople(){

        //instantiate array with the numberOfPeople
        people = new String[numberOfPeople];

        for (int i = 0; i < numberOfPeople; i++){
            //the people array will keep track of the index of the last person
            //as each person get elimnated "person" will be changed to "eliminated"
            people[i] = "person"; 
           
           /*the people2 array will have the index of each person, as each person gets eliminated
           the index will be removed from the people 2 array */
            people2.add(i);
        }

    }

    //this method will process the elimiation based on userInput
    public void eliminatePeople(){

        //while there is more than 1 person left keep eliminating people
        while(people2.size() > 1){
            /*the index of elimination will be the starting index - 1 (because of the off by 1 rule) + the 
              elimination interval. The modulus (%) people2.size() will allow it to loop around if the number is 
              bigger than the amount of people that are left
             */
            indexOfElimination = ((startingIndex - 1) + elimnationInterval) % people2.size();

            //this will change the value in the people array from person to eliminated, based of the index that is eliminated
            people[people2.get(indexOfElimination)] = "eliminated";

            //this will remove the index of the eliminated person from the array 
            people2.remove(indexOfElimination);

            //starting index is now equal to the index of the eliminated person among the people who are still in
            startingIndex = indexOfElimination;
        }
    }

    //this method will return the indexOfLastPerson
    public int returnIndexOfLastPerson(){

        /*All of the other people that were once in the array now have the value eliminated 
          this for loop will find the one index where lies the only person that did not get eliminated
          by checking to see if it contains a value of person at each index
        */
        for(int i = 0; i < numberOfPeople; i++){
            if (people[i].equals("person")){
                
                //once the index of the person is found add 1 due to the off by 0 rule to find the index of the last person
                indexOfLastPerson = i + 1;

            }
        }

        return indexOfLastPerson;
    }


    
}
