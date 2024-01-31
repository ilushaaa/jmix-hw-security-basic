package com.sample.employees.security;

import com.sample.employees.entity.Department;
import com.sample.employees.entity.Employee;
import io.jmix.core.DataManager;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

import java.util.List;

@RowLevelRole(name = "DepartmentOwnEmployeesEditingRole", code = DepartmentOwnEmployeesEditingRole.CODE)
public interface DepartmentOwnEmployeesEditingRole {
    String CODE = "department-own-employees-editing-role";

    @PredicateRowLevelPolicy(entityClass = Employee.class, actions = {RowLevelPolicyAction.UPDATE, RowLevelPolicyAction.DELETE})
    default RowLevelBiPredicate<Employee, ApplicationContext> employeePredicate() {

        return (employee, applicationContext) -> {
            DataManager dataManager = applicationContext.getBean(DataManager.class);
            List<Department> departments = dataManager.load(Department.class)
                    .query("select u.department from User u where u.id = :current_user_id")
                    .list();
            return !departments.isEmpty()
                    && employee.getDepartment() != null
                    && departments.get(0).getId().equals(employee.getDepartment().getId());
        };
    }
}