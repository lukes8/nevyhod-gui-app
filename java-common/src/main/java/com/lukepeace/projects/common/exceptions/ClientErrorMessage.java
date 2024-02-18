package com.lukepeace.projects.common.exceptions;

import lombok.*;

@AllArgsConstructor @Getter @NoArgsConstructor @Setter @ToString
public class ClientErrorMessage {
    private String clientMessage;
    private String errorCode;
    private String url;
}
