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

| Links | Endpoints |
|-------|-----------|


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

axios.post('https://nbapredictor-backend.herokuapp.com/', 
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
