import java.util.Scanner;

public class codess {
	
	public static int getTime() throws Exception {
		Scanner input = new Scanner(System.in);
		int time;
	    String timeStr = input.nextLine();
	    String period = "AM";
	    int spaceIndex = timeStr.indexOf(" ");
	    
	    if (spaceIndex != -1) {
	      period = timeStr.substring(spaceIndex + 1);
	      timeStr = timeStr.substring(0, spaceIndex);
	    }
	    try {
	      time = Integer.parseInt(timeStr);
	    } catch (NumberFormatException e) {
	      int colonIndex = timeStr.indexOf(":");
	      if (colonIndex != -1) {
	        int hours = Integer.parseInt(timeStr.substring(0, colonIndex));
	        int minutes = Integer.parseInt(timeStr.substring(colonIndex + 1));
	        time = hours * 60 + minutes;
	      } else {
	        throw new Exception("Invalid input format");
	      }
	    }
	    if (period.equalsIgnoreCase("PM")) {
	      time += 720;
	    }
	    if (time >= 720) {
	      time -= 720;
	    }
	    return time;
	  }
	
	
	public static int calculaterenderedhrs(int timeIn, int timeOut) {
	    int totalHours;
	    
	    if (timeOut >= timeIn) {
	      totalHours = (timeOut - timeIn) / 60;
	    } else {
	      totalHours = (timeOut + 720 - timeIn) / 60;
	    }
	    return totalHours;
	  }

	
	public static String timeformat(int time) {
		int hours;
		int minutes;
		
	    hours = time / 60;
	    minutes = time % 60;
	    String period = "AM";
	    
	    if (hours >= 12) {
	      period = "PM";
	      if (hours > 12) {
	        hours -= 12;
	      }
	    }
	    if (hours == 0) {
	      hours = 12;
	    }
	    String minutesStr = String.valueOf(minutes);
	    if (minutes < 10) {
	      minutesStr = "0" + minutesStr;
	    }
	    return hours + ":" + minutesStr + " " + period;
	  }

	
  public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int numOfEmployees;
	boolean isValid;
	
    System.out.print("Enter number of employees: ");
    numOfEmployees = input.nextInt();
    input.nextLine(); 

    String[] names = new String[numOfEmployees];
    int[][] time = new int[numOfEmployees][2]; 

    
    for (int i = 0; i < numOfEmployees; i++) {
      System.out.print("Enter employee name: ");
      names[i] = input.nextLine();

      
      isValid = false;
      while (!isValid) {
        System.out.println("Enter time in for " + names[i] + " (HH:mm) AM/PM: ");
        try {
          time[i][0] = getTime();
          isValid = true;
        } catch (Exception e) {
          System.out.println("Invalid input. Please try again.");
        }
      }
      isValid = false;
      while (!isValid) {
        System.out.println("Enter time out for " + names[i] + " (HH:mm) AM/PM: ");
        try {
          time[i][1] = getTime();
          isValid = true;
        } catch (Exception e) {
          System.out.println("Invalid input. Please try again.");
        }
      }
    }
    
    
    System.out.println(" ");
    System.out.println("===========================================================================");
    System.out.println("|Employee|\t|Time In|\t  |Time Out|\t |Total Hours|");
    for (int i = 0; i < numOfEmployees; i++) {
      int totalHours = calculaterenderedhrs(time[i][0], time[i][1]);
      System.out.println(names[i] + "\t\t" + timeformat(time[i][0]) + "\t\t  " + timeformat(time[i][1]) + "\t\t" + totalHours);
      System.out.println("===========================================================================");
    }
  }
}