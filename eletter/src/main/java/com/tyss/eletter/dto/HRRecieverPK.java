package com.tyss.eletter.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Embeddable
public class HRRecieverPK implements Serializable{
	
	private int hId;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rId;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hId;
		result = prime * result + rId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HRRecieverPK other = (HRRecieverPK) obj;
		if (hId != other.hId)
			return false;
		if (rId != other.rId)
			return false;
		return true;
	}
	
	

}
