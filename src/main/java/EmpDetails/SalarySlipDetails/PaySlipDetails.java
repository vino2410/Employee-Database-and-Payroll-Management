package EmpDetails.SalarySlipDetails;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaySlipDetails 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int payslipid;
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal (TemporalType.DATE)
	 private Date date;
     private double payslipbasicsalary;
     private double payslipAllowance;
     private double payslipTDS;
     private double payslipTakehome;
     
     @ManyToOne
     @JoinColumn(name="empid")
     @Nullable
     private EmpDetails empdetails;
}
