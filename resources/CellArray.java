
public class CellArray //implements Comparable
{
	private Cell tete;
	private Cell queue;
	private int size;
	
	public CellArray()
	{
		tete = null;
		queue = null;
		size = 0;
	}
	
	public void add(Cell c)
	{
		if (tete != null)
			tete.setPrev(c);
		tete = c;
			
		if (queue == null)
			queue = c;
		size++;
	}
	
	public Cell remove() throws NullPointerException, ArrayIndexOutOfBoundsException,ArithmeticException
	{
		Cell c = null;
		if (tete == queue)
			queue = null;
		if (tete != null)
		{
			c = tete;
			tete = tete.getNext();
		}
		size--;
		return c;
	}
	
	
	
	
}
