/**
 * 
 */

/**
 * @author AlinaKraft 
 *
 */
public class Activity {
	
	private User person;
	private String sportstype;
	private String date;
	private int durationinminutes;
	
	public Activity(User person, String sportstype, String date, int durationinminutes){
		this.person = person;
		this.sportstype = sportstype;
		this.date = date;
		this.durationinminutes = durationinminutes;
	}

	public User getPerson() {
		return person;
	}

	public void setPerson(User person) {
		this.person = person;
	}

	public String getSportstype() {
		return sportstype;
	}

	public void setSportstype(String sportstype) {
		this.sportstype = sportstype;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDurationinminutes() {
		return durationinminutes;
	}

	public void setDurationinminutes(int durationinminutes) {
		this.durationinminutes = durationinminutes;
	}
	
	
	public String toString(){
		return "User: " + person.getName() + ", Sport: " + sportstype + ", Date: " + date + ", Duration: " + durationinminutes;
	}
	
	
	

}
