import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.*;

/**
 * 
 */

/**
 * @author AlinaKraft
 *
 *
 */
public class ActivityManager {
			
	private static HashMap <User, ArrayList<Activity>> activities = new HashMap<>();
	private static ArrayList <User> users = new ArrayList <>();
	private static User newuser = null;
	private static boolean appisrunning = true;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	
	public static void main(String [] args){
		while (appisrunning){
		///This block is repeated over and over again until user exits
		System.out.print("What do you want to do?" + "\n" + "(1) add a new user"
		+ "\n" + "(2) add a new activity" + "\n" + "(3) show activity statistics" 
		+ "\n" + "(4) exit" + "\n");
		
		String desiredtask = null;
			try { desiredtask = br.readLine();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		
			//checking which option the user chose and if it is executable
		switch(desiredtask){
		case "1":
			boolean correctnamegiven = false;
			//as long as the given name can not be registered we ask the user to input a new name
			while (!correctnamegiven){
				correctnamegiven = AddUser();
			}
			break;
		case "2":
			addActivity();
			break;
		case "3":
			showActivityStatistics();
			break;
		case "4":
			exitapp();
			break;
		default:
			System.out.println("Invalid. Please choose a number between 1 and 4(inclusive)" + "\n");
		}
		}
	}
	
	
	public static boolean AddUser(){
		System.out.println("You want to add a new user. Enter the desired name" + "\n");
		try {
		String desiredname = br.readLine();
		return AddUser(desiredname);
		}
		catch (IOException e){
		e.printStackTrace();
		return false;
		}	
	}
	
	
	public static boolean AddUser(String newname){
		///If we already have some users, we check that the given name is unique, duplicates are not allowed
		if (users != null){
			for (User u : users){
				if (u.getName().equals(newname)){
					System.out.println("Name already taken. Please choose another one" + "\n");
					return false;
				}
			}
		}
		//in case there are no users yet or the name is not taken, we simply create a new user and add it to users
		newuser = new User(newname);
		users.add(newuser);
		return true;
	}
	
	
	public static boolean checkDateFormat(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		
		java.util.Date dDate = sdf.parse(date);
		String strOutput = sdf.format(dDate);
		Date input = (Date)sdf.parse(date);
		
		Date current = new Date(System.currentTimeMillis());
	    ///Date d1 = Date.from(current.minusYears(10).atStartOfDay(ZoneId.systemDefault()).toInstant());

		if (input.after(current)){
			throw new ParseException("", 0);
		}
		return true;
	}
	
	
	public static boolean checkDuration(String duration){
		try{
			if (Integer.parseInt(duration) <= 0){
				System.out.println("Invalid duration. Please enter a positive, non decimal number!" + "\n");
				return false;
			}
			return true;
			}
		
		catch (NumberFormatException e){
			System.out.println("Invalid duration. Please enter a positive, non decimal number!" + "\n");
			return false;
		}
	}
	
	
	public static boolean addActivity() {
		if (newuser == null){
			System.out.println("You cannot add a new activity if you are not registered as a user yet" + "\n");
			return false;
		}
	
		System.out.println("You want to add a new Activity. Type type of sport here:" + "\n");
		String sporttype = "";
		try{
			sporttype = br.readLine();
		}
		catch (IOException e){
				e.printStackTrace();
		}
		
		boolean correctdate = false;
		String date = null;
		while (!correctdate){
			System.out.println("Give the date in format [dd-mm-yyyy]" + "\n");
			try{
				date = br.readLine();
				correctdate = checkDateFormat(date);
			}
			catch(IOException | ParseException e){
				System.out.println("Inacceptable input. Date must actually exist and not be in the future");
				//correctdate = false;
			}
		}
		
		boolean correctduration = false;
		String duration = null;
		while (!correctduration){
			System.out.println("Now give the duration" + "\n");
			try{
			duration = br.readLine();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			correctduration = checkDuration(duration);
		}
		
		addActivity(newuser, sporttype, date, Integer.parseInt(duration));
		return true;	
	}
	
	
	public static void addActivity(User person, String sportstype, String date, int durationinminutes){
		int durationofuser = durationinminutes;
		if (activities != null) {
			if (activities.containsKey(newuser)){
				for (Activity a : activities.get(newuser)){
					if (a.getSportstype().equals(sportstype) && a.getDate().equals(date)){
						durationofuser += a.getDurationinminutes();
						a.setDurationinminutes(durationofuser);
						return;
					}
				}
				
				ArrayList <Activity> activitiesofthatperson = activities.get(newuser);
				activitiesofthatperson.add(new Activity(newuser,sportstype, date, durationinminutes));
				activities.put(newuser, activitiesofthatperson); //muss das sein o reicht das oben? nicht das wir das dann doppelt haben
				return;
			}
		
			Activity activity = new Activity (newuser, sportstype, date, durationinminutes);
			ArrayList <Activity> activitiesofthatperson = new ArrayList <Activity>();
			activitiesofthatperson.add(activity);
			activities.put(newuser, activitiesofthatperson);
		}
	}
	
	
	public static boolean showActivityStatistics(){
		if (newuser == null || !(activities.containsKey(newuser))){
			System.out.println("Statistics can only be shown when User and (at least one) corresponding activities were added");
			return false;
		}
		ArrayList <Activity> allactivities = activities.get(newuser);
		for (Activity a : allactivities){
			System.out.println(a.toString());
		}
		return true;
	}
	
	
	public static void exitapp(){
		newuser = null;
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		appisrunning = false;
		System.out.println("Exit sucessful. Until next time!");
	}
	

}
