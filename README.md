
Step 1: run back end
In root backend folder 'sg-kata-back-sb-main',  Install & Run Back end : mvn install & mvn sprint-boot:run ( on port 8080)

Step 2: run front end
In root front folder 'sga-kata-main', Install & Run front end: npm install & ng serve

Normally , Your are able to see your balance , your statement , you can deposit / withdrawl

Due to some time limitation for a kata : 

- I didn't go too far:
: on css design
: on account management
: Didnt create validators on backend
: I just implemented a InMemoryAdapter / Repository( but we can imagine a JPA implementation or an rest client call to a dedicated microservice)

I created some Acceptance Tests with BDD verbose & IT ( but probably not enought) 
