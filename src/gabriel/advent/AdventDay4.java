package gabriel.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class AdventDay4 {

	private static String fileContent;
	private static List<String> passportStrings;
	private static List<Passport> passports = new ArrayList<>();

	public static int solveProblem(String filePath) {
		fileContent = Util.getFileContent(filePath);
		return createPassportEntries();
	}

	private static int createPassportEntries() {
		int invalidPassports = 0;
		int nonBuiltPassports = 0;
		passportStrings = Arrays.asList(fileContent.replace(" ",", ").replaceAll("(\\S)(\n)(\\S)", "$1, $3").split("\n\n"));
		passportStrings.set(passportStrings.size() - 1, passportStrings.get(passportStrings.size() - 1).replaceAll("\n", ""));
		ObjectMapper objectMapper = new ObjectMapper();
		for (String unWrappedPassport : passportStrings) {
			StringBuilder wrappedPassportBuilder = new StringBuilder();
			wrappedPassportBuilder.append("{");
			wrappedPassportBuilder.append(unWrappedPassport);
			wrappedPassportBuilder.append("}");
			String wrappedPassport = wrappedPassportBuilder.toString().replaceAll("(?<=: ?)(?![ {\\[])(.+?)(?=,|})", "\"$1\"");

			try {
				objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				Passport passport = objectMapper.readValue(wrappedPassport, Passport.class);
				passports.add(passport);
				if (!passport.checkIfValid()) {
					invalidPassports++;
				}
			} catch (InvalidFormatException e) {
				invalidPassports++;
				nonBuiltPassports++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return passports.size() + nonBuiltPassports - invalidPassports;
	}

}
