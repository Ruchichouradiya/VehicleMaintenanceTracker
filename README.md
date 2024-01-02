# VehicleMaintenanceTracker
The Vehicle Maintenance Tracker is a backend-focused application that helps users monitor and manage their vehicle's maintenance schedule. It provides a centralized system for tracking service history, setting reminders for routine maintenance.


**Technologies Used**
Java
Spring Boot
Spring Data JPA
Spring Security
RESTful API
Maven for Dependency Management

**Getting Started**
Prerequisites
Java Development Kit (JDK) 8 or higher
Maven
Your preferred IDE (e.g., IntelliJ IDEA, Eclipse)
Setup

Clone the repository:
git clone https://github.com/Ruchichouradiya/VehicleMaintenanceTracker.git


**userauth-service**
Users start by registering an account or generating token if they already have one. 

**Post mapping** - http://localhost:8080/api/auth/register
Sample request
{
    "username": "test@email.com",
    "password": "test123"
}

**Post Mapping** -  http://localhost:8080/api/auth/generate-token
{
    "username": "test@email.com",
    "password": "test123"
}
in response it will return which can further used in Api getaway for verification of other microservice

**vehicle-mgmt-service**
   - Exposes APIs related to managing vehicle information.
   - Provides endpoints for creating, updating, and retrieving vehicle details.

Get User's Vehicles
- Endpoint: GET/api/vehicles
- Headers: Authorization: {token}

Add a New Vehicle
- Endpoint: POST /api/vehicles
- Headers: Authorization: {token}

**maintenance-history-service**
- Exposes APIs for managing service history information.
- Has endpoints for retrieving the service history of a vehicle, adding new service history records, and updating existing records.

Get Maintenance-history by Vehicle ID:
Endpoint: GET /api/maintenance-history/{vehicleId}

Add Maintenance-history:
Endpoint: POST /api/maintenance-history

Update Maintenance-history:
Endpoint: PUT /api/maintenance-history/{id}

Delete Service History:
Endpoint: DELETE /api/maintenance-history/{id}


**maintenance-reminder-service**
- Exposes APIs for managing reminders for routine maintenance tasks such as oil changes, tire rotations, etc..
- Has endpoints for retrieving the maintenance reminder of vehicle.
- it can be called from Notification service to trigger email/sms notifications for upcoming scheduled maintenance.

Get reminders:
Endpoint: GET /api/reminders/

Add reminders:
Endpoint: POST /api/reminders

Delete reminders:
Endpoint: DELETE /api/reminders/{reminderId}


  It can be further enhanced to have below funcationalities :-
- recommending repair shops when needed
- Notification Service for upcoming scheduled maintenance.
- Fault Diagnosis - Provides basic fault diagnosis based on entered symptoms.
- AAx Integration: Connects with AAx plug-in devices to gather vehicle diagnostic data.






  
