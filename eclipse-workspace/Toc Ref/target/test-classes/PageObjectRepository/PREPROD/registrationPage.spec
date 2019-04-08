Registration Page

# Object Definitions
==================================================================================================================
txt_FieldsMandatory  		0			xpath      			        	//fieldset[@class='form-horizontal']/div[@class='control-group']//span[text()='*']/parent::label
txt_fieldRequired           0           xpath                          //fieldset[@class='form-horizontal']//span[contains(@id,"Error") and not(contains(@style,"display: none"))]
txt_newaccount              0          xpath                           //div[@class="controls"]/input                                          
btn_createNewUser           0          xpath                           //input[@id="submit_button"]
logo_ACS                    0          xpath                           //p[contains(text(),"Test server")]
btn_loginauthorize          0          xpath                          //button[@id='login-authorize-button']  
btn_cancel                  0          xpath                          //input[@type='button' and @value='Cancel']
txt_passwordField           0            css                            #passwordPwd
txt_passwordStrength        0            css                            .password-strength #scorebarBorder #score
txt_acsIdAndProduct         0            css                             .row-fluid .span5
txt_checkBox                0            css                             .controls .checkbox
link_checkbox               0            css                             .controls .checkbox a
img_footerLogos             0            css                             .footer-logos
link_footers                0            xpath                           //div[@role ='contentinfo']//li/a
txt_currentCrumb            0            xpath                           //ul[@role='navigation']//li[contains(@class,'currentCrumb')]
link_footerNavigation       0            xpath                           ( //div[@role ='contentinfo']//li/a)[${'index'}]
txt_accountCreated          0            xpath                           //div[@role='contentinfo']//h3
txt_emailCreated            0            xpath                           //p[text()='An email confirmation was sent to:']/..//div/b
txt_emailconfirmation       0            css                             .seperate-body-padded
btn_reviewerLabTest         0            xpath                            //input[@value='Reviewer Lab Test']
form_CreatePage             0            css                              #signUpForm
userid_orcid				0			css								 #userId
password_orcid				0			css								 #password
button_SignInOrcid			0			css								 #form-sign-in-button
txt_headingORCID            0            css                              #connect-orcid h3
btn_linkAccount             0            css                              .btn-link
link_Register               0            xpath                            //div[@id='connect-orcid']//a[text()='Register']
txt_headingRegister         0            xpath                            //div[@class='step-subheadline' and text()= 'REGISTER']
#logo_ORCID					0			xpath							 //img[contains(@alt,"ORCID")]
logo_ORCID					0			xpath							 //img[contains(@src,"orcid")]