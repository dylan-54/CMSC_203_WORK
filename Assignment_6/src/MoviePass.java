
/**
 * Author: Dylan De Leon
 * CMSC 203
 */
public class MoviePass extends Ticket 
{

  private final double ADULT_EARLY_PRICE = 10.5;
  private final double ADULT_LATE_PRICE = 13.5;
  private final double ADULT_IMAX_PRICE = 3;
  private final double ADULT_THREE_D_PRICE = 2.5;

  public MoviePass() 
  {
	  
    super.movieType = "MoviePass";
    
  }

  public MoviePass(String movieName, String rating, int day, Format format, int movieTime, int id) 
  {
	  
    super(movieName, rating, day, format, movieTime, id);
    super.movieType = "MoviePass";
    
  }

  public double calculateTicketPrice() 
  {
	  
    double price = (movieTime < lateTime) ? ADULT_EARLY_PRICE : ADULT_LATE_PRICE;
    
    if (format.equals(Format.IMAX)) 
    {
    	
      price += ADULT_IMAX_PRICE;
      
    } else if (format.equals(Format.THREE_D)) 
    {
    	
      price += ADULT_THREE_D_PRICE;
      
    }
    
    return price * taxFee;
    
  }

  public int getId() 
  {
	  
    return this.id;
    
  }

  public String toString() 
  {
	  
    return super.toString();
    
  }

}//end MoviePass class
