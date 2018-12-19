package namita.test.model;

public class Car {

	private String make;
	private String model;
	private String vin;
	private Metadata metadata;
	private Metrics metrics;
	private PerDayRent perDayRent;

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Metadata getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public Metrics getMetrics() {
		return this.metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	public PerDayRent getPerDayRent() {
		return this.perDayRent;
	}

	public void setPerDayRent(PerDayRent perDayRent) {
		this.perDayRent = perDayRent;
	}

}
