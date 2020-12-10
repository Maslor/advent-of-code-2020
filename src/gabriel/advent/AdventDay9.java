package gabriel.advent;

import java.util.List;
import java.util.stream.Collectors;

public class AdventDay9 {

	private static final int PREAMBLE_LENGTH = 25;

	public static long solveProblem(String filePath) {
		boolean wasFound = false;
		long corruptedValue = 0;
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
				corruptedValue = numbers.get(i);
				System.out.println(corruptedValue);
				break;
			}
		}
		numbers.remove(corruptedValue);
		long[] encryptionWeakness = findContiguousInterval(corruptedValue, numbers);
		return encryptionWeakness[0] + encryptionWeakness[1];
	}

	private static long[] findContiguousInterval(long corruptedValue, List<Long> numbers) {

		int startPosition = 0;
		long contiguousSum;

		while (startPosition < numbers.size() - 1) {
			if (numbers.get(startPosition) < corruptedValue) {
				int endPosition = startPosition + 1;
				contiguousSum = numbers.get(startPosition) + numbers.get(endPosition);
				while (contiguousSum < corruptedValue) {
					endPosition++;
					contiguousSum += numbers.get(endPosition);
					if (contiguousSum == corruptedValue) {
						return new long[]{numbers.get(startPosition), numbers.get(endPosition)};
					}
				}
			}
			startPosition++;
		}
		return new long[]{0,0};
	}

	private static List<Long> getPreviousNumbers(int index, List<Long> numbers) {
		return numbers.subList(index - PREAMBLE_LENGTH, index);
	}
}
