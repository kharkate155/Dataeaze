import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Pojo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            Connection con = ConnectDatabase.getConnection();
            String sql = "INSERT INTO \"NONCOF2\"( \"ID\", \"ISCONFIDENTIAL\", "
                    + "\"PROJECTNAME\", \"STREET\", \"CITY\", \"STATE\",\"ZIPCODE\", \"COUNTRY\","
                    + " \"LEEDSYSTEMVERSIONDISPLAYNAME\", \"POINTSACHIEVED\", \"CERTLEVEL\","
                    + " \"CERTDATE\", \"ISCERTIFIED\", \"OWNERTYPES\", \"GROSSSQFOOT\", "
                    + "\"TOTALPROPAREA\", \"PROJECTTYPES\", \"OWNERORGANIZATION\", "
                    + "\"REGISTRATIONDATE\" ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader("E:\\downloads\\mycsv.csv"));
            String lineText = null;
            int count = 0;
            int batchSize = 1000;
            lineReader.readLine(); // skip header line
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                for(int i =0;i<19;i++) {
                    if (data[i] == "") {
                        data[i] = "NULL";
                    }
                }
                String ID = data[0];
                String Isconfidential = data[1];
                String ProjectName = data[2];
                String Street = data[3];
                String City = data[4];
                String State = data[5];
                String Zipcode = data[6];
                String Country = data[7];
                String LEEDSystemVersionDisplayName = data[8];
                String PointsAchieved = data[9];
                String CertLevel = data[10];
                String CertDate = data[11];
                String IsCertified = data[12];
                String OwnerTypes = data[13];
                String GrossSqFoot = data[14];
                String TotalPropArea = data[15];
                String ProjectTypes = data[16];
                String OwnerOrganization = data[17];
                String RegistrationDate = data[18];
                stmt.setString(1, ID);
                stmt.setString(2, Isconfidential);
                stmt.setString(3, ProjectName);
                stmt.setString(4, Street);
                stmt.setString(5, City);
                stmt.setString(6, State);
                stmt.setString(7, Zipcode);
                stmt.setString(8, Country);
                stmt.setString(9, LEEDSystemVersionDisplayName);
                stmt.setString(10, PointsAchieved);
                stmt.setString(11, CertLevel);
                stmt.setString(12, CertDate);
                stmt.setString(13, IsCertified);
                stmt.setString(14, OwnerTypes);
                stmt.setString(15, GrossSqFoot);
                stmt.setString(16, TotalPropArea);
                stmt.setString(17, ProjectTypes);
                stmt.setString(18, OwnerOrganization);
                stmt.setString(19, RegistrationDate);

                stmt.addBatch();

                if (count % batchSize == 0) {
                    stmt.executeBatch();
                }
                count++;

            }
            System.out.println(count);
            lineReader.close();
            // execute the remaining queries
            stmt.executeBatch();
            con.commit();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
