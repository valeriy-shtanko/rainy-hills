# Rainy Hills Applications

Rainy hills application takes an array of integer numbers (hills landscape) as an input, and calculates the volume of water
which remained after the rain between hills.

Theoretical algorithm complexity is linear - O(n_, as it incorporates only non-nested cycles.

Downside of the algorithm is high coefficient of memory usage : in each case it requires triple the size of input data 
to maintain temporary values for the final calculation to be performed in one linear pass.

## Project `pom.xml`

This project is a web application based on WildFly Swarm with JAX-RS and CDI dependencies and 
with maven packaging of `war` in the `pom.xml`.

It also creates a `-swarm` runnable jar. 

## Run

You can run application project in several ways:

* deploy built war to EJB container (JBoss or WildFly)
* mvn wildfly-swarm:run
* mvn package && java -jar ./target/rainy-hills-swarm.jar

## Use

To calculate volume of water which remained between hills after the rain send 
POST request to
    
    http://localhost:8080/rainy-hills/calculate        

with payload (hills landscape)

    { "hills" : [1, 10, 5 ...] }
 

To test is application running and get some 'How To' information, open in browser :
    
    http://localhost:8080/rainy-hills/calculate

