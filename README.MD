# Nevyhod Story Course Project 
This repository is used to demonstrate interesting topics around Full Stack development, specifically around Java and Angular (backend and frontend, BE + FE).

The main intention is to collect and practice innovative ideas in high level (or under the hood) on real story examples. Every story example should be ideally related to base main story - nevyhod (coming from repository title).

As a developer you can meet with a lot of useful core aspects such as: patterns, best practices, database concepts, useful libraries, prototyping, frontend design, custom libs, devops, k8s, docker, and so on and so forth.

So feel free to use it and enjoy


## How to run the application without docker
```
for BE
./gradlew :nevyhod-gui:bootRun -x test --info

for FE
utility-components-frontend
ng build utility-components-lib --watch
ng serve
```

## List of used urls
```
http://localhost:8080/swagger-ui/api-docs
http://localhost:8080/swagger-ui/api-docs.yaml
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/h2-console/
```

## Useful notes, support guide (npm etc.)
```
Check if there are any updates for project dependencies
npm outdated

npm info @swimlane/ngx-charts  peerDependencies
```
