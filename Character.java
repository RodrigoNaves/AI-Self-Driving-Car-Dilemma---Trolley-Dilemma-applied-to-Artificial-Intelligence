 /** Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */
package ethicalengine;


public abstract  class Character
{
    public enum ScenarioPosition {PASSENGER, PEDESTRIAN};
    public enum Gender {FEMALE, MALE, UNKNOWN};
    public enum BodyType {AVERAGE, ATHLETIC,OVERWEIGHT,UNSPECIFIED};
    public enum AgeCategory{BABY, CHILD, ADULT, SENIOR};
    public enum Profession {CEO, DOCTOR, HOMELESS, CRIMINAL, YOU , SEXOFFENDER, STUDENT, UNEMPLOYED, EMPLOYED,MURDERER, NONE, UNKNOWN};
    public enum AnimalType {CAT, DOG, LLAMA, BIRD};
    public int age;
    public Gender gender;
    public BodyType bodytype;
    public ScenarioPosition scenariopos;
    
    //contructor
    public Character()
    {
        age = 0;
        gender = null;
        bodytype = null;
    }
    
    /**
     * 
     * @param age is the age in years as integer of the Character
     * @param gender is the gender either male or female of the Character
     * @param bodytype  is the physical build of the Character 
     */
    public Character(int age, Gender gender, BodyType bodytype)
    {
        if(consitentAge(age))
        {
            this.age = age;
            this.gender = gender;
            this.bodytype = bodytype;
        }
        else
        {
            System.out.println("Inconsistent dates.");
        }
    }
    public Character(Character aCharacter)
    {
        age = aCharacter.age;
        gender = aCharacter.gender;
        bodytype = aCharacter.bodytype;
    }
    
    //accesor methods
    public int getAge()
    {
        return age;
    }
    
    public Gender getGender()

    {
        return gender;
    }
    
    public BodyType getBodyType()
    {
        return bodytype;
    }
    
    public ScenarioPosition getScenarioPosition()
    {
        return scenariopos;
    }
    //mutator methods
    public void setAge(int age)
    {
        this.age = age;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
    public void setBodyType(BodyType bodytype)
    {
        this.bodytype = bodytype;
    }
    public void setScenarioPosition(ScenarioPosition scenariopos)
    {
        this.scenariopos = scenariopos;
    }
    private static boolean consitentAge(int age)
    {
        if (age < 0)
        {
            return false;
        }
        else if (age >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //abstract methods
    public abstract boolean isYou();
    public abstract AgeCategory getAgeCategory();
    public abstract Profession getProfession();
    public abstract String getspecies();
    public abstract boolean isPregnant();

    
}
