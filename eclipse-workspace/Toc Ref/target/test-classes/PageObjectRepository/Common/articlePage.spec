Article landing page

# Object Definitions
==================================================================================================================

h1_ArticleTitle				 0			css 			.article_header-title
btn_Navigation               0          xpath           //div[contains(@class,'content-navigation')]//a[@title='${bar}']        
header_tocBook               0          css             .toc-book .issue-item_title >a
list_author                  0          css             .article_header .loa
txt_author                   0          css             .hlFld-ContribAuthor
txt_PubDate                  0          css             .article_header .pub-date
txt_PubDateValue             0          css             .article_header .pub-date-value
txt_DOI		                 0          xpath           //div[contains(@class,'article_header')]/div[contains(@class,'${type}')]/*
metrics_Article	             0          xpath           //div[@class='articleMetrics_row']//div[contains(@class,'articleMetrics')]/h6
value_metrics				 0			xpath			//div[@class='articleMetrics_row']//div[contains(@class,'articleMetrics')]/div[contains(@class,"articleMetrics-val")]
Icon_share		             0          css             .article_header-share .share__ctrl
btn_Pdf                      0          css              .article_header-footer .button_primary
img_CoverArt				 0			css				 .cover-image__image img
Options_share				 0			css				 .js--open .addthis_toolbox a[class*='${Option}']
Img_AuthorsChoice			 0			css				  a[title*='Editors']>img[alt='Authors Choice']	
btn_eAlerts					 0			css				  a[href*='Alert']
link_LoginTest				 0  		xpath			  //a[contains(text(),"Log In or Register")]
text_CiteThis				 0			css				  .article_header-cite-this>a strong
hyperLink_CiteThisInfo		 0			css				  .article_header-cite-this a[title='Cite This']~span
link_CiteThis				 0			css				  .article_header-cite-this a[title='Cite This']
h3_DownloadCitations		 0			xpath			   //h3[contains(text(),"Download Citation")]
button_RisCiteDownload		 0			css				   .submit>input[value*='Download Citation(s)']
TabList_QV					 0			css					[role='presentation']>a
Icon_QVClose				 0			css					.tab__close>i
Imgs_FigureTab				 0			css					.tab__content figure
text_Abstract				 0			css					.articleBody_abstractText
links_PrimaryData			 0			css					.tab__pane-primaryData li>a
References_Tab				 0			css					#rightTab-references .reference	
text_SupportingInfo			 0			xpath				//h2[text()="Supporting Information"]/following-sibling::div[1]	
Tab_DefaultSelected			 0			css					li[role='presentation']		
shortcut_QVTabs				 0			css					.tab-nav-shortcut-active>li	
stickyHeader				 0			css					header[class*='header_article']
h4_StickyHeaderTitle		 0			css					.header_contnav-title>h4	
QuickSearch_StickyHeader	 0			css					div[class*='header_content'] .quick-search>a
HamburgerMenu_StickyHeader	 0			css					.header_burger-menu_button		
JournalLogo_StickyHeader	 0			css					.header_content_row img[alt*='Journal']
NvgtnArrows_StickyHeader	 0 			css					.header_contnav a[title='${Button}']
PopUp_AuthorHover			 0			xpath				//*[@class="loa"]/li[${index}]//div[title_firstRefcontains(@class,"%{InfoType}")]
link_ArticleType			 0			css					.article_relation-${type} a
icon_asterisk				 0			css					.article_relation-${type}>i[class*='asterisk']
texts_ArticleType			 0			css					.article_relation-${type} .relation-text span[class*='%{Option}']
span_ArticleTypeLine		 0			css					.relation-text
link_DOIRetraction			 0			xpath				//p[contains(text(),"The original")]//a[@class="ext-link"]
tab_references               0          css                 [role='presentation']>a[title='References']
link_referencesNum           0          css                 #rightTab-references .reference a[class='refNumLink']
txt_noReference              0          css                 .tab__pane-references-no-refs		
Msg_AccessDenial			 0			css					.access-denial-widget-container .access-denied-msg
title_firstRef               0          css                 #rightTab-cit1 .NLM_article-title
txt_referenceSection         0          xpath               (//div[@class="refs-header-label"]//h2[text()='References'])[1]
link_refrnceSectionNum       0          css                  #references .reference .refLabel a
title_referenceSection       0          css                 .refs-header-label ~#references #cit1 .NLM_article-title
block_ack                    0          css                 .ack