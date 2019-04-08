Current Issue Page

# Object Definitions
==================================================================================================================
logo_Header			        	 0			   css		    			.niHeader_logo>img
Img_JournalCover		    	 0			   css						.niHeader_covers-lg>img
Imgs_SmallJournalCovers		 	 0			   css						.niHeader_covers-sm>img
txt_Metadata				 	 0			   css						.niHeader_about-meta>span
txt_AboutJournal			 	 0			   xpath					//h6[text()="About the Cover:"]/following-sibling::div
link_ViewArticle			     0			   xpath                    //h6[text()="About the Cover:"]/following-sibling::div/a[text()="View the article."]
link_DownloadCover			 	 0			   css						.niHeader_about-caption+div[class*='download-btn']>button
links_InThisIssueSections	 	 0			   xpath					//h6[text()="In this issue:"]/following-sibling::ul//a
link_NextIssue				 	 0			   css						.niHeader_right .niHeader_next
link_PreviousIssue			 	 0			   css						.niHeader_right .niHeader_prev
link_ViewAllIssues			 	 0			   css						.niHeader_right a[title='View all issues']
link_ASAPs						 0			   css						.niHeader_right a[title='ASAPs']
link_JAMs						 0			   css						.niHeader_right a[title='JAMs']
btn_GeteAlerts					 0			   css						.niHeader_getEAlerts
btn_SubmitManuscripts			 0			   css						.niHeader_submitManuscript
icon_Share						 0			   css						.icon-Icon_Share
headings_Sections				 0			   xpath					//h6[text()="${Heading}"]	
Img_SiteHeaderLogo				 0			   css						img[alt='logo']
txt_SortByOption				 0		       xpath					//label[text()="Sort By:"]/following-sibling::select/option
option_SelectedSortBy			 0			   xpath					//label[text()="Sort By:"]/following-sibling::select/option[@selected='selected']
Options_SortBy					 0		       css   					.jcf-list-content li
dropDown_SortBy					 0			   css						[name='sortBy']+.jcf-select
headings_SortedByDate			 0			   css						.toc_group-heading-by-date
headings_SortedByPage			 0			   css						.toc_group-heading