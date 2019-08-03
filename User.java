public class User
{

    public  String name;
    public double highScore;
    
    

    public User(String name, double highScore)
    {
        this.name = name;
        this.highScore = highScore;
        
    }

    public String getName() 
    {
        return name;
    }

    
    public double getScore() 
    {
        return highScore;
    }

    public void setScore(double highScore) 
    {
        this.highScore = highScore;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

}