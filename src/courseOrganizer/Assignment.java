package courseOrganizer;

public class Assignment
{
	private String	title;
	private String	description;
	private int		dateAssigned;
	private int		dateFinished;
	private boolean	finished	= false;	

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

	public Assignment(String title, String description, int dateAssigned)
	{
		this.title = title;
		this.description = description;
		this.dateAssigned = dateAssigned;
	}

	public Assignment(String title, String description, int dateAssigned,
			int dateFinished, boolean finished)
	{
		this.title = title;
		this.description = description;
		this.dateAssigned = dateAssigned;
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

	public int getDateAssigned()
	{
		return dateAssigned;
	}

	public void setDateAssigned(int dateAssigned)
	{
		this.dateAssigned = dateAssigned;
	}

	public int getDateFinished()
	{
		return dateFinished;
	}

	public void setDateFinished(int dateFinished)
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

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}

}
