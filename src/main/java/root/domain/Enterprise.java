package root.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
    private String name;
    private String building;
    private String street;
    private String cityAndZipCode;
    private String emailAddress;
}
