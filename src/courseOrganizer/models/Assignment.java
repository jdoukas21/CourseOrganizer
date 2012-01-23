package courseOrganizer.models;


public class Assignment
{
	private String	title			= "Unknown";
	private String	description		= "Unknown";
	private String	dateAssigned	= "Unknown";
	private String	dateDue			= "Unknown";
	private String	dateFinished	= "(not finished)";
	private boolean	finished		= false;

	public Assignment()
	{
		
	}

	public Assignment(String title)
	{
		this.title = title;
	}

	public Assignment(String title, String description)
	{
		this.title = title;
		this.description = description;
	}

	public Assignment(String title, String description, String dateAssigned, String dateDue)
	{
		this.title = title;
		this.description = description;
		this.dateAssigned = dateAssigned;
		this.dateDue = dateDue;
	}

	public Assignment(String title, String description, String dateAssigned, String dateDue,
			String dateFinished, boolean finished)
	{
		this.title = title;
		this.description = description;
		this.dateAssigned = dateAssigned;
		this.dateDue = dateDue;
		this.dateFinished = dateFinished;
		this.finished = finished;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDateAssigned()
	{
		return dateAssigned;
	}

	public void setDateAssigned(String dateAssigned)
	{
		this.dateAssigned = dateAssigned;
	}

	public String getDateFinished()
	{
		return dateFinished;
	}

	public void setDateFinished(String dateFinished)
	{
		this.dateFinished = dateFinished;
	}

	public boolean isFinished()
	{
		return finished;
	}

	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	
	public void setFinished(String finished)
	{
		if (finished.equalsIgnoreCase("false"))
		{
			this.finished = false;
		}
		else if (finished.equalsIgnoreCase("true"))
		{
			this.finished = true;
		}
		else
		{
			System.out.println("<INTERNAL ERROR> Invalid finish type.");
		}
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public String getDateDue()
	{
		return dateDue;
	}

	public void setDateDue(String dateDue)
	{
		this.dateDue = dateDue;
	}

	@Override
	public String toString()
	{
		if (!finished)
		{
			return "Title: " + title + ". " + "\nDescription: " + description
					+ "\nDate Assigned: " + dateAssigned
					+ "\nDate Due: " + dateDue
					+ "\nFinished: " + finished;
		}
		else
		{
			return "Title: " + title + ". " + "\nDescription: " + description
					+ "\nDate Assigned: " + dateAssigned
					+ "\nDate Due: " + dateDue
					+ "\nFinished: " + finished + "\nDate Finished: "
					+ dateFinished;
		}

	}

}
