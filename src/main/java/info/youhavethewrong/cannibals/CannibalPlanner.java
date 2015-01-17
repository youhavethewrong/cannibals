package info.youhavethewrong.cannibals;

import java.util.*;

public class CannibalPlanner {

	public SearchNode treeSearch(Problem problem, Strategy strategy) {
		List<SearchNode> fringe = problem.getInitialState();

		while (fringe != null && !fringe.isEmpty()) {
			SearchNode node = strategy.selectNode(fringe);
			if (problem.goalTest(node)) {
				return node;
			}
			strategy.addExplored(node);
			List<SearchNode> newf = problem.expand(node);
			fringe.addAll(newf);
		}
		return null;
	}

	public void printSolution(SearchNode solutionNode) {
		List<String> buffer = new LinkedList<String>();
		buffer.add(solutionNode.getState().toString());
		buffer.add(solutionNode.getAction().toString());

		SearchNode parent = solutionNode.getParentNode();
		while (parent != null) {
			buffer.add(parent.getState().toString());
			buffer.add((parent.getAction() == null) ? "" : parent.getAction().toString());
			parent = parent.getParentNode();
		}

		for (int i = buffer.size() - 1; i > 0; i--) {
			System.out.println(buffer.get(i));
		}
	}
}
