package com.artemissoftware.pokeconnect.core.designsystem.window

data class WindowSize(
    val width: WindowType,
    val height: WindowType
){
    fun isLandScape() = if(height == WindowType.Medium || height == WindowType.Expanded) false else true
}