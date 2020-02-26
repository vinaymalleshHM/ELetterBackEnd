package com.tyss.eletter.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="hr_info_bean")
public class HRInfoBean implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "h_id")
	private int  hId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email",unique = true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="ty_id",unique = true)
	private String tyId;
	
	@Column(name="isactive")
	private boolean isActive;
	

	@OneToMany(cascade = CascadeType.ALL)
	private List<RecieverInfoBean> recieverInfoBean; 
	
	
	
	

}
