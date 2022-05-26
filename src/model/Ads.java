package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Ads {

    private int id;
    private String title;
    private String subtitle;
    private String description;
    private double price;
    private String image;
    private int user_id;
}
