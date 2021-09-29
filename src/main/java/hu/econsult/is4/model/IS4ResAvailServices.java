package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.IS4ObjAvailServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResAvailServices extends Response {
	
	private List<IS4ObjAvailServices> serviceList;

	@Override
	public String toString() {
		return "IS4ResAvailServices [serviceList=" + serviceList + ", getCode()=" + getCode() + ", getMessage()="
				+ getMessage() + "]";
	}	
}
