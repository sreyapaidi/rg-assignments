import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
    List<Employee> employees = new ArrayList<Employee>();

    public void create(Employee emp){
        employees.add(emp);
    }

    public List<Employee> readAll(){
        return employees;
    }

    public Employee readById(int id){
        for(Employee emp: employees){
            if(emp.getId() == id) return emp;
        }
        System.out.println("Employee not found");
        return null;
    }

    public void update(int id, String newName, String newDepartment){
        for(Employee emp: employees){
            if(emp.getId() == id) {
                emp.setName(newName);
                emp.setDepartment(newDepartment);
                System.out.println("Employee details updated");
                return;
            }
        }
        System.out.println("Employee not found");
    }
    
    public void delete(int id){
        Object obj=null;
        for(Employee emp: employees){
            if(emp.getId() == id) obj = emp;
        }
        if(obj != null){
            employees.remove(obj);
            System.out.println("Employee details deleted");
        }else{
            System.out.println("Employee not found to delete.");
        }
    }
}