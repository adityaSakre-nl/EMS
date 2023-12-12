**Admin: SignUp** \
Post:localhost:8080/admin/signUp \
Body: 
{
    "name": "admin",
    "email": "admin1@email.com",
    "password": "pass"
} \


**Admin:Login**  \
Post: localhost:8080/admin/login \
Body:
{
    "email":"admin1@email.com",
    "password": "pass1"
}


**Employee: SignUp** \
Post: localhost:8080/emp/signUp \
Body:
{
    "employeeId": "NL100",
    "employeeName":"Person Name",
    "role": "SDET",
    "email": "name@email.com",
    "password": "aspas"
}



**Admin: Get by Employee ID** \
Get: localhost:8080/admin/getEmp/all \
Header: "Authorization":"token" \

**Admin: Get All Employee** \
Get: localhost:8080/admin/getEmp/{EmpID} \
Header: "Authorization":"token" \
example 
localhost:8080/admin/getEmp/NL101

**Admin: Delete by Employee ID** \
Get: localhost:8080/admin/delEmp/{EmpID} \
Header: "Authorization":"token" \


**Admin: Delete All Employee** \
Get:localhost:8080/admin/delEmp/all \
Header: "Authorization":"token" \


**Admin: Update By Employee ID** \
Post: localhost:8080/admin/updateEmp/{EmpId} \
Body: 
{
    "employeeName" : "Harry John",
    "role" : "SDE1"
}
Header: "Authorization":"token" \
