package oracle.gcp.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class RegistryService{

  @PersistenceContext
    private EntityManager entityManager;
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
	public double findRegistryLimit() {
		double limit = 0.0d;
		  //"login" this is the name of your procedure
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ECOMADMIN.ATGWS.GET_REG_CASH_FUND_LIMIT"); 
        try {
        	//Declare the parameters in the same order
        	query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        	query.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
        	//query.registerStoredProcedureParameter(2, Cursor.class, ParameterMode.OUT);
        	//Pass the parameter values
        	query.setParameter(1, Integer.parseInt("520922822"));
        	//Execute query
        	query.execute();

        	//Get output parameters
        	ResultSet output = (ResultSet) query.getOutputParameterValue(2);

        	try {
        		while (output.next()) { 
        			String cfLimit = output.getString("finalAmount");
        			if(!ObjectUtils.isEmpty(cfLimit))
        				limit = Double.valueOf(cfLimit); 
        				System.out.println("Limit is --"+limit);
        			}
        		}
        		catch (NumberFormatException e) { 
        			e.printStackTrace(); 
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        }
		catch(Exception exc) {
			exc.printStackTrace();
		}
        return limit;
	}
	
	public void connectToDB() {
		 System.out.println("Connected to Data base");
         try {
			System.err.println(jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
