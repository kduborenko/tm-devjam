# Ticket Master DevJam blank project

## Prerequisites

* JDK 1.8
* Maven 3.3.9

## Run

1. Configure API keys.

Add file src/main/resources/application-local.yml
    
    ticketMaster:
      apiKey: <insert api key here>
      
2. Run application

    $ mvn spring-boot:run -Drun.profiles=development,local
    
Open: [http://localhost:8080/](http://localhost:8080/)
