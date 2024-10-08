# uberAppAWS

## Technologies Used
  * Spring Boot for building RESTful APIs.
  * Lombok for reducing boilerplate code.
  * Spring Security for user authentication and authorization.
  * PostgreSQL for SQL Database.
  * POSTGIS extension in PostgreSQL for location.
  * Strategy design pattern for DriverAllocation, Payment and Ridefare calculation.
  * ModelMapper for transforming the DTO to concrete classes and vice-versa.
   
## Following endpoints provides an overview of the endpoints available in the com.application.uberApp.controllers.AuthController class. This controller handles user authentication and authorization functionalities for the Uber application.

## Endpoints
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

## Following endpoints provides an overview of the endpoints available in the com.application.uberApp.controllers.DriverController class. This controller handles functionalities specific to drivers within the Uber application.

* Authorization: All endpoints in this controller require the user to be authenticated as a driver (ROLE_DRIVER).

## Endpoints:

 1. Accept Ride (POST /drivers/acceptRide/{rideRequestId})
 * Description: Allows a driver to accept a pending ride request.
 * Request Path Variable: {rideRequestId} - ID of the ride request to be accepted.
 * Response: Returns a ResponseEntity containing a RideDTO object with details of the accepted ride upon success.
 2. Start Ride (POST /drivers/startRide/{rideRequestId})
 * Description: Allows a driver to start a previously accepted ride.
 * Request Path Variable: {rideRequestId} - ID of the ride to be started.
 * Request Body: Requires a RideStartDTO object containing the OTP received from the user. (refer to RideStartDTO class documentation for specific fields)
 * Response: Returns a ResponseEntity containing a RideDTO object with updated ride details upon successful ride start.
 3. End Ride (POST /drivers/endRide/{rideRequestId})
 * Description: Allows a driver to end a ride after it's completed.
 * Request Path Variable: {rideRequestId} - ID of the ride to be ended.
 * Response: Returns a ResponseEntity containing a RideDTO object with updated ride details upon successful ride completion.
 4. Cancel Ride (POST /drivers/cancelRide/{rideId})
 * Description: Allows a driver to cancel a previously accepted ride.
 * Request Path Variable: {rideId} - ID of the ride to be canceled.
 * Response: Returns a ResponseEntity containing a RideDTO object with updated ride details upon successful cancellation.
 5. Rate Rider (POST /drivers/rateRider)
 * Description: Allows a driver to rate a passenger after completing a ride.
 * Request Body: Requires a RatingDTO object containing the ride ID and the rating for the rider. (refer to RatingDTO class documentation for specific fields)
 * Response: Returns a ResponseEntity containing a RiderDTO object with updated rating information upon successful rating submission.
 6. Get My Profile (GET /drivers/getMyProfile)
 * Description: Retrieves the driver's profile information.
 * Response: Returns a ResponseEntity containing a DriverDTO object with the driver's details.
 7. Get My Rides (GET /drivers/getMyRides)
 * Description: Retrieves a paginated list of the driver's past and upcoming rides.
 * Request Parameters:
 * pageOffset (default: 0): The page number to retrieve (zero-based indexing).
 * pageSize (default: 10): The number of rides to retrieve per page.
 * Response: Returns a ResponseEntity containing a Page<RideDTO> object with ride details for the requested page.

## These endpoints provides an overview of the endpoints available in the com.application.uberApp.controllers.RiderController class. This controller handles functionalities specific to riders within the Uber application.

* Authorization: All endpoints in this controller require the user to be authenticated as a rider (ROLE_RIDER).

## Endpoints:

 1. Request Ride (POST /rider/requestRide)
 
 * Description: Allows a rider to request a ride from a nearby driver.
 * Request Body: Requires a RideRequestDTO object containing details about the desired ride (refer to RideRequestDTO class documentation for specific fields).
 * Response: Returns a ResponseEntity containing a RideRequestDTO object with details of the requested ride upon success.
 2. Cancel Ride (POST /rider/cancelRide/{rideId})
 
 * Description: Allows a rider to cancel a previously requested ride.
 * Request Path Variable: {rideId} - ID of the ride to be canceled.
 * Response: Returns a ResponseEntity containing a RideDTO object with updated ride details upon successful cancellation.
 3. Rate Driver (POST /rider/rateDriver)
 
 * Description: Allows a rider to rate a driver after completing a ride.
 * Request Body: Requires a RatingDTO object containing the ride ID and the rating for the driver. (refer to RatingDTO class documentation for specific fields)
 * Response: Returns a ResponseEntity containing a DriverDTO object with updated rating information upon successful rating submission.
 4. Get My Profile (GET /rider/getMyProfile)
 
 * Description: Retrieves the rider's profile information.
 * Response: Returns a ResponseEntity containing a RiderDTO object with the rider's details.
 5. Get My Rides (GET /rider/getMyRides)
 
 * Description: Retrieves a paginated list of the rider's past and upcoming rides.
 * Request Parameters:
 * pageOffset (default: 0): The page number to retrieve (zero-based indexing).
 * pageSize (default: 10): The number of rides to retrieve per page.
 * Response: Returns a ResponseEntity containing a Page<RideDTO> object with ride details for the requested page.
