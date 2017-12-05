package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable<T> implements Iterable <T>{

protected List<T> observerList; 
	
	public Observable(){
		this.observerList = new ArrayList<T>();
	}
	
	/**
	 * Añade un observador a la lista de observadores
	 * @param observer
	 */
	public void addObserver(T observer){
		if(!observerList.contains(observer))
			observerList.add(observer);
	}
	
	/**
	 * Borra un observador de la lista de observadores
	 * @param observer
	 */
	public void removeObserver(T observer){
		if(observerList.contains(observer))
			observerList.remove(observer);
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		Iterator<T> iterator = this.observerList.iterator();
		return iterator;
	}
}
