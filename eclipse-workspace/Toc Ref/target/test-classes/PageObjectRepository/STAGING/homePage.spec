Home Page 

# Object Definitions
==================================================================================================================

Text_LoggedInfo		           0        css	         .institution+.user-login-bar
links_loggedIn				   0	    css			 .user-login-bar>a	
link_LogIn					   0	    xpath		 //a[text()="Log In"]
btn_MyActivity				   0        xpath	     //span[text()="My Activity"]/parent::a 
btn_publication                0  	    xpath       //span[text()='Publications']//ancestor::a[@class='pubModal_button']
lnk_pubmodal                   0        xpath       //div[@class='pub-modal' and contains(@style,"block")]//ul[@class='pub-modal_types-list']//a[text()='${link}']
lnk_topLeftCornerLinks         0        xpath		//div[@class='header_mainmenu']//a[text()='${link}']
logo_acs					   0		css			.menu__logo>img
Img_homePage                   0        css         .header_content_left img
logo_C&EN                      0        css			.logo-link>img[src*='cenLogo']
logo_CAS                       0        css         .cas-logo
link_LogIn					   0		xpath		//a[text()="Log In"]
box_search					   0		css			.quick-search_default
Icon_Hamburger				   0		css			.header_burger-menu>a
Menu_Hamburger				   0        css			.header_burger-menu_opened .header_burger-menu_content
PubButton_Hamburger			   0		css			.header_burger-menu_opened .header_burger-menu_content .pubModal_button
MenuHeadings_Hamburger		   0		xpath        //div[@class="header_burger-menu_content"]/ul[2]/li[not(contains(@class,"hidden"))]/a
subLinks_Hamburger			   0        xpath		(//a[text()='${heading}']/parent::li)//ul//a
input_SearchBox				   0		css			.quick-search_default>input
iconSearch_SearchBox		   0		css			.quick-search_default>button[class='icon-search']
Searchbox_Citations            0        css			.quick-search_citation
header_Citations               0        css         .quick-search_content p
dropdown_JournalsSearchBox     0        css 		span[class*='quick-search_journals']
input_OptionsSearchBox		   0        css			.quick-search_citation input[placeholder='${option}']
iconSearch_Citation            0        css         .quick-search_citation .icon-search
modal_Publications			   0        css			[style*='block;'] .pub-modal_content
ContentTypes_Modal			   0        xpath		//h6[text()="CONTENT TYPES"]/ancestor::div[@class='pub-modal' and contains(@style,"block")]//ul[@class='pub-modal_types-list']/li/a
ContentType_Selected		   0        xpath		//div[@class='pub-modal' and contains(@style,"block")]//ul[@class='pub-modal_types-list']//a[text()='${Type}']/parent::li		
links_PubsContent			   0        xpath		(//div[@class="pub-content"])[1]/a[not(@style="display: none;")]
Section_Subjects			   0        css			.pub-modal_subjects
header_Subjects				   0        css 		.pub-modal_subjects>h6
CheckBoxes_Subjects			   0        css		    div[style*='block'] .pub-modal_subjects li label
text_Subjects				   0		css			div[style*='block'] .pub-modal_subjects li span
headings_Alphabets			   0        css			div[style*='block'] .pub-modal_right--inner span[class='pub_char']:not([style*='none']) >span
select_JournalDropdownSearch   0        css			.quick-search_citation select
selectedJournal_Search		   0        css			.jcf-select-text>span	
texts_EditorsChoice			   0        css 		.heading-title_style-1 span
slides_EditorsChoice		   0        css			#carousel>div
images_EditorsChoice		   0		css			#carousel img  
JounalNames_EditorsChoice	   0        css			.carousel-caption>span[class*='jour-name']
Titles_EditorsChoice		   0        css			.carousel-caption>a[class*='title']
authors_EditorsChoice		   0        css			.carousel-caption ul[title*='list of authors'] 
radioButton_Slides			   0		css			.caro-blip-box .tracker-individual-container div
buttons_SlideNavigation		   0        css			.carousel-container .caro-btn
slide_selected				   0        css			#carousel .selected
title_selectedSlide			   0        css			.carousel-caption.active >a[class*='title']
h2_Section          		   0        xpath		//h2[text()='Information For']
links_InformationSection	   0		xpath		//h2[text()='Information For']/following-sibling::ul//a
h1_AuthorReviewerPage		   0        xpath		//h1[contains(text(),'ACS Publishing Center')]
img_LibrariansPage			   0        css 		img[src*="InfoCentral"]
login_LibrariansPage		   0        css			.loginWidget
tab_MembersPage				   0		css			.active>a[title='Member Benefits']
description_Section 		   0        xpath		//h2[text()='${link}']/following-sibling::p[1]
btn_eAlerts					   0        xpath		//h2[text()='Sign Up for Email']/following-sibling::a[contains(@class,'EAlerts')]
h1_LoginPromptPage			   0		xpath		//h1[contains(text(),"log in")]		
btn_LoginOrRegister			   0		xpath		//a[text()="Log In or Register"]	
tabs_BrowsePublications		   0        xpath		//ul[@role='tablist']//span[text()='${tabName}']/parent::a		
imgs_JournalCoverArt		   0        css			.tab__content .pub-grid a
ListHeadings_Alphabets		   0        css			 .pub-modal_layout-not-fixed .pub-modal_right--inner span[class='pub_char']:not([style*='none']) >span
links_ListView				   0		xpath		 (//div[@class="pub-content"])[2]//a[not(@style="display: none;")]
links_partners				   0        css			 .footer_first-row_logos a
imgs_partners				   0        css			 .footer_first-row_logos img
link_Copyright				   0        css			 .footer_menu_copyright>a
h1_Copyright				   0		css			 h1>span		
link_PrivacyPolicy			   0		xpath		 //li[contains(@class,"hidden-xs")]//a[contains(text(),"Privacy Policy")]
h1_PrivacyPolicy			   0        xpath		 //h1/span[text()='Privacy Policy']
icons_Follow				   0		css          .footer_social a[class*='${tabName}']
PublicationsLink			   0        xpath		 (//div[@class="pub-content"])[1]//a[not(@style="display: none;") and text()='${Content}']	
DD_SearchJournals			   0		xpath		//span[@class="jcf-list-content"]/ul/li/span[text()='${JournalName}']
popup_AdCEN					   0		css			#sb-title-inner
icon_CloseAdCEN				   0		css			#sb-nav-close
pubmodelContent                0       xpath       (//a[text()='${link}'])[1]
btn_myActivity                 0        css         .header_my-activity a
box_myActivityContent          0        css         .header_my-activity-content
col_recentlyViewed             0        css         [data-name=recentlyViewedDropzone]
col_recommendedDropzone        0        css         [data-name=recommendedDropzone
header_recentlyViewed          0        css         .show-recently-header
header_recommendation          0        css         .header_my-activity-right .show-recommended:nth-child(1)
txt_emptyRecentlyViewedList    0        css         .show-recently-empty
txt_recommendedNotLogged       0        css         .header_my-activity-right .noRecommendedWhenNotLoggedIn
txt_showRecommended            0        css         .header_my-activity-right .show-recommended:nth-child(2)
list_recentlySerialTitle       0        css         .show-recently li .show-recently-serialTitle
list_recentlyViewedArticle     0        css         .show-recently li a
    