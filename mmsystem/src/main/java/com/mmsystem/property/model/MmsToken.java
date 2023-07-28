package com.mmsystem.property.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity  
@Table(name="mmstoken") 
public class MmsToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tokenId")
	private int Id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tokenIssuedTo" , nullable=false)
	private MmsUser tokenIssuedTo;
	
	
	private String authToken ;
	private String testing;
	private LocalDateTime tokenIssuedOn;
	private LocalDateTime tokenExpiresOn;
	
	public MmsToken() {
		super();
	}

	public MmsToken( MmsUser tokenIssuedTo, String authToken, LocalDateTime tokenIssuedOn,
			LocalDateTime tokenExpiresOn) {
		super();
		
		this.tokenIssuedTo = tokenIssuedTo;
		this.authToken = authToken;
		this.tokenIssuedOn = tokenIssuedOn;
		this.tokenExpiresOn = tokenExpiresOn;
	}

	
	@Override
	public String toString() {
		return "Token [Id=" + Id + ", tokenIssuedTo=" + tokenIssuedTo + ", authToken=" + authToken + ", tokenIssuedOn="
				+ tokenIssuedOn + ", tokenExpiresOn=" + tokenExpiresOn + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, authToken, tokenExpiresOn, tokenIssuedOn, tokenIssuedTo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MmsToken other = (MmsToken) obj;
		return Id == other.Id && Objects.equals(authToken, other.authToken)
				&& Objects.equals(tokenExpiresOn, other.tokenExpiresOn)
				&& Objects.equals(tokenIssuedOn, other.tokenIssuedOn)
				&& Objects.equals(tokenIssuedTo, other.tokenIssuedTo);
	}
	
	
	

}
