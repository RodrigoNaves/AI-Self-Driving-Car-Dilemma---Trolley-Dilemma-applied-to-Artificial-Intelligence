 /** Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Flags 
{
    boolean fileFound;
    ArrayList <ethicalengine.Scenario> scenariosCsv = new ArrayList <ethicalengine.Scenario> ();

    public Flags()
    {
        
    }
    
    public void helpFlag()
    {
        System.out.println ("EthicalEngine - COMP90041 - Final Project");
        System.out.println();
        System.out.println("Usage: java EthicalEngine [arguments]");
        System.out.println();
        System.out.println("Arguments:");
        System.out.printf("%1s%-8s%n","   -c or --config","Optional: path to config file");
        System.out.printf("%1s%-8s%n","   -h or --help","Print Help (this message) and exit");
        System.out.printf("%1s%-8s%n","   -r or --results","Optional: path to results log file");
        System.out.printf("%1s%-8s%n","   -i or --interactive","Optional: launches interactive mode");

    }
    /**
     * configFlag handles most of the operations related to user input. It handles the reading from a excel file and process text into Character objects and adds them to a scenario
     * @param InputText is user input text depending on the arguments supplied to the program
     */
    public void configFlag(String InputText)
    {

        //Character tempPerson;
        int maxPas;
        int maxPed;
        int countPassengers = 0;
        int countPedestrians = 0;
        ethicalengine.Person.Gender gender;
        ethicalengine.Person.BodyType bodytype;
        ethicalengine.Person.Profession occupation;
        Boolean isYous = false;
        String species;
        Boolean pregnant;
        Boolean isPet;
        Boolean nextLine = true;
        Boolean legalCrossing;
        Boolean person;
        Boolean unfinished = true;
        String columnValues[] = new String [10] ;
        boolean personyes;
        ArrayList <String> csvContents =  new ArrayList <String> ();
        ArrayList <ethicalengine.Character> passengers = new ArrayList <ethicalengine.Character> ();
        ArrayList <ethicalengine.Character> pedestrians = new ArrayList <ethicalengine.Character> ();
        //should I create the file path here?
        fileFound = false;
        int removed = 0;

        try
        {
            BufferedReader csvReader = new BufferedReader(new FileReader(InputText));
            String line;
            nextLine = false;
            while((line = csvReader.readLine()) != null)
            {
                csvContents.add(line);
            }
            csvReader.close();
            fileFound = true;
        }
        catch (Exception FileNotFound)
        {
            
            System.err.println("File Not Found");
            System.exit(0);
        }
        
        try
        {

            for(int i =0; i < csvContents.size(); i ++)
            {
                columnValues = csvContents.get(i).split(",");
                if (columnValues.length > 10)
                {  
                   removed ++;
                   csvContents.remove(i);
                   throw new InvalidDataFormatException(i+removed);
                }

                if(i == csvContents.size() - 1)
                {
                    unfinished = false;
                }
            }
        }
        catch (InvalidDataFormatException e)
        {

        }
        
        try
        {
            for(int i =0; i < csvContents.size(); i ++)
            {
               columnValues = csvContents.get(i).split(",");
               if(columnValues[0].contentEquals("scenario:green") || columnValues[0].contentEquals("scenario:red"))
               {

               }
               else
               {
                    if(columnValues.length < 10)
                    {
                        removed++;
                        csvContents.remove(i);
                        throw new InvalidDataFormatException(i+removed);
                    }
                }
            }
   
        }    
        
    
                
        catch (InvalidDataFormatException e)
        {

        }

        
       

        columnValues = csvContents.get(1).split(","); 
        if (columnValues[0].equalsIgnoreCase("scenario:green"))
            {
                ethicalengine.Scenario newScenario = new ethicalengine.Scenario();
                scenariosCsv.add(newScenario);
                legalCrossing = true;
                scenariosCsv.get(0).setLegalCrossing(legalCrossing);

            }
        else if(columnValues[0].equalsIgnoreCase("scenario:red"))
            {
                ethicalengine.Scenario newScenario = new ethicalengine.Scenario();
                scenariosCsv.add(newScenario);
                legalCrossing = false;
                scenariosCsv.get(0).setLegalCrossing(legalCrossing);
            }

        for(int c =2; c < csvContents.size(); c ++)
        {   
            columnValues =csvContents.get(c).split(","); 

            if(columnValues[0].equalsIgnoreCase("person"))
            {
                String gen = columnValues[1];
                if(gen.contentEquals("female"))
                {
                    gender = ethicalengine.Character.Gender.FEMALE;
                }
                else if(gen.contentEquals("male"))
                {
                    gender = ethicalengine.Character.Gender.MALE;
                }
                else
                {
                    gender = ethicalengine.Character.Gender.UNKNOWN;
                }

                String personAge= columnValues[2];
                int age;
                try
                {
                    age = Integer.parseInt(personAge);
                }
                catch (Exception NumberFormatException)
                {
                    age = 0;
                    System.out.println("WARNING: invalid number format in line" + c);
                }
                String body = columnValues[3];
                bodytype = setBodyType(body,c);
                String personProfession = columnValues[4];
                occupation = setProfession(personProfession, c);
                String preg = columnValues[5];
                pregnant = setPregnant(preg,c);
                String isYou = columnValues[6];
                if(isYou.contentEquals("TRUE"))
                {
                    isYous = true;
                }
                String scenarioPos = columnValues[9];
                if(scenarioPos.contentEquals("passenger"))
                {
                    countPassengers ++;
                    ethicalengine.Person newPerson = new ethicalengine.Person(age,occupation,gender,bodytype,pregnant,isYous);
                    //System.out.println(newPerson.toString());
                    passengers.add(newPerson);
                }
                else if (scenarioPos.contentEquals("pedestrian"))
                {
                    countPedestrians ++;
                    ethicalengine.Person newPerson = new ethicalengine.Person(age,occupation,gender,bodytype,pregnant,isYous);
                    //System.out.println(newPerson.toString());
                    pedestrians.add(newPerson);    
                }

            }
            if(columnValues[0].equalsIgnoreCase("animal"))
            {
                String gen = columnValues[1];
                if(gen.contentEquals("female"))
                {
                    gender = ethicalengine.Character.Gender.FEMALE;
                }
                else if(gen.contentEquals("male"))
                {
                    gender = ethicalengine.Character.Gender.MALE;
                }
                else
                {
                    gender = ethicalengine.Character.Gender.UNKNOWN;
                }

                String personAge= columnValues[2];
                int age = Integer.parseInt(personAge);
                String body = columnValues[3];
                bodytype = setBodyType(body,c);
                String preg = columnValues[5];
                pregnant = setPregnant(preg,c);
                String animalType = columnValues[7];
                species = setSpecies(animalType,c);
                String scenarioPos = columnValues[9];
                String Pet = columnValues[8];
                if(Pet.equalsIgnoreCase("TRUE"))
                {
                    isPet = true;
                }
                else
                {
                    isPet = false;
                }
                if(scenarioPos.contentEquals("passenger"))
                {
                    countPassengers ++;
                    ethicalengine.Animal newAnimal = new ethicalengine.Animal(age,gender,bodytype,species,isPet);
                    //System.out.println(newAnimal.toString());
                    passengers.add(newAnimal);
                }
                else if (scenarioPos.contentEquals("pedestrian"))
                {
                    countPedestrians ++;
                    ethicalengine.Animal newAnimal = new ethicalengine.Animal(age,gender,bodytype,species,isPet);
                    //System.out.println(newAnimal.toString());
                    pedestrians.add(newAnimal);    
                }

            }
            if(columnValues[0].equalsIgnoreCase("scenario:green"))
            {
                maxPed =countPedestrians;
                maxPas = countPassengers;
                countPedestrians = 0;
                countPassengers = 0;
                ethicalengine.Character[] passengersArr = new ethicalengine.Character[passengers.size()] ;
                passengers.toArray(passengersArr);
                ethicalengine.Character[] pedestriansArr = new ethicalengine.Character[pedestrians.size()] ;
                pedestrians.toArray(pedestriansArr);
                //clear off the temporary arrayList for characters, so it can start fresh again.
                passengers.clear();
                pedestrians.clear();

                scenariosCsv.get(scenariosCsv.size()-1).setMaxPassengers(maxPas);
                scenariosCsv.get(scenariosCsv.size()-1).setMaxPedestrians(maxPed);
                scenariosCsv.get(scenariosCsv.size()-1).setPassengers(passengersArr);
                scenariosCsv.get(scenariosCsv.size()-1).setPedestrians(pedestriansArr);

                legalCrossing = true;
                ethicalengine.Scenario newScenario = new ethicalengine.Scenario();
                newScenario.setLegalCrossing(legalCrossing);
                scenariosCsv.add(newScenario);

            }
            if(columnValues[0].equalsIgnoreCase("scenario:red"))
            {
                maxPed =countPedestrians;
                maxPas = countPassengers;
                countPedestrians = 0;
                countPassengers = 0;
                ethicalengine.Character[] passengersArr = new ethicalengine.Character[passengers.size()] ;
                passengers.toArray(passengersArr);
                ethicalengine.Character[] pedestriansArr = new ethicalengine.Character[pedestrians.size()] ;
                pedestrians.toArray(pedestriansArr);
                //clear off the temporary arrayList for characters, so it can start fresh again.
                
                passengers.clear();
                pedestrians.clear();
                // fill out the missing information in last scenario generated.
                scenariosCsv.get(scenariosCsv.size()-1).setMaxPassengers(maxPas);// very important MaxPassengers is set before setPassengers
                scenariosCsv.get(scenariosCsv.size()-1).setMaxPedestrians(maxPed);// very important MaxPedestrians is set before setPedestrians
                scenariosCsv.get(scenariosCsv.size()-1).setPassengers(passengersArr);
                scenariosCsv.get(scenariosCsv.size()-1).setPedestrians(pedestriansArr);

                legalCrossing = false;
                ethicalengine.Scenario newScenario = new ethicalengine.Scenario();
                newScenario.setLegalCrossing(legalCrossing);
                scenariosCsv.add(newScenario);
            }
        }
        maxPed =countPedestrians;
        maxPas = countPassengers;
        countPedestrians = 0;
        countPassengers = 0;
        ethicalengine.Character[] passengersArr = new ethicalengine.Character[passengers.size()] ;
        passengers.toArray(passengersArr);
        ethicalengine.Character[] pedestriansArr = new ethicalengine.Character[pedestrians.size()] ;
        pedestrians.toArray(pedestriansArr);
        //clear off the temporary arrayList for characters, so it can start fresh again.
        passengers.clear();
        pedestrians.clear();

        // fill out the missing information in last scenario generated.
        scenariosCsv.get(scenariosCsv.size()-1).setMaxPassengers(maxPas);// very important MaxPassengers is set before setPassengers
        scenariosCsv.get(scenariosCsv.size()-1).setMaxPedestrians(maxPed);// very important MaxPedestrians is set before setPedestrians
        scenariosCsv.get(scenariosCsv.size()-1).setPassengers(passengersArr);
        scenariosCsv.get(scenariosCsv.size()-1).setPedestrians(pedestriansArr);
        
        
        

                    
            
    }



    public ethicalengine.Person.Profession setProfession(String profession , int c)
    {
        try
        {
            switch(profession)
            {
                case "ceo":
                    return ethicalengine.Person.Profession.CEO;


                case "doctor":
                    return ethicalengine.Person.Profession.DOCTOR;

                case "homeless":
                    return ethicalengine.Person.Profession.HOMELESS;

                case "criminal":
                    return ethicalengine.Person.Profession.CRIMINAL;


                case "murderer":
                    return ethicalengine.Person.Profession.MURDERER;


                case "sexoffender":
                    return ethicalengine.Person.Profession.SEXOFFENDER;


                case "student": 
                    return ethicalengine.Person.Profession.STUDENT;


                case "unemployed":
                    return ethicalengine.Person.Profession.UNEMPLOYED;


                case "employed":
                    return ethicalengine.Person.Profession.EMPLOYED;
                
                case "unknown":
                    return ethicalengine.Person.Profession.UNKNOWN;
                default:
                    return ethicalengine.Person.Profession.UNKNOWN;
            }
        }
        catch (Exception InvalidDataFormatException)
        {
            System.out.println("WARNING: invalid characteristic in config file in line "+c);
            return ethicalengine.Person.Profession.UNKNOWN;
            
        }
    }
    
    public ethicalengine.Person.BodyType setBodyType(String body, int c)
    {
        try
        {
            switch (body)
                {
                    case "athletic":
                        return ethicalengine.Person.BodyType.ATHLETIC;

                    case "average":
                        return ethicalengine.Person.BodyType.AVERAGE;
                    case "overweight":
                        return ethicalengine.Person.BodyType.OVERWEIGHT;
                    case "unspecified":
                        return ethicalengine.Person.BodyType.UNSPECIFIED;
                    default:
                        return ethicalengine.Person.BodyType.UNSPECIFIED;  
                        

                }
        }
        catch (Exception InvalidDataFormatException)
        {
            System.out.println("WARNING: invalid characteristic in config file in line "+c);
            return ethicalengine.Person.BodyType.UNSPECIFIED;  
        }
        
    }
    public boolean setPregnant(String preg, int c)
    {
        try
        {
            if(preg.contentEquals("TRUE"))
            {
                return true;
            }
            else
            {
                return false;
            }
            
        }
        catch (Exception InvalidDataFormatException)
        {
            System.out.println("WARNING: invalid characteristic in config file in line "+c);
            return false;  
        }
        
            

    }
    
    public String setSpecies(String animaltype, int c)
    {
        try
        {
            switch(animaltype)
                   {
                       case "dog":
                            return "dog";
                       case "llama":
                            return "llama";
                       case "cat":
                            return "cat";
                       case "bird":
                            return "bird";
                        default:
                            return "dog";
                   }
        }
        catch (Exception InvalidDataFormatException)
        {
            System.out.println("WARNING: invalid characteristic in config file in line "+c);
            return "dog";  
        }
        
        
    }
    
    public boolean fileFound()
    {
        return fileFound;
    }
    public ArrayList getScenarioArray()
    {
        return scenariosCsv;
    }
    private static class InvalidDataFormatException extends Exception {

        public InvalidDataFormatException(int i) 
        {
            System.out.println("WARNING: invalid data format in config file in line " + i);
        }   
    }

    private static class NumberFormatException extends Exception {

        public NumberFormatException(String string) 
        {
            super(string);
        }   
    }
    
    private static class InvalidCharacteristicException extends Exception {

        public InvalidCharacteristicException(String string) 
        {
            super(string);
        }   
    }
    
    
    
}


