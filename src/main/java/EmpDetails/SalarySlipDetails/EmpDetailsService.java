package EmpDetails.SalarySlipDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpDetailsService implements UserDetailsService
{
	@Autowired
	EmpDetailsRepositary repo;
	

	public EmpDetails create(EmpDetails empdetail) {
	
		return repo.save(empdetail);
	}
	public String erasing(int id)
	{
		EmpDetails emp=repo.findById(id).orElse(new EmpDetails());
		String tmp=emp.getEmpName()+" has deleted successfully";
		repo.delete(emp);
		return tmp;
	}
	public EmpDetails read(int solo)
	{
		return repo.findById(solo).orElse(new EmpDetails());
	}
	public void update (String value)
	{
	   repo.updating(value);
	}
	
	public List<EmpDetails> fetchingSalaryName(double salary1,String name1)
	{
		return repo.gettingAnSalaryandName(salary1, name1);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		EmpDetails emp=repo.findByUsername(username);
		if (emp==null)
		{
			throw new UsernameNotFoundException(username);
			
		}
		return emp;
	}

	

	
	
	

}
