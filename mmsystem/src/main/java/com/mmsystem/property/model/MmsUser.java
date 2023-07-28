package com.mmsystem.property.model;

import java.util.HashSet;

import java.util.Objects;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity  
@Table(name="mmsusers")  
public class MmsUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;
	
	@Column(nullable = false, unique=true )
	@Email(message = "Email cannot be blank")
	private String userEmail;
	private String userPassword;
	private String userFirstname;
	private String userLastname;
	private String userPhone;
	
	
	
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinTable(
			joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), 
		      inverseJoinColumns = @JoinColumn(name = "usrRoleId", 
		      referencedColumnName = "usrRoleId"))
	
	private Set<MmsUserRole> userRoles = new HashSet<>();


	@ManyToOne
	@JoinColumn(name = "userStatusId", nullable = false)
	private MmsUserStatus userStatus;
	
	
	private String isVerified;
	private String activationId;
	
	
	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + ", userFirstname=" + userFirstname + ", userLastname=" + userLastname
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userEmail, userFirstname, userLastname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MmsUser other = (MmsUser) obj;
		return Objects.equals(userEmail, other.userEmail) && Objects.equals(userFirstname, other.userFirstname)
				&& Objects.equals(userLastname, other.userLastname);
	}
	
	public String fullName() {
		return this.userFirstname + " " +this.userLastname;
	}
	
}
