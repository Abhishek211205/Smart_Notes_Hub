# Student Notes & Resource Sharing Portal

This is a Java Servlet + JSP web project for uploading/downloading notes, intended as a college-level project.

## Features
- User registration and login
- Upload notes (PDF/DOCX/PPT) to server storage
- List and download notes
- Simple admin placeholder

## Setup
1. Install Java (JDK 11+), Maven (optional) and Tomcat (or other servlet container).
2. Create MySQL database using `sql/notesportal_schema.sql`. Update DB credentials in `DBConnection.java`.
3. Deploy the webapp to Tomcat (copy the generated WAR or place folder in webapps).
4. Ensure the server has write permissions to the upload directory (default: `${catalina.home}/notes_uploads`).

## Files
- `src/main/java/com/notesportal/` - Java source files
- `src/main/webapp/` - JSP pages and web.xml
- `sql/notesportal_schema.sql` - database script
- `README.md` - this file
- `REPORT_TEMPLATE.md` - project report template

