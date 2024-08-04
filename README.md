# sudheer_vt_04_08_2024
URL Shortner

### Design of a url shortner

- Given a long URL, the service should generate a shorter and unique alias of it.
- When the user hits a short link, the service should redirect to the original link.
- Links will expire after a standard default time span - 10 months.
- The system should be highly available. This is really important to consider because if the service goes down, all the URL redirection will start failing.
- URL redirection should happen in real-time with minimal latency.
- Shortened links should not be predictable.
- Short URL length can be up to 30 characters starting from prefix: http://localhost:8080/

Assuming, we will have 5M new URL shortenings per month, with 100:1 read/write ratio

Database to be used : PostgreSQL

APIs to implement 

1. **POST**: Shorten Url (Destination Url) → Short Url, id → Success/ Failure 
2. **POST**: Update short url (Short Url, Destination Url) → Boolean 
    1. update meaning : new destination link on same short link
3. **GET**: Get Destination Url (Short Url) → Destination Url
4. **POST**: Update Expiry (Short Url, Days to add in expiry) → Boolean
