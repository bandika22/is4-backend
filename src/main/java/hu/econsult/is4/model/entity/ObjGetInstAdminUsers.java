package hu.econsult.is4.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ObjGetInstAdminUsers implements Serializable{
	
	private static final long serialVersionUID = -4255419564605037543L;
	
	@Id
	private Long id;	
	private String uuid;
	private String name;

}
