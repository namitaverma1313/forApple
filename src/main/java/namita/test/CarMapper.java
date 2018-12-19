package namita.test;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import namita.test.model.Car;

public class CarMapper {

	public static String toJsonString(Car car) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(car);
		return jsonString;

	}

	public static Car fromJsonString(String string) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Car car = mapper.readValue(string, Car.class);
		return car;

	}

}
