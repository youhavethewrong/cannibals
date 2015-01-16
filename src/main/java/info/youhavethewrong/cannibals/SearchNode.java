package info.youhavethewrong.cannibals;

public class SearchNode {

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
		if (action != other.action)
			return false;
		if (parentNode == null) {
			if (other.parentNode != null)
				return false;
		} else if (!parentNode.equals(other.parentNode))
			return false;
		if (pathCost == null) {
			if (other.pathCost != null)
				return false;
		} else if (!pathCost.equals(other.pathCost))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (treeDepth == null) {
			if (other.treeDepth != null)
				return false;
		} else if (!treeDepth.equals(other.treeDepth))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchNode [state=" + state + ", parentNode=" + parentNode + ", action=" + action + ", pathCost=" + pathCost
				+ ", treeDepth=" + treeDepth + "]";
	}
}
