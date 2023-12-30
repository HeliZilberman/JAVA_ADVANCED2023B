
import java.util.ArrayList;
import java.util.Collections;

/**
 * class contains the data for graph
 */
public class Data {
	private final int YEARS = 5;
	private final int MONTHS = 12;
	private final double MAX_CELSIUS  = 100;
	private int curr_year_index;
	private final int START = 2017;
	private ArrayList<Double>[] y_data; 
	
	//initialize the temperature data and the curr year index the graph show
	public Data() 
	{
		y_data = new ArrayList[YEARS];
		curr_year_index = 0;
		for (int i = 0; i < YEARS; i++) {
			y_data[i] = new ArrayList<Double>();
			for(int j = 0; j < MONTHS; j++) {
				y_data[i].add(Math.random()*(MAX_DAG+1));
			}
		}
	}
	
	//returns year for graph label
	public int getCurrYear() {
		return (curr_year_index + START);
	}
	
	//returns temperature of requested month in curr year
	public double GetMonthData(int month) {
		return y_data[curr_year_index].get(month-1);
	}
	
	//returns the month with the biggest temperature
	public int maxMonth() {
		double maxVal = Collections.max(y_data[curr_year_index]); 
		return (y_data[curr_year_index].indexOf(maxVal) + 1);
	}
	
	//returns the month with smallest temperature
	public int minMonth() {
		double minVal = Collections.min(y_data[curr_year_index]); 
		return (y_data[curr_year_index].indexOf(minVal) + 1);
	}
	
	//moves years index to next year's data
	public void nextYear() {
		curr_year_index = (curr_year_index +1) % YEARS;	
	}
	
	//returns the max celsius in graph (100)
	public final double getMaxCelsius() {
		return MAX_CELSIUS;
	}
	
	//returns num of monthes in year (12)
	public final int getMonths() {
		return MONTHS;
	}
}
