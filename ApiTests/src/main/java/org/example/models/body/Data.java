package org.example.models.body;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Data {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
