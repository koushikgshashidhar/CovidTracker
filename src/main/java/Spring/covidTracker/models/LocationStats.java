package Spring.covidTracker.models;

public class LocationStats {

	private String state;
	private String country;
	private int latestnocases;
	private int diffromprevday;
	public int getDiffromprevday() {
		return diffromprevday;
	}
	public void setDiffromprevday(int diffromprevday) {
		this.diffromprevday = diffromprevday;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestnocases() {
		return latestnocases;
	}
	public void setLatestnocases(int latestnocases) {
		this.latestnocases = latestnocases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestnocases=" + latestnocases + "]";
	}
	
	
}
