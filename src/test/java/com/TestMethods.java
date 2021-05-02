package com;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

public class TestMethods {

	public static void main(String[] args) throws ParseException {
		String transactionDate = "02/05/2021";
		String datePattern = "((0[1-9]|1[0-2])\\/(0[1-9]|[12]\\d|3[01])\\/[12]\\d{3})";
		Pattern pattern = Pattern.compile(datePattern);

		Matcher matcher = pattern.matcher(transactionDate);
		boolean matches = matcher.matches();
		System.out.println(matches);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		formatter = formatter.withLocale(Locale.US);
		LocalDate date = LocalDate.parse(transactionDate, formatter);
		date = date.plusDays(30);
		System.out.println(date.format(formatter));
		
		String str = RandomStringUtils.randomAlphanumeric(16);
		System.out.println(str);
	}
}
