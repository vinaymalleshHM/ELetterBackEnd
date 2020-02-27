package com.tyss.eletter.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="hr_info_bean")
public class HRInfoBean implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "h_id")
	private int  hId;
	
	@NotNull(message = "name can't blank")
	@Pattern(regexp = "^[a-zA-Z ]*$",message = "name should contaan character only")
	@Size(min = 3, max = 20, message = "name should contain min 3 and max 20 characters  " )
	@Column(name="name")
	private String name;
	
	@Column(name="email",unique = true)
	@NotNull(message = "email can't blank")
	@Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",message = "please check given Email")
	@Size(max = 50)
	private String email;
	
	@NotEmpty(message = "password can't black")
	@Column(name="password")
	private String password;
	
	@NotNull(message = "ty Id can't blank")
	@Column(name="ty_id",unique = true)
	private String tyId;
	
	@Column(name="isactive")
	private boolean isActive;
	

	@OneToMany(cascade = CascadeType.ALL)
	private List<RecieverInfoBean> recieverInfoBean; 
	
	
	
	

}
