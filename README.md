
# PDX Tech Meet Up Server
## Version 1.0
## by Johnny Ray Alt

## About
This server app stores information about a user. This includes the users name, a photo url, and a list of tech events they are interested in attending in the Portland area.

This is a student project and meant to be for documentation purposes only.

If you wish to clone this repo, you will need to set up your own host.

## Main url
> https://pdxmeetups.herokuapp.com/

## Format
> /api/{class}/{route}/{id}/{route}

## End Points

#### Users
* /api/users - Returns all users in the table
* /api/users/new - Posts a new user to the table
* /api/users/:id - Returns a specific user based on an ID
* /api/users/:id/update - Posts an update to an existing user based on ID
* /api/users/:id/delete - Deletes a user based on ID

### Events
* /api/users/:id/events - Shows all events associated with a user
* /api/users/:id/events/new - adds a new event to a user

### Keywords
* /api/keywords - Returns all keywords
* /api/keywords/new - Posts a new keyword
* /api/keywords/:id - Returns a specific keyword based on its id
* /api/keywords/:id/delete - Deletes a specific keyword based on its id

## JSON formating
### Add User JSON format
```
{
	"name": "Woll Smoth", // Accepts: String
	"photo": "https://i.ytimg.com/vi/AyFbegeRcCQ/maxresdefault.jpg" // Accepts: String
}
```

> A string value must be passed into the photo key. If there is no user
> input, a value of N/A should be passed in its place.

### Add Events JSON format
```
{
	"meetUpApiId": 58374, // Accepts: Integer
	"userId": 2 // Accepts: Integer
}
```

### Add Keywords JSON format
```
{
	"keyword": "Java" // Accepts: String
}
```
