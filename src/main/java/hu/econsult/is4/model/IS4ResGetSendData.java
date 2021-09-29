package hu.econsult.is4.model;

import hu.econsult.is4.model.entity.ObjSendData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetSendData extends Response{
	
	private ObjSendData sendData;

}
