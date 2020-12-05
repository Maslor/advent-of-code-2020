package gabriel.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdventDay4 {

	private static String fileContent;
	private static List<String> passportStrings;
	private static List<Passport> passports = new ArrayList<>();

	public static int solveProblem(String filePath) {
		fileContentToString(filePath);
		return createPassportEntries();
	}

	private static int createPassportEntries() {
		int invalidPassports = 0;
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return passports.size() - invalidPassports;
	}

	public static void fileContentToString(String filePath) {
		StringBuilder fileContentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(s -> fileContentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileContent = fileContentBuilder.toString();
	}

}
