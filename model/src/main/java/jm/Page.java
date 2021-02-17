package jm;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    private Long count;
    private List<T> entities;
}
