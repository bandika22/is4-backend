package hu.econsult.is4.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjSavePermissionToUser implements Serializable {

	private static final long serialVersionUID = 590714970799281757L;
	
	private Long modulePermissionFk;
	private Short granted;
	private Short delete;
}
