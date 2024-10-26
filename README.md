# Api for user information

### Get all users
* Url: http://localhost:8080/api/users
* Swagger: http://localhost:8080/swagger-ui/index.html#

Expected output:
``` 
[{
"id": 0,
"username": "string",
"name": "string",
"surname": "string"
}]
```

> [!IMPORTANT]

 On **master** branch is situated version of code where only one datasource is connected.

And on **several-ds** branch - option with several DS connected.
However, it has a small nuance, it's not working properly.