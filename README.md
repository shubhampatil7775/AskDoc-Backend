# AskDoc Web Application

## Overview
AskDoc is a web application designed to facilitate seamless communication between users and doctors. Users can ask questions about their health, and doctors can provide answers based on their specialization. Multiple doctors can respond to the same question, ensuring comprehensive insights.

## Key Features
1. Users can ask health-related questions.
2. Doctors can view all questions or filter them by their specialization.
3. Doctors can answer questions, and multiple answers are supported for each question.
4. API endpoints to retrieve user-specific questions and all answers to a specific question.

---

## Project Setup

### Prerequisites
1. **Java 17**
2. **Maven**
3. **PostgreSQL**
4. **Docker**

### Steps to Run
1. Clone the repository.
2. Set up the PostgreSQL database and configure the connection in `application.properties`.
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## API Endpoints

### 1. Ask a Question
**Endpoint:**
```http
POST /users/{userId}/ask-question
```
**Description:** Allows a user to ask a question.

**Request Body:**
```json
{
    "questionText": "What causes ear pain?",
    "category": "ENT"
}
```
**Response:**
```json
{
    "id": 10,
    "questionText": "What causes ear pain?",
    "category": "ENT",
    "user": {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com"
    }
}
```
**cURL:**
```bash
curl -X POST http://localhost:8080/users/1/ask-question \
-H "Content-Type: application/json" \
-d '{
    "questionText": "What causes ear pain?",
    "category": "ENT"
}'
```

### 2. Get All Questions Asked by a User
**Endpoint:**
```http
GET /users/{userId}/questions
```
**Description:** Fetches all questions asked by a specific user.

**Response:**
```json
[
    {
        "id": 10,
        "questionText": "What causes ear pain?",
        "category": "ENT"
    },
    {
        "id": 11,
        "questionText": "How to deal with headaches?",
        "category": "General"
    }
]
```
**cURL:**
```bash
curl -X GET http://localhost:8080/users/1/questions
```

### 3. Get All Questions
**Endpoint:**
```http
GET /doctors/questions
```
**Description:** Fetches all questions.

**Response:**
```json
[
    {
        "id": 10,
        "questionText": "What causes ear pain?",
        "category": "ENT"
    },
    {
        "id": 11,
        "questionText": "How to deal with headaches?",
        "category": "General"
    }
]
```
**cURL:**
```bash
curl -X GET http://localhost:8080/doctors/questions
```

### 4. Get Questions by Specialization
**Endpoint:**
```http
GET /doctors/{specialization}/questions
```
**Description:** Fetches questions based on a doctor's specialization.

**Response:**
```json
[
    {
        "id": 10,
        "questionText": "What causes ear pain?",
        "category": "ENT"
    }
]
```
**cURL:**
```bash
curl -X GET http://localhost:8080/doctors/ENT/questions
```

### 5. Answer a Question
**Endpoint:**
```http
POST /answers/{doctorId}/answer-question/{questionId}
```
**Description:** Allows a doctor to answer a question.

**Request Body:**
```json
"This is the answer text."
```
**Response:**
```json
{
    "id": 1,
    "answerText": "This is the answer text.",
    "doctor": {
        "id": 1,
        "name": "Dr. Smith",
        "specialization": "ENT"
    },
    "question": {
        "id": 10,
        "questionText": "What causes ear pain?"
    }
}
```
**cURL:**
```bash
curl -X POST http://localhost:8080/answers/1/answer-question/10 \
-H "Content-Type: application/json" \
-d '"This is the answer text."'
```

### 6. Get All Answers for a Question
**Endpoint:**
```http
GET /answers/question/{questionId}
```
**Description:** Fetches all answers provided for a specific question.

**Response:**
```json
[
    {
        "id": 1,
        "answerText": "This is the answer text.",
        "doctor": {
            "id": 1,
            "name": "Dr. Smith",
            "specialization": "ENT"
        }
    }
]
```
**cURL:**
```bash
curl -X GET http://localhost:8080/answers/question/10
```

