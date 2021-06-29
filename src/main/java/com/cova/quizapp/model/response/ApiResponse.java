package com.cova.quizapp.model.response;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class ApiResponse {
    private String message;
    @Nullable
    private int status;
    @Nullable
    private String data;

    public static ApiResponse response(String message, int status, String data){
        return  ApiResponse.builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
    }

    public static ApiResponse response(String message, int status){
        return  ApiResponse.builder()
                .message(message)
                .status(status)
                .build();
    }

    public static ApiResponse errorMessage(String message, int status){
       return  ApiResponse.builder()
               .message(message)
               .data("")
               .status(status)
               .build();
    }

    public static ApiResponse errorMessage(String message){
        return  ApiResponse.builder()
                .message(message)
                .data("")
                .status(400)
                .build();
    }
}
