package hu.econsult.is4.model;

import java.util.HashSet;
import java.util.List;

import hu.econsult.is4.model.entity.ObjGetInstElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IS4ResAvailServices2 extends Response {
		
	private HashSet<ObjGetInstElement> spList;
	private HashSet<ObjGetInstElement> etList;
	private HashSet<ObjGetInstElement> drList;
	
}
