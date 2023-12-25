package org.example.models.body;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Root {
    private Data data;
    private Support support;
}
