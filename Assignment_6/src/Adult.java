/**
 * Author: Dylan De Leon 
 * CMSC 203
 */

public class Adult extends Ticket 
{
	
	//variables
	private final double earlyPrice = 10.5;
	private final double latePrice = 13.5;
	private final double imaxPrice = 3;
	private final double threeDPrice = 2.5;

	public Adult() 
	{

		super.movieType = "Adult";

	}

	/**
	 * @param movieName
	 * @param rating
	 * @param day
	 * @param format
	 * @param time
	 * @param id
	 */
	public Adult(String movieName, String movieRating, int day, Format format, int movieTime, int id) 
	{

		super(movieName, movieRating, day, format, movieTime, id);

		super.movieType = "Adult";

	}

	/**
	 * @return price of ticket
	 */
	public double calculateTicketPrice() 
	{

		double ticketPrice = (movieTime < lateTime) ? earlyPrice : latePrice;

		if(format.equals(Format.IMAX)) 
		{

			ticketPrice += imaxPrice;

		}else if(format.equals(Format.THREE_D)) 
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

}// end Adult class
