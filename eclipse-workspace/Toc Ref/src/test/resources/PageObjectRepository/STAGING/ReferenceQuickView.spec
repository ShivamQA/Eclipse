Reference Quick View POP Up

# Object Definitions
==================================================================================================================

header_RQVPopUp			   0			css 			#rqv-header>h2
window_RQVPopUp            0            id              rqv-container-wrap
block_rqvTopHalf           0            id              rqv-reference
block_lst_rqvList          0            id              rqv-reference-list
txt_firstReferenceDetails  0            css             .ref-content .casRecord
#highlighted_reference     0            xpath           //li[@id="rqv-reference-${num}"]/div[@id="cit${num}"]
highlighted_reference      0            xpath           (//div[@id="cit${num}"])[1]
highlighted_reference1     0           xpath           //li[@id="rqv-reference-${num}"]
txt_abstract               0            css             .ref-content .casRecord .casAbstract
txt_bibliographicInf       0            css             .ref-content .casRecord .citationInfo
lnk_scifinder              0            css             .moreSciFi>a
lnk_close                  0            css              #rqv-container .close
lnk_PrintReference         0            css              .printView
lnk_FullTextOption         0            css              .casRefLink
lnk_seeAllReferences       0            id               rqv-show-all
list_references            0            css              #rqv-reference-list .reference
lnk_nextPrevious           0            css              #ref-nav .${button}-link  
lnk_citations              0             xpath            //div[@class="NLM_citation current"]//div[@class="citationLinks"]/a
title_pubMed               0             css             a[title=PubMed]
txt_casPage                0             xpath           //font[@class="title" and contains(text(),"Welcome to CAS")]
txt_printPage              0             css             .printInfoLayer a    
lnk_citationsPrintPg       0             xpath             //div[@class="citationLinks"]/a  
title_currentRef           0             xpath               //div[@class="NLM_citation current"]//span[@class="NLM_article-title"]
lst_rfrnceDisplayed        0            xpath              //li[@class="reference"]    
title_rfernceRQVTop        0            css                .ref-content .casRecord .casTitle span     
txt_titleNumrefernce       0            xpath              //div[@class="NLM_citation current"]
txt_titleNumrfrnceRQVTop   0            css                 .ref-content .casRecord .casTitleNuber  