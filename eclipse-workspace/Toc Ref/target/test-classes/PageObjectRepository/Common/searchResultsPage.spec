Search results page

# Object Definitions
==================================================================================================================

input_SearchBox				   0		css			.quick-search_default>input
text_ResultCount			   0        css		    .result__current
txt_totalResultCount           0        css         .result__count
txt_FiltersApplied             0        css          a[title='Alphanumeric Filter']
txt_FilterAppliedResult        0        css          #appliedFacet >ul >li>span
img_saveThisSearchbox          0        css          #saveSearchTriggerButton
txtbox_searchName              0        css          [name='saveSearchName']                           
txt_saveThisSearch             0        css          .modal__header>h2
lnk_cancelLink                 0        css          .modal__header>button>i
txt_alertMeNotification        0        css          .modal__body h4    
li_radioOptionsForAlertMe      0        css          .modal__body .input-group>label>input
btn_saveSearchTerm             0        css           #newSearchSaveButton
txt_firstArticleTitle          0        xpath         (//span[@class='single_highlight_class'])[${index}]
btn_radioSearchBox             0        xpath         //div[@class='quick-search_content']//div[@class='quick-search_journal-filter']/label/input[@value='${option}']
quick_search                   0        css           .quick-search_content
txt_followResult               0        css           span[class*=follow__label]
lnk_RssField                   0        css           a[name='RSSFeed']
lbl_txtSORT                    0        css           span[class*=sort__label]
txt_selectedSortByOption       0        css           span[class=sort-by]
arrow_ddSortBy                 0        css           .search__sort .inline-icon
txt_ddSortBy                   0        css           #search-sort-drop>li>a
btn_RefineSearch               0        css           a[class*=search-result__refine]
arrow_RefineSearch             0        css           a[class*=search-result__refine]>i
txt_perPageOption              0        css           div[class*=per-page]>span
lst_perPageOption              0        css           div[class*=per-page]>ul>li>a
txt_selectedPerPage            0        css           div[class*=per-page]>ul>li>a[class*='selected']
lst_narrowResultTitle          0        css           #filter >div[class*='facet']>a
arrow_nextPage                 0        css           .pagination>ul~span>a[title="Next Page"]
arrow_prviousPage              0        css           .pagination>span>a[title="Previous Page"]
btn_editSaveSearch             0        css           a[href="/search/saved"]
lnk_loginSaveSearch            0        css           .modal__body>p>a[href*='showLogin']  
lnk_narrrowTitleOption         0        css           #filter >div[class*='facet']>a[id='${title}'] ~div .facet__list>li>a[title='%{option}']
txt_filterResult               0        css           .facet__title    
txt_filterSearchOptn           0        css           .facet__list--applied>li>span
btn_rmvFilterOptn              0        css           .facet__list--applied>li>a
txt_dateSearchResult           0        css           .pub-date-value
txt_typeSearchResult           0        css           .infoType
txt_journalNameSearchResult    0        css           .issue-item_jour-name
txt_authorNameSearchResult     0        css           .issue-item_loa  
lst_searchResult               0        css           div[class='issue-item clearfix']
select_ddJournal               0        css           span[class*='quick-search_journals']
dd_journalOption               0        css           form[action*='quickLink']>select>option[value='${optn}']
txtbox_journalOption           0        css           span[class*='quick-search_journals']>span>span
txtbox_vol                     0        css           input[name='quickLinkVolume']
txtbox_page                    0        css           input[name='quickLinkPage']
iconSrchCit_SearchBox          0        css           span[class*='quick-search_journals'] ~button[class='icon-search']
page_rssXML                    0        css           .header ~ .pretty-print
tab_refineSearch               0        css           .advancedSearch__tabs>ul>li>a[title='${tab}']
txtbox_advancedOptn            0        css            select[name='field${index}'] ~span
dd_searchFiled                 0        xpath          //select[@name='field${index}']/option[@value='%{option}']
DD_SearchJournals			   0		xpath		//span[@class="jcf-list-content"]/ul/li/span[text()='${JournalName}']
dd_searchFiled1                0        css            select[name='field${index}']
dd_advanceOption               0        css            .advanced-search--searchIn select
dd_valueadvanceOptn            0        css            .advanced-search--searchIn select[name='field${index}']>option
txtbox_advancePublishedIn      0        css            input[name='publication'] 
txt_openAccessLabels           0        css            .advanced-search_access-type>label>span
lnk_optionOpenAccess           0        css            .advanced-search_choice-collection [title*='${option}']
radio_allContent               0        css            label[for='allContent']>input[id='allContent']
header_pubDate                 0        css            #publicationDate-label
select_ddFromCustomRange       0        css            label[for='customRange'] ~select 
select_ddToCustomRange         0        css            label[for='customRange'] ~div select
select_LastPubDateOptn         0        css            #staticRangeSelect ~.jcf-select-staticRange
dd_LastPubDateOptn             0        css            .jcf-option
header_c_enGroup               0        css            .searchIn--field:nth-child(4)>label
txt_optionsC_ENGroup           0        css            .advanced-search_CandEN >label:nth-child(${index})
btn_advancedSearch             0        css            #advanced-search-btn
first                          0        css            #text0
txt_openAccessIconText         0        css            .issue-item_buttons-list >li:nth-child(1)>a
list_issueItemType             0        css            .issue-item_type
list_articleTitles             0        css            .issue-item_title>a
ddbox_CustomRange              0        xpath            //select[@id='${range}']/following-sibling::span
dd_optionCustomRange           0        xpath          //select[@id='${range}']/following-sibling::span//span[text()='%{field}']
txt_colSearchHistory           0        css            th[class='search-history savedResult']
txt_rowSavedHistory            0        css            tr[class='expanded'] td[data-thead='${row}']
txt_rowSearchHistory           0        css            td[data-thead='${row}']
btn_runSearchHistory           0        css            [title='Run']
txt_noSavedSearchMessage       0        css            .emptySavedSearches
txt_colRefineSavedSearch       0        css            .table-responsive th
btn_userActionRefineSaved      0        xpath          //td[@data-thead='Saved Search Name' and text()='${term}']/following-sibling::td/a
Options_SearchField			   0		xpath			//span[@class="jcf-list-content"]//span[text()="${field}"]
filter_selection               0        xpath           //a[@id="${field}"]/following-sibling::div//a
filter_selectionCounter        0        xpath           //a[@id="${field}"]/following-sibling::div//a/span[@class='facet__counter']
filter_showMoreSeletion        0        xpath           //a[@id="${field}"]/following-sibling::div/a
Options_SearchField1		   0		xpath			//span[@class="jcf-list-content"]//span[text()="${field}"]/parent::li
link_AnyPage                   0        xpath           //ul[contains(@class,'pagination__list')]/li/a[text()='${page}']
link_LogIn                     0        css             h1 ~a[href*="/ssoRequestForLoginPage"]
lnk_LogInSaveSearchPopUp       0        xpath           //a[text()="login"]
<<<<<<< HEAD
Options_SearchField			   0		xpath			//span[@class="jcf-list-content"]//span[text()='${field}']	
input_Search				   0		css				input[name='text1']		
txt_noContentMsg               0        css             .search-result__no-result
Title_Article				   0 	    xpath           (//h5[contains(@class,"issue-item_title")]//a)[${index}]	
Authors_Article				   0		xpath			(//ul[contains(@class,"issue-item_loa")])[${index}]	
Content_Title				   0        css				.article_header-title
Content_Abstract			   0		css				#abstractBox
Content_ArticleBody			   0        css				.article_content-left
Button_FirstPage			   0		css				(//a[@title='First Page'])[${index}]
Content_FigTable			   0		css				.article_content-left .hlFld-FigureCaption p
txt_highlightedWordsInTitle    0        xpath			(//h5[contains(@class,"issue-item_title")])[${index}]//span[contains(@class,'highlight')]
txt_highlightedWordsInAuthor   0        xpath			(//ul[contains(@class,"issue-item_loa")])[${index}]//span[contains(@class,'highlight')]
txt_highlightedWordsInAbstract 0        xpath           //div[@id='abstractBox']//span[contains(@class,'highlight')]
txt_hightlightedWordsInArticle 0        css              .NLM_p [class*='highlight']
txt_highlightedWordsfigTbls    0        css			     .article_content-left .hlFld-FigureCaption [class*='highlight']
=======
Options_SearchField			  0			xpath			//span[@class="jcf-list-content"]//span[text()='${field}']	
input_Search				  0			css				input[name='text1']		
txt_noContentMsg              0         css             .search-result__no-result
Title_Article				  0 	    xpath           (//h5[contains(@class,"issue-item_title")]//a)[${index}]	
Authors_Article				  0			xpath			(//ul[contains(@class,"issue-item_loa")])[${index}]	
Content_Title				  0         css				.article_header-title
Content_Abstract			  0			css				#abstractBox
Content_ArticleBody			  0         css				.article_content-left
Button_FirstPage			  0			css				(//a[@title='First Page'])[${index}]
Content_FigTable			  0			css				.article_content-left .hlFld-FigureCaption p
txt_highlightedWordsInTitle   0         xpath			(//h5[contains(@class,"issue-item_title")])[${index}]//span[contains(@class,'highlight')]
txt_highlightedWordsInAuthor  0         xpath			(//ul[contains(@class,"issue-item_loa")])[${index}]//span[contains(@class,'highlight')]
text_Abstract		   	      0			css			    .articleBody_abstractText
lst_highlightedTermInAbstarct 0         css             //div[(@class='article_abstract')]//*[contains(text(),'cell')]
lst_highlightedTermAnywhere   0         css             //*[contains(text(),'cell')]
>>>>>>> c672af02b3c29faebef0a64c9345178234e552f3
