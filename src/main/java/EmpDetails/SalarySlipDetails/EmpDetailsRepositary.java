package EmpDetails.SalarySlipDetails;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDetailsRepositary extends JpaRepository<EmpDetails, Integer>
{
	public EmpDetails findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update from EmpDetails set empSalary=empSalary+(empSalary*10/100) where username=:emp")
	public void updating(String emp);
	
	@Query("from EmpDetails where empSalary>=:salary or empName=:name")
	public java.util.List<EmpDetails> gettingAnSalaryandName(double salary,String name);

}
