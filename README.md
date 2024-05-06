# Poke-Connect
Pokedex application, where we can browse pokemons, check their details and save our favorites

## The Brief

App that searches pokemons from the [pokeapi](https://pokeapi.co/)

## Features
- **Browse Pokémon**: Explore a comprehensive list of Pokémon
- **View Pokémon Details**: Get detailed information about each Pokémon, including base stats, abilities, and others.
- **Save Favorites**: Mark your favorite Pokémon and quickly access them from a dedicated section.

Additionally, I wanted the app to be scalable, testable, and maintainable. That's why I chose clean architecture as the underlying structure.

## Architecture & Libraries
    - MVI
    - Local & Remote Data Sources
    - Retrofit
    - ROOM Database
    - Dependency Injection - Dagger-Hilt
    - Kotlin Coroutines
    - Material Design
    - Shimmer Effect
    - Flow
    - Dark Mode
    - Landscape mode


## App preview:




Image #1            |  Image #2             |  Image #3           
:-------------------------:|:----------------------------:|:----------------------------:
<img src="images/Demeter_Recipes_1.jpg">    |  <img src="images/Demeter_Recipes_2.jpg">     |  <img src="images/Demeter_Recipes_3.jpg"> 

Image #1            |  Image #2             |  Image #3           
:-------------------------:|:----------------------------:|:----------------------------:
<img src="images/Demeter_Recipes_1.jpg">    |  <img src="images/Demeter_Recipes_2.jpg">     |  <img src="images/Demeter_Recipes_3.jpg"> 


# App Architecture Description

In this architecture, the application is designed to be modular, with a core module that encapsulates shared functionalities and resources used across different parts of the app. This modular approach promotes reusability, maintainability, and scalability. Let's dive into the structure and the components of the core module.

## Core Module
The core module serves as the central repository for common functionalities, providing a clean and organized structure. Here's an overview of its key components:

### Common
This package houses utilities, constants, extensions, and helper functions that are commonly used throughout the application. It aims to reduce code duplication by consolidating generic logic in one place.

### Design System
The design system component includes shared UI elements such as themes, colors, typography, and reusable components. This ensures a consistent look and feel across the entire app.

### Network
This component manages network-related tasks, including API calls, network clients (such as Retrofit or OkHttp for RESTful communication), and error handling logic. It also defines common request/response structures and handles network-based utilities.

### Database
The database component is responsible for local data storage. It defines the structure of the local database (like SQLite), manages database migrations, and provides DAOs (Data Access Objects) for querying and modifying local data.

### Models
This package contains data models and entities used throughout the app. These models represent business data and are shared across different layers, facilitating data transfer between modules.

### Presentation
The presentation component includes view models, presenters, and other logic related to presenting data to the user. This package acts as the bridge between the UI and the underlying business logic, often following the MVVM or MVP pattern.

### UI
This package contains the user interface components, such as activities, fragments, widgets, and custom views. It leverages elements from the design system to create a cohesive user experience.

# Data Module

The data module serves as a central repository for all data-related operations, including data retrieval, storage, and transformation. It is the backbone for accessing, modifying, and managing the app's data. Within this module, there are two key sub-modules:

## Pokedex Sub-Module
The Pokedex sub-module handles everything related to retrieving and managing Pokémon data. Its key responsibilities include:

- **Data Retrieval**: Communicating with external sources like PokéAPI to fetch data about Pokémon, including their names, types, abilities, and stats.
- **Data Storage**: Storing Pokémon data in a local database for offline access and caching purposes.
- **Data Transformation**: Converting raw data from external sources into the format expected by the rest of the app.

This sub-module provides a clean interface for fetching Pokémon data, ensuring that all logic related to Pokémon retrieval and management is centralized in one place.

## Favorites Sub-Module
The Favorites sub-module is responsible for managing user-defined favorite Pokémon. Its key responsibilities include:

- **Data Storage**: Storing and retrieving favorite Pokémon in a local database, ensuring persistence across app sessions.
- **Business Logic**: Handling operations like adding a Pokémon to favorites, removing it, or checking if a Pokémon is marked as a favorite.
- **Data Access**: Providing a clean interface for other parts of the app to interact with the favorites data, allowing easy integration with UI components that display favorite Pokémon.

By separating favorites management into its own sub-module, the app can easily add, remove, or modify the way favorites are handled without affecting other parts of the codebase.

# Domain Module

The domain module serves as the core of the application, containing business logic, entities, and use cases. It defines the "what" of the application rather than the "how." The focus is on representing the core concepts and business rules of the app, independent of frameworks or technologies. This module is designed to be framework-agnostic, ensuring flexibility and testability.

Within the domain module, there are two key sub-modules:

## Pokedex Sub-Module
The Pokedex sub-module represents the domain logic related to Pokémon data. It contains business logic for operations like fetching Pokémon lists, retrieving Pokémon details, and defining Pokémon-related entities.

## Favorites Sub-Module
The Favorites sub-module is responsible for managing business logic related to user-defined favorite Pokémon. This includes adding or removing favorites, checking if a Pokémon is marked as a favorite, and retrieving the list of favorite Pokémon.

By isolating the business logic for favorites in its own sub-module, the app can easily manage favorites without affecting other domain concepts. This separation allows for easier testing, flexibility, and the ability to evolve the app's functionality over time.

# Features Module

The features module serves as the primary entry point for feature-specific logic. It acts as a collection of sub-modules, each focused on a specific feature of the application. This design pattern helps maintain a clean architecture by ensuring that each feature is self-contained and only interacts with other parts of the app through well-defined interfaces.

## Pokedex Sub-Module
The Pokedex sub-module is responsible for all functionality related to browsing and managing Pokémon data. It handles the user interface, view models, and navigation for displaying Pokémon lists and details.

## Favorites Sub-Module
The Favorites sub-module manages all operations related to user-defined favorite Pokémon. Its key responsibilities include the user interface for displaying the list of favorite Pokémon, business logic for adding/removing favorites, and navigation related to the favorites section.

## Details Sub-Module
The Details sub-module is responsible for displaying detailed information about a specific Pokémon. It manages the user interface for presenting detailed Pokémon information, such as stats, abilities, and evolutions, and handles the related view models and navigation.

By using sub-modules, this approach reduces direct dependencies and promotes a clean, modular design.
