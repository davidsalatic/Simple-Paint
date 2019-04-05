package app.observer;

import java.util.ArrayList;
import java.util.List;


public class SelectedCount implements Subject{

	private int selectedCount;
	private List<Observer> observers;
	
	public SelectedCount()
	{
		observers = new ArrayList<Observer>();
	}
	

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
		
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(selectedCount);
		}
	}
	
	public int getSelectedCount() {
		return selectedCount;
	}

	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
		notifyObservers();
	}


}
