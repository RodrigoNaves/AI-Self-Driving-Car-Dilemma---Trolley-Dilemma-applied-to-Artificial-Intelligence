 /* Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */
package ethicalengine;

public class Person extends ethicalengine.Character
{
    
    private AgeCategory ageCategory = null;
    private ScenarioPosition position = null;
    private Profession occupation = null;
    private boolean pregnant;
    private Profession isYou = null;
    private enum pregnantE {PREGNANT};
    private pregnantE printPregnant = null;
    
    public Person()
    {
        super();
    }
    public Person(int age, Profession occupation, Gender gender, BodyType bodytype, boolean isPregnant)
    {
        super(age,gender, bodytype);
        this.occupation = occupation;
        setageCategory(age);
        if(gender == Gender.FEMALE)
        {
            pregnant = isPregnant;
            if(pregnant)
            {
                printPregnant = pregnantE.PREGNANT;
            }
        }
    }
        public Person(int age, Profession occupation, Gender gender, BodyType bodytype, boolean isPregnant, boolean isYou)
    {
        super(age,gender, bodytype);
        this.occupation = occupation;
        setageCategory(age);
        if(gender == Gender.FEMALE)
        {
            pregnant = isPregnant;
            if(pregnant)
            {
                printPregnant = pregnantE.PREGNANT;
            }
        }
        if(isYou)
        {
            this.isYou = Profession.YOU;
        }
    }
    
    
    public Person(int age, Gender gender, BodyType bodytype)
    {
        super(age,gender,bodytype);
        occupation = Profession.UNKNOWN;
        setageCategory(age);
        
    }
    
    public Person(Person otherPerson)
    {
        super(otherPerson);
        occupation = otherPerson.occupation;
        setageCategory(otherPerson.age);
        if(otherPerson.gender == Gender.FEMALE)
        {
            pregnant = otherPerson.pregnant;
            if(pregnant)
            {
                printPregnant = pregnantE.PREGNANT;
            }
        }
        
    }
    
    // accesor methods
 
    public AgeCategory getAgeCategory()
    {
        return ageCategory;
    }
    
    /**
     *
     * @return the profession of this character
     */
    @Override
    public Profession getProfession()
    {
        return occupation;
    }
    
    
    public boolean isPregnant()
    {
        return pregnant;
    }
    
    public boolean isYou()
    {
        if (isYou == Profession.YOU)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //mutator methods
    private void setageCategory(int age)
    {
        if (age >= 0 && age <= 4)
        {
            ageCategory = AgeCategory.BABY;
            occupation = Profession.NONE;
            
        }
        else if (age > 4&& age <= 16)
        {
            ageCategory = AgeCategory.CHILD;
            occupation = Profession.NONE;
        }
        else if (age > 16& age <= 68)
        {
            ageCategory = AgeCategory.ADULT;
        }
        else if (age > 68)
        {
            ageCategory = AgeCategory.SENIOR;
            occupation = Profession.NONE;
        }
    }
    
    public void setPregnant()
    {
        if (gender == Gender.FEMALE)
        {
            pregnant = true;
            printPregnant = pregnantE.PREGNANT;
        }
        else
        {
            System.out.println("Person not a FEMALE, impossible to set pregnant.");
        }
    }
    public void setPregnant(boolean isPregnant)
    {
        if (gender == Gender.FEMALE)
        {
            pregnant = isPregnant;
            printPregnant = pregnantE.PREGNANT;
        }
        else
        {
            System.out.println("Person not a FEMALE, impossible to set pregnant.");
        }
        
    }
    
    public void setAsYou()
    {

        isYou = Profession.YOU;
        
    }
    public void setAsYou(boolean isYou)
    {
        if(isYou)
        {
            this.isYou = Profession.YOU;
        }
    }
    public String toString()
    {
        String person;
        if (ageCategory == AgeCategory.ADULT)
        {
            if(pregnant)
            {
                person = bodytype.toString().toLowerCase() + " "+ ageCategory.toString().toLowerCase() + " " + occupation.toString().toLowerCase() + " " + gender.toString().toLowerCase() + " " + printPregnant.toString().toLowerCase() ;
                return  person;
            }
            else
            {
                person = bodytype.toString().toLowerCase() + " "+ ageCategory.toString().toLowerCase() + " " + occupation.toString().toLowerCase() + " " + gender.toString().toLowerCase();
                return person;
            }
        }
        else if (isYou == Profession.YOU)
        {
            person =  isYou.toString().toLowerCase() + " "+ occupation.toString().toLowerCase() + " " + bodytype.toString().toLowerCase() + " "+ ageCategory.toString().toLowerCase() + " " + gender.toString().toLowerCase();
            return person;
        }
        
        else if (pregnant)
        {
            person = bodytype.toString().toLowerCase() + " "+ ageCategory.toString().toLowerCase()  + " " + gender.toString().toLowerCase() + " " + printPregnant.toString().toLowerCase();
            return person;
        }
        
        else
        {
            person =  bodytype.toString().toLowerCase() +" "+ ageCategory.toString().toLowerCase() + " " + gender.toString().toLowerCase();
            return person;
        }
        
    }
    
    public  String getspecies()
    {
        return null;
    }
    
    
    
    
    
}
