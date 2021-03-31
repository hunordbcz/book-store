package net.debreczeni.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String title;
    private String author;
    private String genre;
    private Long quantity;
    private Double price;
}
