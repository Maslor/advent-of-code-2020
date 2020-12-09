package gabriel.advent;

import java.util.List;
import java.util.stream.Collectors;

public class AdventDay9 {

	private static final int PREAMBLE_LENGTH = 25;

	public static long solveProblem(String filePath, int problemPart) {
		boolean wasFound = false;
		List<Long> numbers = Util.getFileLines(filePath).stream().map(Long::parseLong).collect(Collectors.toList());

		for (int i = PREAMBLE_LENGTH; i < numbers.size(); i++) {
			List<Long> preamble = getPreviousNumbers(i, numbers);

			for (int j = 0; j < preamble.size(); j++) {
				long attempt = numbers.get(i) - preamble.get(j);
				if (numbers.indexOf(attempt) != -1) {
					wasFound = true;
					break;
				} else {
					wasFound = false;
				}
			}
			if (!wasFound) {
				return numbers.get(i);
			}
		}
		return -1;
	}

	private static List<Long> getPreviousNumbers(int index, List<Long> numbers) {
		return numbers.subList(index - PREAMBLE_LENGTH, index);
	}
}
