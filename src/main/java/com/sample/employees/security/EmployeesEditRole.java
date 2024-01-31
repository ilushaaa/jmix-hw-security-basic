package com.sample.employees.security;

import com.sample.employees.entity.Department;
import com.sample.employees.entity.Employee;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "EmployeesEditRole", code = EmployeesEditRole.CODE)
public interface EmployeesEditRole {
    String CODE = "employees-edit-role";

    @MenuPolicy(menuIds = "Employee.browse")
    @ScreenPolicy(screenIds = {"Employee.browse", "Employee.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.READ)
    void department();

    @EntityAttributePolicy(entityClass = Employee.class, attributes = "department", action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = Employee.class, attributes = {"id", "firstName", "lastName", "email", "birthDate"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Employee.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE})
    void employee();
}