package EmpDetails.SalarySlipDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vinod")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeDetailsController {
	
	@Autowired
	
	EmpDetailsService service;
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/{user}")
	public EmpDetails purpose(@PathVariable("user") String user)
	{
		EmpDetails e=(EmpDetails) service.loadUserByUsername(user);
		return e;
	}
	@PostMapping("/newone")
	public String makecreate (@RequestBody EmpDetails emp)
	{
		String tmp=encoder.encode(emp.getPassword());
		emp.setPassword(tmp);
		return service.create(emp).getEmpName()+" This EmpDetail has been created";
	}
	@Autowired
	PaySlipService Payserv;
	@PostMapping("/newone1")
	public String makingcreate (@RequestBody PaySlipDetails payslip)
	{
		return Payserv.create(payslip).getPayslipTakehome()+" this PaySlip Detils has been created";
	}
	@GetMapping("/Reading/{reader}")
	public EmpDetails getting (@PathVariable ("reader")int reader)
	{
		return service.read(reader);
	}
	@GetMapping("/Taking/{reader}")
	public Optional<PaySlipDetails> get (@PathVariable("reader")int reader)
	{
		return Payserv.read(reader);
		
	}
	@PutMapping("/updating")
	public String change(@RequestBody EmpDetails emp) 
	{
		String tmp=encoder.encode(emp.getPassword());
		emp.setPassword(tmp);
		EmpDetails obj=service.create(emp);
		return obj.getEmpName()+" has been updated";
	}
	@PutMapping("/update")
		public String updated(@RequestBody PaySlipDetails upd) 
	{
		return Payserv.create(upd).getPayslipid()+"it is updated";
	}
	@DeleteMapping("/Delete/{user}")
	public String erasing(@PathVariable("user")String user)
	{
		EmpDetails e = purpose(user);
		Payserv.impleDeleteByCondition(e.getEmpid());
		return service.erasing(e.getEmpid());
	}
	@PostMapping("/createslip/{user}")
	public PaySlipDetails callnewone(@PathVariable("user") String user,@RequestBody PaySlipDetails slipdetail)
	{
	  EmpDetails temp= purpose(user);
	  double month=temp.getEmpSalary()/12;
	  double basic=month-(month*(slipdetail.getPayslipAllowance())/100);
	  month=basic-(month*(slipdetail.getPayslipTDS())/100);
	  slipdetail.setPayslipbasicsalary((int)basic);
	  slipdetail.setPayslipTakehome(month);
	  temp.getPayslipdetails().add(slipdetail);
	  
	  slipdetail.setEmpdetails(temp);
//	  service.create(temp);
	  Payserv.create(slipdetail);
	  return slipdetail;
	 }
	 @PutMapping("/updated/{value}")
	  public String salaryupdate(@PathVariable("value") String value)
	  {
		EmpDetails emp=purpose(value);
		if(emp.getEmpSalary()<=400000)
		{
			service.update(value);
			return emp.getEmpName()+" got appraisal";		
		}else
		{
			return emp.getEmpName()+" not eligible for appraisal";
		}
	  }
	

	 @GetMapping("/checking/{one}/{two}")
	 
	 public List<EmpDetails> gettingTwo(@PathVariable("one")double one,@PathVariable("two")String two)
	 {
		 return service.fetchingSalaryName(one, two);
	 }
	 @GetMapping("/fetch/{user}")
	
	 public List<PaySlipDetails> getByEmps(@PathVariable("user") String user)
	 {
		 return Payserv.getByEmpDetails(purpose(user));
	 }
	 @GetMapping("/datem/{user}/{date1}/{date2}")
	 
	 public List<PaySlipDetails> showdates(@PathVariable("user")String user,@PathVariable("date1")String date1,@PathVariable("date2")String date2)throws ParseException
	 {
//		Date dt1=new SimpleDateFormat("YYYY-MM-DD").parse(num1);
//		Date dt2=new SimpleDateFormat("YYYY-MM-DD").parse(num2);
		 EmpDetails e=purpose(user);
	     return Payserv.impleDateAndEmp(e.getEmpid(),date1,date2);
		
	 }

}

