package info.youhavethewrong.cannibals;

import java.util.*;

public class CannibalPlanner {

	/**
	 * function treeSearch -> (problem, strategy)
	 * fringe = new searchNode(problem.initialState)
	 * loop
	 *   if empty fringe then return failure;
	 *   node = selectFrom(fringe, strategy)
	 *   if problem.goalTest(node.state) then
	 *     return pathTo(node)
	 *   fringe = fringe + expand(problem, node)
	 * 
	 */
	
	public SearchNode treeSearch(Problem problem, Strategy strategy) {
		List<SearchNode> fringe = problem.getInitialState();
		
		while(fringe != null && !fringe.isEmpty()) {
			SearchNode node = strategy.selectNode(fringe);
			if(problem.goalTest(node)) {
				return node;
			}
			fringe.addAll(problem.expand(node));
		}
		return null;
	}
}
