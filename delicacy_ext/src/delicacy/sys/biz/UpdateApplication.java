package delicacy.sys.biz;

import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseApplication;
import delicacy.system.dao.Application;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guangxun
 */
public class UpdateApplication implements GenericProcessor {

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		BaseApplication ba = new BaseApplication();
		ba.setDataFromJSON(creteria);
		Application a = new Application();
		a.setPrimaryKeyFromBase(ba);
		if(!a.load()){
			throw new SQLException("Not found this application.");
		}
		a.setDataFromBase(ba);
		a.update();
		return ba.toOneLineJSON();
	}
	
	
	
}
