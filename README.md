# humanresources
Human Resources For Companies using Java Spring Boot

## API Documentation

### URL Documentation

#### Authentication Processing

| URL                     | Method | Description      | Request Body / Param                          | Response Body                                                   |
|-------------------------| ------ |------------------|-----------------------------------------------|-----------------------------------------------------------------|
| /api/1.0/auth           | POST | Login the System | {"email": "test@test.com", "pass": "pass123"} | { "name": "test", "surname": "test", "email": "test@test.com" } |
| /api/1.0/getUserByEmail | POST | Get User By Mail | email: test@test.com                          | { "name": "test", "surname": "test", "email": "test@test.com" } |

