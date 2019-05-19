1. Please login through http://localhost:8080/login
2. I initialize getting book from SCB at runtime to put all book list into a cache.
3. This project uses cache to keep the data from SCB book list and TTL is 1 day.
4. This project uses Hibernate to generate tables, so after run at the first time, please change spring.jpa.hibernate.ddl-auto from 'create' to 'none'