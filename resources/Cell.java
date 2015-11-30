public class Cell
{
	private Cell next;
	private Cell prev;
	private Object contain;

	public Cell(Object o)
	{
		next = null;
		prev = null;
		contain = o;
	}

	public Cell getNext()
	{
		return next;
	}

	public void setNext(Cell next)
	{
		this.next = next;
	}

	public Cell getPrev()
	{
		return prev;
	}

	public void setPrev(Cell prev)
	{
		this.prev = prev;
	}

	public Object getContain()
	{
		return contain;
	}

}
