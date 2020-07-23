
 /** Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */
package ethicalengine;


public class Animal extends ethicalengine.Character 
{
    
    private String animalType;
    private boolean pet = false;
    AgeCategory ageCategory;
    
    /**
     * This method constructs Animal that extends Character
     * 
     */
    public Animal()
    {
        animalType= null;
        pet = false;
        
    }
 
    /**
     * 
     * @param species is a string defining what kind of animal this is
     */
    public Animal (String species)
    {
        super();
        animalType = species;  
    }
    
    /**
     * 
     * @param age is an int that represent how old the Animal is
     * @param gender is an enumeration type can either be male or female
     * @param bodytype is an enumeration type listed in super class Character
     * @param species is an enumeration type can be either DOG,CAT or LLAMA
     * @param isPet is a is true if per is owned by a person.
     */
    public Animal(int age, Gender gender, BodyType bodytype, String species, boolean isPet)
    {
        super(age,gender,bodytype);
        animalType = species;
        pet = isPet;
        setageCategory(age);
    }
    public Animal(Animal otherAnimal)
    {
        super(otherAnimal);
        animalType = otherAnimal.animalType;
        setageCategory(otherAnimal.age);
    }
    
    //accesor methods
    
    public String getspecies()
    {
        return animalType;
    }
    
    public String getSpecies()
    {
        return animalType.toLowerCase();
    }
    
    public boolean isPet()
    {
        return pet;
    }
    
    //setter methods
    
    public void setspecies(String species)
    {
        animalType = species;
    }
    
    
    public void setPet(boolean isPet)
    {
        pet = isPet;
    }
    public void setPet()
    {
        pet = true;
    }
    
    public String toString()
    {
        if (pet == true)
        {
          return animalType.toLowerCase() + " is pet"; 
        }
        else 
        {
          return animalType.toLowerCase() + "";
        }
        
    }
    public boolean isYou()
    {
        return false;
    }
    private void setageCategory(int age)
    {
        if (age >= 0 && age <= 1)
        {
            ageCategory = AgeCategory.BABY;
   
        }
        else if (age > 1& age <= 10)
        {
            ageCategory = AgeCategory.ADULT;
        }
        else if (age > 10)
        {
            ageCategory = AgeCategory.SENIOR;

        }
    }
    
    public AgeCategory getAgeCategory()
    {
        return ageCategory;
    }
    
    public Profession getProfession()
    {
        return null;
    }
    public boolean isPregnant()
    {
        return false;
    }
    


}
