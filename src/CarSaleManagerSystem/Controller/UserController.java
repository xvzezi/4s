package CarSaleManagerSystem.Controller;

import CarSaleManagerSystem.Bean.*;
import CarSaleManagerSystem.Service.CustomerService;
import CarSaleManagerSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by HFQ on 2016/8/5.
 */

@Controller
@RequestMapping(value = "/User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    private LoginFilter loginFilter = new LoginFilter();

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createUserPage(){
        ModelAndView modelAndView = new ModelAndView("User/userCreate");
        List<Job> jobs = userService.getAllJobs();
        List<Apartment> apartments = userService.getAllApartment();
        List<Level> levels = userService.getAllLevels();
        List<JobStatus> jobStatuses = userService.getAllJobStatus();
        modelAndView.addObject("jobs",jobs);
        modelAndView.addObject("apartments",apartments);
        modelAndView.addObject("levels", levels);
        modelAndView.addObject("jobStatuses", jobStatuses);
        modelAndView.addObject("user",new User());

        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute User user,HttpSession session,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("redirect:/User/list");
        System.out.println(user.getApartment() + "HHH");
        user.setType(user.getJob());
        userService.createUser(user);
        session.setAttribute("userID",user.getUserID());
        session.setAttribute("username",user.getUsername());
        return modelAndView;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView listUser(HttpSession session) {
//        ModelAndView modelAndView = loginFilter.adminLogin(session);
//        if (modelAndView != null)
//            return modelAndView;
        ModelAndView modelAndView = new ModelAndView("User/userList");
        List<?> userList = userService.getAllUsers();
        modelAndView.addObject("users",userList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView editUserPage(HttpSession session){
        ModelAndView modelAndView = loginFilter.userLogin(session);
        if (modelAndView != null)
            return modelAndView;
        int userID = (int)session.getAttribute("userID");
        modelAndView = new ModelAndView("User/userUpdate");
        User user = userService.findUserById(userID);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{userID}",method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user,@PathVariable Integer userID,HttpSession session){
        ModelAndView modelAndView = loginFilter.userLogin(session);
        if (modelAndView != null)
            return modelAndView;
        modelAndView = new ModelAndView("redirect:/User/list");
        userService.updateUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{userID}",method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable Integer userID,HttpSession session){
//        ModelAndView modelAndView = loginFilter.adminLogin(session);
//        if (modelAndView != null)
//            return modelAndView;
        ModelAndView modelAndView = new ModelAndView("redirect:/User/list");
        User user = userService.findUserById(userID);
        userService.removeUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView("/Logon/login");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute User user, HttpSession session){
        ModelAndView modelAndView;
        int userID = userService.login(user);
        if(userID < 0) {
            modelAndView = new ModelAndView("redirect:/User/login");
            modelAndView.addObject("message","用户名或密码错误");
            return modelAndView;
        }else{
            modelAndView = new ModelAndView("redirect:/User/profile");
            session.setAttribute("userID",userID);
            session.setAttribute("username", user.getUsername());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public ModelAndView getProfile(HttpSession session){
        ModelAndView modelAndView = loginFilter.userLogin(session);
        if (modelAndView != null)
            return modelAndView;
        int userID = (int)session.getAttribute("userID");
        modelAndView = new ModelAndView("User/userCenter");
        User user = userService.findUserById(userID);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("redirect:/Site/index");
        if(session.getAttribute("userID") != null){
            session.removeAttribute("userID");
        }
        if(session.getAttribute("username") != null)
        {
            session.removeAttribute("username");
        }
        if(session.getAttribute("admin") != null)
        {
            session.removeAttribute("admin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/createApartment",method = RequestMethod.GET)
    public ModelAndView createApartmentPage(){
        ModelAndView modelAndView = new ModelAndView("User/createApartment");
        modelAndView.addObject("apartment",new Apartment());
        return modelAndView;
    }

    @RequestMapping(value = "/createApartment",method = RequestMethod.POST)
    public ModelAndView createApartment(@ModelAttribute Apartment apartment){
        ModelAndView modelAndView = new ModelAndView("redirect:/User/listApartment");
        userService.createApartment(apartment);
        return modelAndView;
    }

    @RequestMapping(value = "/listApartment",method = RequestMethod.GET)
    public ModelAndView listApartment(){
        ModelAndView modelAndView = new ModelAndView("User/apartmentList");
        modelAndView.addObject("apartments",userService.getAllApartment());
        return modelAndView;
    }

    @RequestMapping(value = "/apartmentExists")
    public @ResponseBody
    Map<String, Object> apartmentExists(HttpServletRequest request) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();

        String apartment = request.getParameter("apartment");

        if(!userService.apartmentExist(apartment)){

            map.put("message","false");
        }else {

            map.put("message","true");
        }
        return map;
    }

    @RequestMapping(value = "/createJob",method = RequestMethod.GET)
    public ModelAndView createJobPage(){
        ModelAndView modelAndView = new ModelAndView("User/createJob");
        modelAndView.addObject("job",new Job());
        return modelAndView;
    }

    @RequestMapping(value = "/createJob",method = RequestMethod.POST)
    public ModelAndView createJob(@ModelAttribute Job job){
        ModelAndView modelAndView = new ModelAndView("redirect:/User/listJob");
        userService.createJob(job);
        return modelAndView;
    }

    @RequestMapping(value = "/listJob",method = RequestMethod.GET)
    public ModelAndView listJob(){
        ModelAndView modelAndView = new ModelAndView("User/jobList");
        modelAndView.addObject("jobs",userService.getAllJobs());
        return modelAndView;
    }

    @RequestMapping(value = "/jobExists")
    public @ResponseBody
    Map<String, Object> jobExists(HttpServletRequest request) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();

        String job = request.getParameter("job");

        if(!userService.jobExist(job)){

            map.put("message","false");
        }else {

            map.put("message","true");
        }
        return map;
    }

    @RequestMapping(value = "/createJobStatus",method = RequestMethod.GET)
    public ModelAndView createJobStatusPage(){
        ModelAndView modelAndView = new ModelAndView("User/createJobStatus");
        modelAndView.addObject("jobStatus",new JobStatus());
        return modelAndView;
    }

    @RequestMapping(value = "/createJobStatus",method = RequestMethod.POST)
    public ModelAndView createJobStatus(@ModelAttribute JobStatus jobStatus){
        ModelAndView modelAndView = new ModelAndView("redirect:/User/listJobStatus");
        userService.createJobStatus(jobStatus);
        return modelAndView;
    }

    @RequestMapping(value = "/listJobStatus",method = RequestMethod.GET)
    public ModelAndView listJobStatus(){
        ModelAndView modelAndView = new ModelAndView("User/jobStatusList");
        modelAndView.addObject("jobStatuss",userService.getAllJobStatus());
        return modelAndView;
    }

    @RequestMapping(value = "/jobStatusExists")
    public @ResponseBody
    Map<String, Object> jobStatusExists(HttpServletRequest request) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();

        String jobStatus = request.getParameter("jobStatus");

        if(!userService.jobStatusExist(jobStatus)){

            map.put("message","false");
        }else {

            map.put("message","true");
        }
        return map;
    }

    @RequestMapping(value = "/createLevel",method = RequestMethod.GET)
    public ModelAndView createLevelPage(){
        ModelAndView modelAndView = new ModelAndView("User/createLevel");
        modelAndView.addObject("level",new Level());
        return modelAndView;
    }

    @RequestMapping(value = "/createLevel",method = RequestMethod.POST)
    public ModelAndView createLevel(@ModelAttribute Level level){
        ModelAndView modelAndView = new ModelAndView("redirect:/User/listLevel");
        userService.createLevel(level);
        return modelAndView;
    }

    @RequestMapping(value = "/listLevel",method = RequestMethod.GET)
    public ModelAndView listLevel(){
        ModelAndView modelAndView = new ModelAndView("User/levelList");
        modelAndView.addObject("levels",userService.getAllLevels());
        return modelAndView;
    }

    @RequestMapping(value = "/levelExists")
    public @ResponseBody
    Map<String, Object> levelExists(HttpServletRequest request) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();

        String level = request.getParameter("level");

        if(!userService.levelExist(level)){

            map.put("message","false");
        }else {

            map.put("message","true");
        }
        return map;
    }


    /**
     * Customer controller
     */

    @RequestMapping(value = "/createCustomer",method = RequestMethod.GET)
    public ModelAndView createCustomerPage(){
        ModelAndView modelAndView = new ModelAndView("User/updateCustomer");
        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @RequestMapping(value = "createCustomer", method = RequestMethod.POST)
    public ModelAndView createCustomer(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("redirect: /User/createCustomer");

        customerService.createCustomer(customer);

        return modelAndView;
    }


    /*****************************************************Bean System*************************************************/
	/**
     * Mother Page Of Bean Below
     *      > We get several Object inside the mother page which could be used in some situation
     *      >   beans   --  the main content of a user(stuff)'s bean
     *      >   theme   --  the theme of the current page, to show you are using different function
     *      >   fail    --  when failed a operation, this object will be set with an error string
     *      > p.s.  You may have to show the name of a user, try to use other URL to get the info
     * @return
     */
    @RequestMapping(value = "/bean")
    public ModelAndView getBeanHomePage()
    {
        return new ModelAndView("User/bean");
    }

	/**
     * Get the beans of this month
     *      > To use this method, you do not have to set any parameter.
     *      > You will get Objects like beans & theme to use in the
     *      > jsp file for you to present.
     * @return
     */
    @RequestMapping(value = "/bean/thisMonth", method = RequestMethod.GET)
    public ModelAndView getBeanThisMonth()
    {
        ModelAndView modelAndView = getBeanHomePage();
        modelAndView.addObject("beans", userService.getCurrentMonth());
        modelAndView.addObject("theme", "This Month");
        return modelAndView;
    }

	/**
     * Get the beans of a user on a specified month
     *      > To use this method, you will have to set several parameter
     *      >   user_id --  the id of the user
     *      >   year    --  the year you wanted
     *      >   month   --  the month you wanted
     * @param request
     * @return
     */
    @RequestMapping(value = "/bean/userWithDate", method = RequestMethod.GET)
    public ModelAndView getBeanUserWithDate(HttpServletRequest request)
    {
        // get basic page
        ModelAndView modelAndView = getBeanHomePage();

        // get parameter
        Integer year = Integer.getInteger(request.getParameter("year"));
        Integer month = Integer.getInteger(request.getParameter("month"));
        if(year == null || month == null || month > 12 || month < 1)
        {
            modelAndView.addObject("fail", "year or month not correctly specified");
            return modelAndView;
        }

        Integer user_id = Integer.getInteger(request.getParameter("user_id"));
        if(user_id == null)
        {
            modelAndView.addObject("fail", "user id not correctly specified");
            return modelAndView;
        }

        // turn it into date
        Date date = new Date();
        date.setYear(year);
        date.setMonth(month - 1);

        // get the model
        modelAndView.addObject("beans", userService.getByUserWithDate(user_id, date));
        modelAndView.addObject("theme", "On " + year + "-" + month);
        return modelAndView;
    }

	/**
     * Get the beans of a brand on specified date
     *      > To use this method, you have to set several parameter
     *      >   brand   --  the brand of a car
     *      >   year    --  the year you wanted
     *      >   month   --  the month you wanted
     * @param request
     * @return
     */
    @RequestMapping(value = "/bean/brandWithDate", method = RequestMethod.GET)
    public ModelAndView getBeanBrandWithDate(HttpServletRequest request)
    {
        // get the mother page
        ModelAndView modelAndView = getBeanHomePage();

        // get the parameter
        String brand = request.getParameter("brand");
        if(brand == null)
        {
            modelAndView.addObject("fail", "brand not correctly specified");
        }
        Integer year = Integer.getInteger(request.getParameter("year"));
        Integer month = Integer.getInteger(request.getParameter("month"));
        if(year == null || month == null || month > 12 || month < 1)
        {
            modelAndView.addObject("fail", "year or month not correctly specified");
            return modelAndView;
        }

        // build the date
        Date date = new Date();
        date.setYear(year);
        date.setMonth(month - 1);

        // get the model
        modelAndView.addObject("beans", userService.getByBrandWithDate(brand, date));
        modelAndView.addObject("theme", brand + " on " + year + "-" + month);
        return modelAndView;
    }

	/**
     * Create a bean table Of a User on this month
     *      > This is a RESTful API, you will have to set several parameter,
     *      > and be ready to receive a json string. Pay attention to method.
     *      >   brand   --  the brand you want to set
     *      >   user_id --  the user you want to specified
     * @param request
     * @return
     */
    @RequestMapping(value = "/bean/createThisMonth", method = RequestMethod.POST)
    @ResponseBody
    public String createThisMonth(HttpServletRequest request)
    {
        // get the parameter
        String brand = request.getParameter("brand");
        if(brand == null)
        {
            return "fail " + "brand not correctly specified";
        }

        Integer user_id = Integer.getInteger(request.getParameter("user_id"));
        if(user_id == null)
        {
            return "fail " + "user id not correctly specified";
        }

        // get the model
        return userService.createBeanOnUser(user_id, brand);
    }

	/**
     * Not implemented
     * @param request
     * @return
     */
    @RequestMapping(value = "/bean/createAll", method = RequestMethod.POST)
    @ResponseBody
    public String createBeanAll(HttpServletRequest request)
    {
        return "NOT IMPLEMENTED";
    }

	/**
     * Update a bean
     *      >   first, i have to describe what is going on here.
     *          The bean system is a slowly changing system. A
     *          person's bean value could change along the time
     *          changing. Then we have to change several part but
     *          not all the data of a bean.
     *          In this method, you will have to send a bean object
     *          to tell the backend what to you want to change.
     *          - you have to set user_id, brand, sale_date to specify
     *            a bean to change. Here, sale_date is a Date, which
     *            the DAY of it is ignored.
     *          - below are some property you can set. When you set
*                 a property, it means you want to add value on to
     *            the according properties inside the database.
     *            - These property can be seen in the Bean.java
     * @param bean
     * @return
     */
    @RequestMapping(value = "/bean/updateData", method = RequestMethod.PUT)
    @ResponseBody
    public String updateBean(@RequestBody Bean bean)
    {
        // check the bean
        if(bean.getBrand() == null) return "fail with brand not set";

        return userService.addDataOnBean(bean);
    }


}
