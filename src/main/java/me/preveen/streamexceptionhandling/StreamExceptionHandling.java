/**
 * 
 */
package me.preveen.streamexceptionhandling;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author linkbee
 *
 */
public class StreamExceptionHandling {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	/**
	 * Converts given date string in format "MM/dd/yyyy" to LocalDate.
	 * Returns Optional of LocalDate if it is valid, otherwise Optional.empty
	 */
	private static Optional<LocalDate> parseDate(String dateString){
	    LocalDate localDate = null;
	    try {
	        localDate = LocalDate.from(formatter.parse(dateString));
	    }catch (DateTimeParseException e){
	        System.out.println(e.getMessage());
	    }
	    return Optional.ofNullable(localDate);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream.of("12/31/2014",
	            "01-01-2015",
	            "12/31/2015",
	            "not a date",
	            "01/01/2016")
	            .map(StreamExceptionHandling::parseDate)//Parse String to LocalDate
	            .filter(Optional::isPresent) //Filter valid ones
	            .map(Optional::get)//Get wrapped LocalDate
	            .map(DayOfWeek::from) //Map to day of week
	            .forEach(System.out::println); //Print
    }
}
