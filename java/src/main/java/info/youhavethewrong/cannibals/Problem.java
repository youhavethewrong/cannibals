package info.youhavethewrong.cannibals;

import java.util.*;

public class Problem {

	private River goalState;
	private River initialState;

	public Problem(River initialState, River goalState) {
		this.initialState = initialState;
		this.goalState = goalState;
	}

	public List<SearchNode> getInitialState() {
		List<SearchNode> initialNodes = new LinkedList<SearchNode>();
		initialNodes.add(new SearchNode(initialState, null, null, 0, 0));
		return initialNodes;
	}

	public boolean goalTest(SearchNode node) {
		return goalState.equals(node.getState());
	}

	public List<SearchNode> expand(SearchNode node) {
		List<SearchNode> moreFringe = new LinkedList<SearchNode>();
		/*
		 * Right
		 */
		if (actionValid(node, CannibalAction.C1M1R)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries() - 1, node.getState().getLeftBank()
					.getCannibals() - 1, false);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries() + 1, node.getState()
					.getRightBank().getCannibals() + 1, true);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.C1M1R,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.C2R)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries(), node.getState().getLeftBank()
					.getCannibals() - 2, false);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries(), node.getState().getRightBank()
					.getCannibals() + 2, true);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.C2R,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.C1R)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries(), node.getState().getLeftBank()
					.getCannibals() - 1, false);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries(), node.getState().getRightBank()
					.getCannibals() + 1, true);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.C1R,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.M2R)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries() - 2, node.getState().getLeftBank()
					.getCannibals(), false);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries() + 2, node.getState()
					.getRightBank().getCannibals(), true);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.M2R,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.M1R)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries() - 1, node.getState().getLeftBank()
					.getCannibals(), false);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries() + 1, node.getState()
					.getRightBank().getCannibals(), true);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.M1R,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}

		/*
		 * Left
		 */
		if (actionValid(node, CannibalAction.C1M1L)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries() + 1, node.getState().getLeftBank()
					.getCannibals() + 1, true);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries() - 1, node.getState()
					.getRightBank().getCannibals() - 1, false);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.C1M1L,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.C2L)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries(), node.getState().getLeftBank()
					.getCannibals() + 2, true);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries(), node.getState().getRightBank()
					.getCannibals() - 2, false);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.C2L,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.C1L)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries(), node.getState().getLeftBank()
					.getCannibals() + 1, true);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries(), node.getState().getRightBank()
					.getCannibals() - 1, false);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.C1L,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.M2L)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries() + 2, node.getState().getLeftBank()
					.getCannibals(), true);
			RiverBank rightBank = new RiverBank(node.getState().getRightBank().getMissionaries() - 2, node.getState()
					.getRightBank().getCannibals(), false);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.M2L,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}
		if (actionValid(node, CannibalAction.M1L)) {
			RiverBank leftBank = new RiverBank(node.getState().getLeftBank().getMissionaries() + 1, node.getState().getLeftBank()
					.getCannibals(), true);
			RiverBank rightBank = new RiverBank((node.getState().getRightBank().getMissionaries() - 1), node.getState()
					.getRightBank().getCannibals(), false);

			SearchNode actionNode = new SearchNode(new River(leftBank, rightBank), node, CannibalAction.M1L,
					node.getPathCost() + 1, node.getTreeDepth() + 1);
			moreFringe.add(actionNode);
		}

		return moreFringe;
	}

	private boolean actionValid(SearchNode node, CannibalAction action) {
		if (action.equals(CannibalAction.C1M1L)) {
			return (node.getState().getRightBank().getMissionaries() - 1) >= 0
					&& (node.getState().getRightBank().getCannibals() - 1) >= 0
					&& (node.getState().getLeftBank().getCannibals() + 1) <= (node.getState().getLeftBank().getMissionaries() + 1)
					&& node.getState().getRightBank().hasBoat();
		}
		if (action.equals(CannibalAction.C1M1R)) {
			return (node.getState().getLeftBank().getMissionaries() - 1) >= 0
					&& (node.getState().getLeftBank().getCannibals() - 1) >= 0
					&& (node.getState().getRightBank().getCannibals() + 1) <= (node.getState().getRightBank().getMissionaries() + 1)
					&& node.getState().getLeftBank().hasBoat();
		}
		if (action.equals(CannibalAction.M1L)) {
			return (node.getState().getRightBank().getMissionaries() - 1) >= 0
					&& ((node.getState().getRightBank().getMissionaries() - 1) >= node.getState().getRightBank().getCannibals() || (node
							.getState().getRightBank().getMissionaries() - 1) == 0)
					&& (node.getState().getLeftBank().getMissionaries() + 1) >= node.getState().getLeftBank().getCannibals()
					&& node.getState().getRightBank().hasBoat();
		}
		if (action.equals(CannibalAction.M1R)) {
			return (node.getState().getLeftBank().getMissionaries() - 1) >= 0
					&& ((node.getState().getLeftBank().getMissionaries() - 1) >= node.getState().getLeftBank().getCannibals() || (node
							.getState().getLeftBank().getMissionaries() - 1) == 0)
					&& (node.getState().getRightBank().getMissionaries() + 1) >= node.getState().getRightBank().getCannibals()
					&& node.getState().getLeftBank().hasBoat();
		}
		if (action.equals(CannibalAction.M2L)) {
			return (node.getState().getRightBank().getMissionaries() - 2) >= 0
					&& ((node.getState().getRightBank().getMissionaries() - 2) >= node.getState().getRightBank().getCannibals() || (node
							.getState().getRightBank().getMissionaries() - 2) == 0)
					&& (node.getState().getLeftBank().getMissionaries() + 2) >= node.getState().getLeftBank().getCannibals()
					&& node.getState().getRightBank().hasBoat();
		}
		if (action.equals(CannibalAction.M2R)) {
			return (node.getState().getLeftBank().getMissionaries() - 2) >= 0
					&& ((node.getState().getLeftBank().getMissionaries() - 2) >= node.getState().getLeftBank().getCannibals() || (node
							.getState().getLeftBank().getMissionaries() - 2) == 0)
					&& (node.getState().getRightBank().getMissionaries() + 2) >= node.getState().getRightBank().getCannibals()
					&& node.getState().getLeftBank().hasBoat();
		}

		if (action.equals(CannibalAction.C1L)) {
			return (node.getState().getRightBank().getCannibals() - 1) >= 0
					&& (node.getState().getLeftBank().getMissionaries() >= (node.getState().getLeftBank().getCannibals() + 1) || node
							.getState().getLeftBank().getMissionaries() == 0) && node.getState().getRightBank().hasBoat();
		}
		if (action.equals(CannibalAction.C1R)) {
			return (node.getState().getLeftBank().getCannibals() - 1) >= 0
					&& (node.getState().getRightBank().getMissionaries() >= (node.getState().getRightBank().getCannibals() + 1) || node
							.getState().getRightBank().getMissionaries() == 0) && node.getState().getLeftBank().hasBoat();
		}
		if (action.equals(CannibalAction.C2L)) {
			return (node.getState().getRightBank().getCannibals() - 2) >= 0
					&& (node.getState().getLeftBank().getMissionaries() >= (node.getState().getLeftBank().getCannibals() + 2) || node
							.getState().getRightBank().getMissionaries() == 0) && node.getState().getRightBank().hasBoat();
		}
		if (action.equals(CannibalAction.C2R)) {
			return (node.getState().getLeftBank().getCannibals() - 2) >= 0
					&& (node.getState().getRightBank().getMissionaries() >= (node.getState().getRightBank().getCannibals() + 2) || node
							.getState().getRightBank().getMissionaries() == 0) && node.getState().getLeftBank().hasBoat();
		}

		return false;
	}
}
