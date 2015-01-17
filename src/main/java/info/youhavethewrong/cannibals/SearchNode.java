package info.youhavethewrong.cannibals;

import java.lang.Comparable;

public class SearchNode implements Comparable<SearchNode> {

	private River state;
	private SearchNode parentNode;
	private CannibalAction action;
	private Integer pathCost;
	private Integer treeDepth;

	public SearchNode(River state, SearchNode parentNode, CannibalAction action, Integer pathCost, Integer treeDepth) {
		this.state = state;
		this.parentNode = parentNode;
		this.action = action;
		this.pathCost = pathCost;
		this.treeDepth = treeDepth;
	}

	public River getState() {
		return state;
	}

	public SearchNode getParentNode() {
		return parentNode;
	}

	public CannibalAction getAction() {
		return action;
	}

	public Integer getPathCost() {
		return pathCost;
	}

	public Integer getTreeDepth() {
		return treeDepth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((parentNode == null) ? 0 : parentNode.hashCode());
		result = prime * result + ((pathCost == null) ? 0 : pathCost.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((treeDepth == null) ? 0 : treeDepth.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchNode other = (SearchNode) obj;

		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "SearchNode [state=" + state + ", parentNode=" + parentNode + ", action=" + action + ", pathCost=" + pathCost
				+ ", treeDepth=" + treeDepth + "]";
	}

    @Override
    public int compareTo(SearchNode node) {
        if(node == null) {
            throw new NullPointerException();
        }
        if(this.equals(node)) {
            return 0;
        }
        if(this.getPathCost() > node.getPathCost()) {
            return 1;
        }
        return -1;
    }
}
