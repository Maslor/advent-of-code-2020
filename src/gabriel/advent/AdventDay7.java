package gabriel.advent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdventDay7 {
	private static Map<String, Map<String, Integer>> colours = new Hashtable<>();
	private static Set<String> initialColoursThatContainShinyGold = new LinkedHashSet<>();
	private static Set<String> allColoursThatContainShinyGold = new HashSet<>();

	public static int solveProblem(String filePath, int problemPart) {
		for (String line : Util.getFileLines(filePath)) {
			String[] arguments = line.split("bags contain");
			if (arguments[1].trim().equals("no other bags.")) {
				continue;
			}
			List<String> containedColoursWithNumbers = Arrays.asList(arguments[1].split("bags,|bag,|bags\\.|bag\\."));
			String parentColour = arguments[0].trim();
			Map<String, Integer> lineData = new HashMap<>();
			for (String containedString : containedColoursWithNumbers) {
				int number = Integer.parseInt(containedString.trim().split(" ")[0]);
				String colour = containedString.trim().split("[0-9] ")[1];
				lineData.put(colour, number);
				if (colour.equals("shiny gold")) {
					initialColoursThatContainShinyGold.add(parentColour);
				}
			}
			colours.put(parentColour, lineData);

		}

		if (problemPart == 1) {
			return findRootBagsThatContainShinyGold();
		} else {
			return findHowManyBagsFitInside();
		}
	}

	private static int findHowManyBagsFitInside() {
		throw new IllegalStateException("Not implemented yet");
	}

	public static int findRootBagsThatContainShinyGold() {
		allColoursThatContainShinyGold.addAll(initialColoursThatContainShinyGold);
		int i = 0;
		int size = initialColoursThatContainShinyGold.size();
		while (i < size) {
			String colour = (String) initialColoursThatContainShinyGold.toArray()[i];
			allColoursThatContainShinyGold.addAll(searchForColoursThatContainIt(colour));
			size = initialColoursThatContainShinyGold.size();
			i++;
		}

		return allColoursThatContainShinyGold.size();

	}

	private static Set<String> searchForColoursThatContainIt(String colour) {
		Set<String> newColoursThatContainShinyGold = new HashSet<>();
		for (String parentColour : colours.keySet()) {
			for (String babyColour : colours.get(parentColour).keySet()) {
				if (babyColour.equals(colour)) {
					newColoursThatContainShinyGold.add(parentColour);
					initialColoursThatContainShinyGold.add(parentColour);
				}
			}
		}
		return newColoursThatContainShinyGold;

	}
}
