### Tools, Best practices, Tech stack, Innovations, Design Patterns, Principles, TODO focus

AOP
- log memory info before and after method call
- log annotation
- log req and resp in servlet rest 

Annotation
- NoLogArguments
- Measurable

Thread
- asyncCOnfiguration
- MDC decorator

Dao
- jdbcTemplate, datasource

Scheduler 
- fetching some data periodically
- clean data scheduled by cron eg. tmp data for file reports

CommandLineRunner
- checksums
- buildProperties

Security
- common shared @websecurityconfig
- ldap, database sysuser, cert x509, basicauth

LDAP
- list of users
- list of roles

Gradle/Groovy
- scripts 

Angular
- services, firebase and spring kick off
- ag grid
- Subject https://www.learnrxjs.io/learn-rxjs/subjects

Tests, e2e tests, integration tests, mockito
- selenium, cucumber, junit5

NoSQL database 
- firestore GCP
- mongoDB
- nestjs https://nestjs.com/ 

RDBMS databases
- oracle
- postgresql

AWS 
- services, amplify 

Java 17 features

Messaging system
Kafka

Angular topics, todo
- flip card (desc, title, tags, map, category, number of watchers)
- tags location, time added, status (used, new), weight
- buttons
- footer, header, routing
- detail
- security 
- firestore entities
- table with users - user statistics report
- table with logs - audit logs 
- user detail with tabs (first tab reports, second tab logs, third tab statistics charts)
- table grid with refinements #table_grid (expand row that has childs, for eg. user and his list of orders)
- #table_grid dropdown box to list all users according privilege or to list all users according location with specific title (for eg. Praque user1, user2, etc.)



### Best practices + notes
https://github.com/in28minutes/java-best-practices?tab=readme-ov-file#why-should-you-understand-performance-of-string-concatenation
https://howtodoinjava.com/java-best-practices/
https://www.javacodegeeks.com/2015/06/java-programming-tips-best-practices-beginners.html
https://github.com/dereknguyen269/programing-best-practices-in-2024?tab=readme-ov-file#java-best-practices

Immutables
https://reflectoring.io/java-immutables/#immutable-bad-practices
https://reflectoring.io/java-immutables/

https://www.baeldung.com/java-string-concatenation-methods
https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-string-operations-6

JMH Java Microbenchanchmark Harness framework in Java (under maven) to measure performance between small code snippets 

Angular multiselect component
https://nileshpatel17.github.io/ng-multiselect-dropdown/



#### UI component libraries, resources 
https://adminkit.io/  
https://demo.adminkit.io/

MDB
https://mdbootstrap.com/freebies/admin/

https://fontawesome.com/icons/location-dot?f=classic&s=solid

https://www.mariokandut.com/what-is-routing-in-angular-with-basic-example/
https://www.telerik.com/blogs/angular-basics-using-hostlistener-decorator-detect-changes-specific-dom-element

Peer dependencies
https://nodejs.org/en/blog/npm/peer-dependencies/
https://dev.to/icy0307/peer-dependencies-in-depth-1o3b

npm install @swimlane/ngx-charts --save --legacy-peer-deps
npm install @types/d3 --save-dev
"peerDependencies": {
"@types/d3": "^7.4.3",
"@angular/cdk": "^16.2.0"

}
https://github.com/swimlane/ngx-charts/blob/master/docs/examples/pie-charts/pie-chart.md


