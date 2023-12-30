import java.io.Serializable;
import java.util.Objects;

//class date
public class Date implements Serializable {
	private int day,month,year;
	public Date (int day,int month,int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public int getMonth() {
		return month;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		return day == other.day && month == other.month && year == other.year;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
	
}
