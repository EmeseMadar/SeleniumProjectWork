package dataTypes;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class ModifyProfileData {
    /**
     * Hozzáférés biztosítása a globalTestData.properties filehoz.
     */

    protected static GlobalTestData globalTestData = new GlobalTestData();

    private String title;
    private String firstName;
    private String lastName;
    private String gender;

    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    private String address;
    private String locality;
    private String region;
    private String postalCode;
    private String country;


    /** Paraméter nélküli konstuktor, amivel a globalTestData.properties fájlból hozzárféünk a tesztadatokhoz.
     * A példányosításnál nem kell mindenhol felsorolni az össszes adatot.
     */
    public ModifyProfileData() {
        this.title = globalTestData.getProperty(Consts.MOD_TITLE);
        this.firstName = globalTestData.getProperty(Consts.MOD_FIRST_NAME);
        this.lastName = globalTestData.getProperty(Consts.MOD_LAST_NAME);
        this.gender = globalTestData.getProperty(Consts.MOD_GENDER);
        this.homePhone = globalTestData.getProperty(Consts.MOD_HOME_PHONE);
        this.mobilePhone = globalTestData.getProperty(Consts.MOD_MOBILE_PHONE);
        this.workPhone = globalTestData.getProperty(Consts.MOD_WORK_PHONE);

        this.address = globalTestData.getProperty(Consts.MOD_ADDRESS);
        this.locality = globalTestData.getProperty(Consts.MOD_LOCALITY);
        this.region = globalTestData.getProperty(Consts.MOD_REGION);
        this.postalCode = globalTestData.getProperty(Consts.MOD_POSTAL_CODE);
        this.country = globalTestData.getProperty(Consts.MOD_COUNTRY);

    }

}
