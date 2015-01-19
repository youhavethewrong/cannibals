package info.youhavethewrong.cannibals;

public class River {

	private RiverBank leftBank;
	private RiverBank rightBank;

	public River(RiverBank leftBank, RiverBank rightBank) {
		this.leftBank = leftBank;
		this.rightBank = rightBank;
	}

	public RiverBank getLeftBank() {
		return leftBank;
	}

	public void setLeftBank(RiverBank leftBank) {
		this.leftBank = leftBank;
	}

	public RiverBank getRightBank() {
		return rightBank;
	}

	public void setRightBank(RiverBank rightBank) {
		this.rightBank = rightBank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftBank == null) ? 0 : leftBank.hashCode());
		result = prime * result + ((rightBank == null) ? 0 : rightBank.hashCode());
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
		River other = (River) obj;
		if (leftBank == null) {
			if (other.leftBank != null)
				return false;
		} else if (!leftBank.equals(other.leftBank))
			return false;
		if (rightBank == null) {
			if (other.rightBank != null)
				return false;
		} else if (!rightBank.equals(other.rightBank))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "River [leftBank=" + leftBank + ", rightBank=" + rightBank + "]";
	}
}
