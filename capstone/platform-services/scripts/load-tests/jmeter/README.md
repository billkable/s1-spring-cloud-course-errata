# README

If you elect to use 
[Apache Jmeter](https://jmeter.apache.org/)
for your load injector tool,
check out the tools provided here.

## Prerequisites

You must have already installed Jmeter load injector,
and referenced binaries in your PATH.

[Download Jmeter here](https://jmeter.apache.org/download_jmeter.cgi).

## Simulations

Jmeter leverages the *Test Plan* to drive load test scenarios through
client load injectors.

Two are provided:

-   `CreateTimeEntryTest` provides simple injector that by
    default runs 100 concurrent users at 10 rps rate for the
    TimeEntry Creation operation against the default `timesheets-server`
    running at `http://localhost:8084/time-entries` endpoint.

-   `CreateTimeEntryLoadBalancedTest` is identical to
    `CreateTimeEntryTest` except that it also injects load on a
    second `timesheets-server` running at 
    `http://localhost:8184/time-entries` endpoint.
    Use this when simulating a load balanced scenario.
    
