package gabriel.advent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdventDay8 {

	public static int solveProblem(String filePath, int problemPart) {
		int accumulator = 0;
		List<String> commands = Util.getFileLines(filePath);
		Set<Integer> executedLines = new HashSet<>();

		int line = 0;

		boolean isMultiverse = false;
		int savedLine = 0;
		int savedAccumulator = 0;
		Set<Integer> savedLines = new HashSet<>();
		Set<Integer> savedExecutedLines = new HashSet<>();


		while (line < commands.size()) {
			if (!executedLines.add(line)) {
				if (problemPart == 2) {
					isMultiverse = false;
					accumulator = savedAccumulator;
					executedLines = savedExecutedLines;
					line = savedLine;
				} else {
					break;
				}
			} else {
				executedLines.add(line);
			}
			String[] arguments = commands.get(line).split(" ");
			int commandValue = Integer.parseInt(arguments[1]);
			switch (arguments[0]){
				case "nop":
					if (problemPart == 2 && !isMultiverse && savedLines.add(line)) {
						savedLine = line;
						savedExecutedLines = new HashSet<>(executedLines);
						savedAccumulator = accumulator;
						isMultiverse = true;
						line += commandValue;
					} else {
						line++;
					}
					break;
				case "acc":
					accumulator += commandValue;
					line++;
					break;
				case "jmp":
					if (problemPart == 2 && !isMultiverse && savedLines.add(line)) {
						savedLine = line;
						savedExecutedLines = new HashSet<>(executedLines);
						savedAccumulator = accumulator;
						isMultiverse = true;
						line++;
					} else {
						line += commandValue;
					}
					break;
			}
		}
		return accumulator;
	}
}
