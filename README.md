# Sekolah Application

## Description

Sekolah Application is a Spring Boot-based web application designed to manage students and their subjects. The application allows users to register, log in, and manage their profiles. Admin users can add subjects to the system and assign these subjects to students. The application uses a relational database to store information about users and subjects.

## Main Features

1. **User Registration and Login:**
   - Users can register by providing their name, email, grade, major, and password.
   - Users can log in using their email and password.

2. **Profile Management:**
   - Users can view their profile information, including their registered subjects.
   - Users can update their profile details.

3. **Subject Management:**
   - Admin users can add new subjects to the system.
   - Subjects include details like subject name, grade, and major.

4. **Assign Subjects to Users:**
   - Admins can assign multiple subjects to a user.
   - Users can view their assigned subjects in their profile.

## Technologies Used

- **Backend:** Spring Boot
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript, Bootstrap
- **Database:** JPA (Java Persistence API) with Hibernate ORM
- **Authentication:** Basic authentication for login and registration
- **Build Tool:** Maven

## Key Endpoints

- `/api/user/add` - Register a new user
- `/login` - Login endpoint
- `/api/subject/add` - Add a new subject
- `/api/user/addSubjects` - Assign subjects to a user
- `/api/user/getListUser` - Fetch list of users based on filters (name, major, grade)

## Postman Collection

A Postman collection for testing the Sekolah Application API is included in this repository the file name is `SEKOLAH.postman_collection.json`.

## SQL DB

Sql Db is included with file `Sekolah.sql`

---
