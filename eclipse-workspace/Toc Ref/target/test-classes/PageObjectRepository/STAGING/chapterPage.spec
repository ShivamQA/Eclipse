Chapter landing page 

# Object Definitions
==================================================================================================================

header_chapterTOC            0  		css            .article_header-title
btn_NavigationeBookTOC       0          xpath           //div[contains(@class,'content-navigation')]//a[@title='${bar}']        
header_tocBook               0          css             .toc-book .issue-item_title >a
list_author                  0          css             .article_header .loa >li:nth-child(1)
txt_author                   0          css             .hlFld-ContribAuthor
txt_PubeBookTOC              0          css             .article_header .pub-date
txt_PubValueTOC              0          css             .article_header .pub-date-value
txt_DOICodeTOC               0          xpath           //div[contains(@class,'article_header')]/div[contains(@class,'${type}')]
mtrix_Articletoc             0          xpath           //div[@class='articleMetrics_row']//div[contains(@class,'articleMetrics')]/h6
value_metrix				 0			xpath			//div[@class='articleMetrics_row']//div[contains(@class,'articleMetrics')]/div[contains(@class,"articleMetrics-val")]
lnk_iconShareTOC             0          css             .article_header-share .share__ctrl
lnk_fixedTOC                 0          xpath            //div[contains(@class,'article_header')]/div[contains(@class,'${type}')]/a
btn_Pdf                      0          css              .article_header-footer .button_primary
txt_sponsorDivision          0          css              .sponsors
img_CoverArt				 0			css				 .cover-image__image>img
Options_share				 0			css				 .js--open .addthis_toolbox a[class*='${Option}']
header_eBookTOC              0          css              .book-toc-header_text>h1
PopUp_AuthorHover			 0			xpath			//*[@class="loa"]/li[${index}]//div[contains(@class,"%{InfoType}")]
icon_articlePubHistory       0          css             [data-db-target-for='article_header-history'] 
block_dropBlockHolder        0          css             .article_header-history .dropBlock__holder
title_dropBlockHolder        0          css             [class='dropBlock__holder js--open'] h4
list_articleHistory          0          css              .article-chapter-history-list li
txt_articlePubHistory        0          css               .article-chapter-history-list li span:last-child