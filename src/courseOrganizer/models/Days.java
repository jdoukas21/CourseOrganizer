package courseOrganizer.models;

import java.util.ArrayList;

public class Days
{
	private String		dayOne;
	private String		dayTwo;
	private ArrayList<String>	days			= new ArrayList<String>();

	private String[]	acceptableDays	= { "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday", "Sunday" };
	
	public static final String MONDAY = "Monday";
	public static final String TUESDAY = "Tuesday";
	public static final String WEDNESDAY = "Wednesday";
	public static final String THURSDAY = "Thursday";
	public static final String FRIDAY = "Friday";
	public static final String SATURDAY = "Saturday";
	public static final String SUNDAY = "Sunday";
	

	public Days(boolean everyDay)
	{
		if (everyDay)
		{
			days.add("Monday");
			days.add("Tuesday");
			days.add("Wednesday");
			days.add("Thursday");
			days.add("Friday");
			days.add("Saturday");
			days.add("Sunday");
		}
	}

	public Days(String dayOne)
	{
		try
		{
			checkValidity(dayOne);
			this.dayOne = dayOne;
		}
		catch (InvalidDayException e)
		{
			System.exit(1);
		}
	}

	public Days(String dayOne, String dayTwo)
	{
		try
		{
			checkValidity(dayOne);
			checkValidity(dayTwo);
			this.dayOne = dayOne;
			this.dayTwo = dayTwo;
		}
		catch (InvalidDayException e)
		{
			System.exit(1);
		}
	}

	public Days(ArrayList<String> days)
	{
		try
		{
			checkAListValidity(days);
			this.days = days;
		}
		catch (InvalidDayException e)
		{
			System.exit(1);
		}
	}

	public void addDay(String day)
	{
		try
		{
			checkValidity(day);
			checkIfDuplicate(day);
			days.add(day);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void checkIfDuplicate(String day) throws DuplicateDayException
	{
		for (int n = 0; n < days.size(); n++)
		{
			if (day.equals(days.get(n)))
			{
				throw new DuplicateDayException();
			}
		}
	}
	
	private void checkValidity(String dayIn) throws InvalidDayException
	{
		for (int n = 0; n < acceptableDays.length; n++)
		{
			if (dayIn.equals(acceptableDays[n]))
			{
				return;
			}
		}

		throw new InvalidDayException();
	}

	private void checkAListValidity(ArrayList<String> daysIn) throws InvalidDayException
	{
		for (int n = 0; n < daysIn.size(); n++)
		{
			checkValidity(daysIn.get(n));
		}
	}
	
	@Override
	public String toString()
	{
		int dayCount = days.size();
		String str = "";
		
		if (dayCount == 0)
		{
			str = "(none)";
			return str;
			
		}
		else if (dayCount == 1)
		{
			str = days.get(0);
			return str; 
		}
		else if (dayCount == 2)//if two days, make it look like this: Mon/Wed or Tue/Thu
		{
			str = days.get(0).substring(0,3) + "/" + days.get(1).substring(0, 3);
			return str;
			//if not two days, make it look like this: Mon, Tue, Wed, Thu
		}
		else if (dayCount == 3)
		{
			str = days.get(0).substring(0, 3) + "/" + days.get(1).substring(0, 3) + "/" + days.get(2).substring(0, 3);
			return str;
		}
		else if (dayCount > 3)
		{
			str = days.get(0) + "-" + days.get(days.size() - 1);
			return str;
		}
		
		return str;
	}
	
	private class InvalidDayException extends Exception
	{
		public InvalidDayException()
		{
			System.err.print("Invalid day type. Valid types: ");
			System.err.println("Monday, Tuesday, Wednesday, Thursday, "
					+ "\nFriday, Saturday, Sunday");
		}
	}
	
	private class DuplicateDayException extends Exception
	{
		public DuplicateDayException()
		{
			System.err.print("Duplicate day.");
			System.err.println("Don't add the same day twice!");
		}
	}
}
