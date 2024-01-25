package dataTypes;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

/**
 * A Saving típusú accountok megasásakor használt adatok osztályba szervezése.
 */

public class Saving {

    // fiók tipusa
    // Savings
    // Money Market
    @CsvBindByName(column = "accountTypes")
    private String accountTypes;

    // tulajdonos tipus
    // Individual
    // Joint
    @CsvBindByName(column = "ownershipTypes")
    private String ownershipTypes;

    // account neve
    @CsvBindByName(column = "accountName")
    private String accountName;

    // nyitó egyenleg
    @CsvBindByName(column = "openingBalance")
    private String openingBalance;


}
