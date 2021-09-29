package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjServicePoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResGetUserSps extends Response{

	private List<ObjServicePoint> servicePoints;

}
