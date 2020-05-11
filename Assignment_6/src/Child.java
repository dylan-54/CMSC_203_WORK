
/**
 * @author Dylan De Leon
 * CMSC 203
 */

public class Child extends Ticket 
{

  //variables
  private final double earlyPrice = 5.75;
  private final double latePrice = 10.75;
  private final double imaxPrice = 2;
  private final double threeDPrice = 1.5;

  /**
   * @param string
   * @param string2
   * @param i
   * @param j
   * @param string3
   */
  public Child(String string, String string2, int i, int j, String string3) 
  {
	  
    super.movieType = "Child";
    
  }

  /**
   * @param movieName
   * @param rating
   * @param day
   * @param format
   * @param time
   * @param id
   */
  public Child(String movieName, String movieRating, int day, Format format, int movieTime, int id) 
  {
	  
    super(movieName, movieRating, day, format, movieTime, id);
    super.movieType = "Child";
    
  }

  /**
   * @return ticket price
   */
  public double calculateTicketPrice() 
  {
	  
    double ticketPrice = (movieTime < lateTime) ? earlyPrice : latePrice;
    if (format.equals(Format.IMAX)) 
    {
    	
      ticketPrice += imaxPrice;
      
    }else if (format.equals(Format.THREE_D)) 
    {
    	
      ticketPrice += threeDPrice;
      
    }
    
    return ticketPrice * taxFee;
    
  }

  public int getId() 
  {
	  
    return -1;
    
  }

  public String toString() 
  {
	  
    return super.toString();
    
  }
  
}//end Child class

