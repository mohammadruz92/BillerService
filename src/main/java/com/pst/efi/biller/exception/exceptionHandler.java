package com.pst.efi.biller.exception;

public class exceptionHandler {

    public void handle(int statusCode, String exceptionMessage) {
        ErrorResponse errorResponse = new ErrorResponse(statusCode, exceptionMessage);

        switch (statusCode) {
//            case 401:
//                throw new UnauthorizedException(errorResponse.toString());
//            case 404:
//                throw new ResourceNotFoundException(errorResponse.toString());
            case 409:
                throw new DataConflictException(errorResponse.toString());
//            default:
//                throw new InternalServerErrorException(errorResponse.toString());
        }
    }

}
