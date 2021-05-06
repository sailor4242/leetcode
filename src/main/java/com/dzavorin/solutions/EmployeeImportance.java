package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeImportance {

    public int getImportance(List<Employee> employees, int id) {

        Map<Integer, Employee> map = new HashMap<>();
        for (var e : employees) {
            map.put(e.id, e);
        }

        Employee e = map.get(id);

        if (e == null) {
            return 0;
        }

        return calcImp(e, map);
    }

    public int calcImp(Employee e, Map<Integer, Employee> map) {
        int res = e.importance;
        if (e.subordinates != null && !e.subordinates.isEmpty()) {
            for (var id : e.subordinates) {
                res += calcImp(map.get(id), map);
            }
        }
        return res;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

}
