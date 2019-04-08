Page Title: Reagent Chemicals Ebook

# Object Definitions
=========================================================================================================================
img_reagentChemicals			    0			css 						.book-toc-header_cover>img[src*='acsreagent']
lnk_bookParts						0			css							.toc-book>.accordion>a[class*='accordion__control']
txt_bookParts						0			css							.toc-book>.accordion>a[class*='accordion__control']>span
lnk_bookChapters					0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]//h5[contains(@class,"title")]/a
doi_bookChapters					0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]//span[contains(@class,"issue-item_doi")]
pubDate_bookChapters				0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]//span[contains(@class,"pub-date-value")]
accessToken_bookChapters			0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]//span[contains(@class,"issue-item_access")]
pdf_bookChapters					0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]//a[contains(@title,"PDF")]
AbstractAngleDown_bookChapters		0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]//a[contains(@title,"Preview Abstract")]
h1_reagentChemicals					0			css							.book-toc-header_text>h1
txt_subtitle						0			css							.book-toc-header_text>h6
lnk_editors							0			css							[title="list of authors"] li>span>a
txt_publicationDate					0			css							strong>.pub-date-value
txt_CopyrightInfo					0			xpath						//div[contains(text(),"Copyright")]
txt_DOIorISBN						0			css							.book-toc-header_text_right>div:nth-child(${index})
txt_affiliations					0			css							.affiliations>div
img_ACSeBooks						0			css							[alt="ACS Ebooks"]
lnk_ViewAllBooks			    	0			css						    [title="View all books"]
icon_Share							0			css							.share_button
SearchResults_Filter				0			css							.pinned-filter>span
links_SharingOption			    	0           css							.share .addthis_toolbox a
lnk_book4SubSections				0			xpath						//div[preceding-sibling::a[@aria-expanded="true"]]/div/a[contains(@class,"accordion__control")]
txt_book4Subsections            	0           xpath						//div[preceding-sibling::a[@aria-expanded="true"]]/div/a[contains(@class,"accordion__control")]/span
txt_subsectionReagents				0			xpath						//div[preceding-sibling::a//span[contains(text(),'"${alphabet}"')]]//span[@class="hlFld-Title"]//a









lnk_reagentChemicals						xpath						//a[text()='ACS Reagent Chemicals']
img_coverThumbnail							xpath						//div[@class='bookBox']//img[contains(@src,"acsreagents")]
txt_publicationDate							css							.epubdate>strong
txt_CopyrightInfo							css							.meta1>div:nth-child(4)
btn_learnHowToSearchReagents				css							.reallyPadTop a
txt_searchReagentsInfoDisplayed				xpath						//div[@class="toggled" and (@style="" or contains(@style,"block"))]
box_searchSuite								css							#searchText
radioBtn_ReagentChemicals					css							#qsTitleButton
h1_SearchResults							xpath						//h1[contains(text(),"Search Results")]
lnk_book4SubSections						css							.level1>div:nth-child(1)
txt_book4Subsections						css							.level1 h3
lnks_chaptersUnderBookPart					xpath					    (//div[preceding-sibling::div[contains(@class,"expanded")]]//div[contains(@class,"articleLinksIcons")])[${index}]//a
txt_chapterHeadingOnLandingPage				css							.articleTitle span
lnk_shareOptions							css							svg[title='${option}']
lnks_chapterOptions							css							.publicationFormatCont a
lnk_chemworx								xpath						//div[contains(@class,"chemworx")]/a
lnks_OtherOptions							xpath						//a[@title="${Options}"]
box_actionDetailDownloadCitation			css							#bookChapterCit .actionDetailBox
box_actionDetailFavContent					css							#bookChapterFav .actionDetailBox
lnks_actionDetailOptions					xpath						//*[parent::span[@class="actionDetailBox"]/span[contains(text(),"${Options}")]][@class="links"]/a
h1_Sections									xpath						//h1[text()="${section}"]
txt_articleUnderFavContent					css							.favoritePanel .articleBoxMeta a
txt_articleDownloadCitation					css							.downloadCitList h4
txt_articleEmailColleague					css							.email-colleague-pubs a
h2_citationAlerts							css							.citHeader				
txt_articleCitationAlerts					css							.citationsPanel .articleBoxMeta a
txt_metricsPublicationDate					xpath						//div[contains(text(),"Published online")]
h2_ACSNewsHeader							xpath						//h2[contains(text(),"ACS News")]	
txt_ACSNewsHeadlines						css							.lister li a>h2	
txt_accessDenialMessage						xpath						//a[contains(@href,"accessDenialContent")] 
chckBox_Select								css							#articleListHeader_selectAllToc		
btn_remove1									css							.removeFavorites
btn_remove2									css							.removeArticle	
