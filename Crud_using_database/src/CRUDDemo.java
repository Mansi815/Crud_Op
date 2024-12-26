import java.sql.*;
import java.util.Scanner;

class Employee {
    private int empno;
    private String ename;
    private int salary;

    Employee(int empno, String ename, int salary) {
        this.empno = empno;
        this.ename = ename;
        this.salary = salary;
    }

    public int getEmpno() {
        return empno;
    }

    public int getSalary() {
        return salary;
    }

    public String getEname() {
        return ename;
    }
}

public class CRUDDemo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        int ch;
        do {
            System.out.println("1. INSERT");
            System.out.println("2. DISPLAY");
            System.out.println("3. SEARCH");
            System.out.println("4. DELETE");
            System.out.println("5. UPDATE");
            System.out.println("0. EXIT");
            System.out.print("Enter Your Choice: ");
            ch = s.nextInt();

            switch (ch) {
                case 1: // INSERT
                    System.out.print("Enter Empno: ");
                    int eno = s.nextInt();
                    System.out.print("Enter EmpName: ");
                    String ename = s1.nextLine();
                    System.out.print("Enter Salary: ");
                    int salary = s.nextInt();

                    try (Connection conn = ConnectionClass.getConnection()) {
                        String query = "INSERT INTO crud (empno, ename, salary) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setInt(1, eno);
                        pstmt.setString(2, ename);
                        pstmt.setInt(3, salary);
                        pstmt.executeUpdate();
                        System.out.println("Employee added to database.");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2: // DISPLAY
                    try (Connection conn = ConnectionClass.getConnection()) {
                        String query = "SELECT * FROM crud";
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        System.out.println("Employee List from Database:");
                        while (rs.next()) {
                            System.out.println("Empno: " + rs.getInt("empno") +
                                    ", Name: " + rs.getString("ename") +
                                    ", Salary: " + rs.getInt("salary"));
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3: // SEARCH
                    System.out.print("Enter Empno to search: ");
                    int searchEmpno = s.nextInt();
                    try (Connection conn = ConnectionClass.getConnection()) {
                        String query = "SELECT * FROM crud WHERE empno = ?";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setInt(1, searchEmpno);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            System.out.println("Employee Found:");
                            System.out.println("Empno: " + rs.getInt("empno") +
                                    ", Name: " + rs.getString("ename") +
                                    ", Salary: " + rs.getInt("salary"));
                        } else {
                            System.out.println("Employee not found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4: // DELETE
                    System.out.print("Enter Empno to delete: ");
                    int deleteEmpno = s.nextInt();
                    try (Connection conn = ConnectionClass.getConnection()) {
                        String query = "DELETE FROM crud WHERE empno = ?";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setInt(1, deleteEmpno);
                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Employee deleted from database.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5: // UPDATE
                    System.out.print("Enter Empno to update: ");
                    int updateEmpno = s.nextInt();
                    System.out.print("Enter new Name: ");
                    String updateName = s1.nextLine();
                    System.out.print("Enter new Salary: ");
                    int updateSalary = s.nextInt();

                    try (Connection conn = ConnectionClass.getConnection()) {
                        String query = "UPDATE crud SET ename = ?, salary = ? WHERE empno = ?";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setString(1, updateName);
                        pstmt.setInt(2, updateSalary);
                        pstmt.setInt(3, updateEmpno);
                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Employee details updated.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 0: // EXIT
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice! Try Again.");
            }
        } while (ch != 0);

        s.close();
        s1.close();
    }
}