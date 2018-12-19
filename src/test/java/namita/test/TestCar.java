package namita.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import namita.test.model.Car;
import namita.test.model.Metadata;
import namita.test.model.Metrics;
import namita.test.model.PerDayRent;
import namita.test.model.RentalCount;

public class TestCar {

	@Test
	public static void testFirst() throws Exception {
		Car car = new Car();
		Metadata metadata = new Metadata();
		PerDayRent perDayRent = new PerDayRent();
		Metrics metrics = new Metrics();
		RentalCount rentalCount = new RentalCount();
		car.setMake("Audi");
		car.setModel("A7");
		car.setVin("09AGHY64352JITEG98K");
		car.setMetadata(metadata);
		metadata.setColor("Black");
		metadata.setNotes("Scratches on the front side of the car");
		car.setPerDayRent(perDayRent);
		perDayRent.setPrice(140);
		perDayRent.setDiscount(15);
		car.setMetrics(metrics);
		metrics.setYoymaintenancecost((float) 2190.76);
		metrics.setDepreciation((float) 2256.43);
		metrics.setrentalCount(rentalCount);
		rentalCount.setLastWeek(4);
		rentalCount.setYearToDate(221);

		String carAsString = CarMapper.toJsonString(car);
		Car carAsReceived = CarMapper.fromJsonString(carAsString);
		Assert.assertEquals(carAsReceived.getMake(), car.getMake());
		Assert.assertEquals(carAsReceived.getModel(), car.getModel());
		Assert.assertEquals(carAsReceived.getVin(), car.getVin());
		Assert.assertEquals(carAsReceived.getMetadata(), car.getMetadata());
		Assert.assertEquals(carAsReceived.getMetrics(), car.getMetrics());
		Assert.assertEquals(carAsReceived.getPerDayRent(), car.getPerDayRent());
		Assert.assertEquals(carAsReceived.getMetadata().getColor(), car.getMetadata().getColor());
		Assert.assertEquals(carAsReceived.getMetadata().getColor(), car.getMetadata().getNotes());
		Assert.assertEquals(carAsReceived.getPerDayRent().getDiscount(), car.getPerDayRent().getDiscount());
		Assert.assertEquals(carAsReceived.getPerDayRent().getPrice(), car.getPerDayRent().getPrice());
		Assert.assertEquals(carAsReceived.getMetrics().getDepriciation(), car.getMetrics().getDepriciation());
		Assert.assertEquals(carAsReceived.getMetrics().getYoymaintenancecost(),
				car.getMetrics().getYoymaintenancecost());
		Assert.assertEquals(carAsReceived.getMetrics().getrentalCount().getLastWeek(),
				car.getMetrics().getrentalCount().getLastWeek());
		Assert.assertEquals(carAsReceived.getMetrics().getrentalCount().getYearToDate(),
				car.getMetrics().getrentalCount().getYearToDate());
	}

	// Question 1

	public static void printAllCars(List<Car> cars) {
		for (Car car : cars) {
			if (car.getMetadata().getColor() == "Blue" && (car.getMake() == "Tesla")) {
				System.out.println(car);
			}
			System.out.println(car.getMetadata().getNotes());
		}
	}

	// Question 2(a)

	public static List<Car> lowestPrice(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>() {

			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				float price1 = o1.getPerDayRent().getPrice();
				float price2 = o2.getPerDayRent().getPrice();
				return new Float(price1).compareTo(price2);
			}
		});
		float lowestPrice = cars.get(0).getPerDayRent().getPrice();
		List<Car> ret = new ArrayList<Car>();
		for (Car car : cars) {
			if (car.getPerDayRent().getPrice() == lowestPrice) {
				ret.add(car);
			} else {
				break;
			}
		}
		return ret;

	}

	// Question 2(b)

	public static List<Car> lowestPriceWithDiscount(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>() {

			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				float price1 = o1.getPerDayRent().getPrice()
						- (o1.getPerDayRent().getPrice() * o1.getPerDayRent().getDiscount() / 100);
				float price2 = o2.getPerDayRent().getPrice()
						- (o2.getPerDayRent().getPrice() * o2.getPerDayRent().getDiscount() / 100);
				return new Float(price1).compareTo(price2);
			}
		});
		float lowestPrice = cars.get(0).getPerDayRent().getPrice()
				- (cars.get(0).getPerDayRent().getPrice() * cars.get(0).getPerDayRent().getDiscount() / 100);
		List<Car> ret = new ArrayList<Car>();
		for (Car car : cars) {
			float price = car.getPerDayRent().getPrice()
					- (car.getPerDayRent().getPrice() * car.getPerDayRent().getDiscount() / 100);
			if (price == lowestPrice) {
				ret.add(car);
			} else {
				break;
			}
		}
		return ret;

	}

	// Question 3

	public static float getYearlyProfit(Car car) {
		return ((car.getPerDayRent().getPrice()
				- (car.getPerDayRent().getPrice() * car.getPerDayRent().getDiscount() / 100))
				* car.getMetrics().getrentalCount().getYearToDate())
				- (car.getMetrics().getYoymaintenancecost() + car.getMetrics().getDepriciation());

	}

	public static List<Car> highestRevenueCars(List<Car> cars) {
		Collections.sort(cars, new Comparator<Car>() {

			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				float profit1 = getYearlyProfit(o1);
				float profit2 = getYearlyProfit(o2);

				return new Float(profit2).compareTo(profit1);
			}
		});
		float highestProfit = getYearlyProfit(cars.get(0));
		List<Car> ret = new ArrayList<Car>();
		for (Car car : cars) {
			if (getYearlyProfit(car) == highestProfit) {
				ret.add(car);
			} else {
				break;
			}
		}
		return ret;

	}
}
