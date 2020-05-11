
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Collections;

/**
 * Author: Dylan De Leon 
 * CMSC 203 
 * Manages XYZ Theater ticket system
 */
public class MovieTicketManager implements MovieTicketManagerInterface 
{

	//variables
	private ArrayList<Ticket> ticketList;
	private DecimalFormat df = new DecimalFormat("##0.00");

	public MovieTicketManager() 
	{
		
		this.ticketList = new ArrayList<>();
		
	}

	/**
	 * @param id
	 */
	public int numVisits(int id) 
	{
		
		return (int) ticketList.stream().filter(t -> t.getId() == id).count();
		
	}

	/**
	 * @param id
	 * @param movie
	 */
	public int numThisMovie(int id, String movie) 
	{
		
		return (int) ticketList.stream().filter(t -> t.getId() == id && t.getMovieName().equals(movie)).count();
		
	}

	/**
	 * @param id
	 * @param date
	 */
	public int numMoviesToday(int id, int date) 
	{
		
		return (int) ticketList.stream().filter(t -> t.getId() == id && t.getDay() == date).count();
		
	}

	/**
	 * @param movieName
	 * @param movieRating
	 * @param day
	 * @param movieTime
	 * @param format 
	 * @param type
	 * @param id
	 */
	public double addTicket(String movieName, String movieRating, int day, int movieTime, String format, String type, int id) 
	{

		switch (type) {
		case "Adult":
			Adult ticketAdult = new Adult(movieName, movieRating, day, toFormat(format), movieTime, id);
			ticketAdult.setPrice(ticketAdult.calculateTicketPrice());
			ticketList.add(ticketAdult);
			return ticketAdult.calculateTicketPrice();
			
		case "Child":
			Child ticketChild = new Child(movieName, movieRating, day, toFormat(format), movieTime, id);
			ticketChild.setPrice(ticketChild.calculateTicketPrice());
			ticketList.add(ticketChild);
			return ticketChild.calculateTicketPrice();
			
		case "Employee":
			Employee ticketEmployee = new Employee(movieName, movieRating, day, toFormat(format), movieTime, id);
			if (numVisits(id) < 2) 
			{
				ticketEmployee.setPrice(0);
				ticketList.add(ticketEmployee);
				return 0;
			} else 
			{
				ticketEmployee.setPrice(ticketEmployee.calculateTicketPrice());
				ticketList.add(ticketEmployee);
				return ticketEmployee.calculateTicketPrice();
			}
			
		case "MoviePass":
			MoviePass ticketMoviePass = new MoviePass(movieName, movieRating, day, toFormat(format), movieTime, id);
			if (numMoviesToday(id, day) == 0 && numThisMovie(id, movieName) == 0
					&& ticketMoviePass.getFormat() == Format.NONE) 
			{
				if (numVisits(id) == 0) 
				{
					ticketMoviePass.setPrice(9.99);
					ticketList.add(ticketMoviePass);
					return 9.99;
				}
				ticketMoviePass.setPrice(0);
				ticketList.add(ticketMoviePass);
				return 0;
			} else 
			{
				ticketMoviePass.setPrice(ticketMoviePass.calculateTicketPrice());
				ticketList.add(ticketMoviePass);
				return ticketMoviePass.calculateTicketPrice();
			}
			
		}
		
		return -1;
		
	}

	/**
	 * @param string
	 * @return Format value 
	 */
	public static Format toFormat(String string) 
	{
		if (string.equals("3D")) 
		{
			
			return Format.THREE_D;
			
		}
		
		return Enum.valueOf(Format.class, string);

	}

	public double totalSalesMonth() 
	{
		
		return ticketList.stream().mapToDouble(Ticket::getPrice).sum();
		
	}

