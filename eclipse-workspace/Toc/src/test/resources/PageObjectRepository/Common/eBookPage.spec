eBook

# Object Definitions
==================================================================================================================

logo_ebook                      0   xpath        //img[@class='books-home_header-logo']
txt_ebookDescription            0   xpath        //div[contains(@class,'books-home_header-left')]/p[contains(text(),'${ebook}')]
identifiers                     0   css          .books-home_header-meta >span
lnk_booksHomeHeaderList         0  xpath         //a[@title='${link}']
txt_headerTitle                 0   css          .books-home_header-list-title
lnk_headerlist                  0  xpath         //h4[@class='books-home_header-list-title' and text()='${list}']/parent::div//ul//a
block_booksMain                 0  xpath         //div[@class='books-home_main-block']/img[@alt='${block}']
lnk_blockBooksMain              0  xpath         //div[@class='books-home_main-block']/img[@alt='${block}']/parent::div//a
btn_blockBooksMain              0  xpath         //div[@class='books-home_main-block']/img[@alt='${block}']/parent::div//button
txt_booksHomeTitle              0  xpath         //h2[contains(@class,'books-home_heading-title')]/span
lnk_bookHomeBlockSecondary      0   xpath          //h2[contains(@class,'books-home_heading-title')]/span[text()='${block}']/ancestor::div[@class='row']/following-sibling::div//a
txtbox_homePage                 0   xpath         //input[@type='search' and @placeholder='${txtBox}']
txt_headerSubscribe             0   css           .container  h1
txt_headerRecommendation        0   css           .recommend-to-librarian p
lnk_presentationTitle           0   xpath         //li[@role='presentation']//a[@title='${tab}']
btn_SearchFindABook             0   xpath         //input[@placeholder='Find a Book']/parent::div/button[@title='Search']
SearchBooksResultFindABook      0   css           .search-result_book .books-list_content >h4>a
txt_SearchResultFindABook       0   css            .book-qs_result
btn_SearchResultNavigation      0   xpath          //div[@class='simple-pagination']//span[text()='${btn}']/parent::a
txt_selectedYearDropDown        0  xpath          //select[@name='Year']//following-sibling::span/span[@class='jcf-select-text']/span
dd_selectYearOption             0  xpath          //span[text()='${year}']
dd_selectYear                   0  css            select[name='Year'] + span
txt_MessageCountForYear         0  css            .find-abook_year-page
SearchBooksYearResult           0  css            .search-result_book .books-list_content .books-list_content-right >span:nth-child(3)      
coverPage_eBook                 0   css           .book-toc-header_cover >img
header_eBookTOC                 0   css           .book-toc-header_text>h1
txt_EditoreBookTOC              0   css           .book-toc-header_editors>strong
lnk_EditorAuthoreBookTOC        0   xpath         //ul[@title='list of authors']//span/a
txt_fixedeBookTOC               0   xpath         //div[contains(@class,'book-toc-header_text_left')]//div[contains(text(),'${type}')]
txt_PubeBookTOC                 0   css           .book-toc-header_text_left .pub-date
txt_PubValueebookTOC            0   css            .book-toc-header_text_left .pub-date-value
lst_affliations                 0   css             .affiliations
txt_affiliationseBookTOC        0   css            .affiliations>div
txt_SponsoringDivisioneBookTOC  0   css            .sponsors>strong
lnk_SeeAllDivisioneBookTOC      0   css            .sponsors>*>a[title='List of ACS Technical Divisions']
txt_SSNCodeDOIeBookTOC          0   xpath            //div[contains(@class,'book-toc-header_text_right')]/div[contains(text(),'${code}')]
lnk_iconShareeBookTOC           0   xpath          //i[@class='icon-Icon_Share']/parent::a
btn_NavigationeBookTOC          0   xpath          //div[contains(@class,'book-toc-header_right')]//a[@title='${bar}']        
header_tocBook                  0  css            .toc-book .issue-item_title >a
css_ChapterSeperator            0  css            .acs_main-separator
authorNametocBook               0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::ul[@aria-label='author']
list_authorNametocBook          0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::ul[@aria-label='author']//span
txt_ChPageDOItocBook            0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::div/span[contains(@class,'%{type}')]
txt_PubtocBook                  0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::div//span[@class='issue-item_pubdate']/span[1]
txt_PubValuetocBook             0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::div//span[contains(@class,'pub-date-value')]
lnk_freeAccesstocBook           0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::div//span[contains(@class,'issue-item_acces')]
lnk_issueitemtocBook            0  xpath          //a[text()='${Title}']/ancestor::span/following-sibling::div//a[@title='%{type}']
headerTxt_GuideBook             0  css            .book-toc-header_text >h1
cover_logoGuideBook             0  css            img[alt='Book logo']
header_bookAdvancedTOC          0   css           .books-list_content a
txt_PubAdvancedTocBook          0   xpath         //a[text()='${Title}']/ancestor::span/following-sibling::div//span[@class='issue-item_pubdate']/span[1]
lable_selectsponsoring          0   css           label[for=sponsor]
dd_sponsorDivision              0    css          select[name=sponsor]
btn_GoSponsorDivision           0   xpath          //button[text()='Go']
dd_selectsponsorDivisionOption  0   xpath          //span[text()="${lable}"]
dd_selectsponsorDivision        0   css            select[name='sponsor'] + span
txt_selectedsponsorDropDown     0  xpath          //select[@name='sponsor']//following-sibling::span/span[@class='jcf-select-text']/span
cover_books                     0   css           books-list_cover
txt_ddsponsorDivisionOption     0   css           .jcf-list-content >ul>li>span
title_selectAllDivisionPage     0   css           .articleTitle >div>h1>span
container_selectAllDvsnPg       0   css           text parbase section
list_iconShare                  0   xpath         //ul[contains(@class,'rlist w-slide--list')]//a
btn_closeShareIcon              0   css           icon-Icon_Share icon-close_thin
