 /* Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */
package ethicalengine;
import java.util.Random;

public class ScenarioGenerator
{
    Random randomNumber = new Random();
    private long seed;
    private int passengerCountMinimum = 1;
    private int passengerCountMaximum = 7;
    private int pedestrianCountMinimum = 1;
    private int pedestrianCountMaximum = 7;
    private int numofScenarios = 0;
    private int decisionResult;
    private Scenario scenario;
    private String animalOption;
    
    public ScenarioGenerator()
    {
        numofScenarios = randomNumber.nextInt();

    }
    
    
    public ScenarioGenerator(long seed)
    {
        randomNumber.setSeed(seed);
        numofScenarios = randomNumber.nextInt();
    }
    /**
     * 
     * @param seed is an integer that will enable the randomized scenario generation to be audited and repeated with same results
     * @param passengerCountMinimum lower limit on the number of passengers that can be set in the system
     * @param passengerCountMaximum max limit on the number of passengers that can be set in the system
     * @param pedestrianCountMinimum lower limit on the number of pedestrians that can be set in the system
     * @param pedestrianCountMaximum max limit on the number of pedestrians that can be set in the system
     */
    public ScenarioGenerator(long seed, int passengerCountMinimum, int passengerCountMaximum, int pedestrianCountMinimum, int pedestrianCountMaximum)
    {
        randomNumber.setSeed(seed);
        this.passengerCountMinimum = passengerCountMinimum;
        this.passengerCountMaximum = passengerCountMaximum;
        this.pedestrianCountMinimum = pedestrianCountMinimum;
        this.pedestrianCountMaximum = pedestrianCountMaximum; 
    }
    public void setPassengerCountMin(int min)
    {
        passengerCountMinimum = min;
    }
    
   public void setPassengerCountMax(int max)
   {
       passengerCountMaximum = max;
   }
   
   public void setPedestrianCountMin(int min)
   {
       pedestrianCountMinimum = min;
   }
   
   public void setPedestrianCountMax(int max)
   {
       pedestrianCountMaximum = max;
   }

   public Person getRandomPerson()
   {
       int age = randomNumber.nextInt(120);
       Person.Profession occupation = Person.Profession.values()[randomNumber.nextInt(11)]; 
       Person.Gender gender = Person.Gender.values()[randomNumber.nextInt(3)];
       Person.BodyType bodytype = Person.BodyType.values()[randomNumber.nextInt(4)];
       boolean pregnancy = randomNumber.nextBoolean();
       Person newPerson = new Person(age,occupation,gender,bodytype,pregnancy);
       return newPerson;
   }
   
   public Animal getRandomAnimal()
   {
       int age = randomNumber.nextInt(25);
       Animal.Gender gender = Animal.Gender.values()[randomNumber.nextInt(3)];
       Animal.BodyType bodytype = Animal.BodyType.values()[randomNumber.nextInt(4)];
       Animal.AnimalType species = Animal.AnimalType.values()[randomNumber.nextInt(3)];
       animalOption = species.toString();
       boolean isPet = randomNumber.nextBoolean();
       Animal newAnimal = new Animal(age,gender,bodytype,animalOption,isPet);
       return newAnimal;
   }
   public Character AnimalorPerson()
   {
       int random = randomNumber.nextInt(2);
       if(random == 0)
       {
           return getRandomPerson();
       }
       else
       {
           return getRandomAnimal();
       }
       
   }
   
   /**
    * 
    * @return scenario which is a randomized scenario with completely random characters
    */
   public Scenario generate()
   {
       int passengers = randomNumber.nextInt((passengerCountMaximum-1) - passengerCountMinimum)+passengerCountMinimum;
       int pedestrians = randomNumber.nextInt((pedestrianCountMaximum-1)- pedestrianCountMinimum )+pedestrianCountMinimum;
       boolean legalCrossing = randomNumber.nextBoolean();
       Character passengersArray [] = new Character [passengers];
       Character pedestriansArray [] = new Character [pedestrians];
       for (int i=0; i < passengers; i++)
       {
          Character temp = AnimalorPerson();
          passengersArray[i] = temp;
       }
        for (int i=0; i < pedestrians; i++)
       {
           Character temp = AnimalorPerson();
           pedestriansArray[i] = temp;
       }
       
       Scenario newScenario = new Scenario(passengers,pedestrians,passengersArray,pedestriansArray,legalCrossing);
       
       scenario = newScenario;
       //newScenario.toStrings();
       return newScenario;
   }
   
   public void setDecisionResult(int decisionResult)
   {
       this.decisionResult = decisionResult;
       
   }
   
   public int getDecision()
   {
       return decisionResult;
   }
   
   public Scenario getScenario()
   {
       return scenario;
   }
   
   
}

