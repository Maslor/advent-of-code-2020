package gabriel.advent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sun.tools.javac.util.Pair;

import org.apache.commons.lang.StringUtils;

public class AdventDay2 {

	private static Map<String, PasswordValidation> passwordValidationMap = new HashMap<>();

	public static void solveProblem(String filePath, int problemPart) {
		populateMap(filePath);
		if (problemPart == 1) {
			System.out.println(howManyAreValidProblem1());
		} else {
			System.out.println(howManyAreValidProblem2());
		}
	}

	private static int howManyAreValidProblem1() {
		int numberOfValidPasswords = 0;
		for (String password : passwordValidationMap.keySet()) {
			PasswordValidation passwordValidation = passwordValidationMap.get(password);
			int characterOccurrences = StringUtils.countMatches(password, passwordValidation.getCharacter());
			if (characterOccurrences >= passwordValidation.getIntervalCount().fst && characterOccurrences <= passwordValidation.getIntervalCount().snd) {
				numberOfValidPasswords++;
			}
		}
		return numberOfValidPasswords;
	}

	private static int howManyAreValidProblem2() {
		int numberOfValidPasswords = 0;
		for (String password : passwordValidationMap.keySet()) {
			PasswordValidation passwordValidation = passwordValidationMap.get(password);
			String[] passwordCharacterList = password.split("");
			String firstCharacter = passwordCharacterList[passwordValidation.getIntervalCount().fst - 1];
			String secondCharacter = passwordCharacterList[passwordValidation.getIntervalCount().snd - 1];
			if (firstCharacter.equals(passwordValidation.getCharacter()) ^ secondCharacter.equals(passwordValidation.getCharacter())) {
				numberOfValidPasswords++;
			}
		}
		return numberOfValidPasswords;
	}

	public static void populateMap(String filePath) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filePath));

			while (scanner.hasNextLine()) {
				String[] lineArguments = scanner.nextLine().split(" ");
				String[] intervalIndexesStrings = lineArguments[0].split("-");
				Pair<Integer, Integer> interval = new Pair<Integer, Integer>(Integer.valueOf(intervalIndexesStrings[0]), Integer.valueOf(intervalIndexesStrings[1]));
				passwordValidationMap.put(lineArguments[2], new PasswordValidation(interval, lineArguments[1].substring(0,1)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
