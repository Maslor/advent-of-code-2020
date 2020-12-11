package gabriel.advent;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdventDay10 {

	public static int solveProblem(String filePath) {
		int oneJolt = 0;
		int threeJolt = 0;

		List<Integer> joltAdapters = Util.getFileLines(filePath).stream().map(Integer::parseInt).collect(Collectors.toList());
		Collections.sort(joltAdapters);
		joltAdapters.add(0, 0);
		int maxAdapter = joltAdapters.get(joltAdapters.size() - 1) + 3;
		joltAdapters.add(joltAdapters.size(), maxAdapter);
		int i = 0;
		while (i < joltAdapters.size() - 1) {
			switch (joltAdapters.get(i+1) - joltAdapters.get(i)) {
				case 1:
					oneJolt++;
					break;
				case 2:
					break;
				case 3:
					threeJolt++;
					break;
			}
			i++;
		}
		return oneJolt * threeJolt;
	}
}
