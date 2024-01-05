package org.petshelter.model;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {
    private Map<String, Pet> pets = new HashMap<>();
}
