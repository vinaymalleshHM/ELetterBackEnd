package com.tyss.eletter.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "reciever_info_bean")
public class RecieverInfoBean implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="r_id")
	private int rId;
	
	@NotNull(message = "name can't blank")
	@Pattern(regexp = "^[a-zA-Z ]*$",message = "name should contain character only")
	@Size(min = 3, max = 20, message = "name should contain min 3 and max 20 characters" )
	@Column(name="name")
	private String name;
	
	@NotNull(message = "ty Id can't blank")
	@Column(name="ty_id")
	private String tyId;
	
	@NotNull(message = "Date can't blank")
	@Column(name="date")
	private Date date;
	
	@NotNull(message = "typeOfLetter can't blank")
	@Pattern(regexp = "^[a-zA-Z ]*$",message = "typeOfLetter should contain character only")
	@Size(min = 3, max = 20, message = "typeOfLetter should contain min 3 and max 20 characters" )
	@Column(name="type_of_letter")
	private String typeOfLetter;
	
	@Column(name="from_mail")
	private String fromMail;
	
	@ElementCollection
	@CollectionTable(name="recievers")
	@Column(name="to_mail")
	private List<String> toMail;
	
	
	
	
}
