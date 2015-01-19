package info.youhavethewrong.cannibals;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class StrategyTest {

	@Test
	public void shouldPickTheOnlyNodeLeft() {
		River goalRiver = new River(new RiverBank(0, 0, false), new RiverBank(3, 3, true));
		SearchNode goalNode = new SearchNode(goalRiver, null, CannibalAction.C1M1R, 1, 1);

		River wrongRiver = new River(new RiverBank(0, 1, false), new RiverBank(3, 2, true));
		SearchNode fakeNode = new SearchNode(wrongRiver, null, CannibalAction.M1R, 1, 1);

		List<SearchNode> fringe = new LinkedList<SearchNode>();
		fringe.add(fakeNode);
		fringe.add(goalNode);

		Set<SearchNode> explored = new TreeSet<SearchNode>();
		explored.add(fakeNode);
		Strategy strat = new Strategy(explored);

		assertEquals(
				"SearchNode [state=River [leftBank=RiverBank [missionaries=0, cannibals=0, hasBoat=false], rightBank=RiverBank [missionaries=3, cannibals=3, hasBoat=true]], parentNode=null, action=C1M1R, pathCost=1, treeDepth=1]",
				strat.selectNode(fringe).toString());
	}
}