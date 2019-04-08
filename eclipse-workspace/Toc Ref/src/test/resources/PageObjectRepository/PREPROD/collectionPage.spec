Collections landing page

# Object Definitions
==================================================================================================================

h1_Collection			        0			xpath				//h1[contains(text(),"${CollectionName}")]
txt_Description			   		0			xpath				//h6[text()="Description:"]/following-sibling::div[1]
link_TimeframeOptions	   		0           css	     			.mostread-list a
Option_TimeframeSelected		0			css		    		.mostread-list a[class*='mostread-visited']
btn_eAlerts					    0     		xpath				//span[text()="Get e-Alerts"]/parent::a
txt_MetaData			   		0       	css			    	.ajcHeader_about-meta>span
Icon_Share				   		0			css					.share_button>a	
Options_share				    0			css				    .js--open .addthis_toolbox a[class*='${Option}']
Container_Articles				0			css					.issue-item
title_Article					0			css					.issue-item_title>a
Authors_Article					0			css					.issue-item .issue-item_loa
CiteInfo_JournalName			0			css					.issue-item .issue-item_info .issue-item_jour-name
CiteInfo_Year					0			css					.issue-item .issue-item_info .issue-item_year
CiteInfo_Vol					0			css					.issue-item .issue-item_info .issue-item_vol-num
CiteInfo_Issue					0			css					.issue-item .issue-item_info .issue-item_issue-num
CiteInfo_PageRange				0			css					.issue-item .issue-item_info .issue-item_page-range
CiteInfo_Type					0			css					.issue-item .issue-item_info .issue-item_type
Pubdate_Article					0			css					.issue-item .issue-item_info .issue-item_pubdate .pub-date
Pubdate_Value					0			css					.issue-item .issue-item_info .issue-item_pubdate .pub-date-value
Date_EditorsChoice				0			xpath				//*[@class="issue-item_pubdate"]/span[contains(text(),"ACS Editor")]
Date_EditorsChoiceValue			0			xpath				//span[contains(text(),"ACS Editor")]/parent::span
AccessSpecifier_Article			0			css					.issue-item .issue-item_access>span
Abstract_Article				0			css					.issue-item [href*='abs']
PDF_Article						0			css					.issue-item [title='PDF']
FullText_Article				0			css					.issue-item [href*='full']
Icon_AbstractAngleDown			0			css					.issue-item [title='Preview Abstract']
Img_Article						0			css					.issue-item .issue-item_img>img
text_ExpandedAbstract			0			css					[title='Preview Abstract']+div[style*='block']>span
FullText_Indexed				0			xpath				(//*[contains(@class,"issue")]//*[contains(@href,"full")])[${index}]
link_ViewAllIssues				0			css					[title='journal logo']+div a[title='View all issues']
link_CurrentIssue				0			css					[title='journal logo']+div a[title='Current issue']
link_ASAPs						0			css					[title='journal logo']+div a[title='ASAPs']
Img_Header						0			css					.ajcHeader_right img
link_JamFAQ						0			xpath				//a[contains(text(),"FAQ")]
link_EditorsChoiceAbout			0			xpath				//a[contains(text(),"About ACS Editors Choice")]
DropDown_ECSortBy				0			xpath				//label[text()="Sort By:"]/following-sibling::span
links_ECSortBy					0			css					.jcf-select-drop-content li>span
links_ECPublications			0			css					.jcf-select-drop-content li>span
DropDown_ECPublication			0			xpath				//label[text()="Publication:"]/following-sibling::span
