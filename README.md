# Test cases:

![Test cases showing 100% test case pass](https://i.imgur.com/PAAAiyD.jpg)

## Test case 1: PrimaryTypeUtilTest

This test case confirms my function to pick the primary use type functions properly by picking the largest area.

## Test case 2: testGetAllSites

This test case tests the output of getting all of the sites in the database.

## Test case 3: testGetSiteByIdSuccess

This test case tests getting the Site + extra details (total sq footage + primary use type) for a valid Site in the DB

## Test case 4: testGetSiteById404

This test case tests requesting an invalid ID that is not in the database. It confirms a 404 is returned.

## Test case 5: testGetSiteBySize

This tests the function to confirm we filter our Sites by size properly. 



# Assumptions:

-I just used a 'data-h2.sql' file to populate the H2 database, as I assume this wouldn't be static data and in the 'real world' wouldn't be loaded on boot-up.

-It was slightly ambigious when asking for a list of all the sites whether that meant JSON representation of the Site database table, or the Site with details, like in the first task (i.e, including total size + primary type). I opted to demonstrate the former.

-It wasn't stated what to do in the case of 2 equally sized uses. In this case, we default to the first instance of the largest size.


# Things I would do with a bit more time

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


# Other misc notes:

-I made specific data transfer objects to transform into JSON (using gson) versus just transforming entities directly as I would've had to both add in some "ignore this attribute" annotations for GSON, plus if I did the entities directly I'd have to either eager fetch subentities (i.e, the list of siteUses in Site) or unproxy before letting gson transform them into JSON.

-I see warnings in the console for my site controller when swagger UI is launched:

	2020-05-17 18:07:02.568  WARN 17468 --- [nio-8080-exec-6] i.s.m.p.AbstractSerializableParameter    : Illegal DefaultValue null for parameter type integer
	

	java.lang.NumberFormatException: For input string: ""
	
From what I understand, you shouldn't have a default value for a required parameter. This is the first I've used Swagger, though, so it may have something to do with that.

