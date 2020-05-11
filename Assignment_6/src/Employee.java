
/**
 * @author Dylan De Leon
 * CMSC 203
 */

public class Employee extends Ticket 
{

  private final double earlyPrice = 5.25;
  private final double latePrice = 6.75;
  private final double imaxPrice = 1.5;
  private final double threeDPrice = 1.25;

  /**
   * @param string
   * @param string2
   * @param i
   * @param j
   * @param string3
   * @param k
   * @param l
   */
  public Employee(String string, String string2, int i, int j, String string3, int k, int l) 
  {
	  
    super.movieType = "Employee";
    
  }

  /**
   * @param movieName
   * @param rating
   * @param day
   * @param format
   * @param time
   * @param id
   */
  public Employee(String movieName, String movieRating, int day, Format format, int movieTime, int id) 
  {
	  
    super(movieName, movieRating, day, format, movieTime, id);
    super.movieType = "Employee";
    
  }

  /**
   * @return ticket price
   */
  public double calculateTicketPrice() 
  {
	  
    double price = (movieTime < lateTime) ? earlyPrice : latePrice;
    
    if (format.equals(Format.IMAX)) 
    {
    	
      price += imaxPrice;
      
    } else if (format.equals(Format.THREE_D)) 
    {
    	
      price += threeDPrice;
      
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
  
}//end Employee class
