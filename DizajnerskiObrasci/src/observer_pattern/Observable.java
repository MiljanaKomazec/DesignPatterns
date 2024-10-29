package observer_pattern;

public interface Observable {

	void add(Observer o);
	void delete(Observer o);
	void notifyObservers();
	
}
