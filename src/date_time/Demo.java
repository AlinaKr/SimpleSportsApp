package date_time;
import java.util.Date;
import java.text.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Demo {

	public static void main (String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String datestring = null;
		try {
			datestring = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date current = new Date(System.currentTimeMillis());
		java.util.Date dDate = null;
		sdf.setLenient(false);
		try {
			dDate = sdf.parse(datestring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Wrong");
		}
		String strOutput = sdf.format(dDate);
		Date input = null;
		try {
			input = (Date)sdf.parse(datestring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (input.after(current)){
			System.out.println("you go da wrong date");
		}
		System.out.println(strOutput);
		
	}
}
