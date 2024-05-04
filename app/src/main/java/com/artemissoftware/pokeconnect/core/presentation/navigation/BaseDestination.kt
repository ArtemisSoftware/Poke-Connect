package com.artemissoftware.pokeconnect.core.presentation.navigation

import android.net.Uri
import androidx.navigation.NamedNavArgument
import com.google.gson.Gson

abstract class BaseDestination(
    private val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {

    fun fullRoute(): String {
        return if (arguments.isEmpty()) route else completeRoute
    }

    private val completeRoute: String = buildString {
        append(route)
        arguments.forEachIndexed { index, custom ->
            val symbol = if (index == 0) "?" else "&"
            append("$symbol${custom.name}={${custom.name}}")
        }
    }

    fun withCustomArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, argument ->
                val json = if(argument is String){
                    Uri.encode(argument)
                } else {
                    Uri.encode(Gson().toJson(argument))
                }
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${arguments[index].name}=$json")
            }
        }
    }
}
