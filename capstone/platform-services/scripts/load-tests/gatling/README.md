# README

If you elect to use 
[Gatling](https://gatling.io)
for your load injector tool,
check out the tools provided here.

## Prerequisites

You must have already installed Gatling load injector,
set the GATLING_HOME and referenced binaries in your PATH.

See the 
[Gatling installation instructions](https://gatling.io/docs/current/installation) 
for more information.

## Simulations

Gatling leverages the *Simulation* to drive load test scenarios through
client load injectors.

Two are provided:

-   `CreateTimeEntrySimulation` provides simple injector that by
    default runs 100 concurrent users at 10 rps rate for the
    TimeEntry Creation operation against the default `timesheets-server`
    running at `http://localhost:8084/time-entries` endpoint.

-   `CreateTimeEntryLoadBalancedSimulation` is identical to
    `CreateTimeEntrySimulation` except that it also injects load on a
    second `timesheets-server` running at 
    `http://localhost:8184/time-entries` endpoint.
    Use this when simulating a load balanced scenario.
    

    
