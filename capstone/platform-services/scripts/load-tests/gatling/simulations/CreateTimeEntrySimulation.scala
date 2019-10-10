
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class CreateTimeEntrySimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:8084")
		.acceptHeader("application/json")
  	.contentTypeHeader("application/json")

	val scn = scenario("TimesheetCreationSimulation")
		.exec(http("create-time-entry")
			.post("/time-entries")
			.body(StringBody("""{"projectId": 1, "userId": 1, "date": "2015-05-17", "hours": 6}""")).asJson)

	setUp(scn.inject(
		constantConcurrentUsers(100) during (2 minutes))).throttle(
		reachRps(10) in (0 seconds),
		holdFor(2 minutes)
	).protocols(httpProtocol)


}