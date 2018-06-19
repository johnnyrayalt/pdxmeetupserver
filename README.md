# PDX Tech Meet Up Server


## Main url
* https://pdxmeetups.herokuapp.com/

## End Points

* /api/users - Returns all users in the table
* /api/users/new - Posts a new user to the table
* /api/users/:id - Returns a specific user based on an ID
* /api/users/:id/update - Posts an update to an existing user based on ID
* /api/users/:id/delete - Deletes a user based on ID

## Format
* https://pdxmeetups.herokuapp.com/api/users/new
* Accepts standard JSON


### Add User JSON format
```
{
	"name": "Woll Smoth",
	"photo": "https://i.ytimg.com/vi/AyFbegeRcCQ/maxresdefault.jpg"
}
```


* Both Keys accept values in the form of strings
* A string value must be passed into the photo key. If there is no user input, a value of N/A should be passed in its place.