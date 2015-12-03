import java.util.Scanner;

/**
 * @author Ketul
 *
 */
public class DaysDifferenceCalculator {

	public static void main(String[] args) {
		// Scanner to take the input from console.
		Scanner scan = new Scanner(System.in);

		System.out.println("\n**********  Data for First Year  **********");
		System.out.print("Enter Day (DD) :");
		int d1Day = Integer.parseInt(scan.nextLine());
		System.out.print("Enter Month (MM) :");
		int d1Month = Integer.parseInt(scan.nextLine());
		System.out.print("Enter Year (YYYY) :");
		int d1Year = Integer.parseInt(scan.nextLine());

		System.out.println("\n**********  Data for Second Year  **********");
		System.out.print("Enter Day (DD) :");
		int d2Day = Integer.parseInt(scan.nextLine());
		System.out.print("Enter Month (MM) :");
		int d2Month = Integer.parseInt(scan.nextLine());
		System.out.print("Enter Year (YYYY) :");
		int d2Year = Integer.parseInt(scan.nextLine());

		int difference = 0;

		// call method that returns the total difference in days
		difference = getDaysInBetweenDates(d1Year, d1Month, d1Day, d2Year, d2Month, d2Day);

		System.out.println("\nTotal number of days between the dates are : " + difference + " day(s)");

	}

	/**
	 * @param d1Year int first date year
	 * @param d1Month int first date month
	 * @param d1Day int first date day
	 * @param d2Year int first date year
	 * @param d2Month int first date month
	 * @param d2Day int first date day
	 * @return int the total days between dates
	 */
	public static int getDaysInBetweenDates(int d1Year, int d1Month, int d1Day, int d2Year, int d2Month, int d2Day) {
		int differenceDays = 0;

		if (d1Year == d2Year) {
			//if the dates given fall within the same year.
			differenceDays = getDaysForThisYear(d1Day, d1Month, d1Year, d2Day, d2Month, isLeapYear(d1Year), false);

		} else {
			//if dates given dont fall within same year
			int tempYear = d1Year;

			while (tempYear < d2Year) {
				// if the temp year is the first date's year, then count days for the first year
				// else just add days for the full year considering leap year criteria
				if (tempYear == d1Year) {
					differenceDays += getDaysForThisYear(d1Day, d1Month, tempYear, 31, 12, isLeapYear(tempYear), false);
				} else {
					differenceDays += isLeapYear(tempYear) ? 366 : 365;
				}

				// set the temp date to 1st January of Next Year
				tempYear = tempYear + 1;
			}

			// count number of days from 1st Jan of d2Year to the d2Date of d2Year
			differenceDays += getDaysForThisYear(1, 1, d2Year, d2Day, d2Month, isLeapYear(d2Year), true);
		}

		return differenceDays;
	}

	/**
	 * @param startDay int count days from this date
	 * @param startMonth int count days from this month
	 * @param thisYear int count days for thisYear
	 * @param lastDay int day of the second date
	 * @param lastMonth int month of the second date 
	 * @param isLeapYear boolean to indicate leap year
	 * @param isLastYear boolean to see if the year is the same as second date year
	 * @return int days counted for this year
	 */
	public static int getDaysForThisYear(int startDay, int startMonth, int thisYear, int lastDay, int lastMonth,
			boolean isLeapYear, boolean isLastYear) {
		
		int daysThisYear = 0;
		
		if (startMonth == lastMonth) {
			//if the start and last month are same, simple and direct calculation applies
			daysThisYear = lastDay - startDay;
			
		} else {

			int tempMonth = startMonth;
			// loop to calculate days of every month
			while (tempMonth < lastMonth) {
				daysThisYear += getDaysOfMonth(tempMonth, isLeapYear);
				tempMonth += 1;
			}
			
			// if the method call is for calculating days of last year, then no need to substract -1.
			// if the dates are in the same year, then only substract -1.
			if (isLastYear) {
				daysThisYear += lastDay;
			} else {
				daysThisYear += lastDay - 1;
			}
		}

		return daysThisYear;
	}

	/**
	 * @param month int to get days of that month
	 * @param isLeapYear boolean to indicate leap year
	 * @return int days of the required month
	 */
	public static int getDaysOfMonth(int month, boolean isLeapYear) {
		if (month == 2) {
			if (isLeapYear)
				return 29;
			return 28;
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else {
			return 30;
		}
	}

	/**
	 * @param year int to check which year is a leap year
	 * @return boolean to indicate if the year is a leap year
	 */
	public static boolean isLeapYear(int year) {

		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}

		return false;
	}
}
