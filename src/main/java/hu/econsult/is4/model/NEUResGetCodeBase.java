package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjCodeBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NEUResGetCodeBase extends Response{
	
	private List<ObjCodeBase> objCodeBase;


}
