package helloworld.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericItemResponse {

	@JsonProperty("data")
	private Object data = null;

	@JsonProperty("message")
	private String message = null;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GenericItemResponse that = (GenericItemResponse) o;
		return Objects.equals(data, that.data) && Objects.equals(message, that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, message);
	}

	@Override
	public String toString() {
		return "GenericItemResponse{" + "data=" + data + ", message='" + message + '\'' + '}';
	}
}
