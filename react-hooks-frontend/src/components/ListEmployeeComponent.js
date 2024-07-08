import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
         getAllEmployees();
    }, []);

    const getAllEmployees = () => {

        EmployeeService.getAllEmployees()
            .then(response => {
                setEmployees(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error('Error fetching employees:', error);
            });
    }


    const deleteEmployee = (employeeId) => {
        console.log("eliminado: " + employeeId);

        EmployeeService.deleteEmployee(employeeId).then((response) => {

            getAllEmployees();
        }).catch(error=> {
            console.log(error);
        })

    }

    return (
        <div className="container">
            <h1 className="text-center">List Employees</h1>
            <Link to="/add-employee" className="btn btn-primary mb-2">
                Add Employee
            </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee First Name</th>
                        <th>Employee Last Name</th>
                        <th>Employee Email Id</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map(employee => (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.emailId}</td>
                            <td>
                                <Link to={`/edit-employee/${employee.id}`} className="btn btn-info">Update</Link>
                                <button className="btn btn-danger" onClick={() => deleteEmployee(employee.id)} style={{marginLeft:"10px"}}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListEmployeeComponent;
