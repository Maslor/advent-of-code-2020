package gabriel.advent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdventDay3 {

	private static List<List<String>> mapPositions = new ArrayList<>();
	private static int xPosition;
	private static int yPosition;

	public static long solveProblem(String filepath) {
		return (long) findNumberOfTrees(filepath, 3, 1) *
				findNumberOfTrees(filepath, 1, 1) *
				findNumberOfTrees(filepath, 7,1) *
				findNumberOfTrees(filepath, 5, 1) *
				findNumberOfTrees(filepath, 1, 2);
	}

	private static int findNumberOfTrees(String filepath, int movementX, int movementY) {
		populateMap(filepath, movementX);
		int numberOfTrees = 0;
		while (yPosition <= mapPositions.size() - 1) {
			if (xPosition <= mapPositions.get(yPosition).size() - 1 &&
					mapPositions.get(yPosition).get(xPosition).equals("#")) {
				numberOfTrees++;
			}
			yPosition += movementY;
			xPosition += movementX;
		}
		resetVariables();
		return numberOfTrees;
	}

	private static void resetVariables() {
		mapPositions.clear();
		xPosition = 0;
		yPosition = 0;
	}

	public static void populateMap(String filePath, int xMovement) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filePath));

			while (scanner.hasNextLine()) {
				List<String> line = new ArrayList<>(Arrays.asList(scanner.nextLine().split("")));
				mapPositions.add(line);
			}

			for (int i = 0; i < mapPositions.size(); i++) {
				int listSize = mapPositions.get(i).size();
				int necessarySize = xMovement * i + 1;
				if (necessarySize > listSize) {
					int timesToRepeat = necessarySize % listSize == 0 ? necessarySize / listSize : necessarySize / listSize + 1;
					List<String> expandedList = new ArrayList<>();
					for(int j = 0; j < timesToRepeat; j++) {
						expandedList.addAll(mapPositions.get(i));
					}
					mapPositions.set(i, expandedList);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
