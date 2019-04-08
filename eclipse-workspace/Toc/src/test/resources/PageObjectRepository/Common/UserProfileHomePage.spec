Page Title: My Profile

# Object Definitions
==================================================================================================================
txt_userProfileContentHeader		0 	css			                .profile-pages_header .col-xs-12>h1
heading_welcomeuser                 0   css                         .profileMain .extraPageTitle
txt_leftNavigation                  0   css                         .profile-menu-home>p
heading_sideSelectedNavigationBar	0	xpath		               //div[@id='twoColText']/h1[normalize-space(text())='${columnName}']
list_savedSearchesHeadings			0	css			                div[class='savedSearch']>h5
lnk_runSearch						0	xpath		                 (//div[@class='savedSearch']//a[text()='Run Search'])[1]
lnk_deleteSavedSearch				0	xpath		                (//a[contains(@class,'delete')])[1]		
img_rssFeedIcon						0	xpath		                (//img[@alt='RSS feed'])[1]			
btn_YourProfile                     0   css                         #profileButton    
lnk_navigationPanel                 0  xpath                       //div[@class='profile-menu']//ul/li//span[contains(text(),'${link}')]/parent::a
txt_linkHeading                     0    xpath                        //h1[contains(text(),'${heading}')]
txtbox_token                        0    css                           #tokenAccess
lst_navigationLinksText             0    xpath                          //div[@class='profile-menu']//span[not(contains(text(),'Mendeley'))]
txt_associatedTextWithLinks         0    xpath                        //div[@class='profile-menu-home']//span[contains(.,'${linktext}')]/../following-sibling::p
lnk_memberBenifitsTypes             0    xpath                        //div[@class='member-benefits_tab_nav']//a[text()='${tab}']
txt_accessTypesOrHistoryContent     0    xpath                        //a[text()='${tab}']/../following-sibling::div[@class='member-benefits_tab_content']/p
txt_userName                        0    css                           .userInfo-user
btn_changeEmail                     0    xpath                         //input[@value='Change Email']
btn_addAddress                      0   xpath                          (//input[@value='Add Address'])[1]
btn_tokenSubmit                     0   xpath                          //input[@value='Submit']
txt_activateTokenInstructionMsg     0   css                           #accessTokenForm .message
txt_claimNumber                     0   css                           .label label[for=tokenAccess] 
txtbox_claimNumber                  0   css                           #tokenAccess
lnk_lastArticleOfRecommendedContent 0   xpath                          (//div[@class='titleAndAuthor']/h2/a)[last()]
lnk_abstractForLastArticle          0   xpath                          (//div[@class='articleLinksIcons']//a[contains(text(),'Abstract')])[last()]
txt_abstract                        0   css                           #Abstract
lnk_articleOptionsLinks             0   xpath                          (//li/a[contains(text(),'${link}')])[last()]
txt_addedToChemWork                 0   xpath                           //li/a[contains(text(),'Added to ACS ChemWorx')]
lnk_pdf                             0   xpath                           (//li/a[text()='PDF'])[last()]
txt_articleTitle                    0   css                             .articleTitle
txt_noRecommendedArticlesMsg        0   xpath                           //div[@id='twoColText']/p
input_bigLoginForm_email			0    css				               #login-form-userid
input_bigLoginForm_password			0	css				               #login-form-password
btn_bigLoginForm_submit				0	css				               #login-button
txt_emailAddress                    0   css                             div.emailShowDiv span 
lnk_librarianAdministratorSite      0   xpath                            //a[text()='Librarian Administration Site']
link_Mendeley						0	css							    li[style*='display']>img+a[href*='Mendeley']
btn_RevokeMendeley					0	css								.achs-mendeley_alter>button[class*='mendeley-revoke']
Msg_MendeleyPairStatus				0	css								.achs-mendeley_alter>p[class='pair-status-msg']
==================================================================================================================