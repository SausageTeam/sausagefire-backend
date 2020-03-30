package com.sausage.app.domain.housing.housingInfo;

        import lombok.*;
        import java.io.Serializable;
        import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HousingInfo implements Serializable{
    private String address;
    private List<Resident> residentList;
}
