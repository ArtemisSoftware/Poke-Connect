package com.artemissoftware.pokeconnect.core.network

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {
    fun successDispatcher(map: Map<String, String>): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    POKEMON -> {
                        var json = ""
                        if (map.containsKey(POKEMON)) {
                            json = map[POKEMON]!!
                        }
                        MockResponse().setResponseCode(200).setBody(getJsonContent(json))
                    }
                    else -> MockResponse().setResponseCode(200).setBody("")
                }
            }
        }
    }

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }

    companion object Path {
        const val POKEMON = "pokemon"

        val serviceMap: Map<String, String> = mapOf(
            Pair(POKEMON, "pokemon.json"),
        )
    }
}
