# Mock Backend

Mock REST API using Express + Token Auth

## Run the server

```bash
npm install
npm run dev


## Test Using curl

```bash
curl -X POST http://localhost:3000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "test", "password": "1234"}'



```bash
curl -X GET http://localhost:3000/api/newsfeed \
  -H "Authorization: Bearer dGVzdC1tb2NrdG9rZW4="