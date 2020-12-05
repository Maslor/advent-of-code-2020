package gabriel.advent;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AdventDay5 {
	private static List<String> boardingPasses;
	private static TreeMap<Integer, String> seatIds = new TreeMap<>();

	public static long solveProblem(String filePath, int problemPart) {
		boardingPasses = Util.getFileLines(filePath);
		populateSeatIds();
		if(problemPart == 1) {
			return seatIds.lastKey();
		} else {
			return findMissingSeat();
		}
	}

	private static void populateSeatIds() {
		for (String boardingPass : boardingPasses) {
			Integer[] boardingPassPositions = getPositions(boardingPass);
			int row = boardingPassPositions[0];
			int column = boardingPassPositions[1];

			seatIds.put(row * 8 + column, boardingPass);
		}
	}

	private static Integer[] getPositions(String boardingPass) {
		String[] positions = boardingPass
				.replaceAll("([FB])()([RL])", "$1 $3")
				.split(" ");
		int row = Integer.parseInt(positions[0]
				.replace("F", "0")
				.replace("B", "1"), 2);
		int column = Integer.parseInt(positions[1]
				.replace("L", "0")
				.replace("R", "1"), 2);
		return new Integer[] {row, column};
	}

	private static Integer findMissingSeat() {
		Integer[] ids = seatIds.keySet().toArray(new Integer[0]);
		int firstSeat = ids[0];
		int lastSeat = ids[seatIds.size() - 1];
		AtomicInteger total =
				new AtomicInteger((lastSeat + firstSeat) * (lastSeat - firstSeat + 1) / 2); //summation
		seatIds.keySet().stream().forEach(n -> total.addAndGet(-n));

		return total.get();
	}
}
