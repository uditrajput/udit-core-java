package mypack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class EmployeeTestCase {

	static List<Employee> empList = null;
	static {
		empList = new ArrayList<>();
		empList.add(new Employee(101, "Udit", 101, "Active", 150000));
		empList.add(new Employee(102, "Sumit", 102, "Active", 1000));
		empList.add(new Employee(103, "Amit", 103, "Active", 50000));
		empList.add(new Employee(104, "Kumar", 104, "Active", 20000));
		empList.add(new Employee(105, "Madan", 105, "Active", 30000));
		empList.add(new Employee(106, "Sunil", 101, "InActive", 40000));
		empList.add(new Employee(107, "Pradeep", 102, "Active", 80000));
		empList.add(new Employee(108, "Mukesh", 103, "InActive", 100000));
		empList.add(new Employee(109, "Anil", 101, "Active", 5000));
		empList.add(new Employee(1010, "Aanand", 102, "Active", 65000));
		empList.add(new Employee(1011, "Mahesh", 104, "Active", 900000));
		empList.add(new Employee(1012, "Dinesh", 105, "Active", 18000));
		empList.add(new Employee(1013, "kapil", 101, "Active", 125000));
		empList.add(new Employee(1014, "krishna", 105, "Active", 1400000));
		empList.add(new Employee(1015, "Mahi", 106, "Active", 10000));

	}

	public static void main(String[] args) {
		// Print employees details based on department wise.
		Map<Integer, List<Employee>> empListBasedOnDept = empList.stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.toList()));
		empListBasedOnDept.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + "-------" + entry.getValue());
		});
		
		// Employee Count working in each department
		Map<Integer, Long> empCountDept = empList.stream().collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
		empCountDept.entrySet().forEach(entry ->{
			System.out.println("dept "+ entry.getKey()+"--"+entry.getValue());
		});
		
		//Print Active and inactive employee.
		long activeEmpCount = empList.stream().filter(e -> "Active".equals(e.getStatue())).count();
		long inActiveEmpCount = empList.stream().filter(e -> "InActive".equals(e.getStatue())).count();
		System.out.println("Active Employee Count: "+activeEmpCount);
		System.out.println("InActive Employee Count: "+inActiveEmpCount);
		
		// Print max and min salary.
		Optional<Employee> maxSalary = empList.stream().max(Comparator.comparing(Employee::getSalary));
		Optional<Employee> minSalary = empList.stream().min(Comparator.comparing(Employee::getSalary));
		System.out.println("Max Salary = "+maxSalary);
		System.out.println("Min Salary = "+minSalary);
		
		//Max Salary of employee each department.
		Map<Integer, Optional<Employee>> topSalaryEmpDeptWise = empList.stream().collect(Collectors.groupingBy(Employee::getDeptId, 
				Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));
		topSalaryEmpDeptWise.entrySet().forEach(entry -> {
			System.out.println("Deptartment "+entry.getKey()+" Top Employee "+entry.getValue());
		});
	}

}
