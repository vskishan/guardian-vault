We all might have come across Authorization Servers but have you ever built a one ?

The motive of this project is to design and implement a simple authorization server

Here, we will be considering Authorization Code Grant Type for the sake of implementation

# Breakdown of the implementation

The following are the APIs that are being exposed : <br/>
* User Registration
* User Details Retrieval
* Client Registration
* Client Details Retrieval
* Generate Autthorization Code
* Generate Acess Token

👉 User Registration , Client Registration should be public APIs i.e. no authentication should be required

Database Design : <br/>
* user_vault table to store user's details such as username, password
* client_vault table to store client's details such as client_id, client_secret, scopes
* authzcode_vault table to store client_id (fk), authorization_code, redirect_uri, state

👉 Also, we will be storing the passwords, secrets and authorization codes in a base64 encoded format

# My article on Authorization Server
Hey, btw I've also written an interesting article explaining in and out of Authorization Server. <br/>
Do check out here: https://vskishan.substack.com/p/designing-an-oauth-20-authorization
