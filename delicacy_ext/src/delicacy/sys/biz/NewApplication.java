package delicacy.sys.biz;

import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseApplication;
import delicacy.system.dao.Application;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guangxun
 */
public class NewApplication implements GenericProcessor {

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		BaseApplication ba = new BaseApplication();
		ba.setDataFromJSON(creteria);
		Application a = new Application();
		a.setDataFromBase(ba);
		a.save();
		ba = a.generateBase();
		return ba.toOneLineJSON();
	}

}
