# Description
Fullstack application for recommending appropriate sanctions against violence amongst students in school. There are 3 possible user roles: admin, teacher and student. Admin can add, view, update and delete teacher, classrom, student and student violence history. Teacher can view all his students and history of violence for each student and also file report for violence then system recommends sanction. Student can only view his profile and violence history.

## Predefined data
Database has test data to work with. Credentials for users are:

username: adminusername[1-3] or teacherusername[1-6] or studentusername[1-60]

password: #Intel1#


### Technologies
- [Spring boot](https://spring.io/projects/spring-boot)
- [Spring security](https://spring.io/projects/spring-security) and [jjwt 0.11.5](https://github.com/jwtk/jjwt) for authentication and authorization
- [Postgresql database](https://www.postgresql.org/)
- [Passay library v1.6.3](https://www.passay.org/) for password validation
- [Drools v7.49.0.Final](https://www.drools.org/)
- [Angular v16.0.0](https://angular.io/)

### How to run
First install model and kjar maven projects in local repository (in that order). Then build and run service maven project. Run angular application with npm.

### Author
* Nemanja Jevtić [GitHub](https://github.com/njevtic22)
