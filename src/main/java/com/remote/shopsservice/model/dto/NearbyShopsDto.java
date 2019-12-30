package com.remote.shopsservice.model.dto;

import com.remote.shopsservice.model.Location;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearbyShopsDto {
    private Location location;
}
