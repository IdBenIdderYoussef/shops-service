package com.remote.shopsservice.model;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

    private double latitude;
    private double longitude;

}
