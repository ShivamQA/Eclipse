Journal Home page

# Object Definitions
==================================================================================================================
img_JournalLogo					0				css					.jhHeader_logo>a
btn_SubmitManuscript			0				css					.jhHeader_left a[title*='Submit Manuscript']
text_Metrics					0				css					.jhHeader_left .jhHeader_metrics>div>span
Links_HeaderLeft				0				css					.jhHeader_left a[title*='${BtnName}']
Icon_Share						0				css					.share .share__ctrl
Options_share				    0			    css				    .js--open .addthis_toolbox a[class*='${Option}']
btn_ContinueParagon				0				css					.c-modal__actions>a:nth-child(1)
alert_loginParagon				0				css					.alert-info  h4
loginForm_Paragon				0				css					.c-block>form[action*='login']
dropDown_Institution			0				css					select[name*='Institution']
input_FormFields				0				css					.input-group>input
CheckBox_RecommendResons		0				css					.reason~div .checkbox--primary
loginBox_Subscription			0				css					#signInBox
text_EditorsInfo				0				css					.hdr_editors
link_EditorName					0				css					.jhHeader_editor .hdr_editors a[title='About the Editor']
link_EditorialBoard				0				css					.hdr_editorial>a
h1_EditorProfile				0				xpath				//h1[contains(text(),"Editor Profile")]
txt_EditorNameProfilePage		0				css					.editorInfo>dt
h2_EditorProfilePage			0				css					div h2
h3_EditorProfilePage			0				css					div h3
h1_EditorialBoard				0				xpath				//h1[text()="Editorial Board"]
h2_EditorialBoard				0				css					div h2
EditorInfo_EditorialBoard		0				css					.editorInfo
Img_JournalCover				0				css					.jhHeader_right>a>img
MetaData_JournalIssue			0				css					.jhHeader_right .jhHeader_meta>span
btn_eAlerts						0				css					.jhHeader_right a[title*='e-Alerts']
link_LoginTest					0				xpath				//h1[contains(text(),"Log In")]
links_BlueNavigationBar			0				css					.journal-home_linkbar li>a
h1_PublishingCenter				0				css					h1[class*='a-title']
btn_LoginPublishingCenter		0				css					.page-header__btns .btn-lg-pageheader
links_OpenAccess				0			    css          		.page-content .mouseover
h1_AboutJournal					0				xpath				//h1[contains(text(),"About the Journal")]
Img_AboutJournalCover			0				css					.cover-image__image img
text_AboutJournal				0				css					h2~p>cite
header_Collections				0				xpath				//div[contains(@class,"journal-home_section")]//h2[contains(text(),"${Section}")]
txt_DescriptionCollection		0				xpath				//h2[contains(text(),"${Section}")]/parent::div/p
link_SeeMore					0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[contains(@class,"slider-dropzone")]/a
Articles_Filmstrip				0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="owl-stage"]/div
link_MoreFromCollection			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="owl-stage"]//div[@class="teaser_more-from-collection"]/a
btn_NavigationFilmstrip			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="owl-nav"]/button[contains(@title,"%{btn}")]
title_ArticleFilmstrip			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="owl-stage"]//h4/a
txt_AuthorsFilmstrip			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//ul[@title="list of authors"]
img_AuthorsFilmstrip			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="teaser_image"]/a
PubDate_Filmstrip				0				xpath				//h2[contains(text(),"${Section}")]/parent::div//span[@class="pub-date-value"]
Btns_NavigationBalls			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="owl-dots"]/button
Titles_ActiveArticles			0				xpath				//h2[contains(text(),"${Section}")]/parent::div//div[@class="owl-stage"]//div[contains(@class,"active")]//h4/a