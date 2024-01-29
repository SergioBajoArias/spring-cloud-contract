package com.xeridia.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private Hat hat;
}
