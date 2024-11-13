# Google Maps Place API Project

This project provides a simple set of APIs for managing places using Google Maps. The APIs allow you to add, delete, retrieve, and update place information.

## API Overview

### 1. Add Place
- **Purpose**: Adds a new place to the server with location, name, address, and other details.
- **Method**: `POST`
- **Endpoint**: `/maps/api/place/add/json`
- **Key Requirement**: Requires a hardcoded API key (`qaclick123`) in the URL.

### 2. Delete Place
- **Purpose**: Deletes an existing place from the server by `place_id`.
- **Method**: `DELETE`
- **Endpoint**: `/maps/api/place/delete/json`
- **Key Requirement**: Uses the same hardcoded API key in the URL.

### 3. Get Place
- **Purpose**: Retrieves details of an existing place using its `place_id`.
- **Method**: `GET`
- **Endpoint**: `/maps/api/place/get/json`
- **Key Requirement**: Requires the hardcoded API key, as well as the `place_id` from the add response.

### 4. Update Place
- **Purpose**: Updates information of an existing place (such as address).
- **Method**: `PUT`
- **Endpoint**: `/maps/api/place/update/json`
- **Key Requirement**: Needs the hardcoded API key and the `place_id` for identification.

## General Notes
- All API requests require the `qaclick123` API key.
- The `place_id` parameter is essential for identifying a place in delete, get, and update operations.

## TestCases Execution Output
![Test Cases Output](https://github.com/rohitpunekar242/googleplace-api-automation-rest-assured/blob/master/output.png)

