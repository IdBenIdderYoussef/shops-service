# Shops Service

This is a **spring-boot** rest api with **java 8**.

## Description

Rest Api for full-stack united remote coding challenge.<br/>

## Features of the api

This api provide a list of functionality :

- Sign up and sign in using email and password.
- Get the list of shops sorting by distance if the user provide his location or just with the order of appearance in database if he don't.<br/>
- Like a shop, so it will be add the preferred shops list, and won't be with the main list of shops. Also the user can remove his like.
- Get the list of preferred shops.
- Dislike a shop, so it won't be in the main list of shops within the next two hours.

## The Implementation of the api :

This rest api build using a multi-layer architecture, in which there is six layers:

#### model layer (model package): 

Here where we define our business classes :

- **User:** that represent information about users registered in this api (their credentials).<br/>
- **Shop :** that represent information about our shops like name, location and an image of the shop.<br/>
- **Location :** that represent the latitude and the longitude of the location.<br/>
- **UserDislikedShop :** represent the user and shops that disliked.<br/>

#### Repository layer (repository package):

This layer exposes basic **CRUD** and give us an additional level of abstraction over data access, also 
facilitate the process of handling the data.

#### business logic layer (service package):

This layer expose the business logic of the api, this makes the api easy to test since there
are no dependencies to the controller or to the presentation layer. So this layer should be between 
the controller layer and the data access layer (repository layer) and make a sort of responsibility separation. <br/> 
In this layer we use our repository to access data.

#### controller layer (controller package):

In this layer we handle the incoming requests, extract parameters and prepare to call the business logic and retrieve the result.<br/>
This layer make a separation between the logic business and the http requests responses logic.

#### security layer (security package):

This a layer where we make a restrictions to our api layers and components, using the json web token **(JWT)** standard.<br/>
In this layer we have two filters one for the authentication and the other one for the authorisation. 

#### configuration layer (configuration package):

In this layer we hold all the configurations of the api, and in our case we have the configuration of the security layer.

### Database:

We use Mysql as database management system.

### Libraries

These is some of the libraries used in this api :

##### Maven : 
For build and package.

##### Lombok :
Facilitate and reduce the amount of code, provide a lot of annotations. Also have a pre-implementation of the builder design pattern.

#### Java JWT:
It's a java implementation of JSON Web Token (JWT) standard.

