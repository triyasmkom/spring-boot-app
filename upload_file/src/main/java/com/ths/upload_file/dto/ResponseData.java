package com.ths.upload_file.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseData {
    private boolean status;
    private List<String> message = new ArrayList<>();
    private Object payload;
}
