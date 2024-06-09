package hufs.sweepyswipe.service;

import lombok.Data;

@Data
public class MailDto {

    private Long id;
    private String name;
    private String email;
    private String message;
}
