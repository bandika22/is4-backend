package hu.econsult.is4.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ObjCodeBase implements Serializable {
	
	private static final long serialVersionUID = 4777559357311316720L;

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TYPE_CODE")
	private String typeCode;
	
	@Column(name = "TYPE_DESC")
	private String typeDesc;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "VALID_FROM")
	private Date validFrom;
	
	@Column(name = "VALID_TO")
	private Date validTo;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DETAIL_STR")
	private String detailStr;
}