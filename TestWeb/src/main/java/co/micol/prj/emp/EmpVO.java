package co.micol.prj.emp;

public class EmpVO {
	
	private String employeeId;
	private String lastName;
	private String email;
	private String jobId;
	private String hireDate;
	private String departmentId;

	
	
	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getJobId() {
		return jobId;
	}


	public void setJobId(String jobId) {
		this.jobId = jobId;
	}


	public String getHireDate() {
		return hireDate;
	}


	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	

	
	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public EmpVO(String employeeId, String lastName, String email, String jobId, String hireDate, String departmentId) {
		super();
		this.employeeId = employeeId;
		this.lastName = lastName;
		this.email = email;
		this.jobId = jobId;
		this.hireDate = hireDate;

	}


	public EmpVO() {
		
	}
	
	
	
	
	
}
