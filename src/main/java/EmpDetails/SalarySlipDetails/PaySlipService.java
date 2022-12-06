package EmpDetails.SalarySlipDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaySlipService 
{
       @Autowired
       PaySlipRepositary repo;
       
       public void impleDeleteByCondition(int id)
       {
    	   repo.deleteByEmpId(id);
       }
        
       public List<PaySlipDetails> impleDateAndEmp(int emp,String d1, String d2)
       {
    	   return repo.findAllByDateRanges(emp,d1,d2);
       }
       
       public PaySlipDetails create (PaySlipDetails payslip)
       {
    	   return repo.save(payslip);
       }
       public Optional<PaySlipDetails> read(int part)
   	   {
   		   return repo.findById(part);
   	   }
       public String erasing(int id)
   	   {
   		PaySlipDetails emp=repo.findById(id).orElse(new PaySlipDetails() );
   		String tmp=emp.getPayslipid()+"Has deleted Successfully";
   		repo.delete(emp);
   		return tmp;
   	   }
       public List<PaySlipDetails> getByEmpDetails(EmpDetails emp)
       {
    	   return repo.findAllByEmpdetails(emp);
       }
       public List<PaySlipDetails> ShowingDates(Date dt1,Date dt2)
       {
    	   return repo.betweendates(dt1, dt2);
       }
       
       
       
      
}
