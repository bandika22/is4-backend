package hu.econsult.is4.model;

import java.util.List;

import hu.econsult.is4.model.entity.ObjGetInstElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IS4ResGetInstUser extends Response{
	
	private List<ObjGetInstElement>  userList;
	
	@Override
	public String toString() {
		return "IS4ResGetInstUser [userList=" + userList + ", getCode()=" + getCode() + ", getMessage()=" + getMessage()
				+ "]";
	}
}
