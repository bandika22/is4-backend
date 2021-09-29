package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjDoctorToSp;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUResGetDoctorToServicePoint extends Response {
	
	@ApiModelProperty(value = "Pecsétszám alapján előállt doktorok listája.")
	private List<ObjDoctorToSp> doctors;
	
}
