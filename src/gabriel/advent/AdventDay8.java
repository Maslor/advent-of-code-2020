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
		while (line < commands.size()) {
			if (!executedLines.add(line)) {
				break;
			} else {
				executedLines.add(line);
			}
			String[] arguments = commands.get(line).split(" ");
			int commandValue = Integer.parseInt(arguments[1]);
			switch (arguments[0]){
				case "nop":
					line++;
					break;
				case "acc":
					accumulator += commandValue;
					line++;
					break;
				case "jmp":
					line += commandValue;
					break;
			}
		}
		return accumulator;
	}
}
