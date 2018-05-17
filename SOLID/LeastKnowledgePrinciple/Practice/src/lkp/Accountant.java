package lkp;

import java.util.List;

public class Accountant {
    private List<Employee> employees;

    public Accountant(List<Employee> employees) {
        this.employees = employees;
    }

    public void count() {
        System.out.println("總共要：" + getAmount() + " 元");
    }

    private int getAmount() {
        return this.employees.size() * 500;
    }
}
