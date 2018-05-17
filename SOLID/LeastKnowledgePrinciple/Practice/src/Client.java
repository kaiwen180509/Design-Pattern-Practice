import lkp.Boss;
import lkp.Employee;
import lkp.Accountant;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // 有一個老闆，有一間25個員工的公司，以及一個會計師
        Boss boss = new Boss();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            employees.add(new Employee());
        }
        Accountant accountant = new Accountant(employees);

        // 老闆指示會計師去算總共要多少錢
        boss.command(accountant);
    }
}