package EmpDetails.SalarySlipDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.OneToMany;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmpDetails implements UserDetails{
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private int empid;
	private String empName;
	private String username;
	private String password;
	private String empDesignation;
	private int empExp;
	private double empSalary;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="Records",joinColumns = @JoinColumn(name="EmployeID"),inverseJoinColumns = @JoinColumn(name="payslipID"))         
	@Nullable
	@JsonBackReference
	
	private Collection<PaySlipDetails> payslipdetails=new ArrayList<PaySlipDetails>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPassword() 
	{
		return password;
	}
	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
    

}
