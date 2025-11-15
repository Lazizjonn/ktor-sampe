package it.roma.sapienza

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val profileJson = environment.classLoader
        .getResource("questionnaires/theBIKEnet_profile.json")
        ?.readText()
        ?: error("Failed to load profile survey questionnaires ")

    val preTripJson = environment.classLoader
        .getResource("questionnaires/theBIKEnet_trip_pre.json")
        ?.readText()
        ?: error("Failed to load pre trip survey questionnaires ")

    val postTripJson = environment.classLoader
        .getResource("questionnaires/theBIKEnet_trip_post.json")
        ?.readText()
        ?: error("Failed to load post trip survey questionnaires ")

    routing {

        get("questionnaires/profile") {
            call.respondText(profileJson, contentType = ContentType.Application.Json)
        }

        get("questionnaires/pre_trip") {
            call.respondText(preTripJson, contentType = ContentType.Application.Json)
        }

        get("questionnaires/post_trip") {
            call.respondText(postTripJson, contentType = ContentType.Application.Json)
        }

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
