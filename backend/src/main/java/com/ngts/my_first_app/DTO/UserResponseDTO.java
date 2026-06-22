package com.ngts.my_first_app.DTO;

import lombok.*;

/*
The reason we only use @Getter & @Builder for ResponseDTO is:
1. Immutability: Response objects should not be modified after creation.
   → Avoids accidental changes and ensures consistency in API responses.

2. No Setters: Using @Setter/@Data would make the object mutable,
   which is unnecessary since responses are fully constructed before returning.

3. Better Readability: @Builder allows clear, step-by-step object creation
   with named fields instead of relying on constructor parameter order.

4. Safer Construction: Prevents bugs caused by incorrect parameter ordering
   (common issue with @AllArgsConstructor).

5. Cleaner API Design: ResponseDTO represents output only, so we control
   exactly how objects are created and exposed.
*/
@Getter
@Builder
public class UserResponseDTO {
    private String name;
    private int age;
    private String email;
}
