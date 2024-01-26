package com.sample.employees.screen.department;

import io.jmix.ui.screen.*;
import com.sample.employees.entity.Department;

@UiController("Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
public class DepartmentBrowse extends StandardLookup<Department> {
}