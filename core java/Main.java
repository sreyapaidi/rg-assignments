public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee(1,"a","hr");
        EmployeeCRUD crud = new EmployeeCRUD();

        crud.create(e1);
        Object obj = crud.readById(1);
        System.out.println(obj);

        Employee e2 = new Employee(2, "b", "hr");
        crud.create(e2);
        Object obj1 = crud.readAll();
        System.out.println(obj1);

        crud.update(2, "c", "mgmt");
        obj = crud.readById(2);
        System.out.println(obj);

        crud.delete(2);
        obj1 = crud.readAll();
        System.out.println(obj1);

        obj1 = crud.readById(3);
        System.out.println(obj1);

        crud.delete(3);
        obj1 = crud.readAll();
        System.out.println(obj1);
    }
}
