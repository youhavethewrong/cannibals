package info.youhavethewrong.cannibals;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

public class PlanTest {

	@Test
	public void shouldMoveAcrossRiver() {
		RiverBank leftBank = new RiverBank(3, 3, true);
		RiverBank rightBank = new RiverBank(0, 0, false);
		River river = new River(leftBank, rightBank);

		RiverBank leftGoal = new RiverBank(0, 0, false);
		RiverBank rightGoal = new RiverBank(3, 3, true);
		River goalState = new River(leftGoal, rightGoal);

		CannibalPlanner planner = new CannibalPlanner();

		assertEquals(3, river.getLeftBank().getMissionaries().intValue());
		assertEquals(3, river.getLeftBank().getCannibals().intValue());
		assertEquals(0, river.getRightBank().getMissionaries().intValue());
		assertEquals(0, river.getRightBank().getCannibals().intValue());
		assertEquals(false, river.getRightBank().hasBoat());
		assertEquals(true, river.getLeftBank().hasBoat());

		SearchNode goalNode = planner.treeSearch(new Problem(river, goalState), new Strategy(new TreeSet<SearchNode>()));

		River goalRiver = goalNode.getState();

		assertEquals(0, goalRiver.getLeftBank().getMissionaries().intValue());
		assertEquals(0, goalRiver.getLeftBank().getCannibals().intValue());
		assertEquals(3, goalRiver.getRightBank().getMissionaries().intValue());
		assertEquals(3, goalRiver.getRightBank().getCannibals().intValue());
		assertEquals(true, goalRiver.getRightBank().hasBoat());
		assertEquals(false, goalRiver.getLeftBank().hasBoat());

		assertEquals(11, goalNode.getTreeDepth().intValue());
		planner.printSolution(goalNode);
	}
}
