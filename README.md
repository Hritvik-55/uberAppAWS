# uberAppAWS

## Technologies Used
  * Spring Boot for building RESTful APIs.
  * Lombok for reducing boilerplate code.
  * Spring Security for user authentication and authorization.
  * PostgreSQL for SQL Database.
  * POSTGIS extension in PostgreSQL for location.
  * Strategy design pattern for DriverAllocation, Payment and Ridefare calculation.
  * ModelMapper for transforming the DTO to concrete classes and vice-versa.
   
  

# Endpoints
  1. Signup (POST /auth/signUp)
    * Description: This endpoint allows users to register for the Uber application.
    * Request Body: Requires a SignUpDTO object containing user information like email, password, etc. (refer to SignUpDTO class documentation for specific fields)
    * Response: Returns a ResponseEntity with a UserDTO object containing user details and a CREATED (201) status code upon successful registration.
  2. Onboard New Driver (POST /auth/onBoardNewDriver/{userId})
    * Description: This endpoint allows authorized admins to onboard new drivers to the platform.
    * Authorization: Requires ROLE_ADMIN permission.
    * Request Path Variable: {userId} - ID of the user to be onboarded as a driver.
    * Request Body: Requires an OnBoardDriverDTO object containing vehicle details. (refer to OnBoardDriverDTO class documentation for specific fields)
    * Response: Returns a ResponseEntity with a DriverDTO object containing driver details and a CREATED (201) status code upon successful onboarding.
  3. Login (POST /auth/login)
    * Description: This endpoint allows users to login to the application.
    * Request Body: Requires a LoginRequestDTO object containing user email and password. (refer to LoginRequestDTO class documentation for specific fields)
    * Response: Returns a ResponseEntity with a LoginResponseDTO object containing an access token and an OK (200) status code upon successful login.
    * A refresh token is also sent as a cookie named "token" with the HttpOnly flag set.
  4. Refresh Token (POST /auth/refresh)
    * Description: This endpoint allows users to refresh their access token when it expires.
    * Request: Requires a valid refresh token sent as a cookie named "refreshToken".
    * Response: Returns a ResponseEntity with a LoginResponseDTO object containing a new access token and an OK (200) status code upon successful refresh.
    * If the refresh token is not found in the request or is invalid, an AuthenticationServiceException is thrown.
