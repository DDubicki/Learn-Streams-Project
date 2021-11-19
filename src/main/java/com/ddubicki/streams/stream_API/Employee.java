package com.ddubicki.streams.stream_API;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/*
    @Data: @ToString,
           @EqualsAndHashCode,
           @Getter
           @Setter
           @RequiredArgsConstructor
 */
@Data
@AllArgsConstructor
public class Employee {

    private String firstName;
    private String lastName;
    private int age;
    private List<String> skills;

}
