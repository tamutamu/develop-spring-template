package jp.template.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.template.security.LoginUser;

/**
 * メニューコントローラ.
 * 
 * @author hosomi.
 *
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

	/** Logger. */
	private static Logger logger = LogManager.getLogger();

	/**
	 * 初期オープン時のマッピング。
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String show(@AuthenticationPrincipal LoginUser loginUser) throws Exception {

		logger.debug(loginUser.getUsername()); // org.springframework.security.core.userdetails.User を継承したログイン ID の取得。
		logger.debug(loginUser.getUser().getLoginUserId()); // DB（jp.template.domain.User） からのログイン ID の取得。
		
		return "menu";
	}
}
