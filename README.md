# Spring Cloud Course Errata - Spring One 2019

## Instructors

- Bill Kable - bkable@pivotal.io
- Eitan Suez - esuez@pivotal.io

## Spring Cloud R&D

-   Alexey Nesterov: anesterov@pivotal.io
-   Bella Bai: ybai@pivotal.io

## References

### Spring Cloud Service Demo
*Spring Cloud Services Config Server Demo*

- https://github.com/spring-cloud-services-samples/cook

### Alternate Patterns and Architecture styles not covered in the course

*Hystrix Request Collapsing* - If you are doing a number of concurrent
requests you may be performance benefit of batching.
Hystrix provides this feature:

- https://github.com/Netflix/Hystrix/wiki/How-it-Works#request-collapsing

*Hystrix Caching*:

- https://github.com/Netflix/Hystrix/wiki/How-it-Works#request-caching

*Enterprise Integration Patterns*

- https://www.enterpriseintegrationpatterns.com/

*Reactive Messaging Patterns with Actor Model* (Thanks Danny)

- https://books.google.com/books/about/Reactive_Messaging_Patterns_with_the_Act.html?id=wc0oCgAAQBAJ&printsec=frontcover&source=kp_read_button#v=onepage&q&f=false

## Checkout these sessions at Spring One

*Spring Cloud on Kubernetes*
*Tuesday 11:30am–12:40pm, 19AB*

- https://springoneplatform.io/2019/sessions/spring-cloud-on-kubernetes

*Reactive Architectures with RSocket and Spring Cloud Gateway*
*Wednesday, 4:20pm, 19AB*

- https://springoneplatform.io/2019/sessions/reactive-architectures-with-rsocket-and-spring-cloud-gateway

*Spring Cloud Gateway for Stateless Microservice Authorization*
*Thursday, 12:30pm, Ballroom G*

- https://springoneplatform.io/2019/sessions/spring-cloud-gateway-for-stateless-microservice-authorization

*How to Live in a Post - Spring Cloud Netflix World*
*Tuesday, 2:00pm, 19AB*

- https://springoneplatform.io/2019/sessions/how-to-live-in-a-post-spring-cloud-netflix-world

*What's New in Spring Cloud Services 3*
*Tuesday, 3:20pm, 19AB*

- https://springoneplatform.io/2019/sessions/what-s-new-in-pivotal-spring-cloud-products

*Building Robust and Resilient Apps Using Spring Boot and Resilience4j*
*Thursday 12:30pm–1:30pm, 16AB*

- https://springoneplatform.io/2019/sessions/building-robust-and-resilient-apps-using-spring-boot-and-resilience4j

## Student Questions and Answers

**Question**: Will `@RefreshScope` work with Native file Spring Cloud Config backend?

**Answer**: Yes

**Question**: Does spring-cloud-bus use Eureka directly, or does it use an abstract discovery client?
The context of the question is from the 
[reference](https://cloud.spring.io/spring-cloud-bus/reference/html/) with following statement:
"The bus currently supports sending messages to all nodes listening or all nodes for a particular service (as defined by Eureka)".

**Answer**: Spring Cloud Bus does not have any direct or dependencies on Eureka.
It uses pub/sub model with AMQP and Kafka, not service discovery.
This is likely for example use in a Microservices architecture using Eureka as
service registry.

**Question**: Does Sleuth support instrumenting AMQP headers?

**Answer**: Yes (Thanks Matthew for the followup).  See the Spring Cloud Sleuth docs [Messaging](https://cloud.spring.io/spring-cloud-sleuth/reference/html/#messaging) for more broad discussion
of which messaging providers are supported.

**Question**: *I asked a question about using retry for ensuring cross service transactional integrity. The issue we have is 1 microservice is processing an order and making a request to a financial microservice. Occasionally the 2 services lose connectivity but only after the debit has been requested (and most of the time succeeds after the socket is severed). We are using retry with idempotent ids to have the order service re-request and see if the transaction completed. Is there a better way to approach this transient error?*

**Answer**: The short form answer to that has a few options.
Notice they are beyond the scope of the current Spring Cloud course,
and require different architecture patterns:

1.  Check out the [Saga Pattern](https://microservices.io/patterns/data/saga.html)
    for a coarse grained overview,
    and 
    [this blog](https://blog.bernd-ruecker.com/saga-how-to-implement-complex-business-transactions-without-two-phase-commit-e00aa41a1b1b).
    
1.  For traditional service transaction approach 
    (which might be REST/SOAP over HTTP, or other RPC integration),
    consider an 
    [Orchestration Saga](https://microservices.io/patterns/data/saga.html#example-orchestration-based-saga).
    From a Domain-driven-design (DDD) perspective, 
    the Saga might be considered its own bounded context if the complexity of coordination is complex.

2.  For event-based approach,
    consider using
    [Choreographed Saga](https://microservices.io/patterns/data/saga.html#example-choreography-based-saga).
    Event-based and event-sourcing architectures are gaining a lot of attention to solve these types of
    problems,
    but would introduce significant change to an existing, exclusive RPC architecture.
    
**Question**: *I asked a question about Hystrix exception handling for specific types of exceptions vs just run time. Can you please provide the link to the documentation.*

**Answer**: I was incorrect in stating this is (externally) configurable.
If you want to modify,
this would need to be done in code.
See [How to use Hystrix](https://github.com/Netflix/Hystrix/wiki/How-To-Use#error-propagation)
for more info how the error and fallback propagation works.


