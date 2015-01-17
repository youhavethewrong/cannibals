package info.youhavethewrong.cannibals;

import java.util.*;

public class Strategy {

	private Set<SearchNode> explored;

	public Strategy(Set<SearchNode> explored) {
		this.explored = explored;
	}

	public SearchNode selectNode(List<SearchNode> fringe) {
		SearchNode selected = null;
		for (SearchNode n : fringe) {
			if (!explored.contains(n)) {
				selected = n;
				break;
			}
		}

		if (selected != null) {
			fringe.remove(selected);
		}

		return selected;
	}

	public void addExplored(SearchNode node) {
		explored.add(node);
	}
}
