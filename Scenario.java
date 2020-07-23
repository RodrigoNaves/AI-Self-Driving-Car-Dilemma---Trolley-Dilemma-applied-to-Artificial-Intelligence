 /* Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */
package ethicalengine;
public class Scenario
{
    private int minPassengers = 1;
    private int maxPassengers = 15;
    private int minPedestrians = 1;
    private int maxPedestrians = 15;
    private ethicalengine.Character [] passengersArray  = new ethicalengine.Character [maxPassengers];
    private ethicalengine.Character [] pedestriansArray = new ethicalengine.Character [maxPedestrians];
    private boolean legalCrossing;
    private int decisionResult;
    StringBuilder c = new StringBuilder();
   
    public Scenario()
    {
        passengersArray = null;
        pedestriansArray = null;
    }
    public Scenario(int maxPassengers,int maxPedestrians,ethicalengine.Character[] passengers, ethicalengine.Character[] pedestrians, boolean isLegalCrossing)
    {
        this.maxPassengers = maxPassengers;
        this.maxPedestrians = maxPedestrians;
        passengersArray = passengers;
        pedestriansArray = pedestrians;
        legalCrossing = isLegalCrossing;
    }
    
    public Scenario(ethicalengine.Character[] passengers, ethicalengine.Character[] pedestrians, boolean isLegalCrossing)
    {
        passengersArray = passengers;
        pedestriansArray = pedestrians;
        this.legalCrossing = isLegalCrossing;
        
    }
    
    //accessor methods
    
    public int getMaxPassengers()
    {
        return maxPassengers;
    }
    public int getMaxPedestrians()
    {
        return maxPedestrians;
    }
    
    public boolean hasYouInCar()
    {
        int istrue = 0;
        for (int i = 0; i <= passengersArray.length; i++)
        {
            if(passengersArray[i] != null)
            {
                if (passengersArray[i].isYou())
                {
                    istrue = 1;
                }
            }
        }
        if(istrue == 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    
    public boolean hasYouInLane()
    {
        int istrue = 0;
        for (int i = 0; i <= pedestriansArray.length; i++)
        {
            if(pedestriansArray[i] != null)
            {
                if (pedestriansArray[i].isYou())
                {
                    istrue = 1;
                }
            }
        }
        if(istrue == 1)
        {
            return true;
        }
        else
        {
            return false;
        }      
    }
    
    public ethicalengine.Character getPassengers(int i)
    {
        return passengersArray[i];
    }
    
    public ethicalengine.Character[] getPassengers()
    {
        return passengersArray;
    }
    
       public ethicalengine.Character getPedestrians(int i)
    {
        return pedestriansArray[i];
    }
    public ethicalengine.Character[] getPedestrians()
    {
        return pedestriansArray;
    }
    
    public boolean isLegalCrossing()
    {
        return legalCrossing;
    }
    
    public int getPassengerCount()
    {
        int count = 0;
        for(int i = 0; i < passengersArray.length ; i++ )
        {
            count ++;
        }
        return count;
    }
    public int getPedestrianCount()
    {

        int count = 0;
        for(int i = 0; i < pedestriansArray.length ; i++ )
        {
            count ++;
        }
        return count;
    }
    
    
    public String toString()
    {
                          
        c.append("======================================\n");
        c.append("# Scenario\n");
        c.append("======================================\n");
        if (legalCrossing == true)
        {
            c.append("Legal Crossing: yes\n");
        }
        else
        {
           c.append("Legal Crossing: no\n"); 
        }
         c.append("Passengers (").append(getPassengerCount()).append(")\n");
         for (int i=0;i<passengersArray.length; i++)
         {
             if (passengersArray[i] != null)
             {
                 c.append("- "+passengersArray[i].toString()+"\n");
             }
              
         }
         c.append("Pedestrians ("+ getPedestrianCount()+")\n");
         for (int i=0;i<pedestriansArray.length; i++)
         {
             if (pedestriansArray[i] != null)
             {
                 c.append("- "+pedestriansArray[i].toString()+"\n");
             }
              
         }
         return c.toString();
         
    }
    public int getDecision()
    {
       return decisionResult;
    }
    
    
    
    //mutator methods
    
    public void setLegalCrossing(boolean isLegalCrossing)
    {
        legalCrossing = isLegalCrossing;
    }
      public void setDecisionResult(int decisionResult)
   {
       this.decisionResult = decisionResult;
       
   }
    
    public void setMaxPassengers(int maxPassengers)
    {
        this.maxPassengers = maxPassengers;
    }
    public void setMaxPedestrians(int maxPedestrians)
    {
        this.maxPedestrians = maxPedestrians;
    }
    public void setMinPassengers(int minPassengers)
    {
        this.minPassengers = minPassengers;
    }
    public void setMinPedestrians(int minPedestrians)
    {
        this.minPedestrians = minPedestrians;
    }
    
    public void setPassengers(ethicalengine.Character [] passengers)
    {
        passengersArray = passengers;
    }
    public void setPedestrians(ethicalengine.Character [] pedestrians)
    {
        pedestriansArray = pedestrians;
    }

    
}
