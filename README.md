# NBA Career Longevity Predictor - Back End

NBA contracts are sometimes 9 figures long. It is in a team's best interest to have a good idea of the longevity of a player's productivity. This application uses historical data to find similar players and predict a selected player's likely longevity and success in the league.

[**Product Canvas Document**]

---

## **API Documentation**

**BASE URL** https://nbapredictor-backend.herokuapp.com/

- Attach endpoints to the Base URL to hit them with HTTP Requests

### **Table of Contents**

#### NON-PROTECTED ENDPOINTS

| Links                                   | Endpoints            |
| --------------------------------------- | -------------------- |
| [POST Registration](#post-registration) | `/signup`            |
| [POST Login](#post-login)               | `/login`             |

#### PROTECTED ENDPOINTS

| Links                                 | Endpoints                   |
|---------------------------------------|-----------------------------|
| [GET Logout](#get-logout)             | `/logout`                   |
| [GET My Players](#get-my-players)     | `/players/all`              |
| [GET Player By Id](#get-player-by-id) | `/players/:playerid`        |
| [POST Player](#post-player)           | `/players/createplayer`     |
| [PUT Player](#put-player)             | `/players/update/:playerid` |
| [DELETE Player](#delete-player)       | `/players/delete/:playerid` |

---

### [POST] Registration

#### URL: https://nbapredictor-backend.herokuapp.com/signup

**Payload:** _an object with the following credentials:_

> **Required:** `username`, `password`, & `email`

```json
{
  "username": "newUsername",
  "password": "newPassword",
  "email": "johndoe@gmail.com",
}
```

**Return:** _An access token will be returned. Save this token to local storage (or similar). This token will be required for all HTTP requests below (protected endpoints)._

```json
{
  "access_token": "5eafc233-53ea-42ce-814b-5b56e53acfdd",
  "token_type": "bearer",
  "expires_in": 3599,
  "scope": "read trust write"
}
```

[Back to Top](#table-of-contents)

---

### [POST] Login

#### URL: https://nbapredictor-backend.herokuapp.com/login

**Request Structure:** _the username and password with an authorization header containing the CLIENTID and CLIENTSECRET_

```javascript

axios.post('https://nbapredictor-backend.herokuapp.com/login', 
           `grant_type=password&username=${USERNAME}&password=${PASSWORD}`, 
           {
            headers: { Authorization: `Basic ${btoa('lambda-client:lambda-secret')}`,
                      'Content-Type': 'application/x-www-form-urlencoded'
                      },
            })
```

**Return:** _An access token will be returned. Save this token to local storage (or similar). This token will be required for all HTTP requests below (protected endpoints)._

```json
{
  "access_token": "5eafc233-53ea-42ce-814b-5b56e53acfdd",
  "token_type": "bearer",
  "expires_in": 3599,
  "scope": "read trust write"
}
```

[Back to Top](#table-of-contents)

---

### [GET] Logout

#### URL: https://nbapredictor-backend.herokuapp.com/logout

**Return:** _Removes token from token store and returns nothing._

[Back to Top](#table-of-contents)

---

### [GET] My Players

#### URL: https://nbapredictor-backend.herokuapp.com/players/all

**Return:** _An array of all players associated with the user currently logged in._

```json
[
    {
        "playerid": 14,
        "imgurl": "link here",
        "name": "Fname Lname",
        "position": "string",
        "height": "string",
        "weight": "string",
        "college": "string",
        "draft_year": 0,
        "draft_pick": 0,
        "drafted_by": "string",
        "pts_pg": 0.0,
        "rebounds_pg": 0.0,
        "assists_pg": 0.0,
        "mins_pg": 0.0,
        "prediction": 0,
        "similarplayers": []
    }
]
```

[Back to Top](#table-of-contents)

---

### [GET] Player By Id

#### URL: https://nbapredictor-backend.herokuapp.com/players/:playerid

> Place the id of the player which you are requesting data for in the url parameter `:playerid`

**Return:** _A player is returned based on the player id provided._

```json
    {
        "playerid": 14,
        "imgurl": "link here",
        "name": "Fname Lname",
        "position": "string",
        "height": "string",
        "weight": "string",
        "college": "string",
        "draft_year": 0,
        "draft_pick": 0,
        "drafted_by": "string",
        "pts_pg": 0.0,
        "rebounds_pg": 0.0,
        "assists_pg": 0.0,
        "mins_pg": 0.0,
        "prediction": 0,
        "similarplayers": []
    }
```

[Back to Top](#table-of-contents)

---

### [POST] Player

#### URL: https://nbapredictor-backend.herokuapp.com/players/createplayer

>**Required:** a valid player object must be provided with an array of similarplayers

```json
{
  "imgurl": "url here",
  "name": "Fname Lname",
  "position": "string",
  "height": "string",
  "weight": "string",
  "college": "string",
  "draft_year": 0,
  "draft_pick": 0,
  "drafted_by": "string",
  "pts_pg": 0.0,
  "rebounds_pg": 0.0,
  "assists_pg": 0.0,
  "mins_pg": 0.0,
  "prediction": 0,
  "similarplayers" :[]
}
```

**Return:** _An updated array of all players associated with the user currently logged in._

```json
[
    {
        "playerid": 14,
        "imgurl": "link here",
        "name": "Fname Lname",
        "position": "string",
        "height": "string",
        "weight": "string",
        "college": "string",
        "draft_year": 0,
        "draft_pick": 0,
        "drafted_by": "string",
        "pts_pg": 0.0,
        "rebounds_pg": 0.0,
        "assists_pg": 0.0,
        "mins_pg": 0.0,
        "prediction": 0,
        "similarplayers": []
    }
]
```

[Back to Top](#table-of-contents)

---

### [PUT] Player

#### URL: https://nbapredictor-backend.herokuapp.com/players/update/:playerid

> Place the id of the player which you are updating data for in the url parameter `:playerid`

>**Required:** an object with the selected properties updated

```json
{
  "imgurl": "updated url here",
  "position": "updated string",
  "college": "updated string",
}
```

**Return:** _An updated array of all players associated with the user currently logged in._

```json
[
    {
        "playerid": 14,
        "imgurl": "link here",
        "name": "Fname Lname",
        "position": "string",
        "height": "string",
        "weight": "string",
        "college": "string",
        "draft_year": 0,
        "draft_pick": 0,
        "drafted_by": "string",
        "pts_pg": 0.0,
        "rebounds_pg": 0.0,
        "assists_pg": 0.0,
        "mins_pg": 0.0,
        "prediction": 0,
        "similarplayers": []
    }
]
```

[Back to Top](#table-of-contents)

---

### [DELETE] Player

#### URL: https://nbapredictor-backend.herokuapp.com/players/delete/:playerid

> Place the id of the player which you are deleting in the url parameter `:playerid`

**Return:** _An updated array of all players associated with the user currently logged in._

```json
[
    {
        "playerid": 14,
        "imgurl": "link here",
        "name": "Fname Lname",
        "position": "string",
        "height": "string",
        "weight": "string",
        "college": "string",
        "draft_year": 0,
        "draft_pick": 0,
        "drafted_by": "string",
        "pts_pg": 0.0,
        "rebounds_pg": 0.0,
        "assists_pg": 0.0,
        "mins_pg": 0.0,
        "prediction": 0,
        "similarplayers": []
    }
]
```

[Back to Top](#table-of-contents)

---
