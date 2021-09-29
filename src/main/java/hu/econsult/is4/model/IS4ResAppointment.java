package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.IS4ObjAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResAppointment extends Response{
	
	List<IS4ObjAppointment> appointmentList;

	
	@Override
	public String toString() {
		return "IS4ResAppointment [appointmentList=" + appointmentList + ", getCode()=" + getCode() + ", getMessage()="
				+ getMessage() + "]";
	}
	
	

}
