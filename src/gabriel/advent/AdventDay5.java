package gabriel.advent;

import java.util.List;
import java.util.TreeMap;

public class AdventDay5 {
	private static List<String> boardingPasses;
	private static TreeMap<Long, String> seatIds = new TreeMap<>();

	public static long solveProblem(String filePath) {
		boardingPasses = Utils.getFileLines(filePath);
		populateSeatIds();
		return seatIds.lastKey();
	}

	private static void populateSeatIds() {
		for (String boardingPass : boardingPasses) {
			Integer[] boardingPassPositions = getPositions(boardingPass);
			int row = boardingPassPositions[0];
			int column = boardingPassPositions[1];

			seatIds.put(new Long(row * 8 + column), boardingPass);
		}
	}

	private static Integer[] getPositions(String boardingPass) {
		String[] positions = boardingPass.replaceAll("([FB])()([RL])", "$1 $3").split(" ");
		int row = Integer.parseInt(positions[0].replace("F", "0").replace("B", "1"), 2);
		int column = Integer.parseInt(positions[1].replace("R", "1").replace("L", "0"), 2);
		return new Integer[] {row, column};
	}
}
