**Admin: SignUp**
Post:localhost:8080/admin/signUp
Body: 
{
    "name": "admin",
    "email": "admin1@email.com",
    "password": "pass"
}


**Admin:Login**
Post: localhost:8080/admin/login
Body:
{
    "email":"admin1@email.com",
    "password": "pass1"
}


**Employee: SignUp**
Post: localhost:8080/emp/signUp
Body:
{
    "employeeId": "NL100",
    "employeeName":"Aditya Sakre",
    "role": "SDE",
    "email": "aditya@email.com",
    "password": "aspas"
}



**Admin: Get by Employee ID**
Get: localhost:8080/admin/getEmp/all
Header: "":""

**Admin: Get All Employee**
Get: localhost:8080/admin/getEmp/{EmpID}
example 
localhost:8080/admin/getEmp/NL101

**Admin: Delete by Employee ID**
Get: localhost:8080/admin/delEmp/{EmpID}

**Admin: Delete All Employee**
Get:localhost:8080/admin/delEmp/all


**Admin: Update By Employee ID**
Post: localhost:8080/admin/updateEmp/{EmpId}
Body: 
{
    "employeeName" : "Harry John",
    "role" : "SDE1"
}