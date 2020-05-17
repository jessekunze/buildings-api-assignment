Assumptions:

-I just used a 'data-h2.sql' file to populate the H2 database, as I assume this wouldn't be static data and in the 'real world' wouldn't be loaded on boot-up.

-It was slightly ambigious when asking for a list of all the sites whether that meant JSON representation of the Site database table, or the Site with details, like in the first task (i.e, including total size + primary type). I opted to demonstrate the former.

-It wasn't stated what to do in the case of 2 equally sized uses. In this case, we default to the first instance of the largest size.


Things I would do with a bit more time

-I was having an interesting issue with gson/optionals where in the controller for getting the details of a sight from an ID, it was wrapping it all in "Value", like below. 

To circumvent this and have it return what was specified in the requirements, I do a null check instead of an Optional.


[
"value":
  {
    "id": 1,
    "name": "Measurabl HQ",
    "address": "707 Broadway Suite 1000",
    "city": "San Diego",
    "state": "CA",
    "zipcode": "92101",
    "totalSize": 13000,
    "primaryType": {
      "id": 54,
      "name": "Office"
    }
  }
] 


-The logic to get all sites between X and Y size works, but it would be much more efficient to have that filtered in SQL. Right now, it just grabs 'em all then filters. If the database was huge, it would be a real performance issue.

-According to any potential business rules, an interface which allows insertions and updates of the database from the API.


Other misc notes:

-I made specific data transfer objects to transform into JSON (using gson) versus just transforming entities directly as I would've had to both add in some "ignore this attribute" annotations for GSON, plus if I did the entities directly I'd have to either eager fetch subentities (i.e, the list of siteUses in Site) or unproxy before letting gson transform them into JSON.

-I see warnings in the console for my site controller when swagger UI is launched:

	2020-05-17 18:07:02.568  WARN 17468 --- [nio-8080-exec-6] i.s.m.p.AbstractSerializableParameter    : Illegal DefaultValue null for parameter type integer
	

	java.lang.NumberFormatException: For input string: ""
	
From what I understand, you shouldn't have a default value for a required parameter. This is the first I've used Swagger, though, so it may have something to do with that.

