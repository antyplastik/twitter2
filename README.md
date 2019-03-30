# twitter2

If you have installed Docker, run:

`docker-compose up --build`

Or

- Prepare a MySql database with `twitter2` db
- Change the connection string from `jdbc:mysql://mymysql:3306/twitter2` to `"jdbc:mysql://localhost:3306/twitter2"` in `persistence.xml`
- Run `init.sql` against your db
- Update `context.xml` and `tomcat-users.xml` in your `tomcat/conf` (correct content of the files can be found in `tomcat-config`)
- ???

Use `mvn package` to create a `*.war` file, that can be uploaded into Tomcat

#### TODO

Connect `mvn deploy` with Tomcat

chrzasz forked