package com.lucsalmd.catalogconsumer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;


@Getter
public class SQSMessage {
    @Expose
    @SerializedName("Message")
    private String message;
}
