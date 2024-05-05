package com.artemissoftware.pokeconnect.core.network

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {

    fun successDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    PathFile.POKEMON_PAGE_1.path -> {
                        MockResponse().setResponseCode(200).setBody(getJsonContent(PathFile.POKEMON_PAGE_1.file))
                    }
                    PathFile.POKEMON_PAGE_2.path -> {
                        MockResponse().setResponseCode(200).setBody(getJsonContent(PathFile.POKEMON_PAGE_2.file))
                    }
                    PathFile.POKEMON.path -> {
                        MockResponse().setResponseCode(200).setBody(getJsonContent(PathFile.POKEMON.file))
                    }
                    PathFile.POKEMON_SPECIES.path -> {
                        MockResponse().setResponseCode(200).setBody(getJsonContent(PathFile.POKEMON_SPECIES.file))
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

    enum class PathFile(val path: String, val file: String){
        POKEMON_PAGE_1(path = "/pokemon?limit=20&offset=0", file = "pokemon_page_1.json"),
        POKEMON_PAGE_2(path = "/pokemon?limit=20&offset=20", file = "pokemon_page_2.json"),
        POKEMON(path = "/pokemon/1", file = "pokemon.json"),
        POKEMON_SPECIES(path = "/pokemon-species/1", file = "pokemon_species.json"),
    }
}
