package EmpDetails.SalarySlipDetails;

import java.util.Date;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaySlipRepositary extends JpaRepository <PaySlipDetails, Integer>
{
	@Query(value= "select * from pay_slip_details where emp_id=?1 and date between ?2 and ?3",nativeQuery = true)
	public List<PaySlipDetails> findAllByDateRanges (int emp,String d1, String d2);
	
	@Transactional
	@Modifying
	@Query(value="delete from pay_slip_details where emp_id=?1",nativeQuery = true)
	public void deleteByEmpId(int id);
	
	
	@Transactional
	@Modifying
	@Query("from EmpDetails where Date between :dt1 and :dt2")
	public List<PaySlipDetails> betweendates(Date dt1,Date dt2);
	public List<PaySlipDetails> findAllByEmpdetails(EmpDetails emp);

} 
