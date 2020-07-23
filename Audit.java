 /** Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */

import ethicalengine.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Audit 
{
    String name = "Unspecified Audit";
    int runs;
    Boolean consent = false;
    private ArrayList<ethicalengine.Scenario> ScenarioArray = new ArrayList <ethicalengine.Scenario>();
    private ArrayList<ethicalengine.ScenarioGenerator> ScenarioGeneratorArray = new ArrayList <ethicalengine.ScenarioGenerator>();
    StringBuilder listBuilder = new StringBuilder();
    private static DecimalFormat roundUp = new DecimalFormat("0.0");
   

  
     //characteristics
    private int female = 0, male = 0, unknown =0;
    private int average = 0, athletic = 0,overweight = 0,unspecified = 0;
    private int baby = 0, child = 0, adult =0, senior = 0;
    private int ceo =0, doctor =0, homeless = 0, criminal =0, murderer = 0, sexoffender = 0, student = 0, unemployed = 0, employed = 0, you = 0, none =0, unknownO= 0;
    private int pregnant = 0;
    private int person = 0, animal = 0;
    private int cat =0, dog = 0, llama =0, bird =0;
    private int red =0, green =0;
    
    // total characteristics
    private int tfemale = 0, tmale = 0, tunknown =0;
    private int taverage = 0, tathletic = 0,toverweight = 0,tunspecified = 0;
    private int tbaby = 0, tchild = 0, tadult =0, tsenior = 0;
    private int tceo =0, tdoctor =0, thomeless = 0, tcriminal =0, tmurderer = 0, tsexoffender = 0, tstudent = 0, tunemployed = 0, temployed = 0, tyou = 0, tnone =0, tunknownO = 0;
    private int tpregnant = 0;
    private int tperson = 0, tanimal = 0;
    private int tcat =0, tdog = 0, tllama =0, tbird = 0;
    private int tred =0, tgreen =0;
    private int tage = 0; 
    private int tcharactersCount = 0;
    double ageAverage = 0;
    // arrays used for output of statistics to system and files.
    private int characteristics [] = new int[32];
    private int totalCharacteristics [] = new int [32];
    private double outputTotalChar [] = new double [totalCharacteristics.length];
    private String Stringcharacteristics [] ={"female", "male", "unknown", "average" , "athletic" ,"overweight","unspecified", "baby", "child", "adult", "senior",
                                "ceo" , "doctor" , "homeless" , "criminal", "murderer", "sexoffender" , "student" , "unemployed" , "employed" ,"you" ,
                                "none","unknown p","pregnant", "person", "animal", "cat" , "dog" , "llama","bird", "red" , "green"};
    private String outputSchar [] = new String [Stringcharacteristics.length];
    private static String filePathName = "logs";
    private static ArrayList <String> filePathNameArray  = new ArrayList <String>();
    private static String fileName = "logs\\results.log";
    
    //constructors
    Audit()
    {
        
    }
    
    /**
     * 
     * @param scenarios constructs based on scenarios generated manually 
     * or by a excel file
     */
    Audit(ArrayList <ethicalengine.Scenario> scenarios )
    {
        for (int i =0; i < scenarios.size(); i++)
        {
            ScenarioArray.add( scenarios.get(i));  
        }
    }
    //accesor methods
    
    /**
     * 
     * @return name of the audit
     */
    public String getAuditType()
    {
        return name;
    }
    
    /**
     * 
     * @return the array containing all the Scenario Generators in case random scenarios are generated
     */
    public ArrayList getScenarioGeneratorList()
    {
        return ScenarioGeneratorArray;
    }
    
    /**
     * 
     * @return the array of scenarios in case predefined scenarios from an external file are used
     */
    public ArrayList getScenarioArray()
    {
        return ScenarioArray;
    }
    
    /**
     * this function will check if consent has been granted to save decisions into a file
     * @param consent it receives the answer from the user (y/n) as a string
     */
    public void getConsent(String consent)
    {
        if(consent.contentEquals("yes"))
        {
            this.consent = true;
        }
        else if(consent.contentEquals("Yes"))
        {
            this.consent = true;
        }
        else if(consent.contentEquals("y"))
        {
            this.consent = true;
        }
        else if(consent.contentEquals("Y"))
        {
            this.consent = true;
        }
        else
        {
            this.consent = false;
        }
    }
    
    
    //mutator methods
    
    
public void run(int runs)
{
   for (int i =0; i < runs ; i++)
        {
            ethicalengine.ScenarioGenerator newRun = new ScenarioGenerator();
            newRun.generate();
            ScenarioGeneratorArray.add(newRun);
            runStats();
            sort();
            setRuns(runs);
        }
        this.runs = this.runs +runs; 
}
/**
 * run method will create new scenario generators 
 * @param runs is an int that can be input or default to three.
 */
    public void runInter(int runs)
    {
        
        for (int i =0; i < runs ; i++)
        {
            ethicalengine.ScenarioGenerator newRun = new ScenarioGenerator();
            newRun.generate();
            ScenarioGeneratorArray.add(newRun);
            //ScenarioGeneratorArray.get(i).getScenario().toStrings();
            //System.out.println(ScenarioGeneratorArray.get(i).getDecision());
        }
        this.runs = this.runs +runs;
    }
    
    public void setRuns(int runs)
    {
        this.runs = runs;
    }
    /**
     * runConfig will be called when we use the config mode, reading scenarios from an external file
     *
     * @param scenarios is an ArrayList of scenarios that will be executed.
     */
    public void runConfig(ArrayList<ethicalengine.Scenario> scenarios)
    {
        for (int i =0; i < scenarios.size() ; i++)
        {
            ScenarioArray.add(scenarios.get(i));
        }
    }
    /**
     * setAuditType will label the audit with a name 
     * @param name is a String that can be input from the user
     */
    public void setAuditType(String name)
    {
        this.name = name;
    }

    
/**
 * runStats makes a call for statistic Analysis method
 */
    public void runStats()
    {
        statisticAnalysis(ScenarioGeneratorArray);
    }
    public void runStatsConfig(int countRuns)
    {
        statisticAnalysisConfig(countRuns,ScenarioArray);
    }
    /**
     * statisticAnalysis method adds all the attributes found in characters of each scenario in the scenario ArrayList
     * this is because each scenario needs to be randomly generated
     * @param scenarios this is an ArrayList of ScenarioGenerators
     */
    public void statisticAnalysis(ArrayList<ethicalengine.ScenarioGenerator> scenarios)
    {
        for(int i = 0; i < scenarios.size(); i++)
        {
            int countPas = scenarios.get(i).getScenario().getPassengerCount();
            int countPed = scenarios.get(i).getScenario().getPedestrianCount();
            ethicalengine.Character  [] tempPassengers = new ethicalengine.Character [countPas];
            ethicalengine.Character  [] tempPedestrians = new ethicalengine.Character [countPed];

            for (int a = 0; a < countPas ; a++)
            {
                tempPassengers[a] = scenarios.get(i).getScenario().getPassengers(a);
            }
            for(int a = 0; a < countPed; a++)
            {
                tempPedestrians[a] = scenarios.get(i).getScenario().getPedestrians(a);
            }
            if(scenarios.get(i).getDecision() == 1)
            {
                bodytypeEvaluation(tempPassengers);
                genderEvaluation(tempPassengers);
                ageEvaluation(tempPassengers);
                pregnancyEvaluation(tempPassengers);
                professionEvaluation(tempPassengers);
                charactersCount(tempPassengers);
                ageCount(tempPassengers);
            }
            else if(scenarios.get(i).getDecision() == 2)
            {  
                bodytypeEvaluation(tempPedestrians);
                genderEvaluation(tempPedestrians);
                ageEvaluation(tempPedestrians);
                pregnancyEvaluation(tempPedestrians);
                professionEvaluation(tempPedestrians);
                charactersCount(tempPedestrians);
                ageCount(tempPedestrians);
            }
            
            tstatisticsAnalysis(tempPassengers,tempPedestrians);
            
            if(scenarios.get(i).getScenario().isLegalCrossing())
            {
                green +=1;
            }
            else
            {
                red +=1;
            }
            tred = green + red;
            tgreen = green + red;
        }  
        int characteristics [] = {  female, male, unknown, average , athletic ,overweight,unspecified, baby, child, adult, senior,
                                ceo , doctor , homeless , criminal, murderer, sexoffender , student , unemployed , employed , you ,
                                none,unknownO,pregnant, person, animal, cat , dog , llama,bird, red , green };
        int totalcharacteristics [] = {  tfemale, tmale, tunknown, taverage , tathletic ,toverweight,tunspecified, tbaby, tchild, tadult, tsenior,
                                tceo , tdoctor , thomeless , tcriminal, tmurderer, tsexoffender , tstudent , tunemployed , temployed , tyou ,
                                tnone,tunknownO,tpregnant, tperson, tanimal, tcat , tdog , tllama, tbird, tred , tgreen };
        this.characteristics = characteristics;
        this.totalCharacteristics = totalcharacteristics;
    }
    /**
     * statisticAnalysisConfig will run the same analysis as statisticAnalysis but it will receive a Scenario instead of a ScenarioGenerator which 
     * is caused by reading the scenarios from an external file
     * @param scenarios is an array list of scenarios that have been imported from a file
     */
    public void statisticAnalysisConfig(int countRuns,ArrayList <ethicalengine.Scenario> scenarios)
    {
        for(int i = countRuns; i < (countRuns +3) && i < scenarios.size(); i++)
        {
            int countPas = scenarios.get(i).getPassengerCount();
            int countPed = scenarios.get(i).getPedestrianCount();
            ethicalengine.Character  [] tempPassengers = new ethicalengine.Character [countPas];
            ethicalengine.Character  [] tempPedestrians = new ethicalengine.Character [countPed];
            for (int a = 0; a < countPas ; a++)
            {
                tempPassengers[a] = scenarios.get(i).getPassengers(a);
            }
            for(int a = 0; a < countPed; a++)
            {
                tempPedestrians[a] = scenarios.get(i).getPedestrians(a);
            }
            if(scenarios.get(i).getDecision() == 1)
            {
                bodytypeEvaluation(tempPassengers);
                genderEvaluation(tempPassengers);
                ageEvaluation(tempPassengers);
                pregnancyEvaluation(tempPassengers);
                professionEvaluation(tempPassengers);
                charactersCount(tempPassengers);
                ageCount(tempPassengers);
            }
            else if(scenarios.get(i).getDecision() == 2)
            {  
                bodytypeEvaluation(tempPedestrians);
                genderEvaluation(tempPedestrians);
                ageEvaluation(tempPedestrians);
                pregnancyEvaluation(tempPedestrians);
                professionEvaluation(tempPedestrians);
                charactersCount(tempPedestrians);
                ageCount(tempPedestrians);
            }
            
            tstatisticsAnalysis(tempPassengers,tempPedestrians);
            
            if(scenarios.get(i).isLegalCrossing())
            {
                green +=1;
            }
            else
            {
                red +=1;
            }
            tred = green + red;
            tgreen = green + red;
        }  
        int characteristics [] = {  female, male, unknown, average , athletic ,overweight,unspecified, baby, child, adult, senior,
                                ceo , doctor , homeless , criminal, murderer, sexoffender , student , unemployed , employed , you ,
                                none,unknownO,pregnant, person, animal, cat , dog , llama, bird, red , green };
        int totalcharacteristics [] = {  tfemale, tmale, tunknown, taverage , tathletic ,toverweight,tunspecified, tbaby, tchild, tadult, tsenior,
                                tceo , tdoctor , thomeless , tcriminal, tmurderer, tsexoffender , tstudent , tunemployed , temployed , tyou ,
                                tnone,tunknownO,tpregnant, tperson, tanimal, tcat , tdog , tllama, tbird, tred , tgreen };
        this.characteristics = characteristics;
        this.totalCharacteristics = totalcharacteristics;

    }
    
    /**
     * tstatisticsAnalysis will add up the total instances of each one of the characteristics found in every character in every scenario
     * @param tempPassengers is a Character array full of all the passengers in a vehicle
     * @param tempPedestrians is a Character array full of pedestrians in the street
     */
    public void tstatisticsAnalysis(ethicalengine.Character [] tempPassengers,ethicalengine.Character [] tempPedestrians)
    {
        ethicalengine.Character  [] totalCharacters;
        int fal = tempPassengers.length;
        int sal = tempPedestrians.length;
        totalCharacters = new ethicalengine.Character [fal + sal];
        System.arraycopy(tempPassengers, 0, totalCharacters, 0, fal);
        System.arraycopy(tempPedestrians, 0, totalCharacters, fal, sal);
        //generate the total number of attributes in scenarios generated.
        bodytypeEvaluationt(totalCharacters);
        genderEvaluationt(totalCharacters);
        ageEvaluationt(totalCharacters);
        pregnancyEvaluationt(totalCharacters);
        professionEvaluationt(totalCharacters);
        //ageCount(totalCharacters);
        //charactersCount(totalCharacters);

        
    }
    
    public void sort()
    {
        sortOutput(characteristics,totalCharacteristics,Stringcharacteristics, outputSchar, outputTotalChar);
        ageAverage();
    }
    
    public void clearList()
    {
        listBuilder.setLength(0);
    }
    
    public String toString()
    {
        listBuilder.append("======================================\n");
        listBuilder.append("# " + getAuditType().toString() + "\n"  );
        listBuilder.append("======================================\n");
        listBuilder.append("- % SAVED AFTER " + runs +" RUNS\n");
        for( int i = outputSchar.length-1; i >= 0; i--)
        {
            if(outputTotalChar[i] >= 0)
            {
                listBuilder.append( outputSchar[i] + ": " + roundUp.format(outputTotalChar [i])+"\n");   
            }
        }
        listBuilder.append("--\n");
        listBuilder.append("average age: "+  roundUp.format(ageAverage)+"\n");  
        

        return listBuilder.toString();
             
    }
    
    public void printStatistic()
    {
          System.out.println(this.toString());
          clearList();
    }
    /**
     * sortOutput will run a sorting algorithm based on insertion sort
     * It sorts three arrays of different types (double, int, String)
     * these will be used for the output of the statistics of every audit
     * @param characteristics this is the array containing the characteristics of surviving characters in all the scenarios in the audit
     * @param tcharacteristics this is the array containing the characteristics of all characters in all the scenarios in the audit
     * @param Scharacteristics this is the array that contains the Strings corresponding to the label of each characteristic
     * @param outputSchar this is the output array of String labels
     * @param outputTotalChar this is the output array from the percentile values of survival of each characteristic
     */
    public void sortOutput(int [] characteristics, int [] tcharacteristics, String [] Scharacteristics,String [] outputSchar, double [] outputTotalChar)
    {
       int positions[] = new int[characteristics.length];
       for (int i = 0; i < positions.length; i++)
       {
           positions [i] = i;
       }
       double tempDouble;
       for (int i = 0; i < characteristics.length; i++)
       {
           if(tcharacteristics[i] <= 0)
           {
               outputTotalChar[i] = -1; 
           }
           else
           {
               tempDouble = ((double)characteristics[i]/tcharacteristics[i]);
               outputTotalChar[i] = tempDouble;
           }  
       }
       
       for (int i = 0; i < outputTotalChar.length-1; i++)
       {
           int min = i;
           for(int o = i+1;  o < outputTotalChar.length; o++)
           {
               if(outputTotalChar[o] < outputTotalChar[min])
                {
                    min = o;
                }

            }
            double tempA;
            double tempB;
            int tempPosA;
            int tempPosB;
            tempA = outputTotalChar[i];
            tempB = outputTotalChar[min];
            tempPosA = positions[i];
            tempPosB = positions[min];
            outputTotalChar[min] = tempA;
            positions[min] = tempPosA;
            outputTotalChar[i] = tempB;
            positions[i] = tempPosB;
        
       }
       
       for (int i = 0; i < Scharacteristics.length; i++)
       {
           outputSchar[i] = Scharacteristics[positions[i]];
       }
       this.outputSchar = outputSchar;
       this.outputTotalChar = outputTotalChar;
    }
    
    /**
     * this method will print the results to a file in a specified locations, default logs
     * @param newPath is the new location to save file statistics based on decision in the ethical engine
     */
    public void setFilePathName(String newPath)
    {
        fileName = newPath;
        String str[]  = newPath.split("//");
        Collections.addAll(filePathNameArray, str);
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < filePathNameArray.size()-1; i++) 
        {
            sb.append(filePathNameArray.get(i));
            sb.append("//");
        }
        filePathName = sb.toString();
        
    }
    public void printToFile()
    {
        if (consent == true)
        {
            PrintWriter outputStreamName = null;
            try
            {
                File dir = new File(filePathName);
                dir.mkdirs();
            }
            catch (Exception b)
            {
                System.err.println("ERROR: Could not print results. Target directory does not exist.");
            }
            try
            {
                outputStreamName = new PrintWriter(new FileOutputStream(fileName, true)); //might need to change to/ if unix system
            }

            catch(Exception FileNotFound)
            {

            }

             try
            {
                outputStreamName.println("======================================"); 
                outputStreamName.println("# " + getAuditType());              
                outputStreamName.println("======================================");                  
                outputStreamName.println("- % SAVED AFTER " + runs +" RUNS");                 
               for(int i = outputSchar.length-1; i >= 0; i--)
               {
                   if(outputTotalChar[i] >= 0)
                   {
                       outputStreamName.printf("%12s%1s%1.1f%n",outputSchar[i] , ": ", outputTotalChar[i]);    
                   }
               }
               
               outputStreamName.println("--");
               outputStreamName.printf("%12s%1.1f%n","average age: ", ageAverage);

            }
            catch(Exception a)
            {
               System.out.println("Error in file \n");
            }
             outputStreamName.close();
        }

    }
    
    /**
     * The following methods add up the occurrences or characteristics
     * in the scenarios generated, and later on used to create statistical data
     * 
     * @param characters are the either pedestrians or passengers in every scenario
     */
    public void bodytypeEvaluation(ethicalengine.Character  [] characters)
    {
            for(int i = 0; i < characters.length; i++)
        {
            switch (characters[i].getBodyType())
            {
                case ATHLETIC:
                    athletic += 1;
                    break;
                    
                case AVERAGE:
                    average += 1;
                    break;
                case OVERWEIGHT:
                    overweight += 1;
                    break;
                case UNSPECIFIED:
                    unspecified += 1;
                    break;
                 
                default:
                    break;
            }
            
        }

    }
    
     public void genderEvaluation(ethicalengine.Character  [] characters )
    {
        for(int i = 0; i < characters.length; i++)
        {
            switch (characters[i].getGender())
            {
                case MALE:
                    male += 1;
                    break;
                    
                case FEMALE:
                    female += 1;
                    break;
                case UNKNOWN:
                    unknown += 1;
                 
                default:
                    break;
            
            }
            
        }
    }
      public void ageEvaluation(ethicalengine.Character  [] characters)
    {
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] instanceof Person)
                {
                    person += 1;
                    switch (characters[i].getAgeCategory())
                    {
                        case BABY:
                            baby += 1;
                            break;

                        case CHILD:
                            child += 1;
                            break;
                        case ADULT:
                            adult += 1;
                            break;
                        case SENIOR:
                            senior += 1;
                            break;

                        default:
                            break;

                     }
                }
            else if(characters[i] instanceof Animal)
            {
                animal += 1;
                switch (characters[i].getAgeCategory())
                {
                        case BABY:
                            baby += 1;
                            break;

                        case ADULT:
                            adult += 1;
                            break;
                        case SENIOR:
                            senior += 1;
                            break;

                        default:
                            break;

                }
                switch(characters[i].getspecies())
                {
                    case "llama":
                         llama += 1;
                    case "dog":
                         dog += 1;
                    case "cat":
                         cat += 1;
                    case "bird":
                        bird += 1;
                     default:
                         break;
                         
                }
                
            }
            
            
        }
    }
    
    public void professionEvaluation(ethicalengine.Character  [] characters)
    {
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] instanceof Person)
                {
                    switch (characters[i].getProfession())
                    {
                        case CEO:
                            ceo += 1;
                            break;

                        case DOCTOR:
                            doctor += 1;
                            break;
                        case HOMELESS:
                            homeless += 1;
                            break;
                        case CRIMINAL:
                            criminal += 1;
                            break;
                            
                        case MURDERER:
                            murderer += 1;
                            break;

                        case SEXOFFENDER:
                            sexoffender += 1;
                            break;
                            
                        case STUDENT: 
                            student += 1;
                            break;
                        
                        case UNEMPLOYED:
                            unemployed += 1;
                            break;
                        
                        case EMPLOYED:
                            employed += 1;
                            break;
  
                        case NONE:
                            none += 1;
                            break;
                        case UNKNOWN:
                            unknownO += 1;

                        default:
                            break;

                     }
                    if(characters[i].isYou())
                    {
                        you += 1;
                    }
                }         
        }
        
    }
    
    public void pregnancyEvaluation(ethicalengine.Character  [] characters)
    {
        for(int i = 0; i < characters.length; i++)
        {
            if (characters[i].isPregnant())
            {
                pregnant += 1;

            }
        } 
    }
    public void bodytypeEvaluationt(ethicalengine.Character  [] characters)
    {
            for(int i = 0; i < characters.length; i++)
        {
            switch (characters[i].getBodyType())
            {
                case ATHLETIC:
                    tathletic += 1;
                    break;
                    
                case AVERAGE:
                    taverage += 1;
                    break;
                case OVERWEIGHT:
                    toverweight += 1;
                    break;
                case UNSPECIFIED:
                    tunspecified += 1;
                    break;
                 
                default:
                    break;
            }
            
        }

    }
    
    public void ageCount (ethicalengine.Character [] characters)
    {
         for(int i = 0; i < characters.length; i++)
        {
            if (characters[i] instanceof Person)
            {
                tage += characters[i].getAge();
            }
            
        }
        
    }
    public void charactersCount (ethicalengine.Character [] characters)
    {
        int count = 0;
        for(int i = 0; i < characters.length; i++)
        {
            if (characters[i] instanceof Person)
            {           
                count ++;
            }
        }
        tcharactersCount += count;
    }
    public void ageAverage()
    {
        ageAverage = (double)tage/tcharactersCount;
    }
    
     public void genderEvaluationt(ethicalengine.Character  [] characters )
    {
        for(int i = 0; i < characters.length; i++)
        {
            switch (characters[i].getGender())
            {
                case MALE:
                    tmale += 1;
                    break;
                    
                case FEMALE:
                    tfemale += 1;
                    break;
                case UNKNOWN:
                    tunknown += 1;
                 
                default:
                    break;
            
            }
            
        }
    }
      public void ageEvaluationt(ethicalengine.Character  [] characters)
    {
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] instanceof Person)
                {
                    tperson += 1;
                    switch (characters[i].getAgeCategory())
                    {
                        case BABY:
                            tbaby += 1;
                            break;

                        case CHILD:
                            tchild += 1;
                            break;
                        case ADULT:
                            tadult += 1;
                            break;
                        case SENIOR:
                            tsenior += 1;
                            break;

                        default:
                            break;

                     }
                }
            else if(characters[i] instanceof Animal)
            {
                tanimal += 1;
                switch (characters[i].getAgeCategory())
                {
                        case BABY:
                            tbaby += 1;
                            break;

                        case ADULT:
                            tadult += 1;
                            break;
                        case SENIOR:
                            tsenior += 1;
                            break;

                        default:
                            break;

                }
                switch(characters[i].getspecies())
                {
                    case "llama":
                         tllama += 1;
                    case "dog":
                         tdog += 1;
                    case "cat":
                         tcat += 1;
                    case "bird":
                        tbird += 1;
                     default:
                         break;
                         
                }
                
            }
            
            
        }
    }
    
    public void professionEvaluationt(ethicalengine.Character  [] characters)
    {
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] instanceof Person)
                {
                    switch (characters[i].getProfession())
                    {
                        case CEO:
                            tceo += 1;
                            break;

                        case DOCTOR:
                            tdoctor += 1;
                            break;
                        case HOMELESS:
                            thomeless += 1;
                            break;
                        case CRIMINAL:
                            tcriminal += 1;
                            break;
                            
                        case MURDERER:
                            tmurderer += 1;

                        case SEXOFFENDER:
                            tsexoffender += 1;
                            
                        case STUDENT: 
                            tstudent += 1;
                        
                        case UNEMPLOYED:
                            tunemployed += 1;
                        
                        case EMPLOYED:
                            temployed += 1;          
                        case NONE:
                            tnone += 1;
                        case UNKNOWN:
                            tunknownO += 1;

                        default:
                            break;

                     }
                    if(characters[i].isYou())
                    {
                          tyou += 1;
                    }
                }         
        }
        
    }
    
    public void pregnancyEvaluationt(ethicalengine.Character  [] characters)
    {
        for(int i = 0; i < characters.length; i++)
        {
            if (characters[i].isPregnant())
            {
                tpregnant += 1;

            }
        } 
    }
    

}
