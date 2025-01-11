package result.agency.result_agency_intern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MonthlyOutcomeReportDTO {

    private String month;
    private Double outcome;

}
