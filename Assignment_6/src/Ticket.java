import java.text.DecimalFormat;

/**
 * Author Dylan De Leon
 * CMSC 203
 */
public abstract class Ticket 
{

  //variables
  protected String movieType;
  protected String movieName;
  protected String movieRating;
  protected int day;
  protected Format format;
  protected int movieTime;
  protected int id;
  protected double ticketPrice;
  protected final double taxFee = 1.096;
  protected final int lateTime = 18;

  public Ticket() 
  {
	  
    this.movieType = "Ticket";
    this.movieName = "";
    this.movieRating = "";
    this.format = Format.NONE;
    this.day = 1;
    this.movieTime = 8;
    this.ticketPrice = -1;
    
  }

  /**
   * @param movieName
   * @param movieRating
   * @param day
   * @param format
   * @param movieTime
   * @param id
   */
  public Ticket(String movieName, String movieRating, int day, Format format, int movieTime, int id) 
  {
	  
    this.movieType = "Ticket";
    this.movieName = movieName;
    this.movieRating = movieRating;
    this.day = day;
    this.format = format;
    this.movieTime = movieTime;
    this.id = id;
    this.ticketPrice = -1;
    
  }

  public abstract double calculateTicketPrice();

  public abstract int getId();

  /**
   * @return movieName
   */
  public String getMovieName() 
  {
	  
    return movieName;
    
  }

  /**
   * @param movieName 
   */
  public void setMovieName(String movieName) 
  {
	  
    this.movieName = movieName;
    
  }

  /**
   * @return movie rating
   */
  public String getRating() 
  {
	  
    return movieRating;
    
  }

  /**
   * @param rating 
   */
  public void setRating(String rating) 
  {
	  
    this.movieRating = rating;
    
  }

  /**
   * @return the day
   */
  public int getDay() 
  {
	  
    return day;
    
  }

  /**
   * @param day 
   */
  public void setDay(int day) 
  {
	  
    this.day = day;
    
  }

  /**
   * @return format
   */
  public Format getFormat() 
  {
	  
    return format;
    
  }

  /**
   * @param format 
   */
  public void setFormat(Format format) 
  {
	  
    this.format = format;
    
  }

  /**
   * @return movie time
   */
  public int getTime() 
  {
	  
    return movieTime;
    
  }

  /**
   * @param time 
   */
  public void setTime(int time) 
  {
	  
    this.movieTime = time;
    
  }

  /**
   * @return ticket price
   */
  public double getPrice() 
  {
	  
    return ticketPrice;
    
  }

  /**
   * @param price 
   */
  public void setPrice(double price) 
  {
	  
    this.ticketPrice = price;
    
  }

  /**
   * @param id 
   */
  public void setId(int id) 
  {
	  
    this.id = id;
    
  }

  /**
   * @return movie type
   */
  public String getType() 
  {
	  
    return movieType;
    
  }

  /**
   * @param type
   */
  public void setType(String type) 
  {
	  
    this.movieType = type;
    
  }

  public String toString() 
  {
	  
    DecimalFormat df = new DecimalFormat("##0.00");

    String typeID = movieType.toUpperCase();

    if (getId() != -1) 
    {
    	
      typeID += "-" + id;
      
    }

    String view = "";
    
    if (format.equals(Format.THREE_D)) 
    {
    	
      view = "3D";
      
    } else if (format.equals(Format.IMAX)) 
    {
    	
      view = format.toString();
      
    }
    
    return typeID + " " + view + " Movie: " + movieName + " Rating: " + movieRating + " Day: " + day
        + " Time: " + movieTime + " Price: $" + df.format(ticketPrice);
  }

}//end Ticket class
