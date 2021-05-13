package com.aws.codestar.projecttemplates.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.orion.dto.AcctManageForm;
import com.bank.orion.dto.AdminForm;
import com.bank.orion.dto.CreateAcctForm;
import com.bank.orion.dto.LoginForm;
import com.bank.orion.dto.SchedulePaymentForm;
import com.bank.orion.dto.SignUpForm;
import com.bank.orion.dto.UserForm;
import com.bank.orion.model.Account;
import com.bank.orion.model.AccountHolder;
import com.bank.orion.model.Transaction;
import com.bank.orion.operation.AdminOperation;

/**
 * Basic Spring MVC controller that handles all GET requests.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {

	private final String siteName;

	private AdminOperation operation = new AdminOperation();

	public HelloWorldController(final String siteName) {
		this.siteName = siteName;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("siteName", this.siteName);
		return mav;
	}

	@RequestMapping(value = "/signInPage", method = RequestMethod.GET)
	public ModelAndView signInPage(@RequestParam(required = false) String error, ModelMap modelMap,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("signIn");

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				return new ModelAndView("redirect:/adminPage");
			} else {
				return new ModelAndView("redirect:/userPage");
			}
		}

		LoginForm login = new LoginForm();

		mav.addObject("login", login);
		modelMap.addAttribute("error", error);
		mav.addObject(modelMap);
		return mav;
	}

	@RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
	public ModelAndView signUpPage(@RequestParam(required = false) String message, ModelMap modelMap,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("signUp");

		SignUpForm signup = new SignUpForm();

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				return new ModelAndView("redirect:/adminPage");
			} else {
				return new ModelAndView("redirect:/userPage");
			}
		}

		modelMap.addAttribute("message", message);
		mav.addObject(modelMap);

		mav.addObject("signup", signup);
		return mav;
	}

	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public ModelAndView adminPage(@RequestParam(required = false) String searchUser,
			@RequestParam(required = false) String searchTransaction, HttpSession session) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("adminPage");

				AdminForm admin = new AdminForm();
				mav.addObject("admin", admin);

				AcctManageForm acctMangForm = new AcctManageForm();
				mav.addObject("acctMangForm", acctMangForm);

				if (StringUtils.isNotBlank(searchUser)) {
					List<AccountHolder> users = operation.searchAccountHolder(searchUser);
					if (CollectionUtils.isNotEmpty(users)) {
						mav.addObject("users", users);
					}
				}

				if (StringUtils.isNotBlank(searchTransaction)) {
					List<Transaction> transactions = operation.searchTransactions(searchTransaction);
					if (CollectionUtils.isNotEmpty(transactions)) {
						mav.addObject("transactions", transactions);
					}
				}

				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public ModelAndView searchUser(AdminForm adminForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/adminPage");

		String key = adminForm.getSearchUser();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("searchUser", key);
		}
		return mav;
	}

	@RequestMapping(value = "/searchTransaction", method = RequestMethod.POST)
	public ModelAndView searchTransaction(AdminForm adminForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/adminPage");

		String key = adminForm.getSearchTransaction();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("searchTransaction", key);
		}
		return mav;
	}

	@RequestMapping(value = "/viewUserManagePage", method = RequestMethod.POST)
	public ModelAndView viewUserManagePage(AdminForm adminForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/userManagePage");

		String key = adminForm.getEmail();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("emailID", key);
		}
		return mav;
	}

	@RequestMapping(value = "/userManagePage", method = RequestMethod.GET)
	public ModelAndView userManagePage(@RequestParam(required = false) String emailID, HttpSession session) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("userManagePage");

				AcctManageForm acctMangForm = new AcctManageForm();
				mav.addObject("acctMangForm", acctMangForm);

				if (StringUtils.isNotBlank(emailID)) {
					AccountHolder user = operation.getUserWithEmail(emailID);
					if (user != null) {
						mav.addObject("user", user);

						List<Account> accounts = operation.getAccountsWithEmail(emailID);
						mav.addObject("accounts", accounts);

						return mav;
					}
				}
				return new ModelAndView("redirect:/adminPage");
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	public ModelAndView activateUser(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/userManagePage");

				String key = acctManageForm.getEmailId();
				if (StringUtils.isNotBlank(key)) {
					String result = operation.reactivateUser(key);
					System.out.println(result);
					modelMap.addAttribute("emailID", key);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/suspendUser", method = RequestMethod.POST)
	public ModelAndView suspendUser(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/userManagePage");

				String key = acctManageForm.getEmailId();
				if (StringUtils.isNotBlank(key)) {
					String result = operation.suspendUser(key);
					System.out.println(result);
					modelMap.addAttribute("emailID", key);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/viewAcctManagePage", method = RequestMethod.POST)
	public ModelAndView viewAcctManagePage(AcctManageForm acctMangForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/acctManagePage");

		String key = acctMangForm.getAcctNum();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("acctNum", key);
		}
		return mav;
	}

	@RequestMapping(value = "/acctManagePage", method = RequestMethod.GET)
	public ModelAndView acctManagePage(@RequestParam(required = false) String acctNum, HttpSession session) {
		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("acctManagePage");

				AdminForm admin = new AdminForm();
				mav.addObject("admin", admin);

				AcctManageForm acctMangForm = new AcctManageForm();
				mav.addObject("acctMangForm", acctMangForm);

				if (StringUtils.isNotBlank(acctNum)) {

					Account account = operation.getAccountWithAcctNum(acctNum);

					if (account != null) {
						mav.addObject("account", account);

						List<Transaction> transactions = operation.getTransactionsWithAcctNum(acctNum);
						mav.addObject("transactions", transactions);

						return mav;
					}
				}
				return new ModelAndView("redirect:/adminPage");
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public ModelAndView deposit(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/acctManagePage");

				String acctNum = acctManageForm.getAcctNum();
				String deposit = acctManageForm.getDeposit();
				if (StringUtils.isNotBlank(acctNum) && StringUtils.isNotBlank(deposit)) {
					String result = operation.deposit(acctNum, deposit);
					System.out.println(result);
					modelMap.addAttribute("acctNum", acctNum);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/withdrawn", method = RequestMethod.POST)
	public ModelAndView withdrawn(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/acctManagePage");

				String acctNum = acctManageForm.getAcctNum();
				String withdrawn = acctManageForm.getWithdrawn();
				if (StringUtils.isNotBlank(acctNum) && StringUtils.isNotBlank(withdrawn)) {
					String result = operation.deduct(acctNum, withdrawn);
					System.out.println(result);
					modelMap.addAttribute("acctNum", acctNum);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/activateAccount", method = RequestMethod.POST)
	public ModelAndView activateAccount(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/acctManagePage");

				String key = acctManageForm.getAcctNum();
				if (StringUtils.isNotBlank(key)) {
					String result = operation.reactivateAccount(key);
					System.out.println(result);
					modelMap.addAttribute("acctNum", key);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/suspendAccount", method = RequestMethod.POST)
	public ModelAndView suspendAccount(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/acctManagePage");

				String key = acctManageForm.getAcctNum();
				if (StringUtils.isNotBlank(key)) {
					String result = operation.suspendAccount(key);
					System.out.println(result);
					modelMap.addAttribute("acctNum", key);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/viewTransactionAdminPage", method = RequestMethod.POST)
	public ModelAndView viewTransactionAdminPage(AcctManageForm acctMangForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/transactionAdminPage");

		String key = acctMangForm.getTransactionId();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("transactionId", key);
		}
		String acctNum = acctMangForm.getAcctNum();
		if (StringUtils.isNotBlank(acctNum)) {
			modelMap.addAttribute("acctNum", acctNum);
		}
		return mav;
	}

	@RequestMapping(value = "/transactionAdminPage", method = RequestMethod.GET)
	public ModelAndView transactionAdminPage(@RequestParam(required = false) String transactionId,
			@RequestParam(required = false) String error, @RequestParam(required = false) String acctNum,
			HttpSession session) {
		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("transactionAdminPage");

				AcctManageForm acctMangForm = new AcctManageForm();
				mav.addObject("acctMangForm", acctMangForm);

				if (StringUtils.isNotBlank(transactionId)) {

					Transaction transaction = operation.getTransactionWithID(transactionId);

					if (transaction != null) {
						mav.addObject("transaction", transaction);
						mav.addObject("error", error);
						mav.addObject("acctNum", acctNum);
						return mav;
					}
				}
				return new ModelAndView("redirect:/adminPage");
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/approveTransaction", method = RequestMethod.POST)
	public ModelAndView approveTransaction(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/transactionAdminPage");

				String key = acctManageForm.getTransactionId();
				if (StringUtils.isNotBlank(key)) {
					String result = operation.approveTransaction(key);
					System.out.println(result);
					modelMap.remove("error");
					if (!result.equals("OK")) {
						modelMap.addAttribute("error", result);
					}
					modelMap.addAttribute("transactionId", key);
				}
				String acctNum = acctManageForm.getAcctNum();
				if (StringUtils.isNotBlank(acctNum)) {
					modelMap.addAttribute("acctNum", acctNum);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/cancelTransactionAdmin", method = RequestMethod.POST)
	public ModelAndView cancelTransactionAdmin(AcctManageForm acctManageForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/transactionAdminPage");

				String key = acctManageForm.getTransactionId();
				if (StringUtils.isNotBlank(key)) {
					String result = operation.cancelTransaction(key);
					System.out.println(result);
					modelMap.remove("error");
					if (!result.equals("OK")) {
						modelMap.addAttribute("error", result);
					}
					modelMap.addAttribute("transactionId", key);
				}
				String acctNum = acctManageForm.getAcctNum();
				if (StringUtils.isNotBlank(acctNum)) {
					modelMap.addAttribute("acctNum", acctNum);
				}
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/createAccountPage", method = RequestMethod.GET)
	public ModelAndView createAccountPage(@RequestParam(required = false) String message, HttpSession session) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("createAccountPage");

				CreateAcctForm createUser = new CreateAcctForm();
				mav.addObject("createUser", createUser);
				mav.addObject("message", message);
				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ModelAndView createAccount(CreateAcctForm createUser, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {
			if (userSession.getUserRole().equals("Admin")) {
				ModelAndView mav = new ModelAndView("redirect:/createAccountPage");

				String name = createUser.getName();
				String dob = createUser.getDob().replace('-', '/');
				String acctNum = createUser.getAcctNum();
				String acctType = createUser.getAcctType();
				String ammout = createUser.getAmount();
				String ssn = createUser.getSsn();
				String email = createUser.getEmail();
				String phone = createUser.getPhone();

				String result = operation.createUser(acctNum, acctType, email, dob, ssn, name, ammout, phone);
				System.out.println(result);
				modelMap.addAttribute("message", result);

				return mav;
			}
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/userPage", method = RequestMethod.GET)
	public ModelAndView userPage(HttpSession session) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {

			ModelAndView mav = new ModelAndView("userPage");

			UserForm userForm = new UserForm();
			mav.addObject("userForm", userForm);

			List<Account> accounts = operation.getAccountsWithEmail(userSession.getEmailID());
			List<Account> activeAccts = new ArrayList<>();

			for (Account account : accounts) {
				if (account.getAccountStatus().equals("Active")) {
					activeAccts.add(account);
				}
			}

			mav.addObject("accounts", activeAccts);
			return mav;

		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/viewAcctPage", method = RequestMethod.POST)
	public ModelAndView viewAcctPage(UserForm userForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/acctPage");

		String key = userForm.getAcctNum();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("acctNum", key);
		}
		return mav;
	}

	@RequestMapping(value = "/acctPage", method = RequestMethod.GET)
	public ModelAndView acctPage(@RequestParam(required = false) String acctNum, HttpSession session) {
		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {

			ModelAndView mav = new ModelAndView("acctPage");

			UserForm userForm = new UserForm();
			mav.addObject("userForm", userForm);

			if (StringUtils.isNotBlank(acctNum)) {
				Account account = operation.getAccountWithAcctNum(acctNum);
				if (account != null) {
					mav.addObject("account", account);

					List<Transaction> transactions = operation.getTransactionsWithAcctNum(acctNum);
					mav.addObject("transactions", transactions);

					return mav;
				}
			}
			return new ModelAndView("redirect:/userPage");
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/viewTransactionPage", method = RequestMethod.POST)
	public ModelAndView viewTransactionPage(UserForm userForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/transactionPage");

		String key = userForm.getTransactionId();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("transactionId", key);
		}
		String acctNum = userForm.getAcctNum();
		if (StringUtils.isNotBlank(acctNum)) {
			modelMap.addAttribute("acctNum", acctNum);
		}
		return mav;
	}

	@RequestMapping(value = "/transactionPage", method = RequestMethod.GET)
	public ModelAndView transactionPage(@RequestParam(required = false) String transactionId,
			@RequestParam(required = false) String error, @RequestParam(required = false) String acctNum,
			HttpSession session) {
		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {

			ModelAndView mav = new ModelAndView("transactionPage");

			UserForm userForm = new UserForm();
			mav.addObject("userForm", userForm);

			if (StringUtils.isNotBlank(transactionId)) {

				Transaction transaction = operation.getTransactionWithID(transactionId);

				if (transaction != null) {
					mav.addObject("transaction", transaction);
					mav.addObject("error", error);
					mav.addObject("acctNum", acctNum);
					return mav;
				}
			}
			return new ModelAndView("redirect:/userPage");
		}

		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/cancelTransaction", method = RequestMethod.POST)
	public ModelAndView cancelTransaction(UserForm userForm, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {

			ModelAndView mav = new ModelAndView("redirect:/transactionPage");

			String key = userForm.getTransactionId();
			if (StringUtils.isNotBlank(key)) {
				String result = operation.cancelTransaction(key);
				System.out.println(result);
				modelMap.remove("error");
				if (!result.equals("OK")) {
					modelMap.addAttribute("error", result);
				}
				modelMap.addAttribute("transactionId", key);
			}
			String acctNum = userForm.getAcctNum();
			if (StringUtils.isNotBlank(acctNum)) {
				modelMap.addAttribute("acctNum", acctNum);
			}
			return mav;

		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/viewPaymentPage", method = RequestMethod.POST)
	public ModelAndView viewPaymentPage(UserForm userForm, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/schedulePaymentPage");

		String key = userForm.getAcctNum();
		if (StringUtils.isNotBlank(key)) {
			modelMap.addAttribute("acctNum", key);
		}
		return mav;
	}

	@RequestMapping(value = "/schedulePaymentPage", method = RequestMethod.GET)
	public ModelAndView schedulePaymentPage(@RequestParam(required = false) String acctNum,
			@RequestParam(required = false) String message, HttpSession session) {
		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {

			ModelAndView mav = new ModelAndView("schedulePaymentPage");

			SchedulePaymentForm schedulePaymentForm = new SchedulePaymentForm();
			mav.addObject("schedule", schedulePaymentForm);

			if (StringUtils.isNotBlank(acctNum)) {
				Account account = operation.getAccountWithAcctNum(acctNum);
				if (account != null) {
					mav.addObject("account", account);
					mav.addObject("message", message);
					return mav;
				}
			}
			return new ModelAndView("redirect:/userPage");
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/submitPayment", method = RequestMethod.POST)
	public ModelAndView submitPayment(SchedulePaymentForm schedule, HttpSession session, ModelMap modelMap) {

		AccountHolder userSession = (AccountHolder) session.getAttribute("userSession");
		if (userSession != null) {

			ModelAndView mav = new ModelAndView("redirect:/schedulePaymentPage");

			if (schedule != null) {
				String amount = schedule.getAmount();
				String transactionDate = schedule.getTransactionDate().replace('-', '/');
				String description = schedule.getDescription();
				String receivingAccountNumber = schedule.getReceivingAccountNumber();
				String sendingAccountNumber = schedule.getSendingAccountNumber();
				String receivingRounting = schedule.getReceivingRounting();
				boolean isRecur = schedule.getIsRecur().equals("Yes") ? true : false;
				int repeatTime = Integer.parseInt(schedule.getTimes());
				long period = Long.parseLong(schedule.getInterval());

				String result = operation.schedulePayment(amount, transactionDate, description, receivingAccountNumber,
						sendingAccountNumber, receivingRounting, isRecur, repeatTime, period);
				System.out.println(result);

				modelMap.addAttribute("message", result);
				modelMap.addAttribute("acctNum", sendingAccountNumber);
				return mav;
			}
			return new ModelAndView("redirect:/userPage");
		}
		return new ModelAndView("redirect:/signInPage");
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView signUp(SignUpForm signup, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/signUpPage");

		String email = signup.getEmail();
		String dob = signup.getDob().replace('-', '/');
		String SSN = signup.getSsn();
		String name = signup.getName();
		String password = signup.getPassword();
		String userName = signup.getUsername();
		String result = operation.activateUser(email, dob, SSN, name, password, userName);
		System.out.println(result);
		modelMap.addAttribute("message", result);

		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(LoginForm login, HttpSession session, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("redirect:/signInPage");

		AccountHolder user = operation.login(login.getUsername(), login.getPassword());
		if (user != null) {
			if (user.getUserRole().equals("Admin")) {
				modelMap.remove("error");
				session.setAttribute("userSession", user);
				return new ModelAndView("redirect:/adminPage");
			} else {
				if (user.getHolderStatus().equals("Active")) {
					modelMap.remove("error");
					session.setAttribute("userSession", user);
					return new ModelAndView("redirect:/userPage");
				}
				modelMap.addAttribute("error", "Account is suspended. Please contact Admin!!!");
				return mav;
			}
		} else {
			modelMap.addAttribute("error", "Invalid User/Password!!!");
			return mav;
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/");
		session.removeAttribute("userSession");
		return mav;
	}

}
