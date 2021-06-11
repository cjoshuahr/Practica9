package interfaces.ito.poo;

public interface Arreglo<T> {

	public boolean addItem(T item);
	public boolean exiteItem();
	public T getItem(int pos);
	public int getSize();
	public boolean delete(T item);
	public boolean isFree();
	public boolean isFull();
}
