package EmpDetails.SalarySlipDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SalarySlipDetailsApplicationTests {
	
		@MockBean
		EmpDetailsRepositary repo;
		
		@Autowired
		EmpDetailsService service;
		
		@Test
		public void test1()
		{	
			EmpDetails emp=new EmpDetails(1001, "Manojkumar", "Maddy@434", "maddy@3232", "Developer", 2, 240000.0, null);
			EmpDetails emp1=new EmpDetails(1002, "Vinodkumar", "vinod", "vinoth@060", "Full stack developer", 3, 300000.0, null);

			when(repo.save(emp)).thenReturn(emp);

			assertEquals("Manojkumar", service.fetchingSalaryName(240000.0, "Manojkumar"));
			//assertEquals("Manojkumar", service.fetchingAnSalaryName(240000.0, "Manojkumar"));
			//assertNotSame("manojkumar", service.create(emp).getEmpName());
		}
		@Test
		public void test2()
		{	
			EmpDetails emp=new EmpDetails(1001, "Manojkumar", "Maddy@434", "maddy@3232", "Developer", 2, 240000.0, null);
			EmpDetails emp1=new EmpDetails(1002, "Vinodkumar", "vinod", "vinoth@060", "Full stack developer", 3, 300000.0, null);
		
		//when(repo.save(emp)).thenReturn(emp);
//			
//			assertSame("manojkumar", service.create(emp).getEmpName());

			when(repo.findAll()).thenReturn(Stream.of(emp,emp1).collect(Collectors.toList()));

//		assertNull(service.list());
		//assertNull(service.list());
//			assertEquals(emp, service.create(emp).getEmpName());
		}

}


