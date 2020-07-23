 /** Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */


import ethicalengine.*;
import java.util.ArrayList;
import java.util.Collections;
public class EthicalEngine {
    public static enum Decision{PEDESTRIANS, PASSENGERS};
    private String commandInput1;
    private String commandInput2;
    static private ArrayList <String> inputText = new ArrayList <String>();
    private ArrayList <ethicalengine.Scenario> ConfigFileScenarios = new ArrayList<ethicalengine.Scenario>();
    private final Flags flags = new Flags(); 
    private Decision tempDecision;
    private int autoRunscount = 0;
    private int interRunscount = 0;
    private String userConsent;
    private static String newPath = "logs//user.log";
    boolean consentcheck = false;
    boolean numCheck;
    int NumRuns;
    private int countScenarioProcessoric = 0;
    ArrayList <Audit> AuditList = new ArrayList <Audit>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        Collections.addAll(inputText, args);   
        String argsBash[] = args;
        EthicalEngine newEngine = new EthicalEngine();
        newEngine.EthicalEngine();

    }
    
    public void EthicalEngine()
    {
        FileIO newFileIO = new FileIO();
        //System.out.println ("Welcome to EthicalEngine - COMP90041 - Final Project");
        
        int exit = 0;
        Audit newAudit = new Audit(); 
        AuditList.add(newAudit);
        
        
        while (exit == 0)
        {

            try
            {
                outer:
                if (inputText.size() < 1)
                {
                    Audit newInteractiveAudit = new Audit(); 
                    AuditList.add(newInteractiveAudit);
                    newFileIO.Welcomesaver();
                    System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                    consentValidator();
                    newInteractiveAudit.getConsent(userConsent);
                    boolean interactiveLoop = true;
                    interRunscount = 0;
                   // while(interactiveLoop)
                    //{
                        newInteractiveAudit.setAuditType(/*"Interactive Audit " + autoRunscount*/"User" );// finish setting up autoRunsCount
                        newInteractiveAudit.runInter(100);
                        interRunscount += 3;
                        ScenarioProcessor(newInteractiveAudit.getScenarioGeneratorList());
                        newInteractiveAudit.runStats();
                        newInteractiveAudit.sort();
                        newInteractiveAudit.setRuns(interRunscount);
                        newInteractiveAudit.printStatistic();
                        newInteractiveAudit.printToFile();
                       // System.out.println("Would you like to continue? (yes/no)");
                       // interactiveLoop = interactiveContinue();    
                    //}
                    System.out.println("That's all. Press Enter to quit.");
                    ScanningInput.nextLine();
                    System.exit(0); 
                }
                
                else if (inputText.size() >= 1)
                {
                    switch(inputText.get(0))
                    {
                    case "-h":
                        flags.helpFlag();
                        ScanningInput.nextLine();
                        System.exit(0);
                        System.out.println("");
                    case "--help":
                        flags.helpFlag();
                        ScanningInput.nextLine();
                        System.exit(0);
                        System.out.println("");
                    case "-c": //check if there is a path to file. Otherwise run
                        if(inputText.size()>1)
                        {
                            flags.configFlag(inputText.get(1));
                            if( flags.fileFound())
                            {
                                newFileIO.Welcomesaver();
                                System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                                consentValidator();
                                Audit newAuditConfig = new Audit(); 
                                AuditList.add(newAuditConfig);
                                newAuditConfig.getConsent(userConsent);
                                //Collections.copy(ConfigFileScenarios,flags.getScenarioArray()); 
                                boolean interactiveLoop = true;
                                int lenOfFile = 0;
                                newAuditConfig.setAuditType("Audit from Config.csv File" );
                                newAuditConfig.runConfig(flags.getScenarioArray());
                                while(interactiveLoop && interRunscount < newAuditConfig.getScenarioArray().size())
                                {
                                    
                                    lenOfFile = ScenarioProcessorc(newAuditConfig.getScenarioArray());
                                    newAuditConfig.runStatsConfig(interRunscount);
                                    newAuditConfig.sort();
                                    interRunscount+=3;
                                    newAuditConfig.setRuns(lenOfFile);
                                    newAuditConfig.printStatistic();
                                    newAuditConfig.printToFile();
                                    if(interRunscount >= newAuditConfig.getScenarioArray().size() )
                                    {
                                        break;
                                    }
                                    System.out.println("Would you like to continue? (yes/no)");
                                    interactiveLoop = interactiveContinue(); 
                                    
                                }

                                System.out.println("That's all. Press Enter to quit.");
                                ScanningInput.nextLine();
                                System.exit(0);
                                System.out.println("");
                            }
                        }
                        else
                        {
                            flags.helpFlag();
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");

                        }


                    case "--config":
                        if(inputText.size()>1)
                        {
                            flags.configFlag(inputText.get(1));
                                if( flags.fileFound())
                                {
                                    newFileIO.Welcomesaver();
                                    System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                                    consentValidator();
                                    Audit newAuditConfig = new Audit(); 
                                    AuditList.add(newAuditConfig);
                                    newAuditConfig.getConsent(userConsent);
                                    boolean interactiveLoop = true;
                                    int lenOfFile = 0;
                                    newAuditConfig.setAuditType("Audit from Config.csv File" );
                                    newAuditConfig.runConfig(flags.getScenarioArray());
                                    while(interactiveLoop && interRunscount < newAuditConfig.getScenarioArray().size())
                                    {
                                        lenOfFile =ScenarioProcessorc(newAuditConfig.getScenarioArray());
                                        newAuditConfig.runStatsConfig(interRunscount);
                                        newAuditConfig.sort();
                                        interRunscount+=3;
                                        newAuditConfig.setRuns(lenOfFile);
                                        newAuditConfig.printStatistic();
                                        newAuditConfig.printToFile();
                                        if(interRunscount >= newAuditConfig.getScenarioArray().size() )
                                        {
                                            break;
                                        }
                                        System.out.println("Would you like to continue? (yes/no)");
                                        interactiveLoop = interactiveContinue(); 
                                    }

                                    System.out.println("That's all. Press Enter to quit.");
                                    ScanningInput.nextLine();
                                    System.exit(0);
                                    System.out.println("");
                                }
                        }
                        else
                        {
                            flags.helpFlag();
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                            
                        }
                    case "-r":
                        if(inputText.size() > 1)
                        {
                            newPath = inputText.get(1);
                            newAudit.setFilePathName(newPath);
                            System.out.println("That's all. Press Enter to quit.");
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                        }
                        else
                        {
                            flags.helpFlag();
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                        }
                    case "--results":
                        if(inputText.size() > 1)
                        {
                            newPath = inputText.get(1);
                            newAudit.setFilePathName(newPath);
                            System.out.println("That's all. Press Enter to quit.");
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                        }
                        else
                        {
                            flags.helpFlag();
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                        }
                    case "-i":
                        if(inputText.size() > 2)
                        {
                            switch (inputText.get(1))
                            {
                            case "-c":
                                flags.configFlag(inputText.get(2));
                                if( flags.fileFound())
                                {
                                    newFileIO.Welcomesaver();
                                    System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                                    consentValidator();
                                    Audit newAuditConfig = new Audit();
                                    AuditList.add(newAuditConfig);
                                    newAuditConfig.getConsent(userConsent);
                                    boolean interactiveLoop = true;
                                    int lenOfFile = 0;
                                    newAuditConfig.setAuditType("User Audit" );// finish setting up autoRunsCount
                                    newAuditConfig.runConfig(flags.getScenarioArray());
                                    while(interactiveLoop && interRunscount < newAuditConfig.getScenarioArray().size())
                                    {
                                        
                                        lenOfFile = ScenarioProcessoric(newAuditConfig.getScenarioArray());
                                        newAuditConfig.runStatsConfig(interRunscount);
                                        newAuditConfig.sort();
                                        interRunscount+=3;
                                        newAuditConfig.setRuns(lenOfFile);
                                        newAuditConfig.printStatistic();
                                        newAuditConfig.setFilePathName(newPath);
                                        newAuditConfig.printToFile();
                                        if(interRunscount >= newAuditConfig.getScenarioArray().size() )
                                        {
                                            break;
                                        }
                                        System.out.println("Would you like to continue? (yes/no)");
                                        interactiveLoop = interactiveContinue();  
                                        
                                    }
                                    System.out.println("That's all. Press Enter to quit.");
                                    ScanningInput.nextLine();
                                    System.exit(0);
                                    System.out.println("");
                                }

                            case "--config":
                                flags.configFlag(inputText.get(2));
                                if( flags.fileFound())
                                {
                                    newFileIO.Welcomesaver();
                                    System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                                    consentValidator();
                                    Audit newAuditConfig = new Audit();
                                    AuditList.add(newAuditConfig);
                                    newAuditConfig.getConsent(userConsent);
                                    boolean interactiveLoop = true;
                                    int lenOfFile = 0;
                                    newAuditConfig.setAuditType("User Audit" );// finish setting up autoRunsCount
                                    newAuditConfig.runConfig(flags.getScenarioArray());
                                    while(interactiveLoop && interRunscount < newAuditConfig.getScenarioArray().size())
                                    {
                                        
                                        lenOfFile = ScenarioProcessoric(newAuditConfig.getScenarioArray());
                                        newAuditConfig.runStatsConfig(interRunscount);
                                        newAuditConfig.sort();
                                        interRunscount += 3;
                                        newAuditConfig.setRuns(lenOfFile);
                                        newAuditConfig.printStatistic();
                                        newAuditConfig.setFilePathName(newPath);
                                        newAuditConfig.printToFile();
                                        if(interRunscount >= newAuditConfig.getScenarioArray().size() )
                                        {
                                            break;
                                        }
                                        System.out.println("Would you like to continue? (yes/no)");
                                        interactiveLoop = interactiveContinue();  
                                    }
                                    System.out.println("That's all. Press Enter to quit.");
                                    ScanningInput.nextLine();
                                    System.exit(0);
                                    System.out.println("");
                                }
                                break;
                            default:
                
                            
                            }   
                        }
                        else if (inputText.size() <=1)
                        {
                            newFileIO.Welcomesaver();
                            System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                            consentValidator();
                            Audit newInteractiveAudit = new Audit(); 
                            AuditList.add(newInteractiveAudit);
                            newInteractiveAudit.getConsent(userConsent);
                            boolean interactiveLoop = true;
                            while(interactiveLoop)
                            { 
                                interRunscount+= 3;
                                newInteractiveAudit.setAuditType("User" );// finish setting up autoRunsCount
                                newInteractiveAudit.runInter(3);
                                ScenarioProcessori(newInteractiveAudit.getScenarioGeneratorList());
                                newInteractiveAudit.runStats();
                                newInteractiveAudit.sort();
                                newInteractiveAudit.setRuns(interRunscount);
                                newInteractiveAudit.printStatistic();
                                newInteractiveAudit.setFilePathName(newPath);
                                newInteractiveAudit.printToFile();
                                System.out.println("Would you like to continue? (yes/no)");
                                interactiveLoop = interactiveContinue();    
                            }
                            System.out.println("That's all. Press Enter to quit.");
                            ScanningInput.nextLine();
                            System.exit(0);               
                        }
                        else
                        {
                            flags.helpFlag();
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                        }

                        break;
                    case "--interactive":
                         if(inputText.size() > 2)
                        {
                            switch (inputText.get(1))
                            {
                            case "-c":
                                flags.configFlag(inputText.get(2));
                                if( flags.fileFound())
                                {
                                    newFileIO.Welcomesaver();
                                    System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                                    consentValidator();
                                    Audit newAuditConfig = new Audit();
                                    AuditList.add(newAuditConfig);
                                    newAuditConfig.getConsent(userConsent);
                                    boolean interactiveLoop = true;
                                    int lenOfFile = 0;
                                    newAuditConfig.setAuditType("User Audit " );// finish setting up autoRunsCount
                                    newAuditConfig.runConfig(flags.getScenarioArray());
                                    while(interactiveLoop && interRunscount < newAuditConfig.getScenarioArray().size())
                                    {
                                        
                                        lenOfFile = ScenarioProcessoric(newAuditConfig.getScenarioArray());
                                        newAuditConfig.runStatsConfig(interRunscount);
                                        newAuditConfig.sort();
                                        interRunscount+=3;
                                        newAuditConfig.setRuns(lenOfFile);
                                        newAuditConfig.printStatistic();
                                        newAuditConfig.setFilePathName(newPath);
                                        newAuditConfig.printToFile();
                                        if(interRunscount >= newAuditConfig.getScenarioArray().size() )
                                        {
                                            break;
                                        }
                                        System.out.println("Would you like to continue? (yes/no)");
                                        interactiveLoop = interactiveContinue();  
                                        
                                    }
                                    System.out.println("That's all. Press Enter to quit.");
                                    ScanningInput.nextLine();
                                    System.exit(0);
                                    System.out.println("");
                                }
                            case "--config":
                                flags.configFlag(inputText.get(2));
                                if( flags.fileFound())
                                {
                                    newFileIO.Welcomesaver();
                                    System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                                    consentValidator();                                  
                                    Audit newAuditConfig = new Audit();
                                    AuditList.add(newAuditConfig);
                                    newAuditConfig.getConsent(userConsent);
                                    boolean interactiveLoop = true;
                                    int lenOfFile = 0;
                                    newAuditConfig.setAuditType("User Audit" );// finish setting up autoRunsCount
                                    newAuditConfig.runConfig(flags.getScenarioArray());
                                    while(interactiveLoop && interRunscount < newAuditConfig.getScenarioArray().size())
                                    {
                                        lenOfFile =ScenarioProcessoric(newAuditConfig.getScenarioArray());
                                        newAuditConfig.runStatsConfig(interRunscount);
                                        newAuditConfig.sort();
                                        interRunscount+=3;
                                        newAuditConfig.setRuns(lenOfFile);
                                        newAuditConfig.printStatistic();
                                        newAuditConfig.setFilePathName(newPath);
                                        newAuditConfig.printToFile();
                                        if(interRunscount >= newAuditConfig.getScenarioArray().size() )
                                        {
                                            break;
                                        }
                                        System.out.println("Would you like to continue? (yes/no)");
                                        interactiveLoop = interactiveContinue();  
                                        
                                    }
                                    System.out.println("That's all. Press Enter to quit.");
                                    ScanningInput.nextLine();
                                    System.exit(0);
                                    System.out.println("");
                                    break;
                                }
                                break;
                            default:

                            
                            }  
                        }
                        else if (inputText.size() <=1)
                        {
                            newFileIO.Welcomesaver();
                            System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
                            consentValidator();
                            Audit newInteractiveAudit = new Audit(); 
                            AuditList.add(newInteractiveAudit);
                            newInteractiveAudit.getConsent(userConsent);
                            boolean interactiveLoop = true;
                            while(interactiveLoop)
                            {
                                
                                interRunscount+=3;
                                newInteractiveAudit.setAuditType(/*"Interactive Audit " + autoRunscount*/"User" );// finish setting up autoRunsCount
                                newInteractiveAudit.runInter(3);
                                ScenarioProcessori(newInteractiveAudit.getScenarioGeneratorList());
                                newInteractiveAudit.runStats();
                                newInteractiveAudit.sort();
                                newInteractiveAudit.setRuns(interRunscount);
                                newInteractiveAudit.printStatistic();
                                newInteractiveAudit.setFilePathName(newPath);
                                newInteractiveAudit.printToFile();
                                System.out.println("Would you like to continue? (yes/no)");
                                interactiveLoop = interactiveContinue();    
                            }
                            System.out.println("That's all. Press Enter to quit.");
                            ScanningInput.nextLine();
                            System.exit(0);
                        }
                         else
                        {
                            flags.helpFlag();
                            ScanningInput.nextLine();
                            System.exit(0);
                            System.out.println("");
                        }
                        break;
                    case "exit":
                        System.out.println("");
                        System.exit(0);
                        break; 
                    default:
                     throw new notValidCommandException ("'"+inputText.get(0)+"'"+" is not a valid command.");
                }
                    
                 

                }

            }
            catch (notValidCommandException e)
            {
                System.out.println("'"+inputText.get(0)+"'"+" is not a valid command.");
                System.out.println("");  
                System.exit(0);
            }
            catch(Exception tooManyArgs)
            {
                flags.helpFlag();
                System.out.println("Incorrect number of arguments supplied to command.");
                System.out.println("");
            }
        
        }
    }
    
    /**this function @returns an integer that represents whether passengers or pedestrians survive
     @param newScenario is taken as parameter and this is the case for every scenario created.
     * @return integer that is either 1 to save passengers or it is 2 to save pedestrians
     */
    public static EthicalEngine.Decision decide(ethicalengine.Scenario newScenario)
    {
        int passengerScore = 0;
        int pedestrianScore = 0;
        int passCount = newScenario.getPassengerCount();
        int pedCount = newScenario.getPedestrianCount();
        ethicalengine.Character  passengerArray[] = new ethicalengine.Character [passCount];
        ethicalengine.Character  pedestrianArray[] = new ethicalengine.Character [pedCount];
        for(int i = 0; i < passengerArray.length; i++)
        {
            passengerArray[i] = newScenario.getPassengers(i);
        }
           for(int i = 0; i < pedestrianArray.length; i++)
        {
            pedestrianArray[i] = newScenario.getPedestrians(i);
        }
        
        int points = 0;
        
        // EVALUATIONS OF PASSENGERS IN SCENARIO
        passengerScore += bodytypeEvaluation(passengerArray);
        passengerScore += genderEvaluation(passengerArray);
        passengerScore += ageEvaluation(passengerArray); 
        passengerScore += professionEvaluation(passengerArray);
        passengerScore += pregnancyEvaluation(passengerArray);
        // EVLUATIONS OF PEDESTRIANS IN SCENARIO
        pedestrianScore += bodytypeEvaluation(pedestrianArray);
        pedestrianScore += genderEvaluation(pedestrianArray);
        pedestrianScore += ageEvaluation(pedestrianArray);
        pedestrianScore += professionEvaluation(pedestrianArray);
        pedestrianScore += pregnancyEvaluation(pedestrianArray);
        
        //LEGAL CROSSING EVALUATION
        
        if(newScenario.isLegalCrossing())
        {
            pedestrianScore += 80;
        }
        else
        {
            passengerScore += 80;
        }
        
        if (passengerScore > pedestrianScore)
        {
            return EthicalEngine.Decision.PASSENGERS;
        }
        else
        {
            return EthicalEngine.Decision.PEDESTRIANS;
        }
        
    }
    
    /**
    there is a range of scenario processor methods, each one is different to address interactive, config, or standard mode
    @param ScenarioList ArrayList of Scenario Generators and this is the list of automatically generated scenarios with random values.
    * 
    */
    public static void ScenarioProcessor( ArrayList <ethicalengine.ScenarioGenerator> ScenarioList)
    {
        Decision tempDecision = null;
        for(int i=0;i<ScenarioList.size();i++)
        {
            System.out.print(ScenarioList.get(i).getScenario().toString());
            tempDecision = decide(ScenarioList.get(i).getScenario());
            System.out.println();
            System.out.println("AI Survival algorithm decision: " + tempDecision.toString());
            if(tempDecision == Decision.PASSENGERS)
            {
                ScenarioList.get(i).setDecisionResult(1);
            }
            else
            {
                ScenarioList.get(i).setDecisionResult(2);
            }
            
        }
        
    }
    /**
     * 
     * @param ScenarioList is taken given that scenarios are imported from an external file and not automatically generated
     * @return an integer that indicates the number of times this function has run.
     */
    public int ScenarioProcessorc( ArrayList <ethicalengine.Scenario> ScenarioList)
    {
        int count = 0;
        for(int i=countScenarioProcessoric;i <(countScenarioProcessoric + 3)&&i<ScenarioList.size();i++)
        {
            tempDecision = decide(ScenarioList.get(i));
            if(tempDecision == Decision.PASSENGERS)
            {
                ScenarioList.get(i).setDecisionResult(1);
                count ++;
            }
            else
            {
                ScenarioList.get(i).setDecisionResult(2);
                count ++;
            }

        }
        countScenarioProcessoric +=count;
        return countScenarioProcessoric;
        
    }
    /**
     *  scenario processor for interactive mode
     * @param ScenarioList is taken given randomized scenarios are generated for the user to decide which groups will survive
     */
    public void ScenarioProcessori(ArrayList <ethicalengine.ScenarioGenerator> ScenarioList)
    {
        String UserInput;
        for(int i=(ScenarioList.size()-3);i<ScenarioList.size();i++)
        {
            System.out.print(ScenarioList.get(i).getScenario().toString());
            System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
            UserInput = ScanningInput.nextLine();
            boolean validInput = false;
            try
            {
 
                switch(UserInput)
                {
                    case "1":
                        ScenarioList.get(i).setDecisionResult(1);
                        validInput = true;
                        break;
                    case "passenger":
                        ScenarioList.get(i).setDecisionResult(1);
                        validInput = true;
                        break;
                    case "passengers":
                        ScenarioList.get(i).setDecisionResult(1);
                        validInput = true;
                        break;
                    case "2":
                        ScenarioList.get(i).setDecisionResult(2);
                        validInput = true;
                        break;

                    case "pedestrian":
                        ScenarioList.get(i).setDecisionResult(2);
                        validInput = true;
                        break;
                    case "pedestrians":
                        ScenarioList.get(i).setDecisionResult(2);
                        validInput = true;
                        break;
                    default:
                         throw new notValidCommandException ("'"+UserInput+"'"+" is not a valid answer.");
 
                }
                
            }
            catch (notValidCommandException e)
            {
                System.out.println("'"+inputText.get(0)+"'"+" is not a valid answer.");
                System.out.println("");        
            }
        }
    }
    /**
     * Scenario processor in interactive and reading from configuration file 
     * @param ScenarioList is imported from a external file and then the user decides which groups will survive
     * 
     */
    public int ScenarioProcessoric(ArrayList <ethicalengine.Scenario> ScenarioList)
    {
        int count = 0;
        String UserInput;
        for(int i=countScenarioProcessoric;i <(countScenarioProcessoric + 3)&& i<ScenarioList.size();i++)
        {
            System.out.print(ScenarioList.get(i).toString());
            count ++;
            System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
            UserInput = ScanningInput.nextLine();
            try
            {
                switch(UserInput)
                {
                    case "1":
                        ScenarioList.get(i).setDecisionResult(1);
                        break;
                    case "passenger":
                        ScenarioList.get(i).setDecisionResult(1);
                        break;
                    case "passengers":
                        ScenarioList.get(i).setDecisionResult(1);
                        break;
                    case "2":
                        ScenarioList.get(i).setDecisionResult(2);
                        break;
                        
                    case "pedestrian":
                        ScenarioList.get(i).setDecisionResult(2);
                        break;
                    case "pedestrians":
                        ScenarioList.get(i).setDecisionResult(2);
                        break;
                    default:
                    throw new notValidCommandException ("'"+UserInput+"'"+" is not a valid answer.");
                }
            }
            catch (notValidCommandException e)
            {
                System.out.println("'"+inputText.get(0)+"'"+" is not a valid answer.");
                System.out.println("");        
            }
        }
        countScenarioProcessoric += count;
        return countScenarioProcessoric;
    }
    
    /**
     * interactive continue is used to check if user would like to generate 3 more random scenarios and decide the faith of Characters
     * @return true or false to whether you would like to continue or else finish the program
     */
        
    public boolean interactiveContinue()
    {
        boolean continuecheck = false;
        boolean continueAudits = false;
        while (continuecheck == false)
        {
            commandInput2 = ScanningInput.nextLine();
            switch(commandInput2)
            {
                case "y":
                    continuecheck = true;
                    continueAudits =true;
                    break;
                case "Y":
                    continuecheck = true;
                    continueAudits =true;
                    break;
                case "yes":
                    continuecheck = true;
                    continueAudits =true;
                    break;
                case "Yes":
                    continuecheck = true;
                    continueAudits =true;
                    break;
                case "n":
                    continuecheck = true;
                    continueAudits =false;
                    break;
                case "N":
                    continuecheck = true;
                    continueAudits =false;
                    break;
                case "No":
                    continuecheck = true;
                    continueAudits =false;
                    break;
                case "no":
                    continuecheck = true;
                    continueAudits =false;
                    break;
                default:
                    System.out.println("Invalid response. Do you want to continue? (yes/no)");
                    break;  
            }
        }
        if(continueAudits)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /**
     * Consent validation to know if the user would like to have their decisions saved into a log file
     */
    public void consentValidator()
    {
        while (consentcheck == false)
        {
            userConsent = ScanningInput.nextLine();
            switch(userConsent)
            {
                case "y":
                    consentcheck = true;
                    break;
                case "Y":
                    consentcheck = true;
                    break;
                case "yes":
                    consentcheck = true;
                    break;
                case "Yes":
                    consentcheck = true;
                    break;
                case "n":
                    consentcheck = true;
                    break;
                case "N":
                    consentcheck = true;
                    break;
                case "No":
                    consentcheck = true;
                    break;
                case "no":
                    consentcheck = true;
                    break;
                default:
                    System.out.println("Invalid response. Do you consent to have your decisions saved to a file? (yes/no)");
                    break;  
            }
        }
        
    }
    /**
     * 
     * @param characters these are the characters saved in every scenario, they are evaluated by their body type
     * @return an int representing all the points they gather and then used to decide their faith of life or death
     */
    public static int bodytypeEvaluation(ethicalengine.Character [] characters)
    {
        int points = 0;
            for(int i = 0; i < characters.length; i++)
        {
            switch (characters[i].getBodyType())
            {
                case ATHLETIC:
                    points += 100;
                    break;
                    
                case AVERAGE:
                    points += 95;
                    break;
                case OVERWEIGHT:
                    points += 85;
                    break;
                case UNSPECIFIED:
                    points += 95;
                    break;
                 
                default:
            }
            
        }
            return points;
    }
    /**
     * 
     * @param characters these are the characters saved in every scenario, they are evaluated by their gender
     * @return an int representing all the points they gather and then used to decide their faith of life or death
     */
     public static int genderEvaluation(ethicalengine.Character  [] characters )
    {
        int points = 0;
        for(int i = 0; i < characters.length; i++)
        {
            switch (characters[i].getGender())
            {
                case MALE:
                    points += 100;
                    break;
                    
                case FEMALE:
                    points += 100;
                    break;
                 
                default:
            
            }
            
        }
        return points;
    }
     /**
      * 
      * @param characters these are the characters saved in every scenario, they are evaluated by their age
      * @return an int representing all the points they gather and then used to decide their faith of life or death
      */
    public static int ageEvaluation(ethicalengine.Character  [] characters)
    {
        int points = 0;
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] instanceof Person)
                {
                    switch (characters[i].getAgeCategory())
                    {
                        case BABY:
                            points += 86;
                            break;

                        case CHILD:
                            points += 100;
                            break;
                        case ADULT:
                            points += 100;
                            break;
                        case SENIOR:
                            points += 85;
                            break;

                        default:
                            points += 100;
                            break;

                     }
                }
            else if(characters[i] instanceof Animal)
            {
                switch (characters[i].getAgeCategory())
                    {
                        case BABY:
                            points += 90;
                            break;

                        case ADULT:
                            points += 80;
                            break;
                        case SENIOR:
                            points += 65;
                            break;

                        default:
                            points += 100;
                            break;

                     }
                
            }
            
            
        }
        return points;
    }
    /**
     * 
     * @param characters these are the characters saved in every scenario, they are evaluated by their occupation
      * @return an int representing all the points they gather and then used to decide their faith of life or death
     */
    public static int professionEvaluation(ethicalengine.Character  [] characters)
    {
        int points = 0;
        for(int i = 0; i < characters.length; i++)
        {
            if(characters[i] instanceof Person)
                {
                    switch (characters[i].getProfession())
                    {
                        case CEO:
                            points += 96;
                            break;

                        case DOCTOR:
                            points += 100;
                            break;
                        case HOMELESS:
                            points += 90;
                            break;
                        case CRIMINAL:
                            points += 50;
                            break;
                            
                        case MURDERER:
                            points += 50;

                        case SEXOFFENDER:
                            points += 30;
                            
                        case STUDENT: 
                            points += 95;
                        
                        case UNEMPLOYED:
                            points += 95;
                        
                        case EMPLOYED:
                            points += 97;

                            
                        case NONE:
                            points += 97;

                        default:
                            points += 90;
                            break;

                     }
                    if(characters[i].isYou())
                    {
                        points += 50;
                    }
                }         
        }
        return points;
        
    }
    /**
     * 
     * @param characters these are the characters saved in every scenario, they are evaluated for whether their pregnant or not
      * @return an int representing all the points they gather and then used to decide their faith of life or death
     */
    public static int pregnancyEvaluation(ethicalengine.Character  [] characters)
    {
        int points = 0;
        for(int i = 0; i < characters.length; i++)
        {
            if (characters[i].isPregnant())
            {
                points += 50;
            }
        }
        return points;  
    }
    public class notValidCommandException extends Exception {

    public notValidCommandException(String string) {
        super(string);
    }
    
    
    
    }
}