	//monthly sales report for tickets
	public String monthlySalesReport() 
	{

		int adultTickets = (int) ticketList.stream().filter(t -> t.getType().equals("Adult")).count();
		int childTickets = (int) ticketList.stream().filter(t -> t.getType().equals("Child")).count();
		int employeeTickets = (int) ticketList.stream().filter(t -> t.getType().equals("Employee")).count();
		int moviePassTickets = (int) ticketList.stream().filter(t -> t.getType().equals("MoviePass")).count();

		double adultSales = ticketList.stream().filter(t -> t.getType().equals("Adult")).mapToDouble(Ticket::getPrice).sum();

		double childSales = ticketList.stream().filter(t -> t.getType().equals("Child")).mapToDouble(Ticket::getPrice).sum();

		double employeeSales = ticketList.stream().filter(t -> t.getType().equals("Employee")).mapToDouble(Ticket::getPrice).sum();
		
		double moviePassSales = ticketList.stream().filter(t -> t.getType().equals("MoviePass")).mapToDouble(Ticket::getPrice).sum();

		double totalSales = adultSales + childSales + employeeSales + moviePassSales;

		String title = "\tMonthly Sales Report\n\n";
		String columns = "\t\t\tSales\tNumber";
		String adultRow = "\nADULT\t\t$" + df.format(adultSales) + "\t\t" + adultTickets;
		String childRow = "\nCHILD\t\t$" + df.format(childSales) + "\t\t" + childTickets;
		String employeeRow = "\nEMPLOYEE\t$" + df.format(employeeSales) + "\t\t" + employeeTickets;
		String moviePassRow = "\nMOVIEPASS\t$" + df.format(moviePassSales) + "\t\t" + moviePassTickets;
		String totalString = "\n\nTotal Monthly Sales $" + df.format(totalSales) + "\n";
		
		return title + columns + adultRow + childRow + employeeRow + moviePassRow + totalString;
		
	}

	public ArrayList<String> get3DTickets() 
	{
		
		ArrayList<Ticket> threeDList = new ArrayList<>();
		
		for (Ticket t : ticketList) 
		{
			
			if (t.getFormat() == Format.THREE_D)
				threeDList.add(t);
			
		}
		Collections.sort(threeDList, sortByDay());
		
		return ticketToString(threeDList);
		
	}

	public ArrayList<String> getAllTickets() 
	{
		
		ArrayList<Ticket> fullList = new ArrayList<>();
		
		for (Ticket t : ticketList) 
		{
			
			fullList.add(t);
			
		}
		
		Collections.sort(fullList, sortByDay());
		
		return ticketToString(fullList);
		
	}

	public ArrayList<String> getMoviePassTickets() 
	{

		ArrayList<Ticket> moviePassList = new ArrayList<>();
		
		for (Ticket t : ticketList) 
		{
			
			if (t.getType().equals("MoviePass"))
				moviePassList.add(t);
			
		}
		Collections.sort(moviePassList, sortById());
		
		return ticketToString(moviePassList);
		
	}

	public void readFile(File file) throws FileNotFoundException 
	{
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) 
		{
			
			String[] ticket = sc.nextLine().split(":");
			addTicket(ticket[0], ticket[1], Integer.parseInt(ticket[2]), Integer.parseInt(ticket[3]), ticket[4], ticket[5], Integer.parseInt(ticket[6]));
			
		}
		
		sc.close();
		
	}

	public String toString() 
	{
		
		return "MovieTicketManager [ticketList=" + ticketList + "]";
		
	}

	/**
	 * @param list 
	 * @return list of String elements
	 */
	private ArrayList<String> ticketToString(ArrayList<Ticket> list) 
	{
		
		return list.stream().map(Ticket::toString).collect(Collectors.toCollection(ArrayList::new));
		
	}

	/**
	 * @return ticketList
	 */
	public ArrayList<Ticket> getTicketList() 
	{
		
		return ticketList;
		
	}

	/**
	 * @return Comparator by id
	 */
	private Comparator<Ticket> sortById() 
	{
		
		return new Comparator<Ticket>() 
		{
			
			public int compare(Ticket t1, Ticket t2) 
			{
				
				return (t1.getId() - t2.getId());
				
			}
		};
	};

	/**
	 * @return Comparator by day
	 */
	private Comparator<Ticket> sortByDay() 
	{
		
		return new Comparator<Ticket>() 
		{
			
			public int compare(Ticket t1, Ticket t2) 
			{
				
				return (t1.getDay() - t2.getDay());
				
			}
		};
	}
	
}//end MovieTicketManager class
