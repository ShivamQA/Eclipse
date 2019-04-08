Login Page

# Object Definitions
==================================================================================================================

h2_Login             		0  		    css          .login-form h2
textbox_userId			    0			id		     userid
textbox_password	        0			id		     password
checkbox_rememberMe			0			id			 rememberMe
box_loginForm				0			xpath		//form[@name="loginform"]
btn_LogIn			        0			css		     input[value='Log In']
btn_LogOut					0			xpath		 //div[@class="pull-right"]/a[text()="LOGOUT"]
link_loginpage              0            xpath       //a[text()='${linktype}']
img_ACSlogo			        0			xpath	     (//img[contains(@alt,"American Chemical Society")])[1]    
txt_incorrectlogin          0      		xpath		//div[@class="alert alert-error"]
form_signup                 0           id          signUpForm
textbox_newemail            0           xpath       //input[@id="forgotEmail"]
txt_resetpasssword          0            css       .push-down.text-right
txt_helptitle               0            css       form[name=loginform] >small>a:nth-child(2)
txt_headerHelpTitle         0            css       .articleTitle h1>span
txt_newaccount              0            xpath       //div[@class="control-group"]//div[@class="controls"]/input                                          
btn_createNewUser           0            xpath        //input[@id="submit_button"]
txt_emailconfirmation       0            xpath        //p[text()='An email confirmation was sent to:']/..//div/b
logo_ACS                    0            css          .menu__logo
btn_linkaccount             0            xpath        //a[text()='Link Account']
txt_ORCIDpage               0            xpath        //p[text()='Sign in with your ORCID account']
link_Registernow            0            css          #switch-to-register-form
btn_loginauthorize          0            xpath        //button[@id='login-authorize-button']  
button_BackToCourse			0		    xpath         //a[text()="BACK TO COURSE"]
btn_cancelfeedback          0           css          .fsrInvite .fsrCloseBtn