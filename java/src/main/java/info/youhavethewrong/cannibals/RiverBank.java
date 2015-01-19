package info.youhavethewrong.cannibals;

public class RiverBank {

	private Integer missionaries;
	private Integer cannibals;
	private Boolean hasBoat;

	public RiverBank(Integer missionaries, Integer cannibals, Boolean hasBoat) {
		this.missionaries = missionaries;
		this.cannibals = cannibals;
		this.hasBoat = hasBoat;
	}

	public Integer getMissionaries() {
		return missionaries;
	}

	public void setMissionaries(Integer missionaries) {
		this.missionaries = missionaries;
	}

	public Integer getCannibals() {
		return cannibals;
	}

	public void setCannibals(Integer cannibals) {
		this.cannibals = cannibals;
	}

	public Boolean hasBoat() {
		return hasBoat;
	}

	public void setHasBoat(Boolean hasBoat) {
		this.hasBoat = hasBoat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cannibals == null) ? 0 : cannibals.hashCode());
		result = prime * result + ((hasBoat == null) ? 0 : hasBoat.hashCode());
		result = prime * result + ((missionaries == null) ? 0 : missionaries.hashCode());
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
		RiverBank other = (RiverBank) obj;
		if (cannibals == null) {
			if (other.cannibals != null)
				return false;
		} else if (!cannibals.equals(other.cannibals))
			return false;
		if (hasBoat == null) {
			if (other.hasBoat != null)
				return false;
		} else if (!hasBoat.equals(other.hasBoat))
			return false;
		if (missionaries == null) {
			if (other.missionaries != null)
				return false;
		} else if (!missionaries.equals(other.missionaries))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RiverBank [missionaries=" + missionaries + ", cannibals=" + cannibals + ", hasBoat=" + hasBoat + "]";
	}
}
