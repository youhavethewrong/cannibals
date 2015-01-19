package info.youhavethewrong.cannibals;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;

public class ProblemTest {

	private River initialState;
	private River goalState;
	private Problem problem;

	@Before
	public void setUp() {
		RiverBank leftBank = new RiverBank(3, 3, true);
		RiverBank rightBank = new RiverBank(0, 0, false);
		initialState = new River(leftBank, rightBank);
		goalState = new River(rightBank, leftBank);
		problem = new Problem(initialState, goalState);
	}

	@Test
	public void shouldSomething() {
		RiverBank leftBank = new RiverBank(1, 1, true);
		RiverBank rightBank = new RiverBank(2, 2, false);
		River almostDone = new River(leftBank, rightBank);

		SearchNode node = new SearchNode(almostDone, null, null, 0, 0);
		List<SearchNode> searchList = problem.expand(node);
		assertEquals(2, searchList.size());
	}

	@Test
	public void shouldBeAbleToFindGoal() {
		River localState = new River(new RiverBank(0, 0, false), new RiverBank(3, 3, true));
		SearchNode node = new SearchNode(localState, null, null, 1, 1);
		assertEquals(true, problem.goalTest(node));
	}
}