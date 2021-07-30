# Wishlist-API

## Requisitos para Rodar
1. Setup mongo docker container
`docker pull mongo`
`docker run mongo`


## Endpoints
1. Create User
```
curl --location --request POST 'http://localhost:8080/api/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "augusto",
    "loginName": "augusto3",
    "password": "1234567890",
    "confirmPassword": "1234567890"
}'
```

2. Generar Token para os requests de adicionar e remover e listar wishlist
```
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginName": "augusto3",    
    "password": "1234567890"
}'
```
PS: O response desse request, no header, conterá o bearer token que **deve** ser utilizado nos proximos requests

3. Adicionar wishlist
```
curl --location --request POST 'http://localhost:8080/api/wishlist/addToWishlist' \
--header 'Authorization: Bearer Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhdWd1c3RvMyIsImV4cCI6MTYyODUxMDkwMX0.Q49MPUcX1pTpkoL7_B6v0NWZuoRZTA4xZCnkzjuVQD2XA7P9Y617XRtR3hzYSYVA6chkEQMWPaqbszmlJIRZeA' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginName":"augusto3",
    "itemName": "item123"
}'
```

4. Remover wishlist

```
curl --location --request POST 'http://localhost:8080/api/wishlist/removeFromWishlist' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhdWd1c3RvMyIsImV4cCI6MTYyODUxMDkwMX0.Q49MPUcX1pTpkoL7_B6v0NWZuoRZTA4xZCnkzjuVQD2XA7P9Y617XRtR3hzYSYVA6chkEQMWPaqbszmlJIRZeA' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginName":"augusto3",
    "itemName": "item123"
}'
```

5. Listar wishlist

```
curl --location --request GET 'http://localhost:8080/api/wishlist/augusto3' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhdWd1c3RvMyIsImV4cCI6MTYyODUxMDkwMX0.Q49MPUcX1pTpkoL7_B6v0NWZuoRZTA4xZCnkzjuVQD2XA7P9Y617XRtR3hzYSYVA6chkEQMWPaqbszmlJIRZeA'
```
