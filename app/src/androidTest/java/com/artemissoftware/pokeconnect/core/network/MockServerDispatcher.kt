package com.artemissoftware.pokeconnect.core.network

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {

    var shouldReturnError = false


    fun successDispatcher(map: Map<String, String>): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {

                if(shouldReturnError){
                    return MockResponse().setResponseCode(200).setBody(getJsonContent("error.json"))
                }


                return when (request.path) {
                    POKEMON_PAGE_1 -> {
                        var json = ""
                        if (map.containsKey(POKEMON_PAGE_1)) {
                            json = map[POKEMON_PAGE_1]!!
                        }
                        MockResponse().setResponseCode(200).setBody(getJsonContent(json))
                    }
                    POKEMON_PAGE_2 -> {
                        var json = ""
                        if (map.containsKey(POKEMON_PAGE_2)) {
                            json = map[POKEMON_PAGE_2]!!
                        }
                        MockResponse().setResponseCode(200).setBody(getJsonContent(json))
                    }
                    else -> MockResponse().setResponseCode(200).setBody("")
                }
            }
        }
    }

    fun errorDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(getJsonContent("error.json"))
            }
        }
    }

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }

    companion object Path {
        const val POKEMON_PAGE_1 = "/pokemon?limit=20&offset=0"
        const val POKEMON_PAGE_2 = "/pokemon?limit=20&offset=20"

        val serviceMap: Map<String, String> = mapOf(
            Pair(POKEMON_PAGE_1, "pokemon.json"),
            Pair(POKEMON_PAGE_2, "pokemon_page_2.json"),
        )
    }
}
