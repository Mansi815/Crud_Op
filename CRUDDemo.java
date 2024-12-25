import java.util.*;
class Employee{
    private int empno;
    private String ename;
    private double salary;

Employee(int empno, String ename, double salary){
    this.empno = empno;
    this.ename = ename;
    this.salary = salary;
}

public int getEmpno(){
    return empno;
}
public String getEname(){
    return ename;
}
public double getSalary(){
    return salary;
}
public String toString(){
    return empno+" "+ename+" "+salary;
}
}
class CRUDDemo{
    public static void main(String args[]){
        List<Employee> c = new ArrayList<Employee>();
        Scanner sc = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        int ch;
        do{
            System.out.println("0. EXIT");
            System.out.println("1. INSERT");
            System.out.println("2. DISPLAY");
            System.out.println("3. SEARCH");
            System.out.println("4. DELETE");
            System.out.println("5. UPDATE");
            System.out.println("6. SORT ENO");

            System.out.println("Enter Your Choice : ");
            ch = sc.nextInt();
            switch(ch){
                case 1:
                System.out.print("Enter employee number : ");
                int empno = sc.nextInt();
                System.out.print("Enter employee name : ");
                String ename = s1.nextLine();
                System.out.print("Enter employee salary : ");
                double salary = sc.nextDouble();
                c.add(new Employee(empno, ename, salary));
                    break;
        
                case 2 :
                System.out.println("=====================================================");
                System.out.println("ENo."+"    "+"EName"+"     "+"Salary");
                Iterator<Employee> i = c.iterator();
                while(i.hasNext()){
                    Employee e = i.next();
                    System.out.println(e);
                }
                System.out.println("=====================================================");
                break;

                case 3 :
                boolean found = false;
                System.out.println("Enter Employee Number to Search :");
                int eno = sc.nextInt();
                System.out.println("=====================================================");
                System.out.println("ENo."+"  "+"EName"+"  "+"Salary");
                 i = c.iterator();
                while(i.hasNext()){
                    Employee e = i.next();
                    if(e.getEmpno()==eno){
                    found = true;
                    System.out.println(e);
                    }
                }
                    if(!found){
                        System.out.println("The Employee doesn't exist.");
                    }
                System.out.println("=====================================================");
                break;

                case 4 :
                 found = false;
                System.out.println("Enter Employee Number to Delete :");
                 eno = sc.nextInt();
                System.out.println("=====================================================");
                System.out.println("ENo."+"  "+"EName"+"  "+"Salary");
                 i = c.iterator();
                while(i.hasNext()){
                    Employee e = i.next();
                    if(e.getEmpno()==eno){
                    i.remove();
                    found = true;
                    }
                }
                    if(!found){
                        System.out.println("The Employee doesn't exist.");
                    }else{
                        System.out.println("Record is deleted successfully.");
                    }
                System.out.println("=====================================================");
                break;

                case 5 :
                found = false;
               System.out.println("Enter Employee Number to Update :");
                eno = sc.nextInt();
               
               ListIterator<Employee> li = c.listIterator();
               while(li.hasNext()){
                   Employee e = li.next();
                   if(e.getEmpno()==eno){
                   System.out.println("Enter new Name :");
                   ename = s1.nextLine();
                   System.out.println("Enter new Salary :");
                   salary = sc.nextDouble();
                   System.out.println("=====================================================");
               System.out.println("ENo."+"  "+"EName"+"  "+"Salary");
                   li.set(new Employee(eno, ename, salary));
                   found = true;
                   }
               }
                   if(!found){
                       System.out.println("The Employee doesn't exist.");
                   }else{
                       System.out.println("Record is Updated successfully.");
                   }
               System.out.println("=====================================================");
               break;

               case 6 :
                System.out.println("=====================================================");
                System.out.println("ENo."+"    "+"EName"+"     "+"Salary");
                c.sort(Comparator.comparingInt(Employee::getEmpno));
                 i = c.iterator();
                while(i.hasNext()){
                    Employee e = i.next();
                    System.out.println(e);
                }
                System.out.println("=====================================================");
                break;
                 }

        }
        while(ch !=0);
    }
}