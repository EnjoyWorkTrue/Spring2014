public class Emp implements Comparable<Emp>{
	String name;
	int bornYear;
	int bornMonth;
	int bornDate;

	public Emp(String name, int bornYear, int bornMonth, int bornDate) {
		super();
		this.name = name;
		this.bornYear = bornYear;
		this.bornMonth = bornMonth;
		this.bornDate = bornDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBornYear() {
		return bornYear;
	}

	public void setBornYear(int bornYear) {
		this.bornYear = bornYear;
	}

	public int getBornMonth() {
		return bornMonth;
	}

	public void setBornMonth(int bornMonth) {
		this.bornMonth = bornMonth;
	}

	public int getBornDate() {
		return bornDate;
	}

	public void setBornDate(int bornDate) {
		this.bornDate = bornDate;
	}

	@Override
	public String toString() {
		return "Emp [name=" + name + ", bornYear=" + bornYear + ", bornMonth=" + bornMonth + ", bornDate=" + bornDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bornDate;
		result = prime * result + bornMonth;
		result = prime * result + bornYear;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Emp other = (Emp) obj;
		if (bornDate != other.bornDate)
			return false;
		if (bornMonth != other.bornMonth)
			return false;
		if (bornYear != other.bornYear)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Emp o) {
		if (((Integer) bornYear).compareTo(o.bornYear) == 0 ) {
			if (((Integer) bornMonth).compareTo(o.bornMonth) == 0 ) {
				if (((Integer) bornDate).compareTo(o.bornDate) == 0 ) {
					return ((String) name).compareTo(o.name);
				} else
					return ((Integer) bornDate).compareTo(o.bornDate);
			} else
				return ((Integer) bornMonth).compareTo(o.bornMonth);
		} else
			return ((Integer) bornYear).compareTo(o.bornYear);
	}

	// @Override
	// public int compareTo(Emp another) {
	//
	// return 0;
	// }

}
