package gabriel.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdventDay6 {

	private AdventDay6() {
		throw new IllegalStateException("Utility class");
	}

	public static int solveProblem(String filePath, int problemPart) {
		String[] groups	 = Util.getFileContent(filePath).split("\n\n");
		if (problemPart == 1) {
			return sumOfGroupCounts(groups, false);
		} else {
			return sumOfGroupCounts(groups, true);
		}
	}

	private static int sumOfGroupCounts(String[] groups, boolean onlyCommonAnswers) {
		int sumAllGroups = 0;
		for (String group : groups) {
			String[] personAnswersStrings = group.split("\n");
			Set<String> answeredYesSet = new HashSet<>();
			List<List<String>> answersList = new ArrayList<>();
			for (String personAnswers : personAnswersStrings) {
				if (onlyCommonAnswers) {
					answersList.add(Arrays.asList(personAnswers.split("")));
				} else {
					answeredYesSet.addAll(Arrays.asList(personAnswers.split("")));
				}
			}
			if (onlyCommonAnswers) {
				Iterator<List<String>> iterator = answersList.iterator();
				List<String> commonElements = new ArrayList<>(iterator.next());
				while (iterator.hasNext()) {
					commonElements.retainAll(iterator.next());
				}
				sumAllGroups += commonElements.size();
			} else {
				sumAllGroups += answeredYesSet.size();
			}
		}
		return sumAllGroups;
	}
}
